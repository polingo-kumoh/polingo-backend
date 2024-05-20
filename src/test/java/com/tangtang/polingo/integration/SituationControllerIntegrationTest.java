package com.tangtang.polingo.integration;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import com.tangtang.polingo.situation.service.holiday.HolidayMap;
import java.time.LocalDate;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("dev")
public class SituationControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetHolidaySituation() throws Exception {
        // Mock 현재 날짜를 1월 1일로 설정합니다.
        LocalDate mockDate = LocalDate.of(2024, 1, 1);

        HolidayMap holidayMap = new HolidayMap(new RestTemplateBuilder());
        holidayMap.init();

        // ENGLISH 테스트
        mockMvc.perform(get("/api/situation/holiday")
                        .param("lang", "ENGLISH")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.type").value("공휴일"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.sentance").value("Today is New Year\'s Day!"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.situationImage").value("http://example.com/new_year.jpg"))
                .andDo(MockMvcResultHandlers.print());

        // JAPANESE 테스트
        mockMvc.perform(get("/api/situation/holiday")
                        .param("lang", "JAPANESE")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.type").value("공휴일"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.sentance").value("今日は元日です！"))
                .andExpect(
                        MockMvcResultMatchers.jsonPath("$.situationImage").value("http://example.com/new_year_jp.jpg"))
                .andDo(MockMvcResultHandlers.print());

        // KOREAN 테스트
        mockMvc.perform(get("/api/situation/holiday")
                        .param("lang", "KOREAN")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.type").value("공휴일"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.sentance").value("오늘은 민족대명절 설날입니다!"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.situationImage").value("http://example.com/seollal.jpg"))
                .andDo(MockMvcResultHandlers.print());
    }
}
