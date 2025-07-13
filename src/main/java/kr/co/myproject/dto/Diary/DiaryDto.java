package kr.co.myproject.dto.Diary;

import kr.co.myproject.enums.Emotion;
import lombok.Getter;

@Getter
public class DiaryDto {
    private Emotion emotion;
    private String title;
    private String content;
    private String aiAnswer;
}
