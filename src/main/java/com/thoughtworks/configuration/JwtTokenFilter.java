package com.thoughtworks.configuration;

import com.thoughtworks.common.RedisHandle;
import com.thoughtworks.exception.NotAuthorizedException;
import com.thoughtworks.exception.TokenExpiredException;
import com.thoughtworks.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

import static com.thoughtworks.common.Constant.JWT_TOKEN_KEY;

@Slf4j
public class JwtTokenFilter implements Filter {

    private final UserService userService;

    private final RedisHandle redisHandle;

    public JwtTokenFilter(UserService userService, RedisHandle redisHandle) {
        this.userService = userService;
        this.redisHandle = redisHandle;
    }

    @Override
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        //do your self request请求
        HttpServletRequest req = (HttpServletRequest) request;
        String jwtToken = req.getHeader("Authorization");
        if (StringUtils.isNotBlank(jwtToken)) {
            validatedJwtToken(jwtToken);
            userService.parseToken(jwtToken);
        } else {
            throw new NotAuthorizedException();
        }
        chain.doFilter(request, response);
    }

    private void validatedJwtToken(String jwtToken) {
        String token = redisHandle.get(JWT_TOKEN_KEY);
        if(StringUtils.isBlank(token) && !jwtToken.equals(token)) {
            throw new TokenExpiredException();
        }
    }

    @Override
    public void destroy() {

    }
}
