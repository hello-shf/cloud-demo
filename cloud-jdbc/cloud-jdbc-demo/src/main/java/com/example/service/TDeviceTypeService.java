package com.example.service;


import com.example.entity.device.TDeviceType;

/**
 * 描述：设备类型表服务类
 *
 * @author: shf
 * @date: 2019-04-22 15:56:15
 * @version: V1.0
 * 注意：本内容仅限于新疆感知科技有限公司内部传阅,禁止外泄以及用于其他的商业目
 * Copyright © 2018-ganinfo. All rights reserved.
 */
public interface TDeviceTypeService {
    TDeviceType getById(Long id);
}
