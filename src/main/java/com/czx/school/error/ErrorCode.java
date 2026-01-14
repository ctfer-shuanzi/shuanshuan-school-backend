package com.czx.school.error;

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

    ACCOUNT_NOT_EXIST(1000,"用户不存在，请先注册"),
    ACCOUNT_LOGIN_FAILURE(1001,"登录失败，用户名或密码错误"),
    ACCOUNT_ALREADY_EXIST(1002,"用户已存在，将自动跳转至登陆页面"),
    ACCOUNT_REGISTER_FAILURE(1003,"注册失败"),
    ACCOUNT_DELETE_FAILURE(1004,"删除失败"),
    ACCOUNT_QUERY_FAILURE(1005,"查询用户失败"),

    STUDENT_ALREADY_EXIST(1010,"该学生已存在"),
    STUDENT_NOT_EXIST(1011,"该学生不存在"),
    PAGE_EMPTY(1020,"页面记录为空");

    private final int code;
    private final String msg;
}
