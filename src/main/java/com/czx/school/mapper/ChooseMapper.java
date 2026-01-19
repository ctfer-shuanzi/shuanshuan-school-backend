package com.czx.school.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.czx.school.entity.Choose;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface ChooseMapper extends BaseMapper<Choose> {
    @Delete("delete from sc where id = #{id}")
    boolean deleteById(String id);
}