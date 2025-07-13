package kr.co.myproject.controller.User;

import kr.co.myproject.dto.User.UserRegisterDto;
import kr.co.myproject.entity.User;
import kr.co.myproject.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/api/user/register")
    public Map<String, Object> register(@RequestBody UserRegisterDto dto) {
        return userService.register(dto);
    }
}
