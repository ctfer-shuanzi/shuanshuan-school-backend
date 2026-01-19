package com.czx.school.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    REFRESH_TOKEN_INVALID(400, "refresh_token 已失效"),
    UNAUTHORIZED(401, "还未授权，不能访问"),
    FORBIDDEN(403, "没有权限，禁止访问"),
    TOKEN_INVALID(404, "token 非法"),
    TOKEN_EXPIRE(405, "token 失效"),
    INTERNAL_SERVER_ERROR(500, "服务器异常，请稍后再试"),
    PARAM_INVALID(998, "参数错误"),
    BUSINESS_ERROR(999, "业务错误"),

    // 自定义错误码
    ACCOUNT_NOT_EXIST(1000,"该用户不存在，请先注册"),
    ACCOUNT_ALREADY_EXIST(1001,"该用户已存在，自动跳转至登录页面中"),
    ACCOUNT_REGISTER_FAILURE(1002,"注册用户失败"),
    ACCOUNT_DELETE_FAILURE(1003,"删除用户失败"),
    ACCOUNT_UPDATE_FAILURE(1004,"修改用户失败"),
    ACCOUNT_LOGIN_FAILURE(1005,"登录失败，用户名或密码错误"),

    STUDENT_NOT_EXIST(1010,"该学生不存在"),
    STUDENT_ALREADY_EXIST(1011,"该学生已存在"),
    STUDENT_ADD_FAILURE(1012,"添加学生失败"),
    STUDENT_DELETE_FAILURE(1013,"删除学生失败"),
    STUDENT_UPDATE_FAILURE(1014,"修改学生失败"),
    STUDENT_QUERY_BY_MAJOR_FAILURE(1015,"该专业没有学生"),

    TEACHER_NOT_EXIST(1020,"该老师信息不存在"),
    TEACHER_ALREADY_EXIST(1021,"该老师已存在"),
    TEACHER_ADD_FAILURE(1022,"添加老师失败"),
    TEACHER_DELETE_FAILURE(1023,"删除老师失败"),
    TEACHER_UPDATE_FAILURE(1024,"修改老师失败"),
    TEACHER_QUERY_BY_DEPARTMENT_FAILURE(1025,"该部门没有老师"),

    COURSE_NOT_EXIST(1030,"该课程信息不存在"),
    COURSE_ALREADY_EXIST(1031,"该课程已存在"),
    COURSE_ADD_FAILURE(1032,"添加课程失败"),
    COURSE_DELETE_FAILURE(1033,"删除课程失败"),
    COURSE_UPDATE_FAILURE(1034,"修改课程失败"),
    COURSE_QUERY_BY_TEACHER_FAILURE(1035,"该老师没有课程"),

    CHOOSE_NOT_EXIST(1040,"该选课信息不存在"),
    CHOOSE_ALREADY_EXIST(1041,"此生已选该课程"),
    CHOOSE_ADD_FAILURE(1042,"选课失败"),
    CHOOSE_DELETE_FAILURE(1043,"退课失败"),
    CHOOSE_UPDATE_FAILURE(1044,"选课信息修改失败"),
    CHOOSE_QUERY_BY_STUDENT_FAILURE(1045,"该学生没有选课"),
    CHOOSE_QUERY_BY_COURSE_FAILURE(1046,"没有学生选了这门课"),

    PAGE_EMPTY(1050,"页面记录为空"),
    NO_RECORD(1060,"表格记录数量为0");

    private final int code;
    private final String msg;
}
