package cn.lookk.powerauth.annotation;


import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 自定义注解, 登录拦截校验
 *
 */

@Target(value = {TYPE, METHOD})
@Retention(RUNTIME)
@Documented
public @interface Login {

}
