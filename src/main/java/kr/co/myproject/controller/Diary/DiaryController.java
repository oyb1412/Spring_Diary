package kr.co.myproject.controller.Diary;

import kr.co.myproject.dto.Diary.DiaryCreateDto;
import kr.co.myproject.entity.Diary;
import kr.co.myproject.service.DiaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class DiaryController {
    private final DiaryService diaryService;

    @PostMapping("/api/diary/create")
    public Map<String, Object> createDiary(@RequestBody DiaryCreateDto dto) {
        return diaryService.create(dto);
    }
}
