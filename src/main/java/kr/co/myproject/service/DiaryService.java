package kr.co.myproject.service;

import com.fasterxml.jackson.databind.JsonNode;
import kr.co.myproject.Component.OpenAiComponent;
import kr.co.myproject.Mapper.DiaryMapper;
import kr.co.myproject.Util.GptPrompt;
import kr.co.myproject.dto.Diary.DiaryCreateDto;
import kr.co.myproject.entity.Diary;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class DiaryService {
    private final DiaryMapper diaryMapper;
    private final OpenAiComponent openAiComponent;

    @Transactional
    public Map<String, Object> create(DiaryCreateDto dto)
    {
        String prompt = GptPrompt.diaryAnswerPrompt(dto.getContent());
        JsonNode node = openAiComponent.resultJson(prompt);

        String aiAnswer = node.get("aiAnswer").asText();
        Diary diary = dto.createDiary(aiAnswer);
        diaryMapper.insert(diary);
        return Map.of("success", true, "message", "일기 작성 성공");
    }
}
