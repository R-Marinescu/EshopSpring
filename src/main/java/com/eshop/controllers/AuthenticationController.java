package com.eshop.controllers;//package com.eshop.controllers;
//
//import com.eshop.DTO.UserDTO;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//public class AuthenticationController {
//
//    @GetMapping("/success")
//    public UserDTO success() {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        String username = authentication.getName();
//
//        UserDTO userDetailsDto = new UserDTO();
//        userDetailsDto.setUsername(username);
//        // Set other user details here
//
//        return userDetailsDto;
//    }
//}
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import com.eshop.config.TokenGenerator;

@RestController
public class AuthenticationController {

//    @GetMapping("/login/success")
//    public String loginSuccess() {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        String username = authentication.getName();
//        String sessionToken = TokenGenerator.generateSessionToken(username); // Generate session token
//
//        // Redirect user back to frontend with username and session token
//        return "redirect:http://localhost:5173/" + username + "&token=" + sessionToken;
//    }
}
