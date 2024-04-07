package com.tangtang.polingo.testutils;


import com.tangtang.polingo.user.property.GoogleProperties;
import org.mockserver.integration.ClientAndServer;
import org.mockserver.model.MediaType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;

import java.net.URLEncoder;

import static org.mockserver.integration.ClientAndServer.startClientAndServer;
import static org.mockserver.model.HttpRequest.*;


public class MockServerSetUpUtils {
    private ClientAndServer mockServer;

    private final TestGoogleProperties googleProperties;

    public MockServerSetUpUtils(TestGoogleProperties googleProperties) {
        this.googleProperties = googleProperties;
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
                .respond(org.mockserver.model.HttpResponse.response()
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
                .respond(org.mockserver.model.HttpResponse.response()
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

    public void close(){
        mockServer.close();
        mockServer = null;
    }

}
