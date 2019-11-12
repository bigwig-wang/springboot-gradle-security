package com.thoughtworks.configuration;

import com.thoughtworks.common.RedisHandle;
import com.thoughtworks.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserService userService;

    @Autowired
    private RedisHandle redisHandle;

    //若你想当前请求完全不经过springsecurity校验，且连springsecurityFilter也不经过时，就用web的形式
    //所有web里面一般配置一些资源文件类的请求
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/api/user/login")
                .antMatchers("/api/user/helloworld");

        web.ignoring().antMatchers(HttpMethod.GET, "/swagger-ui.html**")
                .antMatchers(HttpMethod.GET, "/webjars/springfox-swagger-ui/**")
                .antMatchers(HttpMethod.GET, "/swagger-resources/**")
                .antMatchers(HttpMethod.GET, "/v2/api-docs");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //是否开启csrf跨站请求防护，若开启，则前端必须携带CSRF_token校验，否则会报错
        http.csrf().disable();
        http.authorizeRequests()
                .anyRequest().authenticated();

        http.addFilterAfter(new JwtTokenFilter(userService, redisHandle), BasicAuthenticationFilter.class);
    }
}
