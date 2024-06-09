package com.example.testovoe.security;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
@Configuration
public class SecurityConfig {
  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
      .authorizeHttpRequests(authorizeRequests ->
        authorizeRequests
          .requestMatchers(HttpMethod.GET, "/swagger-ui/**", "/v3/api-docs/**", "/swagger-resources/**", "/webjars/**","/swagger-ui.html").permitAll()
          .requestMatchers(HttpMethod.GET, "/patient/**").hasAnyAuthority("ROLE_practitioner", "ROLE_patient")
          .requestMatchers(HttpMethod.POST, "/patient/create").hasAuthority("ROLE_practitioner")
          .requestMatchers(HttpMethod.PUT, "/patient/**").hasAuthority("ROLE_practitioner")
          .requestMatchers(HttpMethod.DELETE, "/patient/**").hasAuthority("ROLE_practitioner")
          .anyRequest().authenticated()
      )
      .oauth2ResourceServer(oauth2ResourceServer ->
        oauth2ResourceServer.jwt(jwt -> jwt.jwtAuthenticationConverter(jwtAuthenticationConverter()))
      );
    return http.build();
  }

  private JwtAuthenticationConverter jwtAuthenticationConverter() {
    JwtAuthenticationConverter converter = new JwtAuthenticationConverter();
    converter.setJwtGrantedAuthoritiesConverter(jwt -> {
      Collection<GrantedAuthority> authorities = new ArrayList<>();
      Map<String, Object> realmAccess = jwt.getClaimAsMap("realm_access");
      if (realmAccess != null) {
        List<String> roles = (List<String>) realmAccess.get("roles");
        if (roles != null) {
          roles.forEach(role ->
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role))
          );
        }
      }
      return authorities;
    });
    return converter;
  }
}
