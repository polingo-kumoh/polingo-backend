package com.tangtang.polingo.integration;


import com.tangtang.polingo.testutils.MockServerSetUpUtils;
import com.tangtang.polingo.testutils.TestGoogleProperties;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
@Import(UserOAuth2IntegrationTest.TestConfig.class)
@AutoConfigureMockMvc
public class UserOAuth2IntegrationTest {


    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private MockServerSetUpUtils mockServerSetUpUtils;

    @Value("${frontend-url}")
    private String frontendUrl;



    @Test
    @DisplayName("사용자는 구글 로그인을 수행해 JWT 토큰을 발급받을 수 있다. 이 때, 사용자 정보가 없다면 DB에 저장된다.")
    public void testWhenGoogleAuthThenReturnJWTToken() throws Exception{
        //given
        mockServerSetUpUtils.setUpGoogleOAuth2MockServer();
        //when
        MvcResult mvcResult = mockMvc.perform(get("/api/login/google/callback")
                        .param("code", TestGoogleProperties.GOOGLE_AUTH_CODE))
                .andExpect(status().is3xxRedirection())
                .andDo(print())
                .andReturn();
        //then
        String redirectedUrl = mvcResult.getResponse().getRedirectedUrl();
        boolean validResult = validRedirectUrl(redirectedUrl);

        assertThat(validResult).isTrue();


    }

    private boolean validRedirectUrl(String redirectedUrl) {
        String pattern = String.format("^%s\\?token=.+", frontendUrl);

        Pattern compiledPattern = Pattern.compile(pattern);


        Matcher matcher = compiledPattern.matcher(redirectedUrl);


        boolean matches = matcher.matches();
        return matches;
    }


    @TestConfiguration
    public static class TestConfig{

        @Bean
        public TestGoogleProperties testGoogleProperties(){
            return new TestGoogleProperties();
        }

        @Bean
        public MockServerSetUpUtils mockServerSetUpUtils(TestGoogleProperties testGoogleProperties){
            return new MockServerSetUpUtils(testGoogleProperties);
        }

    }

}