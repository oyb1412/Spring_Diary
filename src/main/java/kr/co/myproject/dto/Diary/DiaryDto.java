package kr.co.myproject.dto.Diary;

import kr.co.myproject.entity.Diary;
import kr.co.myproject.enums.Emotion;
import lombok.Getter;

@Getter
public class DiaryDto {
    private Emotion emotion;
    private String title;
    private String content;
    private String aiAnswer;

    private boolean happy;
    private boolean sad;
    private boolean angry;
    private boolean tried;
    private boolean neutral;

    public DiaryDto(Diary diary)
    {
        this.emotion = diary.getEmotion();
        this.title = diary.getTitle();
        this.content = diary.getContent();
        this.aiAnswer = diary.getAiAnswer();

        switch (this.emotion)
        {
            case HAPPY:
                happy = true;
                break;
            case SAD:
                sad = true;
                break;
            case ANGRY:
                angry = true;
                break;
            case TRIED:
                tried = true;
                break;
            case NEUTRAL:
                neutral = true;
                break;
        }
    }
}
