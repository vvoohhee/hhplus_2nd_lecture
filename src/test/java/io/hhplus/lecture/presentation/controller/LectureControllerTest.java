package io.hhplus.lecture.presentation.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.hhplus.lecture.presentation.dto.ApplyDto;
import io.hhplus.lecture.presentation.dto.ApplyResultDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(LectureController.class)
public class LectureControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Test
    @DisplayName("특강 신청 컨트롤러 테스트")
    void applyTest() throws Exception {
        Long userId = 1L;
        Long lectureId = 1L;

        ApplyDto.Request request = new ApplyDto.Request(userId, lectureId);

        MvcResult mvcResult = mockMvc.perform(post("/lectures/apply")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andReturn();

        String content = mvcResult.getResponse().getContentAsString();
        ApplyDto.Response response = objectMapper.readValue(content, ApplyDto.Response.class);

        assertThat(response).isEqualTo(new ApplyDto.Response(true));
    }

    @Test
    @DisplayName("특강 신청 완료 여부 조회 컨트롤러 테스트")
    void applyResultTest() throws Exception {
        Long userId = 1L;

        MvcResult mvcResult = mockMvc.perform(get("/lectures/application/{userId}", userId))
                .andExpect(status().isOk())
                .andReturn();

        String content = mvcResult.getResponse().getContentAsString();
        ApplyResultDto.Response response = objectMapper.readValue(content, ApplyResultDto.Response.class);

        assertThat(response).isEqualTo(new ApplyResultDto.Response(true));
    }
}
