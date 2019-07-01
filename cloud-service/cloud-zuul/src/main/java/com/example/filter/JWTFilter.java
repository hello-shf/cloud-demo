package com.example.filter;

import com.alibaba.fastjson.JSON;
import com.example.properties.JwtProperties;
import com.example.util.JwtTokenUtil;
import com.example.util.RedisUtil;
import com.example.util.Response;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * 描述：
 *
 * @Author shf
 * @Date 2019/7/1 14:29
 * @Version V1.0
 **/
@Component
public class JWTFilter extends ZuulFilter {
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
        return 0;
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
        try {
            if (StringUtils.isEmpty(authorization)) {
                context.addZuulResponseHeader("Content-type", "application/json;charset=UTF-8");
                context.setSendZuulResponse(false);
                context.setResponseBody(JSON.toJSONString(result.buildFailedResponse("403", "未提供token")));
            }
            if(!authorization.startsWith(jwtProperties.getTokenHead())){
                context.addZuulResponseHeader("Content-type", "application/json;charset=UTF-8");
                context.setSendZuulResponse(false);
                context.setResponseBody(JSON.toJSONString(result.buildFailedResponse("403", "token格式错误")));
            }
            final String token = authorization.replace(jwtProperties.getTokenHead(), "");
            String username = jwtTokenUtil.getUsernameFromToken(token);
            if(null == username){
                context.addZuulResponseHeader("Content-type", "application/json;charset=UTF-8");
                context.setSendZuulResponse(false);
                context.setResponseBody(JSON.toJSONString(result.buildFailedResponse("403", "无效的Token")));
            }
            if(!redisUtil.hHasKey(username, token)){
                context.addZuulResponseHeader("Content-type", "application/json;charset=UTF-8");
                context.setSendZuulResponse(false);
                context.setResponseBody(JSON.toJSONString(result.buildFailedResponse("403", "Token已过期")));
            }
            redisUtil.expire(username, jwtProperties.getExpiration());
            return null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
