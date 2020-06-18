package com.example.iot.vo;

/**
 * encapsulate the content to return
 * @date 2020年6月13日
 * @author Debonex
 */
public class Response {


    private int code;

    public Response(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
