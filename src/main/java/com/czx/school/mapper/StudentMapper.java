package com.czx.school.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.czx.school.entity.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface StudentMapper extends BaseMapper<Student> {
    // 继承 BaseMapper<Student> 后，直接拥有 CRUD 方法（如 selectById, insert, update 等）
    // 自定义查询方法
    @Select("select * from Student where number = #{number}")
    Student selectByNumber(@Param("number") String number);
}
