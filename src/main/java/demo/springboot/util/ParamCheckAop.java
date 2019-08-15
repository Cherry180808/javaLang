package demo.springboot.util;

import demo.springboot.annotation.ParamCheck;
import demo.springboot.exception.ParamIsNullException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 *@program: ParamCheckAop
 *@description: 检查参数值不为空的统一处理切面类
 *@author: Xiong Aiqian
 *@create: 2019-08-15
 */
@Aspect
@Component
public class ParamCheckAop {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Pointcut("execution(public * demo.springboot.controller..*.*(..))")
    public void checkParam(){ }

    @Before("checkParam()")
    public void doBefore(JoinPoint joinPoint) { }

    @Around("checkParam()")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable{
        MethodSignature signature =  (MethodSignature)pjp.getSignature();
        //得到拦截的方法
        Method method = signature.getMethod();
        //获取方法参数的注解，返回二维数组是因为某些参数可能存在多个注解
        Annotation[][] parameterAnnotations = method.getParameterAnnotations();
        if(parameterAnnotations == null || parameterAnnotations.length == 0){
            return pjp.proceed();
        }
        //获取方法参数名
        String[] paramNames = signature.getParameterNames();
        //获取参数值
        Object[] paramValues = pjp.getArgs();
        //获取方法参数类型
        Class<?>[] parameterTypes = method.getParameterTypes();
        for(int i = 0; i < parameterAnnotations.length; i ++){
            for(int j = 0; j < parameterAnnotations[i].length; j ++){
                //如果该参数前面的注解是ParamCheck的实例，并且notNull()=true,则进行非空校验
                if(parameterAnnotations[i][j] != null &&
                    parameterAnnotations[i][j] instanceof ParamCheck &&
                    ((ParamCheck)parameterAnnotations[i][j]).notNull()){
                    paramIsNull(paramNames[i],paramValues[i],parameterTypes[i] == null ? null : parameterTypes[i].getName());
                    break;
                }
            }
        }
        return pjp.proceed();
    }

    @AfterReturning(pointcut = "checkParam()")
    public void doAfterReturning(JoinPoint joinPoint) { }

    private void paramIsNull(String paramName, Object value, String parameterType){
        if(value == null || "".equals(value.toString().trim())){
            throw new ParamIsNullException(paramName, parameterType);
        }
    }
}
