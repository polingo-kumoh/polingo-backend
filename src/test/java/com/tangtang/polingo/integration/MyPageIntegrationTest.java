package com.tangtang.polingo.integration;


import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tangtang.polingo.global.constant.Language;
import com.tangtang.polingo.security.jwt.JwtProvider;
import com.tangtang.polingo.user.constant.LoginType;
import com.tangtang.polingo.user.dto.UserResponse;
import com.tangtang.polingo.user.entity.User;
import com.tangtang.polingo.user.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.transaction.annotation.Transactional;

@AutoConfigureMockMvc
@Transactional
@SpringBootTest
@ActiveProfiles("test")
@Sql("classpath:sql/init_user.sql")
public class MyPageIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JwtProvider jwtProvider;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ObjectMapper objectMapper;


    @Test
    @DisplayName("인증이 완료된 유저는 자신의 정보를 조회할 수 있다.")
    public void testGivenJwtTokenWhenGetMyInfoThenReturnInfo() throws Exception {
        //given
        String jwtToken = createToken();

        //when
        MvcResult mvcResult = mockMvc.perform(get("/api/user")
                        .header("Authorization", "Bearer " + jwtToken)
                        .characterEncoding("UTF-8"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        //then
        UserResponse actual = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), UserResponse.class);

        assertThat(actual).extracting("id", "nickname", "defaultLanguage")
                .containsExactly(1L, "홍길동", Language.ENGLISH);
    }

    @Test
    @DisplayName("인증이 완료된 사용자는 자신의 기본 언어를 변경해 DB에 반영할 수 있다.")
    public void testGivenAccessTokenWhenChangeDefaultLanguageThenSuccess() throws Exception {
        //given
        String jwtToken = createToken();

        //when
        mockMvc.perform(put("/api/user/language")
                        .header("Authorization", "Bearer " + jwtToken)
                        .param("languageCode", "jp")
                        .characterEncoding("UTF-8"))
                .andDo(print())
                .andExpect(status().isOk());
        //then
        User user = getUser();

        assertThat(user.getLanguage()).isEqualTo(Language.JAPANESE.name());
    }

    @Test
    @DisplayName("인증이 완료된 유저는 자신의 닉네임을 변경해 DB에 반영할 수 있다.")
    public void testGivenAccessTokenWhenChangeNickNameThenSuccess() throws Exception {
        //given
        String jwtToken = createToken();
        //when
        mockMvc.perform(put("/api/user/nickname")
                        .header("Authorization", "Bearer " + jwtToken)
                        .param("name", "변경된닉네임")
                        .characterEncoding("UTF-8"))
                .andDo(print())
                .andExpect(status().isOk());
        //then
        User user = getUser();

        assertThat(user.getNickname()).isEqualTo("변경된닉네임");

    }


    private String createToken() {
        User user = getUser();

        return jwtProvider.createToken(user);
    }

    private User getUser() {
        User user = userRepository.findByLoginTypeAndProviderId(LoginType.GOOGLE, "123456789")
                .orElseThrow(() -> new IllegalArgumentException("해당 유저가 존재하지 않습니다."));
        return user;
    }


}

