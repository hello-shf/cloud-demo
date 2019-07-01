package com.example.api;

import com.example.entity.user.TUser;
import com.example.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 描述：
 *
 * @Author shf
 * @Date 2019/7/1 15:48
 * @Version V1.0
 **/
@RestController
@RequestMapping("/tUser")
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/findOneByName", method = RequestMethod.POST)
    public TUser findOneByName(@RequestParam("username")String username){
        log.info("username----->{}", username);
        TUser user = userService.findOneByName(username);
        log.info("user----->{}", user);
        return user;
    }
}
