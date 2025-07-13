package kr.co.myproject.dto.Horoscope;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class HoroscopeDto {
    private String content;

    public HoroscopeDto(String content) {
        this.content = content;
    }
}
