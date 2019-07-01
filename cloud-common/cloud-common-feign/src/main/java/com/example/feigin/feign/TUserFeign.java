package com.example.feigin.feign;

import com.example.entity.user.TUser;
import com.example.feigin.fallback.TUserFallBack;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


/**
 * 描述：用户表feign操作
 * @author: shf
 * @date: 2019-04-18 17:45:52
 * @version: V1.0
 */
@FeignClient(name="cloud-jdbc-user",fallback = TUserFallBack.class)
public interface TUserFeign {
    /**
     * 根据用户名获取用户表实体
     * @param username
     * @return
     */
    @RequestMapping(value = "/tUser/findOneByName", method = RequestMethod.POST)
    TUser findOneByName(@RequestParam String username);

}
