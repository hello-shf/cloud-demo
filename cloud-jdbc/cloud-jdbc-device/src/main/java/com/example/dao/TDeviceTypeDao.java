package com.example.dao;

import com.example.entity.device.TDeviceType;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

/**
 * 描述：
 *
 * @Author shf
 * @Date 2019/6/17 17:52
 * @Version V1.0
 **/
public interface TDeviceTypeDao extends CrudRepository<TDeviceType, Long>, JpaSpecificationExecutor<TDeviceType> {

}
