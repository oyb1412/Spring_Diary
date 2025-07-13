package kr.co.myproject.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import kr.co.myproject.Component.OpenAiComponent;
import kr.co.myproject.Util.GptPrompt;
import kr.co.myproject.dto.Famous.FamousDto;
import kr.co.myproject.dto.Famous.FamousSaveDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

@Service
@RequiredArgsConstructor
public class FamousService {
    private final OpenAiComponent openAiComponent;
    private final RedisTemplate<String, String> redis;

    private String CACHE_KEY = "famousCache";
    private static final Duration TTL = Duration.ofDays(30);      // 캐시 1일


    public FamousDto getRandomFamous()
    {
        String url = "https://zenquotes.io/api/random";
        RestTemplate restTemplate = new RestTemplate();

        FamousSaveDto[] response = restTemplate.getForObject(url, FamousSaveDto[].class);

        if (response == null || response.length == 0) {
            throw new RuntimeException("명언 불러오기 실패!");
        }

        FamousSaveDto original = response[0];

        String key = CACHE_KEY + original.getFamousEn();

        String cached = redis.opsForValue().get(key);
        if(cached != null) {
            try {
                JsonNode node = new ObjectMapper().readTree(cached);
                return new FamousDto(node.get("famous").asText(),
                        node.get("author").asText());
            } catch (JsonProcessingException e) {
                throw new RuntimeException("GPT 응답 파싱 실패", e);
            }
        }

        String prompt = GptPrompt.koreanPrompt(original);
        JsonNode node = openAiComponent.resultJson(prompt);

        String famousKo = node.get("famous").asText();
        String authorKo = node.get("author").asText();

        String valueJson = String.format(
                "{\"famous\":\"%s\",\"author\":\"%s\"}",
                famousKo, authorKo);

        redis.opsForValue().set(key, valueJson, TTL);

        return new FamousDto(famousKo, authorKo);
    }
}
