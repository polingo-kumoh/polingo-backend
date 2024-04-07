package com.tangtang.polingo.integration;


import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.tangtang.polingo.testutils.MockServerSetUpUtils;
import com.tangtang.polingo.testutils.TestGoogleProperties;
import com.tangtang.polingo.testutils.TestKakaoProperties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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

    @Autowired
    private TestGoogleProperties googleProperties;

    @Autowired
    private TestKakaoProperties kakaoProperties;


    @Test
    @DisplayName("사용자가 구글 로그인을 시도하면 구글 정보동의화면으로 리다이렉션 한다.")
    public void testWhenGoogleLoginStartsThenRedirectToGoogleAgreementPage() throws Exception {
        //given

        //when
        MvcResult mvcResult = mockMvc.perform(get("/api/login/google"))
                .andExpect(status().is3xxRedirection())
                .andDo(print())
                .andReturn();

        //then
        String actualRedirectUrl = mvcResult.getResponse().getRedirectedUrl();
        String expectedRedirectUrl = String.format("%s?client_id=%s&redirect_uri=%s&response_type=%s&scope=%s",
                googleProperties.getAuthorizationUri(),
                googleProperties.getClientId(),
                googleProperties.getRedirectUri(),
                googleProperties.getResponseType(),
                googleProperties.getScope().replace(" ", "%20")
        );

        assertThat(actualRedirectUrl).isEqualTo(expectedRedirectUrl);

    }


    @Test
    @DisplayName("사용자는 구글에 정보동의를 완료하면 JWT 토큰을 발급받을 수 있다. 이 때, 사용자 정보가 없다면 DB에 저장된다.")
    public void testWhenGoogleAuthThenReturnJWTAndSave() throws Exception {
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
        boolean validResult = validSocialLoginResult(redirectedUrl);

        assertThat(validResult).isTrue();
        // TODO :DB에 저장되어 있는지 확인하는 검증 로직 추가 필요

        mockServerSetUpUtils.close();
    }

    @Test
    @DisplayName("사용자가 카카오 로그인을 시도하면 카카오 정보동의화면으로 리다이렉션 한다.")
    public void testWhenKakaoLoginStartsThenRedirectToKakaoAgreementPage() throws Exception {
        //given

        //when
        MvcResult mvcResult = mockMvc.perform(get("/api/login/kakao"))
                .andExpect(status().is3xxRedirection())
                .andDo(print())
                .andReturn();

        //then
        String actualRedirectUrl = mvcResult.getResponse().getRedirectedUrl();
        String expectedRedirectUrl = String.format("%s?client_id=%s&redirect_uri=%s&response_type=%s",
                kakaoProperties.getAuthorizationUri(),
                kakaoProperties.getClientId(),
                kakaoProperties.getRedirectUri(),
                kakaoProperties.getResponseType()
        );

        assertThat(actualRedirectUrl).isEqualTo(expectedRedirectUrl);

    }

    @Test
    @DisplayName("사용자는 카카오 정보동의를 완료하면 JWT 토큰을 발급받을 수 있다. 이 때, 사용자 정보가 없다면 DB에 저장된다.")
    public void testWhenKakaoLoginThenReturnJWTAndSave() throws Exception {
        //given
        mockServerSetUpUtils.setUpKakaoOAuth2MockServer();
        //when
        MvcResult mvcResult = mockMvc.perform(get("/api/login/kakao/callback")
                        .param("code", TestKakaoProperties.KAKAO_AUTH_CODE))
                .andExpect(status().is3xxRedirection())
                .andDo(print())
                .andReturn();
        //then
        String redirectedUrl = mvcResult.getResponse().getRedirectedUrl();
        boolean validResult = validSocialLoginResult(redirectedUrl);

        assertThat(validResult).isTrue();
        // TODO :DB에 저장되어 있는지 확인하는 검증 로직 추가 필요

        mockServerSetUpUtils.close();

    }


    private boolean validSocialLoginResult(String redirectedUrl) {
        String pattern = String.format("^%s\\?token=.+", frontendUrl);

        Pattern compiledPattern = Pattern.compile(pattern);

        Matcher matcher = compiledPattern.matcher(redirectedUrl);

        boolean matches = matcher.matches();
        return matches;
    }


    @TestConfiguration
    public static class TestConfig {

        @Bean
        public TestGoogleProperties testGoogleProperties() {
            return new TestGoogleProperties();
        }


        @Bean
        public TestKakaoProperties testKakaoProperties() {
            return new TestKakaoProperties();
        }

        @Bean
        public MockServerSetUpUtils mockServerSetUpUtils(
                TestGoogleProperties testGoogleProperties,
                TestKakaoProperties testKakaoProperties

        ) {
            return new MockServerSetUpUtils(testGoogleProperties, testKakaoProperties);
        }

    }

}
