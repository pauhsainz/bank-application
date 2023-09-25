package com.rabobank.bankapplication.services;

import com.rabobank.bankapplication.models.User;
import com.rabobank.bankapplication.repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> User = userRepository.getUserByEmail(username);

        if (User.isEmpty()) {
            throw new UsernameNotFoundException("User not found");
        }

        User userObject = User.get();
        return org.springframework.security.core.userdetails.User.withUsername(userObject.getEmail())
                .password(userObject.getPassword())
                .authorities("USER")
                .build();
    }
}
