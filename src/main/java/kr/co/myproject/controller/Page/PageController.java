package kr.co.myproject.controller.Page;

import kr.co.myproject.dto.Famous.FamousDto;
import kr.co.myproject.dto.Famous.FamousSaveDto;
import kr.co.myproject.dto.Horoscope.HoroscopeDto;
import kr.co.myproject.dto.User.SessionUser;
import kr.co.myproject.service.FamousService;
import kr.co.myproject.service.HoroscopeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.time.LocalDateTime;

@Controller
@RequiredArgsConstructor
public class PageController {
    private final FamousService famousService;
    private final HoroscopeService horoscopeService;

    @GetMapping("/")
    public String index(@SessionAttribute("user") SessionUser user,
                        Model model)
    {
        if(user != null)
        {

        }
        FamousDto famousDto = famousService.getRandomFamous();
        LocalDateTime date = LocalDateTime.now();
        HoroscopeDto horoscopeDto = horoscopeService.getHoroscope(date);
        model.addAttribute("famous", famousDto);
        model.addAttribute("horoscope", horoscopeDto);
        return "other/index";
    }

    @GetMapping("/login-page")
    public String loginPage()
    {
        return "user/loginPage";
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

    @GetMapping("/diaryList-page")
    public String diaryListPage()
    {
        return "diary/diaryListPage";
    }

    @GetMapping("/diary-page/{id}")
    public String diaryPage(@PathVariable Long id)
    {
        return "diary/diaryPage";
    }
}
