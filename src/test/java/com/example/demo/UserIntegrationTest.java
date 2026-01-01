package com.example.demo;

import com.example.demo.dto.UserDto;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class UserIntegrationTest {

        @Autowired
        private MockMvc mockMvc;

        @Autowired
        private ObjectMapper objectMapper;

        @Test
        void shouldCreateAndRetrieveUser() throws Exception {
                UserDto newUser = UserDto.builder()
                                .name("integrationUser")
                                .emailAddress("integration@example.com")
                                .build();

                // Create User
                String responseContent = mockMvc.perform(post("/users")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(newUser)))
                                .andExpect(status().isOk())
                                .andExpect(jsonPath("$.id").exists())
                                .andExpect(jsonPath("$.name").value("integrationUser"))
                                .andExpect(jsonPath("$.emailAddress").value("integration@example.com"))
                                .andReturn().getResponse().getContentAsString();

                UserDto createdUser = objectMapper.readValue(responseContent, UserDto.class);

                // Retrieve User
                mockMvc.perform(get("/users/" + createdUser.getId()))
                                .andExpect(status().isOk())
                                .andExpect(jsonPath("$.name").value("integrationUser"));
        }
}
