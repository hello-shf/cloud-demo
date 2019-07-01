package com.example.api;

import com.example.entity.user.TPermission;
import com.example.service.PermissionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 描述：
 *
 * @Author shf
 * @Date 2019/7/1 20:55
 * @Version V1.0
 **/
@RestController
@Slf4j
public class PermissionController {
    @Autowired
    private PermissionService permissionService;

    @RequestMapping(value = "/findByName")
    public List<TPermission> findByName(@RequestParam("username")String username){
        List<TPermission> permissions = permissionService.findByName(username);
        return permissions;
    }
}
