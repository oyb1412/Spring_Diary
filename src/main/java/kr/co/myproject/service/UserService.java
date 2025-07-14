package kr.co.myproject.service;

import kr.co.myproject.Mapper.UserMapper;
import kr.co.myproject.dto.User.UserDto;
import kr.co.myproject.dto.User.UserRegisterDto;
import kr.co.myproject.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public Map<String, Object> register(UserRegisterDto dto) {

        //아이디 중복체크
        if(userMapper.findByUsername(dto.getUserName()) != null)
        {
            return Map.of("success", false, "message", "이미 등록된 아이디입니다");
        }
        //1,2차 비밀번호 체크 필요
        if(!dto.getPassword().equals(dto.getConfirmPassword()))
        {
            return Map.of("success", false, "message", "확인용 비밀번호가 틀립니다");
        }

        String newPassword = passwordEncoder.encode(dto.getPassword());
        User user = dto.createUser(newPassword);

        userMapper.insert(user, LocalDate.now());
        return Map.of("success", true, "message", "회원가입 성공");
    }

    public UserDto findById(Long id)
    {
        User user = userMapper.findById(id);

        return new UserDto(user);
    }

    public UserDto findByUserName(String userName)
    {
        User user = userMapper.findByUsername(userName);

        return new UserDto(user);
    }
}
