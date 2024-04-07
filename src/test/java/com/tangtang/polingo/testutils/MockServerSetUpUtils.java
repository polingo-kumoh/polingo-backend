package com.tangtang.polingo.testutils;


import com.tangtang.polingo.user.property.GoogleProperties;
import org.mockserver.integration.ClientAndServer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;

import static org.mockserver.integration.ClientAndServer.startClientAndServer;


public class MockServerSetUpUtils {
    private ClientAndServer mockServer;

    private final TestGoogleProperties googleProperties;

    public MockServerSetUpUtils(TestGoogleProperties googleProperties) {
        this.googleProperties = googleProperties;
    }

    public void setUpGoogleOAuth2MockServer() {
        mockServer = startClientAndServer(googleProperties.getAuthServerHost() , 80);

        mockServer.when(org.mockserver.model.HttpRequest.request()
                .withMethod("GET")
                .withPath(googleProperties.getAuthorizationEndpoint())
                .withQueryStringParameter("client_id", googleProperties.getClientId())
                .withQueryStringParameter("redirect_uri", googleProperties.getRedirectUri())
                .withQueryStringParameter("scope", googleProperties.getScope()))
                .respond(org.mockserver.model.HttpResponse.response()
                        .withStatusCode(302)
                        .withHeader("Location", googleProperties.getRedirectUri() + "?code=" + TestGoogleProperties.GOOGLE_AUTH_CODE));
    }

    public void close(){
        mockServer.close();
        mockServer = null;
    }

}
