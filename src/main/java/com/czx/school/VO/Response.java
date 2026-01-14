package com.czx.school.VO;

import com.czx.school.error.ErrorCode;
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
    public static <T> Response<T> success(){
        return success(null);
    }
    public static <T> Response<T>   success(T data) {
        return new Response<>(200, "操作成功", data);
    }
    public static <T> Response<T> success(String message, T data){
        return new Response<>(200, message, data);
    }

    public static <T> Response<T> fail(){
        return fail(ErrorCode.INTERNAL_SERVER_ERROR);
    }
    public static <T> Response<T> fail(String message){
        return fail(ErrorCode.INTERNAL_SERVER_ERROR.getCode(),message);
    }
    public static <T> Response<T> fail(ErrorCode errorCode){
        return fail(errorCode.getCode(), errorCode.getMsg());
    }
    public static <T> Response<T> fail(int code, String message) {
        return new Response<>(code, message, null);
    }
}
