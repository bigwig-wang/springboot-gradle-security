package com.thoughtworks.common;

import com.thoughtworks.dto.JwtUser;
import org.springframework.security.core.context.SecurityContextHolder;

public class CurrentUser {

    public static String getUsername() {
        return getCurrentUser().getUsername();
    }

    public static JwtUser getCurrentUser() {
        JwtUser jwtUser = (JwtUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return jwtUser;
    }
}
