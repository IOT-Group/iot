package com.example.iot.vo;

/**
 * @date 2020年3月2日
 */
public class Response {
//    调用是否成功
    private Boolean succ;
//    返回的消息
    private String message;
//    返回的内容
    private Object content;

    /**
     *
     * @param content
     * @return 建立调用成功的response
     */
    public static Response ResponseSuccess(Object content){
        Response response = new Response();
        response.setContent(content);
        response.setSucc(true);
        return response;
    }

    /**
     *
     * @param message
     * @return 建立调用失败的response，并设置错误信息
     */
    public static Response ResponseFail(String message){
        Response response = new Response();
        response.setMessage(message);
        response.setSucc(false);
        return response;
    }

    public Boolean getSucc() {
        return succ;
    }

    public void setSucc(Boolean succ) {
        this.succ = succ;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }
}
