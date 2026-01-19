package com.czx.school.controller;

import com.czx.school.DTO.ChangeChooseDTO;
import com.czx.school.DTO.PageDTO;
import com.czx.school.common.ErrorCode;
import com.czx.school.common.PageResponse;
import com.czx.school.common.Response;
import com.czx.school.entity.Choose;
import com.czx.school.entity.Student;
import com.czx.school.service.ChooseService;
import com.czx.school.service.CourseService;
import com.czx.school.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/choose")
public class ChooseController {
    @Autowired
    private ChooseService chooseService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private CourseService courseService;
    // 增
    @PostMapping("/add")
    public Response<String> add(@RequestBody Choose choose){
        if(chooseService.selectBySno(choose.getSno()) == null){
            return Response.fail(ErrorCode.STUDENT_NOT_EXIST.getCode(), ErrorCode.STUDENT_NOT_EXIST.getMsg());
        }
        if(chooseService.selectByCid(choose.getCid()) == null){
            return Response.fail(ErrorCode.COURSE_NOT_EXIST.getCode(), ErrorCode.COURSE_NOT_EXIST.getMsg());
        }

        if(chooseService.selectById(choose.getId()) != null){
            return Response.fail(ErrorCode.CHOOSE_ALREADY_EXIST.getCode(), ErrorCode.CHOOSE_ALREADY_EXIST.getMsg());
        }

        return chooseService.add(choose)
                ? Response.success("选课成功",null)
                : Response.fail(ErrorCode.CHOOSE_ADD_FAILURE.getCode(), ErrorCode.CHOOSE_ADD_FAILURE.getMsg());
    }
    // 删
    @PostMapping("/delete")
    public Response<String> delete(@RequestParam String id){
        if(chooseService.selectById(id) == null){
            return Response.fail(ErrorCode.CHOOSE_NOT_EXIST.getCode(), ErrorCode.CHOOSE_NOT_EXIST.getMsg());
        }

        return chooseService.deleteById(id)
                ? Response.success("退课成功",null)
                : Response.fail(ErrorCode.CHOOSE_DELETE_FAILURE.getCode(), ErrorCode.CHOOSE_DELETE_FAILURE.getMsg());
    }
    // 改
    @PostMapping("/update")
    public Response<String> update(@RequestBody ChangeChooseDTO changeChooseDTO){
        if(chooseService.selectById(changeChooseDTO.getId()) == null){
            return Response.fail(ErrorCode.CHOOSE_NOT_EXIST.getCode(), ErrorCode.CHOOSE_NOT_EXIST.getMsg());
        }

        if(studentService.selectByNumber(changeChooseDTO.getSno()) == null){
            return Response.fail(ErrorCode.STUDENT_NOT_EXIST.getCode(), ErrorCode.STUDENT_NOT_EXIST.getMsg());
        }

        if(courseService.selectByCid(changeChooseDTO.getCid()) == null){
            return Response.fail(ErrorCode.COURSE_NOT_EXIST.getCode(), ErrorCode.COURSE_NOT_EXIST.getMsg());
        }

        return chooseService.update(changeChooseDTO)
                ? Response.success("选课信息修改成功",null)
                : Response.fail(ErrorCode.COURSE_UPDATE_FAILURE.getCode(), ErrorCode.CHOOSE_UPDATE_FAILURE.getMsg());
    }
    // 查
    @PostMapping("/select/student")
    public Response<List<Choose>> selectByStudent(@RequestParam String name){
        List<Choose> list = chooseService.selectBySno(studentService.selectByName(name).getNumber());

        return !list.isEmpty()
                ? Response.success("选课查询成功",list)
                : Response.fail(ErrorCode.CHOOSE_QUERY_BY_STUDENT_FAILURE.getCode(), ErrorCode.CHOOSE_QUERY_BY_STUDENT_FAILURE.getMsg());
    }
    @PostMapping("/select/course")
    public Response<List<Choose>> selectByCourse(@RequestParam String name){
        List<Choose> list = chooseService.selectByCid(courseService.selectByName(name).getId());

        return !list.isEmpty()
                ? Response.success("选课查询成功",list)
                : Response.fail(ErrorCode.CHOOSE_QUERY_BY_COURSE_FAILURE.getCode(), ErrorCode.CHOOSE_QUERY_BY_COURSE_FAILURE.getMsg());
    }
    @PostMapping("/page")
    public Response<PageResponse<Choose>> getPages(@RequestBody PageDTO pageDTO){
        PageResponse<Choose> records = chooseService.getPages(pageDTO);

        return !records.getList().isEmpty()
                ? Response.success("选课信息分页查询成功",records)
                : Response.fail(ErrorCode.PAGE_EMPTY.getCode(),ErrorCode.PAGE_EMPTY.getMsg());
    }
}