package com.example.controller;

import com.example.entity.device.TDeviceType;
import com.example.feigin.feign.TDeviceTypeFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 描述：
 *
 * @Author shf
 * @Date 2019/6/18 8:56
 * @Version V1.0
 **/
@RestController
@RequestMapping("/deviceType")
public class TDeviceTypeController {
    @Autowired
    private TDeviceTypeFeign deviceTypeFeign;

    @GetMapping("/{id}")
    public TDeviceType getById(@PathVariable Long id){
        return deviceTypeFeign.getById(id);
    }
}
