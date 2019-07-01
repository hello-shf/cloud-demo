package com.example.feigin.feign;

import com.example.entity.device.TDeviceType;
import com.example.feigin.fallback.TDeviceTypeFallBack;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


/**
 * 描述：设备类型表fegin操作
 * @author: lihaohan
 * @date: 2019-04-18 17:45:52
 * @version: V1.0
 */
@FeignClient(name="cloud-jdbc-device",fallback = TDeviceTypeFallBack.class)
public interface TDeviceTypeFeign {
    /**
     * 根据id获取设备类型表实体对象
     * @param id
     * @return
     */
    @RequestMapping(value = "/tDeviceType/{id}",method = RequestMethod.GET)
    public TDeviceType getById(@PathVariable(value = "id") Long id);

}
