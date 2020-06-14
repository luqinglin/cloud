package me.sta.auth.model;

import org.springframework.security.core.GrantedAuthority;

public class Role implements GrantedAuthority {
    @Override
    public String getAuthority() {
        return "1,2,3";
    }
}
