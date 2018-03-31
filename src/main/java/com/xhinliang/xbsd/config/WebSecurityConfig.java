package com.xhinliang.xbsd.config;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.xhinliang.xbsd.services.XUserService;

/**
 * @author xhinliang
 */
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private XUserService xUserService;

    @Bean
    UserDetailsService customUserService() {
        return xUserService;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserService());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // CHECKSTYLE:OFF: MagicNumber
        http.authorizeRequests()
                .anyRequest().authenticated() //
                .and().formLogin().loginPage("/login") //
                // 设置默认登录成功跳转页面
                .defaultSuccessUrl("/index").failureUrl("/login?error").permitAll() //
                .and() //
                // 开启cookie保存用户数据
                .rememberMe() //
                // 设置cookie有效期
                .tokenValiditySeconds((int)TimeUnit.DAYS.toSeconds(7)) //
                // 设置cookie的私钥
                .key("xbsd-cookie") //
                .and() //
                .logout() //
                // 默认注销行为为logout，可以通过下面的方式来修改
                .logoutUrl("/logout") //
                // 设置注销成功后跳转页面，默认是跳转到登录页面
                .logoutSuccessUrl("") //
                .permitAll();
        // CHECKSTYLE: ON MagicNumber
    }
}
