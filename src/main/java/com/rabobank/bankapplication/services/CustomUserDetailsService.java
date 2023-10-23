package com.rabobank.bankapplication.services;

import com.rabobank.bankapplication.models.User;
import com.rabobank.bankapplication.models.BankAccount;
import com.rabobank.bankapplication.models.UserWithAssociatedBankAccounts;
import com.rabobank.bankapplication.repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOptional = userRepository.getUserByEmail(username);
        if (userOptional.isEmpty()) {
            throw new UsernameNotFoundException("User not found");
        }

        User user = userOptional.get();
        UserDetails userDetails = org.springframework.security.core.userdetails.User.withUsername(user.getEmail())
                .password(user.getPassword())
                .authorities("USER")
                .build();

        return (UserDetails) new UserWithAssociatedBankAccounts(user, userDetails);
    }

    public Set<BankAccount> getAssociatedBankAccounts(String userEmail) {
        Optional<User> userOptional = userRepository.getUserByEmail(userEmail);
        if (userOptional.isPresent()) {
            return userOptional.get().getBankAccounts();
        }
        return new HashSet<>(); // Return an empty set if the user is not found
    }
}
