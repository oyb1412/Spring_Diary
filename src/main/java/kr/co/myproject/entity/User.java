package kr.co.myproject.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
public class User extends BaseTimeEntity {
    private Long id;
    private String userName;
    private String password;
    private String realName;
    private LocalDateTime birthDate;

    @Builder
    public User(String userName, String password, String realName, LocalDateTime birthDate) {
        this.userName = userName;
        this.password = password;
        this.realName = realName;
        this.birthDate = birthDate;
        this.createdDate = LocalDateTime.now();
    }
}
