package com.tangtang.polingo.integration;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.tangtang.polingo.global.constant.Language;
import com.tangtang.polingo.security.service.JwtService;
import com.tangtang.polingo.user.constant.LoginType;
import com.tangtang.polingo.user.constant.UserRole;
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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;


import static org.assertj.core.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@Transactional
@SpringBootTest
@ActiveProfiles("test")
@Sql("classpath:sql/init_user.sql")
public class MyPageIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ObjectMapper objectMapper;


    @Test
    @DisplayName("인증이 완료된 유저는 자신의 정보를 조회할 수 있다.")
    public void testGivenJwtTokenWhenGetMyInfoThenReturnInfo() throws Exception{
        //given
        User user = userRepository.findByLoginTypeAndProviderId(LoginType.GOOGLE, "123456789")
                .orElseThrow(() -> new IllegalArgumentException("해당 유저가 존재하지 않습니다."));

        String jwtToken = jwtService.createToken(user);

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


}
