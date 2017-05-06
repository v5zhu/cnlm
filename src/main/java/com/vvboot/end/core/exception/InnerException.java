package com.vvboot.end.core.exception;

/**
 * Created by LONG on 2017/5/4.
 */
public class InnerException extends RuntimeException {
    private int errorCode = 500000;
    private String error = "系统异常";

    public InnerException(String error) {
        super(error);
        this.error = error;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
