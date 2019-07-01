package com.example.auth.service.impl;

import com.example.auth.dao.TUserDao;
import com.example.auth.service.TUserService;
import com.example.auth.entity.TUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 描述：
 * @author: shf
 * @date: 2017/11/16 0016 13:12
 * @version: V1.0
 */
@Service
public class TUserServiceImpl implements TUserService {
    @Autowired
    private TUserDao userDao;

    @Override
    public TUser findByName(String username) {
        return userDao.findByName(username);
    }
}
