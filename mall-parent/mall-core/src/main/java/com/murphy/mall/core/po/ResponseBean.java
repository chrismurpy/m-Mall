package com.murphy.mall.core.po;

import java.io.Serializable;

/**
 * @author murphy
 * @Title: Controller响应实体
 * @Description:
 * @Version V1.0
 */
public class ResponseBean implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 成功标记
     */
    private boolean success = true;
    /**
     * 提示信息
     */
    private String msg = "操作成功";
    /**
     * 添加，修改的实体类
     */
    private Object model;

    /**
     * http状态码
     */
    private int code = 200;

    /**
     * session id
     */
    private String token;


    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getModel() {
        return model;
    }

    public void setModel(Object model) {
        this.model = model;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
