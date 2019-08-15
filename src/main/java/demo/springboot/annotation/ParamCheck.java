package demo.springboot.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *@program: ParamCheck
 *@description: 参数不能为空注解
 *@author: Xiong Aiqian
 *@create: 2019-08-15
 */
@Target(ElementType.PARAMETER)  //该注解作用于方法参数上
@Retention(RetentionPolicy.RUNTIME)
public @interface ParamCheck {
    boolean notNull() default true;
}
