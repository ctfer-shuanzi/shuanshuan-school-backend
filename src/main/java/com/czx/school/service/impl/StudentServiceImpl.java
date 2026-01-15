package com.czx.school.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.czx.school.DTO.PageDTO;
import com.czx.school.entity.Student;
import com.czx.school.DTO.ChangStudentMajorDTO;
import com.czx.school.DTO.ChangeStudentNameDTO;
import com.czx.school.mapper.StudentMapper;
import com.czx.school.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements StudentService {
    @Autowired
    private StudentMapper studentMapper;
    // StudentMapper中定义的带有@Select注解的方法
    @Override
    public Student selectStudentByNumber(String number) {
        return studentMapper.selectStudentByNumber(number);
    }
    // 调用wrapper和mapper方法的方法
    // 增
    @Override
    public boolean addStudent(Student student) {
        return studentMapper.insert(student) > 0;
    }
    // 删
    @Override
    public boolean deleteByName(String name) {
        LambdaQueryWrapper<Student> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Student::getName, name);
        return studentMapper.delete(queryWrapper) > 0;
    }
    // 查
    @Override
    public Student selectStudentByName(String name){
        LambdaQueryWrapper<Student> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Student::getName, name);
        return studentMapper.selectOne(queryWrapper);
    }
    @Override
    public List<Student> selectStudentsByMajor(String major){
        LambdaQueryWrapper<Student> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Student::getMajor, major);
        return studentMapper.selectList(queryWrapper);
    }
    // 改
    @Override
    public boolean changeName(ChangeStudentNameDTO changeStudentNameDTO){
        UpdateWrapper<Student> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("name",changeStudentNameDTO.getName());

        Student updateStudent = new Student();
        updateStudent.setName(changeStudentNameDTO.getNewName());

        return studentMapper.update(updateStudent, updateWrapper) > 0;
    }
    @Override
    public boolean changeMajor(ChangStudentMajorDTO changStudentMajorDTO){
        UpdateWrapper<Student> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("name", changStudentMajorDTO.getName());

        Student updateStudent = new Student();
        updateStudent.setMajor(changStudentMajorDTO.getMajor());

        return studentMapper.update(updateStudent, updateWrapper) > 0;
    }

    @Override
    public List<Student> getPages(PageDTO pageDTO) {
        IPage<Student> page = new Page<>(pageDTO.getCurrentPage(), pageDTO.getLimit());
        IPage<Student> studentIPage = studentMapper.selectPage(page,null);
        System.out.println(studentIPage.getPages());
        System.out.println(studentIPage.getTotal());
        return studentIPage.getRecords();
    }

    @Override
    public Integer getTotalRecordsNum() {
        return studentMapper.getTotalRecordsNum();
    }
}