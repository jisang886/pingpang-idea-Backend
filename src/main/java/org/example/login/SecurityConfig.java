package org.example.login;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
                )
                .csrf(csrf -> csrf.disable())  // 关闭 CSRF 防护（仅 API 测试）
                .authorizeRequests(auth -> auth
                        .anyRequest().permitAll()  // 允许所有请求（仅测试用）
                )
                .formLogin(login -> login
                        .permitAll()  // 允许所有用户访问登录页
                        .defaultSuccessUrl("/home", true)  // 登录成功后跳转到指定页面
                )
                .httpBasic(basic -> basic.disable()); // 禁用 HTTP Basic 认证

        return http.build();
    }



    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
