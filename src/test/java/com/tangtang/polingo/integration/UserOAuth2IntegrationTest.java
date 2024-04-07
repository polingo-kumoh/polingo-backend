package com.tangtang.polingo.integration;


import com.tangtang.polingo.testutils.MockServerSetUpUtils;
import com.tangtang.polingo.testutils.TestGoogleProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
public class UserOAuth2IntegrationTest {








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
