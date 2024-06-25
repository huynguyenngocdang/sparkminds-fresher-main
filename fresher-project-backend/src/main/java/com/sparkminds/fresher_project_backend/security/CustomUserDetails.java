package com.sparkminds.fresher_project_backend.security;

import com.sparkminds.fresher_project_backend.entity.Role;
import com.sparkminds.fresher_project_backend.entity.User;
import com.sparkminds.fresher_project_backend.entity.UserRole;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class CustomUserDetails extends User implements UserDetails {
    private String username;
    private String password;
    Collection<? extends GrantedAuthority> authorities;
    public CustomUserDetails(Optional<User> byUsername) {
        User user = byUsername.get();
        this.username = user.getUsername();
        this.password = user.getPassword();
        List<GrantedAuthority> auths = new ArrayList<>();
        for (UserRole userRole : user.getUserRoles()) {
            Role role = userRole.getRole();
            auths.add(new SimpleGrantedAuthority("ROLE_" + role.getRole().toUpperCase()));
        }
        this.authorities = auths;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }
    @Override
    public String getPassword() {
        return password;
    }
    @Override
    public String getUsername(){
        return username;
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

