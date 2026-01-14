package com.czx.school.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.czx.school.entity.Course;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface CourseMapper extends BaseMapper<Course> {
}