package com.pre21.controller.auth;

import com.google.gson.Gson;
import com.pre21.controller.UserController;
import com.pre21.dto.UserDto;
import com.pre21.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import javax.servlet.Filter;

import java.util.List;

import static com.pre21.controller.util.ApiDocumentUtils.getDocumentRequest;
import static com.pre21.controller.util.ApiDocumentUtils.getDocumentResponse;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(
        value = {
                "jwt.access-token-expiration-minutes=15",
                "jwt.refresh-token-expiration-minutes=30",
                "JWT_SECRET_KEY=ewqiopasdasdhjqwjkdqwdjkashdjkashdasjdhqweqeqweqwdasjxasxqweu123dhasd3423hasjdk",
                "mail.address.admin=2ne1admin@gmail.com"
        }
)
@AutoConfigureMockMvc
@AutoConfigureRestDocs(outputDir = "build/generated-snippets")
public class jwtSecurityTest {

    @Autowired
    private MockMvc mockMvc;
    private Gson gson;
    private UserDto.Join signupRequest;

    @Autowired
    private Filter springSecurityFilterChain;

    @Autowired
    private WebApplicationContext context;

    @MockBean
    private UserService userService;

    @MockBean
    private RestDocumentationContextProvider contextProvider;

    @BeforeEach
    void setUp() {
//        mockMvc = MockMvcBuilders
//                .webAppContextSetup(context)
//                .addFilters(springSecurityFilterChain)
//                .addFilters(new CharacterEncodingFilter("UTF-8", true))
//                .apply(documentationConfiguration(contextProvider))
//                .alwaysDo(print())
//                .build();

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
                        "post-user",
                        getDocumentRequest(),
                        getDocumentResponse(),
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
