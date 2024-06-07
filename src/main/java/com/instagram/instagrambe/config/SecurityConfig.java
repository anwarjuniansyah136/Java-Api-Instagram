package com.instagram.instagrambe.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.instagram.instagrambe.constant.RoleConstant;
import com.instagram.instagrambe.exception.CustomAccessDeniedException;
import com.instagram.instagrambe.exception.CustomUnAuthorizeException;
import com.instagram.instagrambe.security.JwtFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
        @Autowired
        JwtFilter filter;

        @Bean
        PasswordEncoder getPasswordEncoder() {
                return new BCryptPasswordEncoder();
        }

        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
                http
                                .csrf(csrf -> csrf.disable())
                                .sessionManagement(session -> session
                                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                                .exceptionHandling(ex -> ex
                                                .authenticationEntryPoint(new CustomUnAuthorizeException())
                                                .accessDeniedHandler(new CustomAccessDeniedException()))
                                .authorizeHttpRequests(auth -> auth
                                                .requestMatchers("/auth/**",
                                                                "/v3/api-docs/**",
                                                                "/swagger-ui/**",
                                                                "/user/**")
                                                .permitAll()
                                                .requestMatchers("/post/**","/comment/**","/follow/**","/like/**","/message/**","/saved/**","/story/**").hasAnyAuthority(RoleConstant.USERS_ROLE)
                                                // .requestMatchers(
                                                //                 "/cart/**",
                                                //                 "/report",
                                                //                 "/transaction/post")
                                                // .hasAnyAuthority(RoleConstant.CUSTOMER_ROLE)
                                                // .requestMatchers(
                                                //                 "/product/**",
                                                //                 "/category/**")
                                                // .hasAnyAuthority(RolesConstant.WAREHOUSE_ROLE)
                                                // .requestMatchers("/transaction/{id}","/transaction/view")
                                                // .hasAuthority(RolesConstant.ACCOUNTANT_ROLE)
                                                .anyRequest().authenticated())
                                .addFilterAfter(filter, UsernamePasswordAuthenticationFilter.class);
                return http.build();
        }
}