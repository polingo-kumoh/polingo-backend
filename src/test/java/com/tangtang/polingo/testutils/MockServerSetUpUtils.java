package com.tangtang.polingo.testutils;


import com.tangtang.polingo.user.property.GoogleProperties;
import org.mockserver.integration.ClientAndServer;
import org.mockserver.model.MediaType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;

import java.net.URLEncoder;
import org.springframework.stereotype.Component;

import static org.mockserver.integration.ClientAndServer.startClientAndServer;
import static org.mockserver.model.HttpRequest.*;
import static org.mockserver.model.HttpResponse.*;

public class MockServerSetUpUtils {
    private ClientAndServer mockServer;

    private final TestGoogleProperties googleProperties;

    private final TestKakaoProperties kakaoProperties;

    public MockServerSetUpUtils(TestGoogleProperties googleProperties, TestKakaoProperties kakaoProperties) {
        this.googleProperties = googleProperties;
        this.kakaoProperties = kakaoProperties;
    }

    public void setUpGoogleOAuth2MockServer() {
        mockServer = startClientAndServer(  1080);


        // GOOGLE Server가 Access Token을 리턴하는 것을 모킹
        mockServer.when(request()
                .withMethod("POST")
                        .withHeader("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8")
                .withPath(googleProperties.getTokenEndpoint())
                        .withBody("code=" + TestGoogleProperties.GOOGLE_AUTH_CODE
                                + "&client_id=" + googleProperties.getClientId()
                                + "&client_secret=" + googleProperties.getClientSecret()
                                + "&redirect_uri=" +  URLEncoder.encode(googleProperties.getRedirectUri())
                                + "&grant_type=" + googleProperties.getGrantType()))
                .respond(response()
                        .withStatusCode(200)
                        .withContentType(MediaType.APPLICATION_JSON)
                        .withBody(
                                "{\n" +
                                "  \"access_token\": \"" + TestGoogleProperties.GOOGLE_ACCESS_TOKEN +"\",\n" +
                                "  \"token_type\": \"Bearer\",\n" +
                                "  \"expires_in\": 3599,\n" +
                                "  \"refresh_token\": \""+ TestGoogleProperties.GOOGLE_REFRESH_TOKEN+"\"\n" +
                                "}\n"
                        ));

        // GOOGLE Server가 User Info를 리턴하는 것을 모킹
        mockServer.when(request()
                .withMethod("GET")
                .withHeader("Authorization", "Bearer " + TestGoogleProperties.GOOGLE_ACCESS_TOKEN)
                .withPath(googleProperties.getUserInfoEndpoint()))
                .respond(response()
                        .withStatusCode(200)
                        .withContentType(MediaType.APPLICATION_JSON)
                        .withBody(
                                "{\n" +
                                "  \"id\": \"1234567890\",\n" +
                                "  \"name\": \"gildong hong\",\n" +
                                "  \"given_name\": \"gildong\",\n" +
                                "  \"family_name\": \"hong\",\n" +
                                "  \"picture\": \"https://lh3.googleusercontent.com/a-/AOh14Gj3Zz7z\",\n" +
                                "  \"locale\": \"ko\",\n" +
                                "  \"email\": \"hong@email.com\", \n" +
                                "  \"email_verified\": true\n" +
                                "}"
                        )
        );

    }


    public void setUpKakaoOAuth2MockServer() {
        mockServer = startClientAndServer(  1090);

        // 카카오 서버가 토큰을 리턴하는 것을 모킹
        mockServer.when(request()
                .withMethod("POST")
                .withHeader("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8")
                .withPath("/oauth/token")
                .withBody("" +
                        "code=" + TestKakaoProperties.KAKAO_AUTH_CODE
                        + "&grant_type=" + kakaoProperties.getGrantType()
                        + "&client_id=" + kakaoProperties.getClientId()
                        // + "&redirect_uri=" + URLEncoder.encode(kakaoProperties.getRedirectUri())
                ))
                .respond(response()
                        .withStatusCode(200)
                        .withContentType(MediaType.APPLICATION_JSON)
                        .withBody(
                                "{\n" +
                                "  \"access_token\": \"" + TestKakaoProperties.KAKAO_ACCESS_TOKEN +"\",\n" +
                                "  \"token_type\": \"bearer\",\n" +
                                "  \"refresh_token\": \""+ TestKakaoProperties.KAKAO_REFRESH_TOKEN+"\",\n" +
                                "  \"expires_in\": 21599,\n" +
                                "  \"scope\": \"account_email profile\"\n" +
                                "}\n"
                        ));

        // 카카오 서버가 유저 정보를 리턴하는 것을 모킹
        mockServer.when(request()
                .withPath("/v2/user/me")
                .withMethod("POST")
                .withHeader("Authorization", "Bearer " + TestKakaoProperties.KAKAO_ACCESS_TOKEN)
                .withHeader("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8")
        ).respond(response()
                .withStatusCode(200)
                .withContentType(MediaType.APPLICATION_JSON)
                .withBody("{\n" +
                        "    \"id\":123456789,\n" +
                        "    \"connected_at\": \"2022-04-11T01:45:28Z\",\n" +
                        "    \"kakao_account\": { \n" +
                        "        \"profile_nickname_needs_agreement\": false,\n" +
                        "        \"profile_image_needs_agreement\": false,\n" +
                        "        \"profile\": {\n" +
                        "            \"nickname\": \"홍길동\",\n" +
                        "            \"thumbnail_image_url\": \"http://yyy.kakao.com/.../img_110x110.jpg\",\n" +
                        "            \"profile_image_url\": \"http://yyy.kakao.com/dn/.../img_640x640.jpg\",\n" +
                        "            \"is_default_image\":false,\n" +
                        "            \"is_default_nickname\": false\n" +
                        "        },\n" +
                        "        \"name_needs_agreement\":false, \n" +
                        "        \"name\":\"홍길동\"\n" +
                        "    }\n" +
                        "}")
        );
    }



    public void close(){
        mockServer.close();
        mockServer = null;
    }

}
