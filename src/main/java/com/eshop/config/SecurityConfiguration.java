package com.eshop.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {

    private final MyUserDetailsService myUserDetailsService;

    @Autowired
    public SecurityConfiguration(MyUserDetailsService myUserDetailsService) {
        this.myUserDetailsService = myUserDetailsService;
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth, PasswordEncoder passwordEncoder) throws Exception {
        auth.userDetailsService(myUserDetailsService).passwordEncoder(passwordEncoder);
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .formLogin(formLogin ->
                        formLogin
                                .loginPage("/login")
                                .permitAll()
                                .defaultSuccessUrl("/authenticated", true))
                .logout(logout ->
                        logout
                                .logoutUrl("/logout") // Specify the URL for logout
                                .logoutSuccessUrl("/login") // Redirect to login page after logout
                                .invalidateHttpSession(true) // Invalidate the HttpSession
                                .deleteCookies("JSESSIONID"));
        http
                .authorizeHttpRequests(authorizeRequests ->
                        authorizeRequests
                                .requestMatchers("/index").permitAll()
                                .requestMatchers("/login").permitAll()
                                .requestMatchers("/home").permitAll()
                                .requestMatchers("/profile").hasAnyRole("ADMIN", "USER")
                                .requestMatchers("/admin").hasAnyRole("ADMIN")
                                .requestMatchers("/user").hasRole("USER")
                                .requestMatchers("/api/products/**").permitAll()
                                .requestMatchers("/api/users/**").permitAll()
                                .requestMatchers("/api/products/**").permitAll()
                                .requestMatchers("/api/orders/**").permitAll()
                                .requestMatchers("/api/cart/**").permitAll()
                                .anyRequest().permitAll())
                .csrf(csrf -> csrf
                        .ignoringRequestMatchers("/api/**"))
                .httpBasic(Customizer.withDefaults());
        return http.build();
    }
}