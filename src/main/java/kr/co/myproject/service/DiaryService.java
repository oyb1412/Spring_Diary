package kr.co.myproject.service;

import com.fasterxml.jackson.databind.JsonNode;
import kr.co.myproject.Component.OpenAiComponent;
import kr.co.myproject.Mapper.DiaryMapper;
import kr.co.myproject.Mapper.UserMapper;
import kr.co.myproject.Util.GptPrompt;
import kr.co.myproject.dto.Diary.DiaryCreateDto;
import kr.co.myproject.dto.Diary.DiaryDto;
import kr.co.myproject.dto.User.SessionUser;
import kr.co.myproject.entity.Diary;
import kr.co.myproject.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DiaryService {
    private final DiaryMapper diaryMapper;
    private final UserMapper userMapper;
    private final OpenAiComponent openAiComponent;

    @Transactional
    public Map<String, Object> create(DiaryCreateDto dto, SessionUser sessionUser)
    {
        User user = userMapper.findByUsername(sessionUser.getUserName());
        String prompt = GptPrompt.diaryAnswerPrompt(dto.getContent());
        JsonNode node = openAiComponent.resultJson(prompt);

        String aiAnswer = node.get("aiAnswer").asText();
        Diary diary = dto.createDiary(aiAnswer, user.getId());
        diaryMapper.insert(diary);
        return Map.of("success", true, "message", aiAnswer);
    }

    public List<DiaryDto> findByUserIdAndDate(Long userId, LocalDate date)
    {
        List<Diary> diarys = diaryMapper.findByUserIdAndCreatedDate(userId, date);

        if(diarys.isEmpty())
        {
            return null;
        }

        return diarys.stream()
                .map(DiaryDto::new)
                .collect(Collectors.toList());
    }
}
