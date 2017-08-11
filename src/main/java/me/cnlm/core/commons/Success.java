package me.cnlm.core.commons;

/**
 * Created by LONG on 2017/5/4.
 */
public class Success {
    private int code = 200000;
    private Object obj;
    private String desc = "操作成功";

    public Success(Object obj) {
        this.obj = obj;
    }

    public Success(Object obj, String desc) {
        this.obj = obj;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
