package kr.co.myproject.controller.Page;

import jakarta.servlet.http.HttpSession;
import kr.co.myproject.dto.Diary.DiaryDto;
import kr.co.myproject.dto.Famous.FamousDto;
import kr.co.myproject.dto.Famous.FamousSaveDto;
import kr.co.myproject.dto.Horoscope.HoroscopeDto;
import kr.co.myproject.dto.User.SessionUser;
import kr.co.myproject.dto.User.UserDto;
import kr.co.myproject.entity.User;
import kr.co.myproject.service.DiaryService;
import kr.co.myproject.service.FamousService;
import kr.co.myproject.service.HoroscopeService;
import kr.co.myproject.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.MonthDay;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class PageController {
    private final UserService userService;
    private final DiaryService diaryService;
    private final FamousService famousService;
    private final HoroscopeService horoscopeService;

    @GetMapping("/")
    public String index()
    {
        return "other/index";
    }

    @GetMapping("/main-page")
    public String mainPage(HttpSession session,
                           Model model)
    {
        SessionUser sessionUser = (SessionUser)session.getAttribute("user");
        UserDto dto = userService.findByUserName(sessionUser.getUserName());
        LocalDate date = dto.getBirthDate();
        FamousDto famousDto = famousService.getRandomFamous();
        HoroscopeDto horoscopeDto = horoscopeService.getHoroscope(date);
        model.addAttribute("famous", famousDto);
        model.addAttribute("horoscope", horoscopeDto);
        return "other/main";
    }

    @GetMapping("/register-page")
    public String registerPage()
    {
        return "user/registerPage";
    }

    @GetMapping("/diaryCreate-page")
    public String diaryCreatePage()
    {
        return "diary/diaryCreatePage";
    }

    @GetMapping("/diary-page/{id}")
    public String diaryPage(@PathVariable Long id)
    {
        return "diary/diaryPage";
    }

    @GetMapping("/diary/{dateStr}")
    public String diaryCheck(@PathVariable("dateStr") String dateStr,
                             HttpSession session,
                             Model model)
    {
        MonthDay md = MonthDay.parse(dateStr,
                DateTimeFormatter.ofPattern("MMdd"));

        LocalDate diaryDate = md.atYear(LocalDate.now().getYear());


        //현재 유저가 해당 날짜에 작성한 글이 있나 체크
        SessionUser sessionUser = (SessionUser)session.getAttribute("user");
        UserDto dto = userService.findByUserName(sessionUser.getUserName());

        //작성한 글이 있다면, 글 확인 페이지로
        //다이어리 서비스에서 유저id랑 선택 날짜로
        List<DiaryDto> diary = diaryService.findByUserIdAndDate(dto.getId(), diaryDate);

        model.addAttribute("month", diaryDate.getMonthValue());
        model.addAttribute("day", diaryDate.getDayOfMonth());

        //작성한 글이 없다면, 글 작성 페이지로
        if(diary == null)
        {
            model.addAttribute("dateStr", dateStr);
            return "diary/diaryCreatePage";
        }
        //작성한 글이 있다면, 글 확인 페이지로 ( [0] )
        else
        {
            DiaryDto diaryDto = diary.get(0);
            model.addAttribute("diary", diaryDto);
            return "diary/diaryPage";
        }

    }
}
