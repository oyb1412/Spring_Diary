package kr.co.myproject.Mapper;

import kr.co.myproject.dto.User.UserRegisterDto;
import kr.co.myproject.entity.User;
import org.apache.ibatis.annotations.*;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface UserMapper {
    @Select("SELECT * FROM user WHERE username=#{userName}")
    public User findByUsername(@Param("userName") String userName);

    @Select("SELECT * FROM user WHERE id=#{id}")
    public User findById(@Param("id") Long id);

    @Select("SELECT * FROM user")
    List<User> findAll();

    @Insert("INSERT INTO user(username, password, name, birth_date, created_date) VALUES (#{user.userName}, #{user.password}, #{user.name}, #{user.birthDate}, #{createdDate})")
    public int insert(@Param("user") User user, @Param("createdDate")LocalDate createdDate);
}
