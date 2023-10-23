package com.rabobank.bankapplication.controllers;


import com.rabobank.bankapplication.models.BankAccount;
import com.rabobank.bankapplication.models.User;
import com.rabobank.bankapplication.repositories.BankAccountRepository;
import com.rabobank.bankapplication.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private BankAccountRepository bankAccountRepository;

    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/profile")
    public ResponseEntity<User> getProfile(@AuthenticationPrincipal UserDetails userDetails) {
        // userDetails.getUsername() would typically provide the email or username,
        // depending on how you've set up your CustomUserDetailsService
        String username = userDetails.getUsername();

        Optional<User> userOptional = userRepository.getUserByEmail(username); // Assuming email as the username

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            // Hide sensitive fields before sending user data
            user.setPassword(null);
            return ResponseEntity.ok(user);
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
    @GetMapping("/associated-bank-accounts/{email}")
    public ResponseEntity<Set<BankAccount>> getAssociatedBankAccounts(@PathVariable String email) {
        // Find the user by email first
        Optional<User> userOptional = userRepository.getUserByEmail(email);
        if (!userOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        // Fetch the associated bank accounts
        Set<BankAccount> bankAccounts = bankAccountRepository.findByUser_Email(email);

        return ResponseEntity.ok(bankAccounts);
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + id));
    }

    @GetMapping("/all")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @PostMapping("/create")
    public User createUser(@RequestBody User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @PutMapping("/update/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User updatedUser) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + id));
        user.setFirstName(updatedUser.getFirstName());
        user.setLastName(updatedUser.getLastName());
        user.setEmail(updatedUser.getEmail());
        user.setPassword(passwordEncoder.encode(updatedUser.getPassword()));  // Encrypt the password before saving
        return userRepository.save(user);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteUser(@PathVariable Long id) {
        userRepository.deleteById(id);
    }
}
