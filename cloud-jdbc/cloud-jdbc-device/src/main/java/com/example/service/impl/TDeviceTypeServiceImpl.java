package com.example.service.impl;

import com.example.dao.TDeviceTypeDao;
import com.example.service.TDeviceTypeService;
import com.example.entity.device.TDeviceType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 描述：
 * @author: ruikanwang
 * @date: 2017/11/16 0016 13:12
 * @version: V1.0
 * 注意：本内容仅限于新疆感知科技有限公司内部传阅,禁止外泄以及用于其他的商业目
 * Copyright © 2017-ganinfo. All rights reserved.
 */
@Service
public class TDeviceTypeServiceImpl implements TDeviceTypeService {
    @Autowired
    TDeviceTypeDao deviceTypeDao;

    @Override
    public TDeviceType getById(Long id) {
        TDeviceType deviceType = deviceTypeDao.findById(id).get();
        return deviceType;
    }
}
