package com.pre21.controller.auth;

import com.google.gson.Gson;
import com.pre21.dto.UserDto;
import com.pre21.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.Filter;

import java.util.List;
import static com.pre21.util.ApiDocumentUtils.getDocumentRequest;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureRestDocs
public class AuthControllerRestDocsTest {


    private MockMvc mockMvc;
    private Gson gson;
    private UserDto.Join signupRequest;

    @Autowired
    private Filter springSecurityFilterChain;

    @MockBean
    private UserService userService;

    @Autowired
    RestDocumentationContextProvider restDocumentation;

    @Autowired
    WebApplicationContext context;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(documentationConfiguration(restDocumentation))
                .addFilters(springSecurityFilterChain)
                .alwaysDo(print())
                .build();

        gson = new Gson();
        signupRequest = new UserDto.Join("홍길동",
                "hgd@gmail.com",
                "ghdrlfehd");
    }


    @Test
    @DisplayName("[Security] 회원가입 테스팅")
    public void SignUpTest() throws Exception {

        String content = gson.toJson(signupRequest);

        ResultActions actions =
                mockMvc.perform(post("/users/signup")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content)
                );

        actions
                .andExpect(status().isOk())
                .andDo(document(
                        "users/signup",
                        getDocumentRequest(),
                        requestFields(
                                List.of(
                                        fieldWithPath("nickname").type(JsonFieldType.STRING).description("닉네임"),
                                        fieldWithPath("email").type(JsonFieldType.STRING).description("이메일"),
                                        fieldWithPath("password").type(JsonFieldType.STRING).description("비밀번호")
                                )
                        ),
                        responseBody()
                ));
    }

}