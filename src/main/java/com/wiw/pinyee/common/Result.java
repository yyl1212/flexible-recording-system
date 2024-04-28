package com.wiw.pinyee.common;

import com.wiw.pinyee.constant.ResultEnum;
import lombok.Data;

import java.io.Serializable;

@Data
public class Result<T> implements Serializable {

    private String code;

    private String msg;

    private T data;

    public Result() {
    }

    public Result(T data) {
        this.data = data;
    }

    public static Result<?> success() {
        Result<?> result = new Result<>();
        result.setCode("200");
        result.setMsg("success");
        return result;
    }

    public static <T> Result<T> success(T data) {
        Result<T> result = new Result<>(data);
        result.setCode("200");
        result.setMsg("success");
        return result;
    }

    public static Result<?> error(String code, String msg) {
        Result<?> result = new Result<>();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }

    public static Result<?> ok(ResultEnum sign) {
        Result<?> result = new Result<>();
        result.setCode(sign.code);
        result.setMsg(sign.msg);
        return result;
    }
}
