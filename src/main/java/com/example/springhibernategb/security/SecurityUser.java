package com.example.springhibernategb.security;

import com.example.springhibernategb.entity.User;
import com.example.springhibernategb.entity.Status;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Data
public class SecurityUser implements UserDetails {

    private final String username;
    private final String password;
    private final List<SimpleGrantedAuthority> authorities;
    private final boolean isActive;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return isActive;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isActive;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isActive;
    }

    @Override
    public boolean isEnabled() {
        return isActive;
    }

    public static UserDetails fromUser(User buyer) {
        return new org.springframework.security.core.userdetails.User(
                buyer.getName(),
                buyer.getPassword(),
                buyer.getStatus().equals(Status.ACTIVE),
                buyer.getStatus().equals(Status.ACTIVE),
                buyer.getStatus().equals(Status.ACTIVE),
                buyer.getStatus().equals(Status.ACTIVE),
                buyer.getRole().getAuthorities()
        );
    }
}
