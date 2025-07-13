package kr.co.myproject.service;

import kr.co.myproject.Mapper.UserMapper;
import kr.co.myproject.dto.User.UserRegisterDto;
import kr.co.myproject.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public Map<String, Object> register(UserRegisterDto dto) {
        String newPassword = passwordEncoder.encode(dto.getPassword());
        User user = dto.createUser(newPassword);

        userMapper.insert(user);
        return Map.of("success", true, "message", "회원가입 성공");
    }
}
