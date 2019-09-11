package cn.lookk.powerauth.constants;

/**
 * @ClassName: ResultEnum
 * @Description: 响应状态码和提示枚举类
 * @Author jiaoxiangyu
 * @Date 2019/9/4
 * @Version 1.0
 * @Since JDK1.8
 */
public enum ResponseEnum {

    PARAM_IS_NULL(412,"String is null"),
    OPERATE_ERROR(420,"operate error");


    private Integer code;//状态码

    private String msg;//获得提示信息

    ResponseEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
