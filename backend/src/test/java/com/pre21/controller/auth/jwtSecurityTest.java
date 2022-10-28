package com.pre21.controller.auth;

import com.google.gson.Gson;
import com.pre21.dto.UserDto;
import com.pre21.mapper.UserMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
//@AutoConfigureRestDocs
public class jwtSecurityTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserMapper mapper;

    @Autowired
    private Gson gson;

    private UserDto.Join signupRequest;

    @BeforeEach
    void setUp() {
        signupRequest = new UserDto.Join("길똥쓰",
                "hgd@gmail.com",
                "12341234");
    }


//    @Test
//    @DisplayName("[Security] 회원가입 테스팅")
    void SignUpTest() throws Exception {

        String content = gson.toJson(signupRequest);

        ResultActions actions =
                mockMvc.perform(post("/login/signup")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content)
                );

        MvcResult result = actions
                .andExpect(status().isOk()).andReturn();
    }

}
