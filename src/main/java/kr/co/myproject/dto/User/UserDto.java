package kr.co.myproject.dto.User;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
public class UserDto {
    private String userName;
    private String realName;
    private LocalDateTime birthDate;
}
