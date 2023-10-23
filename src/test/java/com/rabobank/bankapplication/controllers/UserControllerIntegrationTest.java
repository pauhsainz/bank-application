package com.rabobank.bankapplication.controllers;

import com.rabobank.bankapplication.models.User;
import com.rabobank.bankapplication.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class UserControllerIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    public void setup() {
        // You can initialize test data or configure the userRepository here.
    }

    @Test
    public void testGetUser() {
        Long userId = 1L; // Adjust to an actual user ID in your test data
        ResponseEntity<User> responseEntity = restTemplate.getForEntity("http://localhost:" + port + "/api/users/" + userId, User.class);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        // Additional assertions for the user data.
    }

    @Test
    public void testGetAllUsers() {
        ResponseEntity<User[]> responseEntity = restTemplate.getForEntity("http://localhost:" + port + "/api/users", User[].class);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        // Additional assertions for the list of users.
    }

    @Test
    public void testCreateUser() {
        User newUser = new User();
        newUser.setFirstName("John");
        newUser.setLastName("Doe");
        newUser.setEmail("john.doe@example.com");
        newUser.setPassword("password123");

        ResponseEntity<User> responseEntity = restTemplate.postForEntity("http://localhost:" + port + "/api/users", newUser, User.class);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        // Additional assertions for the created user.
    }

    @Test
    public void testUpdateUser() {
        Long userId = 1L; // Adjust to an actual user ID in your test data
        User updatedUser = new User();
        updatedUser.setFirstName("UpdatedFirstName");
        updatedUser.setLastName("UpdatedLastName");
        updatedUser.setEmail("updated.email@example.com");
        updatedUser.setPassword("updatedPassword");

        restTemplate.put("http://localhost:" + port + "/api/users/" + userId, updatedUser);
        User retrievedUser = userRepository.findById(userId).orElse(null);

        // Assert that the user has been updated successfully.
        assertEquals("UpdatedFirstName", retrievedUser.getFirstName());
        assertEquals("UpdatedLastName", retrievedUser.getLastName());
        assertEquals("updated.email@example.com", retrievedUser.getEmail());
        assertEquals("updatedPassword", retrievedUser.getPassword());
    }

    @Test
    public void testDeleteUser() {
        Long userId = 1L; // Adjust to an actual user ID in your test data
        restTemplate.delete("http://localhost:" + port + "/api/users/" + userId);

        // Assert that the user has been deleted successfully.
        assertEquals(0, userRepository.count());
    }

    // Add more test methods for other controller endpoints.
}
