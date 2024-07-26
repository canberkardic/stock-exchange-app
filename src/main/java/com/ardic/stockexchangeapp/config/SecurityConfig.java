package com.ardic.stockexchangeapp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    private static final String[] WHITELIST = {
            "/v2/api-docs",
            "/swagger-resources",
            "/swagger-resources/**",
            "/swagger-ui.html",
            "/v3/api-docs/**",
            "/swagger-ui/**",
    };

    @Value("${security.users.admin.username}")
    private String adminUsername;

    @Value("${security.users.admin.password}")
    private String adminPassword;

    @Value("${security.users.user.username}")
    private String regularUserUsername;

    @Value("${security.users.user.password}")
    private String regularUserPassword;


    @Autowired
    @Qualifier("customAuthenticationEntryPoint")
    AuthenticationEntryPoint authEntryPoint;


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(){
        UserDetails admin = User.withUsername(adminUsername).password(passwordEncoder().encode(adminPassword)).roles("ADMIN").build();
        UserDetails user = User.withUsername(regularUserUsername).password(passwordEncoder().encode(regularUserPassword)).roles("USER").build();

        return new InMemoryUserDetailsManager(admin,user);
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().requestMatchers(new AntPathRequestMatcher("/h2-console/**"));
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http.csrf(h -> h.disable()).authorizeHttpRequests((authorize) -> {
            authorize
                    .requestMatchers(WHITELIST).permitAll()
            .requestMatchers("/api/v1/**").authenticated()
                    .anyRequest().authenticated();
        }).httpBasic(httpBasic -> httpBasic
                .authenticationEntryPoint(new CustomAuthenticationEntryPoint()))
                .logout(Customizer.withDefaults());
        return http.build();
    }

}
