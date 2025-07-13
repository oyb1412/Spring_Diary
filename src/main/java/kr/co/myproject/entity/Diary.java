package kr.co.myproject.entity;

import kr.co.myproject.enums.Emotion;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
public class Diary extends BaseTimeEntity {
    private Long id;
    private Long userId;
    private Emotion emotion;
    private String title;
    private String content;
    private String aiAnswer;

    @Builder
    public Diary(Long userId, Emotion emotion, String title, String content, String aiAnswer) {
        this.emotion = emotion;
        this.title = title;
        this.content = content;
        this.aiAnswer = aiAnswer;
        this.createdDate = LocalDateTime.now();
    }
}
