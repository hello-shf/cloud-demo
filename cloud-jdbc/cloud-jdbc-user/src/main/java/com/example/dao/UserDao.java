package com.example.dao;

import com.example.entity.user.TUser;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 * 描述：
 *
 * @Author shf
 * @Date 2019/7/1 15:49
 * @Version V1.0
 **/
public interface UserDao extends CrudRepository<TUser, Long>, JpaSpecificationExecutor<TUser> {
    @Query("select t from TUser t where t.username=?1")
    TUser findOneByName(String username);
}
