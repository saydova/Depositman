package com.bank.depositman.config;

import com.bank.depositman.security.JwtAuthenticationFilter;
import com.bank.depositman.security.UserSecurity;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authorization.AuthorityAuthorizationManager;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.RequestAuthorizationContext;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;
    private final UserSecurity userSecurity;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf()
                .disable()
                .authorizeHttpRequests(auth -> auth
                                .anyRequest().permitAll()
//                        .requestMatchers("/api/user").permitAll()
//                        .requestMatchers("/api/user/**").permitAll()
//                        .requestMatchers("/api/auth/**").permitAll()
                        //testing req
//                        .requestMatchers("/api/**").permitAll()
//                        .requestMatchers(HttpMethod.POST, "/api/auth/login").permitAll()

//                        .requestMatchers(HttpMethod.POST, "/api/todos").hasAnyAuthority("ROLE_USER")
//                        .requestMatchers(HttpMethod.GET, "/api/todos/{id}").hasAnyAuthority("ROLE_USER")
////                        .requestMatchers(HttpMethod.PUT, "/api/todos*").hasAnyAuthority("ROLE_USER")
//                        .requestMatchers(HttpMethod.DELETE, "/api/todos*").hasAnyAuthority("ROLE_USER")



//                        .anyRequest().authenticated()
                )
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public AuthorizationManager<RequestAuthorizationContext> userAuthorizationManager() {
        AuthorizationManager<RequestAuthorizationContext> adminAuth = AuthorityAuthorizationManager.hasRole("ADMIN");
        return (authentication, context) -> {
            if (adminAuth.check(authentication, context).isGranted()) {
                return new AuthorizationDecision(true);
            }
            try {
                int userId = Integer.parseInt(context.getVariables().get("id"));
                return new AuthorizationDecision(userSecurity.isUser(authentication.get(), userId));
            } catch (NumberFormatException e) {
                return new AuthorizationDecision(false);
            }
        };
    }
}
