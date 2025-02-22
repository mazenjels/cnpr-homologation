package com.cnpr.homologation.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cnpr.homologation.models.ViewUser;
import com.cnpr.homologation.repository.ViewUserRepository;



@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final ViewUserRepository userRepository;

    public CustomUserDetailsService(ViewUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    	ViewUser user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
//        return org.springframework.security.core.userdetails.User
//                .withUsername(user.getUsername())
//                .password(user.getPassword())
//                //.accountExpired(user.isAccountExpired())
//                //.accountLocked(user.isAccountLocked())
//               // .credentialsExpired(user.isCredentialsExpired())
//                .disabled(user.isEnabled())
//                .roles(user.getRole())
//                .build();
        return new CustomUserDetails(user);
    }

}
