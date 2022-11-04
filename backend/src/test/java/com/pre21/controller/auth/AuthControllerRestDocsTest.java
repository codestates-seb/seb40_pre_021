package com.pre21.controller.auth;

import com.google.gson.Gson;
import com.pre21.dto.AuthDto;
import com.pre21.mapper.UserMapper;
import com.pre21.security.dto.LoginDto;
import com.pre21.security.jwt.JwtTokenizer;
import com.pre21.service.AuthService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import javax.servlet.http.Cookie;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.pre21.util.ApiDocumentUtils.getDocumentRequest;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureRestDocs
public class AuthControllerRestDocsTest {

    @Autowired
    private MockMvc mockMvc;

    private Gson gson;

    @MockBean
    private AuthService authService;

    @MockBean
    private UserMapper mapper;

    @MockBean
    private JwtTokenizer jwtTokenizer;

    private AuthDto.Join savedUser;
    private String accessToken;
    private Cookie refreshCookie;
    private Cookie userCookie;
    LoginDto login;

    @BeforeEach
    @WithMockUser
    void setUp() {

        Map<String, Object> claims = new HashMap<>();
        String email = "hgd@gmail.com";
        claims.put("username", email);
        claims.put("roles", List.of("USER"));
        login = new LoginDto(email, "ghdrlfehd");
        savedUser = new AuthDto.Join("gildong",
                "hgd@gmail.com",
                "ghdrlfehd");
        authService.createUser(mapper.joinToUserEntity(savedUser));

//        Date expiration = jwtTokenizer.getTokenExpiration(jwtTokenizer.getRefreshTokenExpirationMinutes());
//        String base64EncodedSecretKey = jwtTokenizer.encodeBase64SecretKey(jwtTokenizer.getSecretKey());
//        accessToken = jwtTokenizer.generateAccessToken(claims, email, expiration, base64EncodedSecretKey);
//        String refreshToken = jwtTokenizer.generateRefreshToken(email, expiration, base64EncodedSecretKey);
//        userCookie = new Cookie("userId", "1");
//        userCookie.setPath("/");
//        userCookie.setHttpOnly(true);
//        refreshCookie = new Cookie("RefreshToken", refreshToken);
//        refreshCookie.setPath("/");
//        refreshCookie.setHttpOnly(true);

        gson = new Gson();
    }


    @Test
    @DisplayName("[Security] 회원가입 테스트")
    public void SignUpTest() throws Exception {

        AuthDto.Join signupRequest = new AuthDto.Join("회원가입유저",
                "joinUser@gmail.com",
                "join");

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

    @Test
    @WithMockUser
    @DisplayName("[Security] 로그인 테스트")
    void loginTest() throws Exception {
//        savedUser = new Auth.Join("gildong",
//                "hgd@gmail.com",
//                "ghdrlfehd");
//        userService.createUser(mapper.joinToUserEntity(savedUser));

        String content = gson.toJson(login);

        ResultActions actions =
                mockMvc.perform(post("/users/login")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
//                        .header("Authorization", "Bearer " + accessToken)
//                        .cookie(userCookie, refreshCookie)
                        .content(content)
                );

        actions
                .andExpect(status().isOk())
                .andDo(document(
                        "users/login",
                        getDocumentRequest(),
                        requestFields(
                                List.of(
                                        fieldWithPath("email").type(JsonFieldType.STRING).description("이메일"),
                                        fieldWithPath("password").type(JsonFieldType.STRING).description("비밀번호")
                                )
                        ),
                        responseBody()
                ));

    }
}
