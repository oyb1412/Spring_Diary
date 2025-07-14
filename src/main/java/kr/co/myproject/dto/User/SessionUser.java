package kr.co.myproject.dto.User;

import kr.co.myproject.entity.User;
import lombok.Getter;

@Getter
public class SessionUser {
    private String name;
    private String userName;

    public SessionUser(User user)
    {
        this.userName = user.getUserName();
        this.name = user.getName();
    }
}
