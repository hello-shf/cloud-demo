package com.example.service;

import com.example.entity.user.TPermission;

import java.util.List;

public interface PermissionService {
    List<TPermission> findByName(String username);
}
