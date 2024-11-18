package br.edu.infnet.tiago.infrastructure.security;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

import static br.edu.infnet.tiago.domain.model.UserRole.ROLE_ADMIN;
import static br.edu.infnet.tiago.domain.model.UserRole.ROLE_USER;
import static org.springframework.http.HttpMethod.*;
import static org.springframework.security.config.Customizer.withDefaults;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@SecurityScheme(name = "basicAuth", type = SecuritySchemeType.HTTP, scheme = "basic")
@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfiguration {

    private final SecurityUserDetailsService securityUserDetailsService;

    @SneakyThrows(Exception.class)
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) {
        httpSecurity
                .formLogin(AbstractHttpConfigurer::disable)
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .csrf(AbstractHttpConfigurer::disable)
                .requiresChannel(channel -> channel.anyRequest().requiresSecure())
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(GET, "/swagger-ui/**", "/v3/api-docs/**").permitAll()
                        .requestMatchers(POST, "/**").hasAuthority(ROLE_ADMIN.name())
                        .requestMatchers(PUT, "/**").hasAuthority(ROLE_ADMIN.name())
                        .requestMatchers(DELETE, "/**").hasAuthority(ROLE_ADMIN.name())
                        .requestMatchers(GET, "/**").hasAnyAuthority(ROLE_ADMIN.name(), ROLE_USER.name())
                        .anyRequest()
                        .authenticated())
                .httpBasic(withDefaults())
                .sessionManagement(sessionManager -> sessionManager.sessionCreationPolicy(STATELESS));
        return httpSecurity.build();
    }

    @SneakyThrows(Exception.class)
    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity httpSecurity) {
        AuthenticationManagerBuilder authenticationManagerBuilder = httpSecurity.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(securityUserDetailsService).passwordEncoder(passwordEncoder());
        return authenticationManagerBuilder.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of());
        configuration.setAllowedMethods(List.of());
        configuration.setAllowedHeaders(List.of());
        configuration.setExposedHeaders(List.of());
        configuration.setMaxAge(1800L);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}