package com.sap.intellgentcam.demo1.result;

/**
 * @author : Jenson.Liu
 * @date : 2019-07-30  10:48
 */
public class AjaxVO {

    private Boolean success;

    private String message;

    private Object data;

    private AjaxVO(){

    }

    public AjaxVO(boolean success, String message, Object data){
        this.success = success;
        this.message = message;
        this.data = data;
    }

    public static AjaxVO success(){
        return new AjaxVO(true, null, null);
    }

    public static AjaxVO success(Object data){
        return new AjaxVO(true, null, data);
    }

    public static AjaxVO failed(String message){
        return new AjaxVO(false, message, null);
    }
    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public Object getData() {
        return data;
    }

    @Override
    public String toString() {
        return "AjaxVO{" +
                "success=" + success +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}