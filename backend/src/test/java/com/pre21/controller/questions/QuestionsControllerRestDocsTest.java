package com.pre21.controller.questions;

import com.google.gson.Gson;
import com.pre21.controller.QuestionsController;
import com.pre21.dto.QuestionsPostDto;
import com.pre21.dto.QuestionsResponseDto;
import com.pre21.entity.Questions;
import com.pre21.entity.QuestionsTags;
import com.pre21.mapper.QuestionsMapper;
import com.pre21.service.QuestionsService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;

import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(QuestionsController.class)
@MockBean(JpaMetamodelMappingContext.class)
@AutoConfigureRestDocs
public class QuestionsControllerRestDocsTest {
//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    private QuestionsService questionsService;
//
//    @MockBean
//    private QuestionsMapper mapper;
//
//    @Autowired
//    private Gson gson;
//
//    @Test
//    public void createQuestion() throws Exception {
//        //given
//        QuestionsPostDto postDto = new QuestionsPostDto("제목", "내용", List.of("태그1","태그2"));
//        String content = gson.toJson(postDto);
//
//        //given(questionsService.createQuestion(Mockito.any(QuestionsPostDto.class), Mockito.anyLong()));
//
//        //when
//        ResultActions actions =
//                mockMvc.perform(
//                        post("/questions/ask")
//                                .accept(MediaType.APPLICATION_JSON)
//                                .contentType(MediaType.APPLICATION_JSON)
//                                .content(content)
//                );
//
//        //then
//        actions
//                .andExpect(status().isCreated())
//                .andExpect(jsonPath("$.data.title").value(postDto.getTitle()))
//                .andExpect(jsonPath("$.data.contents").value(postDto.getContents()))
//                .andExpect(jsonPath("$.data.tags").value(postDto.getTags()))
//                .andDo(document(
//                        "post-question",
//                        requestFields(
//                                List.of(
//                                        fieldWithPath("title").type(JsonFieldType.STRING).description("제목"),
//                                        fieldWithPath("contents").type(JsonFieldType.STRING).description("내용"),
//                                        fieldWithPath("tags").type(JsonFieldType.ARRAY).description("태그")
//                                )
//                        ),
//                        /*responseFields(
//                                List.of(
//                                        fieldWithPath("data").type(JsonFieldType.OBJECT).description("결과 데이터"),
//                                        fieldWithPath("data.id").type(JsonFieldType.NUMBER).description("질문 식별자"),
//                                        fieldWithPath("data.title").type(JsonFieldType.STRING).description("제목"),
//                                        fieldWithPath("data.contents").type(JsonFieldType.STRING).description("내용"),
//                                        fieldWithPath("data.tags").type(JsonFieldType.ARRAY).description("태그")
//                                )
//                        )*/
//                        responseBody()
//                ));
//    }
}
