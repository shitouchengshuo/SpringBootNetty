package com.qiqi.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.boot.jackson.JsonComponent;
import org.springframework.stereotype.Component;
import org.springframework.validation.ObjectError;

import java.util.List;
import java.util.Optional;

/**
 * 返回给前端的响应
 */
@Component
@JsonComponent
public class Response<T> {

    public String code;

    public String msg;

    //表示,如果值为null,则不返回
    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    public T data;

    public Response() {
    }

    public Response(String code, String msg) {
        this.code = code;
        this.msg = msg;
        this.data = null;
    }

    public Response(String code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public static Response ok() {
        return new Response("0", "OK");
    }

    public static Response error() {
        return new Response("-1", "error");
    }

    public static <T> Response<T> okWithResult(T result) {
        return new Response<>("0", "OK", result);
    }

    public static Response notFound() {
        return new Response("1051", "not found");
    }

    public static Response error(List<ObjectError> errors) {
        Optional<ObjectError> optFirstError = errors.stream().findFirst();
        if (optFirstError.isPresent()) {
            ObjectError error = optFirstError.get();
            String msg = String.format("%s %s",
                    error.getObjectName(),
                    error.getDefaultMessage());
            return new Response("1001", msg);
        }
        return new Response("1001", "Error: what's happen?");
    }

    public static Response errorWithCode(String code, String description) {
        return new Response(code, description);
    }
}
