package com.czx.school.controller;

import com.czx.school.entity.SC;
import com.czx.school.entity.Student;
import com.czx.school.DTO.ChangStudentMajorDTO;
import com.czx.school.DTO.ChangeStudentNameDTO;
import com.czx.school.VO.Response;
import com.czx.school.error.ErrorCode;
import com.czx.school.service.SCService;
import com.czx.school.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {
    // 相当于new了一个实例
    @Autowired
    private StudentService studentService;
    @Autowired
    private SCService scService;
    // 增
    @PostMapping("/add")
    public Response<String> addStudent(Student student) {
        Student student1 = studentService.selectStudentByName(student.getName());
        if (student1 != null)
        {
            return Response.fail(ErrorCode.STUDENT_ALREADY_EXIST.getCode(), ErrorCode.STUDENT_ALREADY_EXIST.getMsg());
        }else
        {
            return studentService.addStudent(student)
                    ? Response.success("添加成功")
                    : Response.fail(ErrorCode.STUDENT_NOT_EXIST.getCode(), ErrorCode.STUDENT_NOT_EXIST.getMsg());
        }
    }
    // 删
    @PostMapping("/delete")
    public Response<String> deleteStudent(String name) {
        Student student = studentService.selectStudentByName(name);
        if(student == null){
            return Response.fail(400, "该学生不存在");
        }else{
            List<SC> scList = scService.selectBySno(student.getNumber());
            if(scList.isEmpty()){
                return studentService.deleteByName(name) ? Response.success("删除成功") : Response.fail(400, "删除失败");
            }
            return scService.deleteBySno(student.getNumber()) && studentService.deleteByName(name) ? Response.success("删除成功") : Response.fail(400, "删除失败");
        }
    }
    // 查
    @PostMapping("/select/major")
    public Response<List<String>> selectNamesByMajor(String major) {
        List<Student> studentList = studentService.selectStudentsByMajor(major);
        if (studentList.isEmpty())
        {
            return Response.fail(400, "该专业没有学生");
        }else
        {
            List<String> nameList = studentList.stream().map(Student::getName).toList();
            return Response.success("查询成功", nameList);
        }
    }
    // 改
    @PostMapping("change/name")
    public Response<String> changeName(ChangeStudentNameDTO changeStudentNameDTO){
        if (studentService.selectStudentByName(changeStudentNameDTO.getName()) == null)
        {
            return Response.fail(400, "该学生不存在");
        }else{
            return studentService.changeName(changeStudentNameDTO) ? Response.success("修改成功") : Response.fail(400, "修改失败");
        }
    }
    @PostMapping("change/major")
    public Response<String> changeMajor(ChangStudentMajorDTO changStudentMajorDTO){
        if (studentService.selectStudentByName(changStudentMajorDTO.getName()) == null)
        {
            return Response.fail(400, "该学生不存在");
        }else{
            return studentService.changeMajor(changStudentMajorDTO) ? Response.success("修改成功") : Response.fail(400, "修改失败");
        }
    }
}
