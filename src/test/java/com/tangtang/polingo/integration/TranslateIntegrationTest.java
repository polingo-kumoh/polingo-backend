package com.tangtang.polingo.integration;


import com.deepl.api.TextResult;
import com.deepl.api.Translator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.cloud.speech.v1.SpeechClient;
import com.tangtang.polingo.global.constant.Language;
import com.tangtang.polingo.security.service.JwtService;
import com.tangtang.polingo.translate.dto.PlainTextTranslateRequest;
import com.tangtang.polingo.translate.dto.TranslateResponse;
import com.tangtang.polingo.translate.service.ocr.ImageToText;
import com.tangtang.polingo.translate.service.stt.SpeachToText;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

import static com.tangtang.polingo.testutils.UserTestDataUtils.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
@Transactional
@Sql(scripts = {"classpath:sql/init_user.sql"})
@ExtendWith(MockitoExtension.class)
public class TranslateIntegrationTest {

    @MockBean
    private Translator translator;

    @MockBean
    SpeachToText speachToText;

    @MockBean
    SpeechClient speechClient;

    @MockBean
    ImageToText imageToText;


    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private JwtService jwtService;

    @Test
    @DisplayName("인증된 사용자는 텍스트 원문을 가지고 번역을 수행해 번역본을 제공받을 수 있다.")
    void given_AuthenticatedUserOriginText_when_Translate_then_ReturnOriginTextAndTranslatedText() throws Exception{
        // given
        String givenToken = jwtService.createToken(testUser);

        when(translator.translateText("Hello, world!", "en", "ko"))
                .thenReturn(new TextResult("안녕, 세계!", "en"));


        PlainTextTranslateRequest givenRequest = new PlainTextTranslateRequest(Language.ENGLISH, "Hello, world!");
        TranslateResponse expectedResponse = new TranslateResponse("Hello, world!", "안녕, 세계!");


        // when
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/api/translate/plain")
                        .header("Authorization", "Bearer " + givenToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(givenRequest)))
                .andDo(print())  // 요청과 응답 출력
                .andExpect(status().isOk())
                .andReturn();
        // then
        TranslateResponse actualResponse = objectMapper
                .readValue(result.getResponse().getContentAsString(), TranslateResponse.class);

        assertThat(actualResponse).isEqualTo(expectedResponse);


    }
    
    @Test
    @DisplayName("인증된 사용자는 음성 파일을 가지고 번역을 수행해 번역본을 제공받을 수 있다.")
    void given_AuthenticatedUserOriginVoice_when_Translate_then_ReturnOriginTextAndTranslatedText() throws Exception{
        // given
        String givenToken = jwtService.createToken(testUser);

        MockMultipartFile voiceFile =
                new MockMultipartFile("voice", "test-audio.mp3", "audio/mpeg", "test audio data".getBytes());

        TranslateResponse expectedResponse = new TranslateResponse("Hello, world!", "안녕, 세계!");



        when(speachToText.convert(voiceFile, Language.ENGLISH)).thenReturn("Hello, world!");
        when(translator.translateText("Hello, world!", "en", "ko"))
                .thenReturn(new TextResult("안녕, 세계!", "en"));
        // when
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.multipart("/api/translate/voice")
                        .file(voiceFile)
                        .param("source-type", Language.ENGLISH.toString())
                        .header("Authorization", "Bearer " + givenToken)
                        .contentType(MediaType.MULTIPART_FORM_DATA_VALUE))
                .andExpect(status().isOk())
                .andReturn();
        // then
        TranslateResponse actualResponse = objectMapper
                .readValue(result.getResponse().getContentAsString(), TranslateResponse.class);

        assertThat(actualResponse).isEqualTo(expectedResponse);

    }



}
