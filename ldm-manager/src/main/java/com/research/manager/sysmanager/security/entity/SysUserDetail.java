package com.research.manager.sysmanager.security.entity;

import com.research.manager.sysmanager.entity.User;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
public class SysUserDetail implements Serializable, UserDetails {
    private static final long serialVersionUID = 1L;
    private User user;
    private List<String> permissions;

    public SysUserDetail(User user, List<String> permissions){
        this.user= user;
        this.permissions = permissions;
    }
    public SysUserDetail(User user){
        this.user= user;
    }

    private List<SimpleGrantedAuthority> authorities;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (authorities != null){
            return authorities;
        }
        authorities = new ArrayList<>();
        permissions.forEach(permission -> {
            authorities.add(new SimpleGrantedAuthority(permission));
        });
        return authorities;
    }

    @Override
    public String getPassword() {
        return this.user.getPassword();
    }

    @Override
    public String getUsername() {
        return this.user.getUserName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.user.getStatus().equals("0");
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.user.getStatus().equals("0");
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.user.getStatus().equals("0");
    }

    @Override
    public boolean isEnabled() {
        return this.user.getStatus().equals("0");
    }
}
