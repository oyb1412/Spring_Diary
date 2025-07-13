package kr.co.myproject.Mapper;

import kr.co.myproject.entity.Diary;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface DiaryMapper {
    @Select("SELECT * FROM diary WHERE id =#{id}")
    public Diary findById(@Param("id") Long id);

    @Select("SELECT * FROM diary WHERE user_id =#{userId}")
    public Diary findByUserId(@Param("id") Long userId);

    @Insert("INSERT INTO diary(user_id, emotion, title, content, ai_answer) VALUES(#{diary.userId}, #{diary.emotion}, #{diary.title}, #{diary.content}, #{diary.aiAnswer})")
    public int insert(Diary diary);
}
