package com.swifttech.main.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.swifttech.controller.AuthenticationController;
import com.swifttech.response.AuthenticationRequest;
import com.swifttech.service.AuthenticationService;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.nio.charset.StandardCharsets;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
@SpringBootTest
class AuthenticationControllerTest {

    @Mock
    private AuthenticationService authenticationService;

    @Autowired
     MockMvc mockMvc;

    @InjectMocks
    private AuthenticationController authenticationController;


    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(authenticationController).build();
    }


    private final ObjectMapper objectMapper = new ObjectMapper();


    @Test
    public void testAuthentication() throws Exception {

        AuthenticationRequest authenticationRequest = new AuthenticationRequest("ashimgotame@gmail.com", "securePassword123");
        String content = objectMapper.writeValueAsString(authenticationRequest);
        performPostRequest("/auth/authenticate", content)
                .andExpect(status().isOk())
                .andDo(print());

    }

    @Test
    public void testMissingCredentials() throws Exception {
        AuthenticationRequest authenticationRequest = new AuthenticationRequest(null, null);
        String content = objectMapper.writeValueAsString(authenticationRequest);
        performPostRequest("/auth/authenticate", content)
                .andExpect(result -> {
                    Exception resolvedException = result.getResolvedException();
                    assert resolvedException != null;
                    Assertions.assertEquals("Required field not found", resolvedException.getMessage());
                });
    }

    @Test
    public void testEmptyEmailAuthentication() throws Exception {
        AuthenticationRequest authenticationRequest = new AuthenticationRequest("", "securePassword123");
        String content = objectMapper.writeValueAsString(authenticationRequest);
        performPostRequest("/auth/authenticate", content)
                .andExpect(status().isNotFound());
    }

    private ResultActions performPostRequest(String url, String content) throws Exception {
        return mockMvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding(StandardCharsets.UTF_8)
                .content(content));
    }
}