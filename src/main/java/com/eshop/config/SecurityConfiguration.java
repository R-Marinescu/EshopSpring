package com.eshop.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
public class SecurityConfiguration {

    private final MyUserDetailsService myUserDetailsService;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private AuthenticationManager authenticationManager;

    @Autowired
    public SecurityConfiguration(MyUserDetailsService myUserDetailsService, JwtAuthenticationFilter jwtAuthenticationFilter) {
        this.myUserDetailsService = myUserDetailsService;
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth, PasswordEncoder passwordEncoder) throws Exception {
        auth.userDetailsService(myUserDetailsService).passwordEncoder(passwordEncoder);
    }

    @Autowired
    private AuthenticationConfiguration authenticationConfiguration;

    @Bean
    public AuthenticationManager getAuthenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

//   @Bean
//    public AuthenticationManager getAuthenticationManager() {
//        return authenticationManager;
//    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login")
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID"))
                .sessionManagement(sessionManagement -> sessionManagement
                        .sessionFixation().migrateSession()
                        .maximumSessions(1).expiredUrl("/login"))
                .authorizeHttpRequests(authorizeRequests -> authorizeRequests
                        .requestMatchers("/home").permitAll()
                        .requestMatchers("/api/auth/loginApi").permitAll()
                        .requestMatchers("/login").permitAll()
                        .requestMatchers("/index").permitAll()
                        .requestMatchers("/profile").hasAnyRole("ADMIN", "USER")
                        .requestMatchers("/admin").hasAnyRole("ADMIN")
                        .requestMatchers("/user").hasRole("USER")
                        .requestMatchers("/api/products/**").permitAll()
                        //.requestMatchers("/api/users/**").permitAll()
                        .requestMatchers("/api/users/user-details").authenticated()
                        .requestMatchers("/api/products/**").permitAll()
                        .requestMatchers("/api/orders/**").permitAll()
                        .requestMatchers("/api/cart/**").permitAll()
                        .requestMatchers("/api/**").authenticated()
                        .anyRequest().permitAll())
                .csrf(csrf -> csrf.ignoringRequestMatchers("/api/**"))
                .csrf(AbstractHttpConfigurer::disable);

        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}