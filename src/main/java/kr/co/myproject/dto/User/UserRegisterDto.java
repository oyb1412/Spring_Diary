package kr.co.myproject.dto.User;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import kr.co.myproject.entity.User;
import lombok.Getter;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
public class UserRegisterDto {

    @NotBlank(message = "아이디는 필수 입력 사항입니다")
    @Size(min = 6, max = 20, message="아이디는 6자 이상 20자 이하로 지어주세요")
    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "아이디는 영문/숫자만 입력 가능합니다")
    private String userName;

    @NotBlank(message = "이름은 필수 입력 사항입니다")
    @Size(min = 1, max = 4, message="이름은 4자 이하로 지어주세요")
    @Pattern(regexp = "^[가-힣]+$", message = "이름은 한글만 입력 가능합니다")
    private String name;

    @NotBlank(message = "비밀번호는 필수 입력 사항입니다")
    @Size(min = 6, max = 20, message ="비밀번호는 6자 이상 20자 이하로 지어주세요")
    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "비밀번호는 영문/숫자만 입력 가능합니다")
    private String password;

    @NotBlank(message = "비밀번호는 필수 입력 사항입니다")
    @Size(min = 6, max = 20, message ="비밀번호는 6자 이상 20자 이하로 지어주세요")
    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "비밀번호는 영문/숫자만 입력 가능합니다")
    private String confirmPassword;

    @NotNull(message = "생년월일은 필수입니다")
    private LocalDate birthDate;

    public User createUser(String encodePassword)
    {
        return User.builder()
                .userName(userName)
                .password(encodePassword)
                .name(name)
                .birthDate(birthDate)
                .build();
    }
}
