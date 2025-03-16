package com.milkit_shop.config;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.channel.ChannelProcessingFilter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.io.IOException;

@Configuration
@EnableWebSecurity
public class SecurityConfig implements AuthenticationFailureHandler, AuthenticationEntryPoint {
  @Bean
  public BCryptPasswordEncoder bCryptPasswordEncoder(){
    return new BCryptPasswordEncoder();
  }
  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http, CorsFilter corsFilter) throws Exception{
   return  http.csrf(authentication -> authentication.disable())
       .addFilterBefore(corsFilter, ChannelProcessingFilter.class)
        .authorizeHttpRequests((authentication) -> authentication
            .requestMatchers("/user/**").authenticated()
            .requestMatchers("/admin/**").hasRole("ADMIN")
            .anyRequest().permitAll()
        ).formLogin((authentication) -> authentication
            .loginPage("/login")
            .loginProcessingUrl("/loginProc")
            .defaultSuccessUrl("/loginOk")
            .usernameParameter("email")
            .permitAll()
            .failureHandler(this)
        ).logout( authentication -> authentication
           .logoutUrl("/logout")
            .logoutSuccessUrl("/logoutOk")
            .deleteCookies("JSESSIONID")
        ).exceptionHandling(e -> e
            .authenticationEntryPoint(this)
        ).csrf(authentication -> authentication.disable())
        .build();
  }
  @Override
  public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
    response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
  }
  @Override
  public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
    response.sendRedirect("/login/error");
  }
  @Bean
  public CorsFilter corsFilter() {
    CorsConfiguration config = new CorsConfiguration();
    config.setAllowCredentials(true);
    config.addAllowedOrigin("http://localhost:3000");// 리액트 서버
    config.addAllowedHeader("*");
    config.addAllowedMethod("*");
    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", config);
    return new CorsFilter(source);
  }


}
