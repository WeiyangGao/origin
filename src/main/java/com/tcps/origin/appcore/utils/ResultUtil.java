package com.tcps.origin.appcore.utils;

public class ResultUtil {

    private static final int FAILURE_CODE = 0;
    private static final String FAILURE_STRING = "操作失败";
    private static final int SUCCESS_CODE = 0;
    private static final String SUCCESS_STRING = "操作成功";

    private ResultUtil() {

    }

    public static Result successResult(Object data) {
        if (data == null) {
            data = SUCCESS_STRING;
        }
        return new Result(SUCCESS_CODE, SUCCESS_STRING, data);
    }

    public static Result failureResult(Object data) {
        if (data == null) {
            data = FAILURE_STRING;
        }
        return new Result(FAILURE_CODE, FAILURE_STRING, data);
    }

    public static Result businessResult(boolean state) {
        if (state) {
            return new Result(SUCCESS_CODE, SUCCESS_STRING);
        }
        return new Result(FAILURE_CODE, FAILURE_STRING);
    }

    public static Result successResult() {
        return new Result(SUCCESS_CODE, SUCCESS_STRING);
    }

    public static Result failureResult() {
        return new Result(FAILURE_CODE, FAILURE_STRING);
    }

}
