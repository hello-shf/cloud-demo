package com.example.feigin.fallback;

import com.example.entity.device.TDeviceType;
import com.example.feigin.feign.TDeviceTypeFeign;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


/**
 * 描述：设备类型表回调操作
 * @author: shf
 * @date: 2019-04-18 17:45:52
 * @version: V1.0
 */
@Component
@Slf4j
public class TDeviceTypeFallBack implements TDeviceTypeFeign {
    /**
     * 根据id获取设备类型表实体对象
     * @param id
     * @return
     */
    @Override
    public TDeviceType getById(Long id) {
        log.error("fallback---请求失败了");
        return null;
    }

    /*@Override
    public TDeviceTypeFeign create(Throwable throwable) {
        return new TDeviceTypeFeign() {
            @Override
            public TDeviceType getById(Long id) {
                if(throwable.getCause() instanceof HystrixTimeoutException){
                    log.error("进入fallback，超时异常---{}",throwable.getCause().getMessage());
                }else{
                    log.error("进入fallback");
                }
                return null;
            }
        };
    }*/
}
