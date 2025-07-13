package kr.co.myproject.Util;

import kr.co.myproject.dto.Famous.FamousSaveDto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class GptPrompt {
    public static String koreanPrompt(FamousSaveDto dto)
    {
        return String.format("""
            다음 영어 명언과 저자를 자연스러운 한국어로 번역해서
            JSON 형식으로 응답해줘. 키는 famous, author.

            명언: "%s"
            저자: %s
            """, dto.getFamousEn(), dto.getAuthor());
    }

    public static String horoscopePrompt(LocalDate date)
    {
        return String.format("""
                다음 날짜를 보고 오늘의 운세를 말해줘.
                정확하지 않아도 괜찮아.
                JSON 형식으로 응답해줘. 키는 horoscope.
                날짜 : "%s"
                """, date);
    }

    public static String diaryAnswerPrompt(String diary)
    {
        return String.format("""
                다음 일기를 보고 위로나 격려의 말을 해줘.
                길이는 최소 30자, 최대 50자.
                JSON 형식으로 응답해줘. 키는 aiAnswer.
                일기 : "%s"
                """, diary);
    }
}
