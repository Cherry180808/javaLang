package demo.springboot.common;

/**
 *@program: ResultEnums
 *@description: 返回类枚举
 *@author: Xiong Aiqian
 *@create: 2019-08-15
 */
public enum  ResultEnums {
    SUCCESS("0000","请求成功"),
    ERROR("1000","请求失败"),
    SYSTEM_ERROR("1001","系统异常"),
    BUSSINESS_ERROR("1002","业务逻辑错误"),
    PARAM_ERROR("1003","业务参数值不能为空");

    private String code;
    private String msg;

    ResultEnums(String code, String msg){
        this.code = code;
        this.msg = msg;
    }

    public String getCode(){
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
