package com.example.auth.dao;

import com.example.auth.entity.TUser;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface TUserDao extends CrudRepository<TUser, Long>, JpaSpecificationExecutor<TUser> {

    @Query("select t from TUser t where t.username=?1")
    public TUser findByName(String username);
}
