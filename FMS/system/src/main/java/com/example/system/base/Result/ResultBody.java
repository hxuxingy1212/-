package com.example.system.base.Result;

import lombok.Data;


@Data
public class ResultBody {
    //返回是否成功
    private boolean flag;

    //返回结果表述
    private String msg;


    //返回数据实体
    private Object data;

    //当业务层无返回值时，使用此无参构造器
    public ResultBody() {
        this.flag = true;
        this.msg = Message.NULL;
    }

    //当出现异常时，调用此构造函数
    public ResultBody(Exception e) {
        this.flag = false;
        this.msg = Message.ERROR;
    }

    //未出错误时，调用此构造器返回结果
    public ResultBody(Object data) {
        this.flag = true;
        this.msg = Message.SUCCESS;
        this.data = data;
    }


    public ResultBody(boolean flag, Object data) {
        this.flag = flag;
        this.data = data;
    }

    public ResultBody(boolean flag) {
        this.flag = flag;
    }

    public static ResultBody error(String message) {
        ResultBody rb = new ResultBody();
        rb.setFlag(false);
        rb.setMsg(message);
        return rb;
    }

    public static ResultBody ok(String message) {
        ResultBody rb = new ResultBody();
        rb.setFlag(true);
        rb.setMsg(message);
        return rb;
    }

    public static ResultBody isNull() {
        return new ResultBody();
    }


}
