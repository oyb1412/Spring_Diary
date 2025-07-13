package kr.co.myproject.dto.Diary;

import kr.co.myproject.entity.Diary;
import kr.co.myproject.enums.Emotion;
import lombok.Getter;

@Getter
public class DiaryCreateDto {
    private Long userId;
    private Emotion emotion;
    private String title;
    private String content;

    public Diary createDiary(String aiAnswer)
    {
        return Diary.builder()
                .userId(userId)
                .emotion(emotion)
                .title(title)
                .content(content)
                .aiAnswer(aiAnswer)
                .build();
    }
}
