package kr.co.myproject.dto.User;

import kr.co.myproject.entity.User;
import lombok.Getter;

@Getter
public class SessionUser {
    private Long id;
    private String realName;

    public SessionUser(User user)
    {
        this.id = user.getId();
        this.realName = user.getRealName();
    }
}
