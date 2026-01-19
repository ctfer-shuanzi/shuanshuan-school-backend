package com.czx.school.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.czx.school.DTO.ChangeTeacherDTO;
import com.czx.school.DTO.PageDTO;
import com.czx.school.common.PageResponse;
import com.czx.school.entity.Teacher;
import com.czx.school.mapper.TeacherMapper;
import com.czx.school.service.TeacherService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherServiceImpl extends ServiceImpl<TeacherMapper, Teacher> implements TeacherService {
    @Autowired
    private TeacherMapper teacherMapper;
    // 增
    @Override
    public boolean add(Teacher teacher) {
        return teacherMapper.insert(teacher) > 0;
    }
    // 删
    @Override
    public boolean delete(String name) {
        return teacherMapper.delete(new LambdaQueryWrapper<Teacher>().eq(Teacher::getName,name)) > 0;
    }
    // 改
    @Override
    public boolean update(ChangeTeacherDTO changeTeacherDTO) {
        Teacher teacher = new Teacher();
        BeanUtils.copyProperties(changeTeacherDTO,teacher);
        teacher.setName(changeTeacherDTO.getNewName());

        return teacherMapper.update(teacher,new UpdateWrapper<Teacher>().eq("name",changeTeacherDTO.getOldName())) > 0;
    }
    // 查
    @Override
    public Teacher selectByName(String name) {
        return teacherMapper.selectOne(new LambdaQueryWrapper<Teacher>().eq(Teacher::getName,name));
    }
    @Override
    public List<Teacher> selectByDepartment(String department) {
        return teacherMapper.selectList(new LambdaQueryWrapper<Teacher>().eq(Teacher::getDepartment,department));
    }
    @Override
    public PageResponse<Teacher> getPages(PageDTO pageDTO) {
        IPage<Teacher> page = new Page<>(pageDTO.getCurrentPage(), pageDTO.getLimit());
        IPage<Teacher> result = teacherMapper.selectPage(page,null);

        List<Teacher> records = result.getRecords();

        PageResponse<Teacher> pageResponse = new PageResponse<>();
        pageResponse.setList(records);
        pageResponse.setTotal(result.getTotal());
        pageResponse.setCurrent(result.getCurrent());
        pageResponse.setSize(result.getSize());
        pageResponse.setPages(result.getPages());

        return pageResponse;
    }
}