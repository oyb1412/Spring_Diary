package kr.co.myproject.dto.User;

import kr.co.myproject.entity.User;
import lombok.Getter;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;

@Getter
public class UserRegisterDto {
    private String userName;
    private String password;
    private String realName;
    private LocalDateTime birthDate;

    public User createUser(String encodePassword)
    {
        return User.builder()
                .userName(userName)
                .password(encodePassword)
                .realName(realName)
                .birthDate(birthDate)
                .build();
    }
}
