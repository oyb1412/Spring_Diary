package kr.co.myproject.dto.Horoscope;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class HoroscopeSaveDto {
    private Long userId;
    private LocalDateTime targetDate;
    private String content;
}
