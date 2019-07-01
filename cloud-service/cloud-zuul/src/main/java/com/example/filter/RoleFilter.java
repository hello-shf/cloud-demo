package com.example.filter;

import com.alibaba.fastjson.JSON;
import com.example.entity.user.TPermission;
import com.example.entity.user.TRole;
import com.example.entity.user.TUser;
import com.example.feigin.feign.TUserFeign;
import com.example.util.RedisUtil;
import com.example.properties.JwtProperties;
import com.example.util.JwtTokenUtil;
import com.example.util.Response;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 描述：
 *
 * @Author shf
 * @Date 2019/7/1 15:36
 * @Version V1.0
 **/
@Component
public class RoleFilter extends ZuulFilter {
    @Autowired
    private TUserFeign userFeign;
    @Autowired
    private JwtProperties jwtProperties;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private RedisUtil redisUtil;


    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    @Override
    public boolean shouldFilter() {
        RequestContext context = RequestContext.getCurrentContext();
        HttpServletRequest request = context.getRequest();
        if("/portal/auth/getVerifyCode".equalsIgnoreCase(request.getRequestURI())
                || "/portal/auth/login".equalsIgnoreCase(request.getRequestURI())){
            return false;
        }
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        Response result = new Response();
        RequestContext context = RequestContext.getCurrentContext();
        HttpServletRequest request = context.getRequest();
        String authorization = request.getHeader("Authorization");
        final String token = authorization.replace(jwtProperties.getTokenHead(), "");
        String username = jwtTokenUtil.getUsernameFromToken(token);
        Set<Object> urls = redisUtil.sGet("permission_" + username);
        if(null == urls || 0 == urls.size()){
            TUser user = userFeign.findOneByName(username);
            urls = getUrl(user);
            Object[] urlArr = urls.toArray();
            redisUtil.sSet("permission_" + username, urlArr);
        }
        for (Object url : urls){
            if(url.toString().equals(request.getRequestURI())){
                return null;
            }
        }
        context.setSendZuulResponse(false);
        context.addZuulResponseHeader("Content-type", "application/json;charset=UTF-8");
        context.setResponseBody(JSON.toJSONString(result.buildFailedResponse("403", "权限不足")));
        return null;
    }
    private Set<Object> getUrl(TUser user){
        Set<Object> result = new HashSet<>();
        List<TRole> roles = user.getRoleList();
        for (TRole role:roles){
            List<TPermission> permissions = role.getPermissions();
            for (TPermission permission:permissions){
                // 此处将权限信息添加到 GrantedAuthority 对象中，在后面进行权限验证时会使用GrantedAuthority 对象
                result.add(permission.getUrl());
            }
        }
        return result;
    }
}
