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


                        .requestMatchers(
                                "/",
                                "/login",
                                "/member/**",
                                "/api/**",
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
                        .successHandler((request, response, authentication) -> {
                            System.out.println("===== SUCCESS HANDLER =====");
                            boolean isAdmin = authentication.getAuthorities()
                                    .stream()
                                    .anyMatch(authority ->
                                            authority.getAuthority().equals("ROLE_ADMIN"));

                            if (isAdmin) {
                                response.sendRedirect("/admin");
                            } else {
                                response.sendRedirect("/");
                            }

                        })
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