package com.czx.school.controller;

import com.czx.school.DO.Course;
import com.czx.school.DO.SC;
import com.czx.school.DTO.ChangeScoreDTO;
import com.czx.school.DTO.ChangeTeacherDTO;
import com.czx.school.VO.Response;
import com.czx.school.service.CourseService;
import com.czx.school.service.SCService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/course")
public class CourseController {
    @Autowired
    private CourseService courseService;
    @Autowired
    private SCService scService;
    @PostMapping("/add")
    public Response<String> addCourse(Course course) {
        Course course1 = courseService.selectByName(course.getName());
        if (course1 != null)
        {
            return Response.error("400", "该课程已存在");
        }else
        {
            return courseService.addCourse(course) ? Response.success("添加成功") : Response.error("400", "添加失败");
        }
    }
    @PostMapping("/delete")
    public Response<String> deleteCourse(String name) {
        Course course = courseService.selectByName(name);
        if(course == null){
            return Response.error("400", "该课程不存在");
        }else{
            List<SC> scList = scService.selectByCid(course.getId());
            if(scList.isEmpty()){
                return courseService.deleteByName(name) ? Response.success("删除成功") : Response.error("400", "删除失败");
            }
            return scService.deleteByCid(course.getId()) && courseService.deleteByName(name) ? Response.success("删除成功") : Response.error("400", "删除失败");
        }
    }
    @PostMapping("select/score")
    public Response<Double> selectScoreByName(String name){
        Course course = courseService.selectByName(name);
        if(course == null){
            return Response.error("400", "该课程不存在");
        }else{
            return Response.success("查询成功", course.getScore());
        }
    }
    @PostMapping("select/teacher")
    public Response<String> selectTeacherByName(String name){
        Course course = courseService.selectByName(name);
        if(course == null){
            return Response.error("400", "该课程不存在");
        }else{
            return Response.success("查询成功", course.getTeacher());
        }
    }
    @PostMapping("/change/score")
    public Response<String> changeScore(ChangeScoreDTO changeScoreDTO){
        Course course = courseService.selectByName(changeScoreDTO.getName());
        if(course == null){
            return Response.error("400", "该课程不存在");
        }else{
            return courseService.changeScore(changeScoreDTO) ? Response.success("修改成功") : Response.error("400", "修改失败");
        }
    }
    @PostMapping("/change/teacher")
    public Response<String> changeTeacher(ChangeTeacherDTO changeTeacherDTO){
        Course course = courseService.selectByName(changeTeacherDTO.getName());
        if(course == null){
            return Response.error("400", "该课程不存在");
        }else{
            return courseService.changeTeacher(changeTeacherDTO) ? Response.success("修改成功") : Response.error("400", "修改失败");
        }
    }
}
