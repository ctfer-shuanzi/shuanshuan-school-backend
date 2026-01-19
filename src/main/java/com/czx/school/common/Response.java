package com.czx.school.common;

import lombok.Data;

@Data
public class Response<T> {
    private int code;
    private String message;
    private T data;
    public Response(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }
    public static <T> Response<T>   success(T data) {
        return new Response<>(200, "操作成功", data);
    }
    public static <T> Response<T> success(String message, T data){
        return new Response<>(200, message, data);
    }
    public static <T> Response<T> fail(int code, String message) {
        return new Response<>(code, message, null);
    }
}