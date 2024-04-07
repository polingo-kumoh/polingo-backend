package com.tangtang.polingo.oauth2.util;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.tangtang.polingo.oauth2.dto.KakaoResponse;
import java.io.IOException;

public class KakaoResponseDeserializer extends JsonDeserializer<KakaoResponse> {
    @Override
    public KakaoResponse deserialize(JsonParser jp, DeserializationContext context) throws IOException {
        JsonNode rootNode = jp.getCodec().readTree(jp);
        JsonNode kakaoAccountNode = rootNode.path("kakao_account");
        JsonNode profileNode = kakaoAccountNode.path("profile");

        String name = profileNode.path("nickname").asText();
        String id = rootNode.path("id").asText();

        return KakaoResponse.builder()
                .id(id)
                .name(name)
                .build();
    }
}
