package com.layer.layer.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .csrf(csrf -> csrf.disable())

                .authorizeHttpRequests(auth -> auth

                        // 관리자만
                        .requestMatchers("/admin/**").hasRole("ADMIN")

                        // ✅ 수정 후 코드
                        .requestMatchers(
                                "/",
                                "/login",
                                "/member/**",     // 👈 [수정 1] /member/하위의 모든 경로(중복확인 등) 허용
                                "/api/**",        // 👈 [추가 2] 혹시 fetch 경로가 /api/ 로 시작할 경우 대비
                                "/css/**",
                                "/js/**",
                                "/images/**",
                                "/product/**",
                                "/brands",
                                "/search"
                        ).permitAll()

                        // 나머지는 로그인 필요
                        .anyRequest().authenticated()
                )

                .formLogin(form -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/")
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutSuccessUrl("/")
                );

        return http.build();
    }

    /*DI (Dependency Injection) */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}