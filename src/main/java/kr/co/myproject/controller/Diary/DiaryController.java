package kr.co.myproject.controller.Diary;

import jakarta.servlet.http.HttpSession;
import kr.co.myproject.dto.Diary.DiaryCreateDto;
import kr.co.myproject.dto.User.SessionUser;
import kr.co.myproject.entity.Diary;
import kr.co.myproject.service.DiaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.MonthDay;
import java.time.format.DateTimeFormatter;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class DiaryController {
    private final DiaryService diaryService;

    @PostMapping("/api/diary/create")
    public Map<String, Object> createDiary(@RequestBody DiaryCreateDto dto,
                                           HttpSession session)
    {
        SessionUser sessionUser = (SessionUser) session.getAttribute("user");
        return diaryService.create(dto, sessionUser);
    }

    @GetMapping("/api/diary/check/{dateStr}")
    public Map<String, Object> checkDiary(@PathVariable("dateStr") String dateStr,
                                          HttpSession session)
    {
        MonthDay md = MonthDay.parse(dateStr,
                DateTimeFormatter.ofPattern("MMdd"));

        LocalDate date = md.atYear(LocalDate.now().getYear());

        SessionUser sessionUser = (SessionUser) session.getAttribute("user");

        return diaryService.check(date, sessionUser);
    }
}
