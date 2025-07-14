package kr.co.myproject.service;

import com.fasterxml.jackson.databind.JsonNode;
import kr.co.myproject.Component.OpenAiComponent;
import kr.co.myproject.Util.GptPrompt;
import kr.co.myproject.dto.Horoscope.HoroscopeDto;
import kr.co.myproject.dto.Horoscope.HoroscopeSaveDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class HoroscopeService {
    private final OpenAiComponent openAiComponent;
    private final RedisTemplate<String, String> redis;  // 문자열 템플릿

    private String CHCHE_KEY = "horoscope";
    private static final Duration TTL = Duration.ofDays(1);      // 캐시 1일


    public HoroscopeDto getHoroscope(LocalDate date)
    {
        String key = CHCHE_KEY + date;           // 오늘 날짜 key

        String cached = redis.opsForValue().get(key);
        if (cached != null) {
            return new HoroscopeDto(cached);            // 바로 리턴
        }

        String prompt = GptPrompt.horoscopePrompt(date);

        JsonNode node = openAiComponent.resultJson(prompt);

        String horoscope = node.get("horoscope").asText();

        redis.opsForValue().set(key, horoscope, TTL);

        return new HoroscopeDto(horoscope);
    }
}
