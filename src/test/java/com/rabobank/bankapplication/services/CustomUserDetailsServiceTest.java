package com.rabobank.bankapplication.services;

import com.rabobank.bankapplication.models.User;
import com.rabobank.bankapplication.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

    public class CustomUserDetailsServiceTest {

        @Mock
        private UserRepository userRepository;

        private CustomUserDetailsService userDetailsService;

        @BeforeEach
        public void setup() {
            MockitoAnnotations.openMocks(this);
            userDetailsService = new CustomUserDetailsService(userRepository);
        }

        @Test
        public void testLoadUserByUsername_UserExists() {
            // Arrange
            String email = "test@test.com";
            User user = new User();
            user.setEmail(email);
            user.setPassword("password");

            Mockito.when(userRepository.getUserByEmail(email)).thenReturn(Optional.of(user));

            // Act
            UserDetails userDetails = userDetailsService.loadUserByUsername(email);

            // Assert
            assertEquals(email, userDetails.getUsername());
            assertEquals("password", userDetails.getPassword());
            assertEquals("USER", userDetails.getAuthorities().iterator().next().getAuthority());
        }

        @Test
        public void testLoadUserByUsername_UserNotFound() {
            // Arrange
            String username = "nonexistentuser";
            Mockito.when(userRepository.getUserByEmail(username)).thenReturn(Optional.empty());

            // Act and Assert
            assertThrows(UsernameNotFoundException.class, () -> userDetailsService.loadUserByUsername(username));
        }
    }


