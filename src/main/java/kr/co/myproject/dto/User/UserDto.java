package kr.co.myproject.dto.User;

import kr.co.myproject.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
public class UserDto {
    private Long id;
    private String name;
    private LocalDate birthDate;

    public UserDto(User user)
    {
        this.id = user.getId();
        this.name = user.getName();
        this.birthDate = user.getBirthDate();
    }
}
