package demo.springboot.exception;

import demo.springboot.common.ResponseData;
import demo.springboot.common.ResponseDataUtil;
import demo.springboot.common.ResultEnums;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 *@program: GlobalExceptionHandler
 *@description: 全局异常统一处理
 *@author: Xiong Aiqian
 *@create: 2019-08-15
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    private final static Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /***
     * 参数为空异常处理
     * @param ex
     * @return
     */
    @ExceptionHandler({MissingServletRequestParameterException.class,ParamIsNullException.class})
    public ResponseData paramIsNullHandler(Exception ex){
        return ResponseDataUtil.buildError(ResultEnums.PARAM_ERROR);
    }

    @ExceptionHandler({Exception.class})
    public ResponseData errorHandler(Exception ex){
        return ResponseDataUtil.buildError();
    }
}
