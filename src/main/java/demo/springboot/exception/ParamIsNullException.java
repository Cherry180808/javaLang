package demo.springboot.exception;

/**
 *@program: ParamIsNullException
 *@description: 自定义参数为空异常类
 *@author: Xiong Aiqian
 *@create: 2019-08-15
 */
public class ParamIsNullException extends RuntimeException {
    private final String parameterName;
    private final String parameterType;

    public ParamIsNullException(String parameterName, String parameterType){
        super("");
        this.parameterName = parameterName;
        this.parameterType = parameterType;
    }

    @Override
    public String getMessage(){
        return "Required " + this.parameterType +
                " parameter \'" + this.parameterName + "\' must not be null";
    }

    public final String getParameterName(){
        return this.parameterName;
    }

    public final String getParameterType(){
        return this.parameterType;
    }
}
