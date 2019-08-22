package demo.springboot.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import demo.springboot.common.ResponseData;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;

/**
 *@program: ParamCheckAop
 *@description: 日志的统一处理切面类
 *@author: Xiong Aiqian
 *@create: 2019-08-15
 */
@Aspect
@Component
public class WebLogAspect {

    private final static Logger logger = LoggerFactory.getLogger(WebLogAspect.class);

    @Pointcut("execution(public * demo.springboot..*.*(..))")
    public void webLog(){

    }

    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint){
//        //接收请求，记录请求内容
//        ServletRequestAttributes attributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
//        HttpServletRequest request =  attributes.getRequest();
//
//        //记录下请求内容
//        logger.info("URL:" + request.getRequestURL().toString());
//        logger.info("HTTP_METHOD:" + request.getMethod());
//        logger.info("IP:" + request.getRemoteAddr());
//        logger.info("CLASS_METHOD:" + joinPoint.getSignature().getDeclaringTypeName() + "."
//            + joinPoint.getSignature().getName());
//        logger.info("ARGS:" + Arrays.toString(joinPoint.getArgs()));
    }

    @Around("webLog()")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
        long beginTime = System.currentTimeMillis();
        ServletRequestAttributes attributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
        HttpServletRequest request =  attributes.getRequest();
        String remoteAddr = request.getRemoteAddr();  //代理地址
        String requestUri = request.getRequestURI();  //请求地址
        String methodName = pjp.getSignature().getName();  //请求类
        String clazzName = pjp.getTarget().getClass().getSimpleName();  //方法名
        MethodSignature signature =  (MethodSignature)pjp.getSignature();  //获取请求参数
        String[] paramNames = signature.getParameterNames();  //获取方法参数名
        Object[] paramValues = pjp.getArgs();  //获取参数值
        StringBuilder sb = new StringBuilder();
        if(paramNames != null && paramNames.length > 0){
            for(int i = 0 ; i < paramNames.length; i ++){
                if(paramNames[i].equals("bindingResult")) {
                    break;
                }
                if(paramValues[i] instanceof HttpServletRequest ||
                    (paramValues[i]) instanceof HttpServletResponse){
                    sb.append("[").append(paramNames[i]).append("=").append(paramValues[i]).append("]");
                }else{
                    sb.append("[").append(paramNames[i]).append("=").append(JSON.toJSONString(paramValues[i], SerializerFeature.WriteDateUseDateFormat)).append("]");
                }
            }
        }
        Object result = null;
        try{
            result = pjp.proceed();
        }catch(Throwable throwable){
            logger.error("切面处理请求错误！IP信息：" + remoteAddr + " UIR信息：" + requestUri +
                    " 请求映射控制类：" + clazzName + " 请求方法：" + methodName + " 请求参数列表：" + sb.toString());
            throw throwable;
        }
        // 请求成功
        String resultJsonString = "";
        if(result != null){
            if(result instanceof ResponseData){
                resultJsonString = JSON.toJSONString(result,SerializerFeature.WriteDateUseDateFormat);
            }else{
                resultJsonString = String.valueOf(result);
            }
        }
        long endTime = System.currentTimeMillis();
        long usedTime = endTime - beginTime;
        logger.info("请求操作成功！请求耗时：" + usedTime + " IP信息：" + remoteAddr + " UIR信息：" + requestUri +
                " 请求映射控制类：" + clazzName + " 请求方法：" + methodName + " 请求参数列表：" + sb.toString() +
                " 返回值：" + resultJsonString);
        return result;
    }

    @AfterReturning(returning = "ret",pointcut = "webLog()")
    public void doAfterReturning(Object ret) {
        //处理完请求，返回内容
        logger.info("RESPONSE:" + ret);
    }
}
