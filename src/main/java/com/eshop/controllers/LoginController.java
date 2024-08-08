package com.eshop.controllers;

import com.eshop.DTO.UserDTO;
import com.eshop.Pojo.LoginResponse;
import com.eshop.config.MyUserDetailsService;
import com.eshop.config.SecurityConfiguration;
import com.eshop.config.TokenConfig.TokenGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class LoginController {

    @Autowired
    private MyUserDetailsService userDetailsService;

    @Autowired
    private SecurityConfiguration securityConfiguration; // Autowire SecurityConfiguration

    @Autowired
    private TokenGenerator tokenGenerator; // Inject TokenGenerator

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/loginApi")
    @ResponseBody
    public ResponseEntity<LoginResponse> login(@RequestBody UserDTO loginRequestDto) throws AuthenticationException {
        UserDetails userDetails;

        try {
            userDetails = userDetailsService.loadUserByUsername(loginRequestDto.getUsername());
        } catch (UsernameNotFoundException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new LoginResponse("Invalid username or password"));
        }

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                userDetails, loginRequestDto.getPassword(), userDetails.getAuthorities());

        Authentication authentication = authenticationManager.authenticate(token);

        // Set authentication in security context
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Successful authentication
        if (authentication.isAuthenticated()) {
            String generatedToken = tokenGenerator.generateSessionToken(authentication.getName());
            return ResponseEntity.ok(new LoginResponse("Login successful", generatedToken));
        } else {
            // Login failed
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new LoginResponse("Invalid username or password"));
        }
    }
}

