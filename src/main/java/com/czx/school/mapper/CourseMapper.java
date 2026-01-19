package com.czx.school.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.czx.school.entity.Course;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface CourseMapper extends BaseMapper<Course> {
    @Select("select * from course where id = #{cid}")
    Course selectByCid(String cid);
}