package com.thoughtworks.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JwtUser {

    private String username;

    private String fullname;

    private String id;

    private List<? extends GrantedAuthority> authorities;
}
