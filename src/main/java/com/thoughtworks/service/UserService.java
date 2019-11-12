package com.thoughtworks.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.thoughtworks.common.RedisHandle;
import com.thoughtworks.dto.JwtUser;
import com.thoughtworks.entity.Role;
import com.thoughtworks.entity.User;
import com.thoughtworks.exception.UserNotFoundException;
import com.thoughtworks.repository.RoleRepository;
import com.thoughtworks.repository.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.thoughtworks.common.Constant.JWT_SECRET;
import static com.thoughtworks.common.Constant.JWT_TOKEN_KEY;

@Service
@Slf4j
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private RedisHandle redisHandle;

    public static final String JWT_TOKEN_PREFIX = "Bearer ";

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public String generateJwtToken(String username, String password) {
        User user = userRepository.findByUsernameAndPassword(username, password).orElseThrow(UserNotFoundException::new);
        String jwtToken = generateJwtToken(user);
        redisHandle.set(JWT_TOKEN_KEY, jwtToken, 10);
        return jwtToken;
    }

    private String generateJwtToken(User user) {
        Map<String, Object> headers = new HashMap<>();
        headers.put("X-token", "123");
        headers.put("Y-token", "456");

        List<Role> roles = roleRepository.findRolesByUserId(user.getId());
        List<SimpleGrantedAuthority> grantedAuthorities = roles.stream().map(role ->
                new SimpleGrantedAuthority(role.getRoleCode())).collect(Collectors.toList());

        Map<String, Object> claims = new HashMap<>();
        claims.put("username", user.getUsername());
        claims.put("fullname", user.getFullName());
        claims.put("id", user.getId());
        claims.put("authorities", grantedAuthorities);

        return JWT_TOKEN_PREFIX + Jwts.builder()
                .setClaims(claims)
                .setHeader(headers)
                .signWith(SignatureAlgorithm.HS256, JWT_SECRET.getBytes())
                .compact();
    }

    public void parseToken(String jwtToken) {
        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(JWT_SECRET.getBytes()).parseClaimsJws(jwtToken.replace(JWT_TOKEN_PREFIX, ""));
        Claims claims = claimsJws.getBody();
        List<SimpleGrantedAuthority> authorities = JSONArray.parseArray(JSON.toJSONString(claims.get("authorities")), SimpleGrantedAuthority.class);
        String id = (String) claims.get("id");
        JwtUser jwtUser = JwtUser.builder()
                .username((String) claims.get("username"))
                .fullname((String) claims.get("fullname"))
                .authorities(authorities)
                .id(id).build();

        log.info("current user is {}", jwtUser);
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(jwtUser, null, authorities);
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}
