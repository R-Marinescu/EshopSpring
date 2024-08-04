package com.eshop.config;

import com.eshop.models.Role;
import com.eshop.models.User;
import com.eshop.repositories.MyUserPrincipal;
import com.eshop.repositories.RoleRepo;
import com.eshop.repositories.UserRepo;
import com.eshop.repositories.UserRolesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private RoleRepo roleRepo;

    @Autowired
    private UserRolesRepo userRolesRepo;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public MyUserDetailsService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException(username);
        }

        // Use the PasswordEncoder to encode the password
        String encodedPassword = passwordEncoder.encode(user.getPassword());

        List<Role> roles = roleRepo.findRolesByUserId(user.getUserId());
       // System.out.println("edw??" + roles);
        return new MyUserPrincipal(user, roles);
    }
}


