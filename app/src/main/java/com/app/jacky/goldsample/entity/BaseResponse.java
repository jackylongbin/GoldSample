package com.app.jacky.goldsample.entity;

public class BaseResponse {
    private boolean result;
    private String error;
    private Object data;

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "BaseResponse{" +
                "result=" + result +
                ", error='" + error + '\'' +
                ", data=" + data +
                '}';
    }
}
