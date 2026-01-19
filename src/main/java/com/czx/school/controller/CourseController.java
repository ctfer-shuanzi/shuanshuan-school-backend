package com.czx.school.controller;

import com.czx.school.DTO.ChangeCourseDTO;
import com.czx.school.DTO.PageDTO;
import com.czx.school.common.ErrorCode;
import com.czx.school.common.PageResponse;
import com.czx.school.common.Response;
import com.czx.school.entity.Choose;
import com.czx.school.entity.Course;
import com.czx.school.service.CourseService;
import com.czx.school.service.ChooseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/course")
public class CourseController {
    @Autowired
    private CourseService courseService;
    @Autowired
    private ChooseService chooseService;
    // 增
    @PostMapping("/add")
    public Response<String> add(@RequestBody Course course){
        if(courseService.selectByName(course.getName()) != null){
            return Response.fail(ErrorCode.COURSE_ALREADY_EXIST.getCode(), ErrorCode.COURSE_ALREADY_EXIST.getMsg());
        }
        return courseService.add(course)
                ? Response.success("添加成功",null)
                : Response.fail(ErrorCode.COURSE_ADD_FAILURE.getCode(), ErrorCode.COURSE_ADD_FAILURE.getMsg());
    }
    // 删
    @PostMapping("/delete")
    public Response<String> delete(@RequestParam String name){
        Course course = courseService.selectByName(name);
        if(course == null){
            return Response.fail(ErrorCode.COURSE_NOT_EXIST.getCode(), ErrorCode.COURSE_NOT_EXIST.getMsg());
        }

        List<Choose> list = chooseService.selectByCid(course.getId());
        if(list.isEmpty()){
            return courseService.delete(name)
                    ? Response.success("课程非级联式删除成功",null)
                    : Response.fail(ErrorCode.COURSE_DELETE_FAILURE.getCode(), ErrorCode.COURSE_DELETE_FAILURE.getMsg());
        }

        return chooseService.deleteByCid(course.getId()) && courseService.delete(name)
                ? Response.success("课程级联式删除成功",null)
                : Response.fail(ErrorCode.COURSE_DELETE_FAILURE.getCode(), ErrorCode.COURSE_DELETE_FAILURE.getMsg());
    }
    // 改
    @PostMapping("/update")
    public Response<String> update(@RequestBody ChangeCourseDTO changeCourseDTO){
        if(courseService.selectByName(changeCourseDTO.getOldName()) == null){
            return Response.fail(ErrorCode.COURSE_NOT_EXIST.getCode(), ErrorCode.COURSE_NOT_EXIST.getMsg());
        }

        return courseService.update(changeCourseDTO)
                ? Response.success("课程修改成功",null)
                : Response.fail(ErrorCode.COURSE_UPDATE_FAILURE.getCode(), ErrorCode.COURSE_UPDATE_FAILURE.getMsg());
    }
    // 查
    @PostMapping("/select/teacher")
    public Response<List<Course>> selectByTeacher(@RequestParam String teacher){
        List<Course> list = courseService.selectByTeacher(teacher);

        return !list.isEmpty()
                ? Response.success("课程查询成功",list)
                : Response.fail(ErrorCode.COURSE_QUERY_BY_TEACHER_FAILURE.getCode(), ErrorCode.COURSE_QUERY_BY_TEACHER_FAILURE.getMsg());
    }
    @PostMapping("/page")
    public Response<PageResponse<Course>> getPage(@RequestBody PageDTO pageDTO){
        PageResponse<Course> records = courseService.getPages(pageDTO);

        return !records.getList().isEmpty()
                ? Response.success("课程分页查询成功",records)
                : Response.fail(ErrorCode.PAGE_EMPTY.getCode(), ErrorCode.PAGE_EMPTY.getMsg());
    }
}