package com.czx.school.controller;

import com.czx.school.DTO.ChangeTeacherDTO;
import com.czx.school.DTO.PageDTO;
import com.czx.school.common.ErrorCode;
import com.czx.school.common.PageResponse;
import com.czx.school.common.Response;
import com.czx.school.entity.Teacher;
import com.czx.school.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teacher")
public class TeacherController {
    @Autowired
    private TeacherService teacherService;
    // 增
    @PostMapping("/add")
    public Response<String> add(@RequestBody Teacher teacher){
        if(teacherService.selectByName(teacher.getName()) != null){
            return Response.fail(ErrorCode.TEACHER_ALREADY_EXIST.getCode(),ErrorCode.TEACHER_ALREADY_EXIST.getMsg());
        }
        return teacherService.add(teacher)
                ? Response.success("添加成功",null)
                : Response.fail(ErrorCode.TEACHER_ADD_FAILURE.getCode(),ErrorCode.TEACHER_ADD_FAILURE.getMsg());
    }
    // 删
    @PostMapping("/delete")
    public Response<String> delete(@RequestParam String name){
        if(teacherService.selectByName(name) == null){
            return Response.fail(ErrorCode.TEACHER_NOT_EXIST.getCode(),ErrorCode.TEACHER_NOT_EXIST.getMsg());
        }
        return teacherService.delete(name)
                ? Response.success("删除成功",null)
                : Response.fail(ErrorCode.TEACHER_DELETE_FAILURE.getCode(),ErrorCode.TEACHER_DELETE_FAILURE.getMsg());
    }
    // 改
    @PostMapping("/update")
    public Response<String> update(@RequestBody ChangeTeacherDTO changeTeacherDTO){
        if(teacherService.selectByName(changeTeacherDTO.getOldName()) == null){
            return Response.fail(ErrorCode.TEACHER_NOT_EXIST.getCode(),ErrorCode.TEACHER_NOT_EXIST.getMsg());
        }
        return teacherService.update(changeTeacherDTO)
                ? Response.success("修改成功",null)
                : Response.fail(ErrorCode.TEACHER_UPDATE_FAILURE.getCode(),ErrorCode.TEACHER_UPDATE_FAILURE.getMsg());
    }
    // 查
    @PostMapping("/select/department")
    public Response<List<Teacher>> selectByDepartment(@RequestParam String department){
        List<Teacher> list = teacherService.selectByDepartment(department);

        if(list.isEmpty()){
            return Response.fail(ErrorCode.TEACHER_QUERY_BY_DEPARTMENT_FAILURE.getCode(),
                    ErrorCode.TEACHER_QUERY_BY_DEPARTMENT_FAILURE.getMsg());
        }
        return Response.success("查询成功",list);
    }
    @PostMapping("/page")
    public Response<PageResponse<Teacher>> getPages(@RequestBody PageDTO pageDTO){
        PageResponse<Teacher> records = teacherService.getPages(pageDTO);

        if(records.getList().isEmpty()){
            return Response.fail(ErrorCode.PAGE_EMPTY.getCode(),ErrorCode.PAGE_EMPTY.getMsg());
        }

        return Response.success("分页查询成功",records);
    }
}
