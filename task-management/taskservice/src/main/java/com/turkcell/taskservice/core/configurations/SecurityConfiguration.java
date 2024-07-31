package com.turkcell.taskservice.core.configurations;

import com.turkcell.tcellfinalcore.services.BaseSecurityService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfiguration {
    private final BaseSecurityService baseSecurityService;
    private static final String[] WHITE_LIST={
            "/swagger-ui/**",
            "/v2/api-docs",
            "/v3/api-docs",
            "/v3/api-docs/**",
    };

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        baseSecurityService.configureCoreSecurity(http);
        http
                .authorizeHttpRequests((req)-> req
                        .requestMatchers(WHITE_LIST).permitAll()
                        .requestMatchers(("/api/v1/*"))
                        .hasAnyAuthority("Turkcell")
                        .anyRequest().authenticated());
        return http.build();
    }
}