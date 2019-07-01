package com.example.auth.security.auth;

import com.example.auth.entity.TUser;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * 描述：自定义UserDetails，使UserDetails具有TUser的实体结构
 *
 * @Author shf
 * @Date 2019/4/19 10:30
 * @Version V1.0
 **/
@Data
public class CustomUserDetails extends TUser implements UserDetails {
    public CustomUserDetails(TUser tUser, Collection<? extends GrantedAuthority> authorities){
        if(null != tUser){
            this.setId(tUser.getId());
            this.setUsername(tUser.getUsername());
            this.setPassword(tUser.getPassword());
            this.setEmail(tUser.getEmail());
            this.setPhone(tUser.getPhone());
            this.setState(tUser.getState());
        }
        this.setAuthorities(authorities);
    }
    Collection<? extends GrantedAuthority> authorities;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
