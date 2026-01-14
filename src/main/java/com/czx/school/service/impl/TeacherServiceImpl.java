package com.czx.school.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.czx.school.entity.Teacher;
import com.czx.school.mapper.TeacherMapper;
import com.czx.school.service.TeacherService;
import org.springframework.stereotype.Service;

@Service
public class TeacherServiceImpl extends ServiceImpl<TeacherMapper, Teacher> implements TeacherService {
}
