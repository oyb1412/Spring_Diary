package kr.co.myproject.Component;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import com.fasterxml.jackson.databind.JsonNode;

import java.util.List;
import java.util.Map;

@Component
public class OpenAiComponent {

    private final WebClient webClient;
    private final String model;


    public OpenAiComponent(
            @Value("${openai.api-key}") String apiKey,
            @Value("${openai.model}") String model) {

        this.model = model;

        this.webClient = WebClient.builder()
                .baseUrl("https://api.openai.com/v1/chat/completions")
                .defaultHeader(HttpHeaders.AUTHORIZATION, "Bearer " + apiKey)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }

    public JsonNode resultJson(String prompt) {
        Map<String, Object> payload = Map.of(
                "model", model,
                "messages", List.of(
                        Map.of("role", "system",
                                "content", "You are a helpful translator. 한국어 JSON으로만 응답해."),
                        Map.of("role", "user", "content", prompt)
                )
        );


        JsonNode response = webClient
                .post()
                .bodyValue(payload)
                .retrieve()
                .onStatus(
                        status -> status.is4xxClientError() || status.is5xxServerError(),
                        clientResponse -> clientResponse
                                .bodyToMono(String.class)
                                .map(body -> new RuntimeException("OpenAI error: " + body))
                )
                .bodyToMono(JsonNode.class)
                .block();

        String jsonText = response
                .get("choices").get(0)
                .get("message").get("content")
                .asText()
                .trim();

        try {
            return new ObjectMapper().readTree(jsonText);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("GPT 응답 파싱 실패", e);
        }
    }

}
