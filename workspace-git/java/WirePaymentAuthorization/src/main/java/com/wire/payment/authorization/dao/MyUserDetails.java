package com.wire.payment.authorization.dao;

import java.util.Arrays;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.wire.payment.entity.TUserDetail;
public class MyUserDetails implements UserDetails{
 
    private TUserDetail user;
     
    public MyUserDetails(TUserDetail user) {
        this.user = user;
    }
 
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(user.getUserRole());
        return Arrays.asList(authority);
    }
 
    @Override
    public String getPassword() {
        return new BCryptPasswordEncoder().encode(user.getUserPassword());
    }
 
    @Override
    public String getUsername() {
        return user.getUserPassword();
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
