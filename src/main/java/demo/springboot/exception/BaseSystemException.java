package demo.springboot.exception;

/**
 *@program: BaseSystemException
 *@description: 自定义基础异常类,暂未使用
 *@author: Xiong Aiqian
 *@create: 2019-08-15
 */
public class BaseSystemException extends RuntimeException {
    private String errorCode;  //错误编码
    private String errorMsg;  //错误信息
    private String complementary;  //补充内容

    public BaseSystemException(String errorCode, String errorMsg, String complementary) {
        super("errorCode：[" + errorCode + "] errorMsg：[" + errorMsg + "] complementary：[" + complementary + "]");
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
        this.complementary = complementary;
    }

    public BaseSystemException(String message, Throwable cause, String errorCode, String errorMsg, String complementary) {
        super("errorCode：[" + errorMsg + "] errorMsg：[" + errorMsg + "] complementary：[" + complementary + "]", cause);
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
        this.complementary = complementary;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getComplementary() {
        return complementary;
    }

    public void setComplementary(String complementary) {
        this.complementary = complementary;
    }
}
