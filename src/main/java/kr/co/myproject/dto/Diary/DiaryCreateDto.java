package kr.co.myproject.dto.Diary;

import kr.co.myproject.entity.Diary;
import kr.co.myproject.enums.Emotion;
import lombok.Getter;

import java.time.LocalDate;
import java.time.MonthDay;
import java.time.format.DateTimeFormatter;

@Getter
public class DiaryCreateDto {
    private Emotion emotion;
    private String title;
    private String content;
    private String dateStr;

    public Diary createDiary(String aiAnswer, Long userId)
    {
        MonthDay md = MonthDay.parse(dateStr,
                DateTimeFormatter.ofPattern("MMdd"));
        LocalDate diaryDate = md.atYear(LocalDate.now().getYear());

        return Diary.builder()
                .userId(userId)
                .emotion(emotion)
                .title(title)
                .content(content)
                .createdDate(diaryDate)
                .aiAnswer(aiAnswer)
                .build();
    }
}
