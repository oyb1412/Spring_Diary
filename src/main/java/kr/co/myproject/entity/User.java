package kr.co.myproject.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
public class User extends BaseTimeEntity {
    private Long id;
    private String userName;
    private String password;
    private String name;
    private LocalDate birthDate;

    @Builder
    public User(String userName, String password, String name, LocalDate birthDate) {
        this.userName = userName;
        this.password = password;
        this.name = name;
        this.birthDate = birthDate;
        this.createdDate = LocalDate.now();
    }
}
