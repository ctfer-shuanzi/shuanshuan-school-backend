package com.czx.school.controller;

import com.czx.school.DTO.*;
import com.czx.school.common.PageResponse;
import com.czx.school.entity.Choose;
import com.czx.school.entity.Student;
import com.czx.school.common.Response;
import com.czx.school.common.ErrorCode;
import com.czx.school.service.ChooseService;
import com.czx.school.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired// 相当于new了一个实例
    private StudentService studentService;
    @Autowired
    private ChooseService chooseService;
    // 增
    @PostMapping("/add")
    public Response<String> addStudent(@RequestBody Student student) {
        if (studentService.selectByName(student.getName()) != null) {
            return Response.fail(ErrorCode.STUDENT_ALREADY_EXIST.getCode(), ErrorCode.STUDENT_ALREADY_EXIST.getMsg());
        }
        return studentService.add(student)
                ? Response.success("学生添加成功",null)
                : Response.fail(ErrorCode.STUDENT_ADD_FAILURE.getCode(), ErrorCode.STUDENT_ADD_FAILURE.getMsg());
    }
    // 删
    @PostMapping("/delete")
    public Response<String> deleteStudent(@RequestParam String name) {
        Student student = studentService.selectByName(name);

        if(student == null){
            return Response.fail(ErrorCode.STUDENT_NOT_EXIST.getCode(), ErrorCode.STUDENT_NOT_EXIST.getMsg());
        }

        List<Choose> chooseList = chooseService.selectBySno(student.getNumber());
        if(chooseList.isEmpty()){
            return studentService.delete(name)
                    ? Response.success("学生非级联式删除成功",null)
                    : Response.fail(ErrorCode.STUDENT_DELETE_FAILURE.getCode(), ErrorCode.STUDENT_DELETE_FAILURE.getMsg());
        }

        return chooseService.deleteBySno(student.getNumber()) && studentService.delete(name)
                ? Response.success("学生级联式删除成功",null)
                : Response.fail(ErrorCode.STUDENT_DELETE_FAILURE.getCode(), ErrorCode.STUDENT_DELETE_FAILURE.getMsg());
    }
    // 改
    @PostMapping("/update")
    public Response<String> update(@RequestBody ChangeStudentDTO changeStudentDTO){
        if(studentService.selectByName(changeStudentDTO.getOldName()) == null){
            return Response.fail(ErrorCode.STUDENT_NOT_EXIST.getCode(),ErrorCode.STUDENT_NOT_EXIST.getMsg());
        }

        return studentService.update(changeStudentDTO)
                ? Response.success("学生修改成功",null)
                : Response.fail(ErrorCode.STUDENT_UPDATE_FAILURE.getCode(),ErrorCode.STUDENT_UPDATE_FAILURE.getMsg());
    }
    // 查
    @PostMapping("/select/major")
    public Response<List<Student>> selectByMajor(@RequestParam String major) {
        List<Student> list = studentService.selectByMajor(major);

        return !list.isEmpty()
                ? Response.success("学生查询成功", list)
                : Response.fail(ErrorCode.STUDENT_QUERY_BY_MAJOR_FAILURE.getCode(), ErrorCode.STUDENT_QUERY_BY_MAJOR_FAILURE.getMsg());
    }
    @PostMapping("/page")
    public Response<PageResponse<Student>> getPages(@RequestBody PageDTO pageDTO){
        PageResponse<Student> records = studentService.getPages(pageDTO);

        return !records.getList().isEmpty()
                ? Response.success("学生分页查询成功",records)
                : Response.fail(ErrorCode.PAGE_EMPTY.getCode(),ErrorCode.PAGE_EMPTY.getMsg());
    }
}