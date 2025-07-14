package kr.co.myproject.Mapper;

import kr.co.myproject.entity.Diary;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface DiaryMapper {
    @Select("SELECT * FROM diary WHERE id =#{id}")
    public Diary findById(@Param("id") Long id);

    @Select("SELECT * FROM diary WHERE user_id =#{userId}")
    public List<Diary> findByUserId(@Param("userId") Long userId);

    @Insert("INSERT INTO diary(user_id, emotion, title, content, ai_answer, created_date) VALUES(#{diary.userId}, #{diary.emotion}, #{diary.title}, #{diary.content}, #{diary.aiAnswer}, #{diary.createdDate})")
    public int insert(@Param("diary")Diary diary);

    @Select("SELECT * FROM diary WHERE user_id =#{userId} AND created_date =#{date}")
    public List<Diary> findByUserIdAndCreatedDate(@Param("userId") Long userId, @Param("date") LocalDate date);
}
