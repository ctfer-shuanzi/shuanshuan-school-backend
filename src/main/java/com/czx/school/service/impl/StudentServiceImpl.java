package com.czx.school.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.czx.school.DTO.ChangeStudentDTO;
import com.czx.school.DTO.PageDTO;
import com.czx.school.common.PageResponse;
import com.czx.school.entity.Student;
import com.czx.school.mapper.StudentMapper;
import com.czx.school.service.StudentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements StudentService {
    @Autowired
    private StudentMapper studentMapper;
    // 增
    @Override
    public boolean add(Student student) {
        return studentMapper.insert(student) > 0;
    }
    // 删
    @Override
    public boolean delete(String name) {
        return studentMapper.delete(new LambdaQueryWrapper<Student>().eq(Student::getName, name)) > 0;
    }
    // 改
    @Override
    public boolean update(ChangeStudentDTO changeStudentDTO) {
        Student student = new Student();
        BeanUtils.copyProperties(changeStudentDTO,student);
        student.setName(changeStudentDTO.getNewName());

        return studentMapper.update(student,new UpdateWrapper<Student>().eq("name",changeStudentDTO.getOldName())) > 0;
    }
    // 查
    @Override
    public Student selectByNumber(String number) {
        return studentMapper.selectByNumber(number);
    }
    @Override
    public Student selectByName(String name){
        return studentMapper.selectOne(new LambdaQueryWrapper<Student>().eq(Student::getName, name));
    }
    @Override
    public List<Student> selectByMajor(String major){
        return studentMapper.selectList(new LambdaQueryWrapper<Student>().eq(Student::getMajor, major));
    }

    @Override
    public PageResponse<Student> getPages(PageDTO pageDTO) {
        IPage<Student> page = new Page<>(pageDTO.getCurrentPage(), pageDTO.getLimit());
        IPage<Student> result = studentMapper.selectPage(page,null);

        List<Student> records = result.getRecords();

        PageResponse<Student> pageResponse = new PageResponse<>();
        pageResponse.setList(records);
        pageResponse.setTotal(result.getTotal());
        pageResponse.setCurrent(result.getCurrent());
        pageResponse.setSize(result.getSize());
        pageResponse.setPages(result.getPages());

        return pageResponse;
    }
}