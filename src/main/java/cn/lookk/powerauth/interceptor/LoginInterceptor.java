package cn.lookk.powerauth.interceptor;

import cn.lookk.powerauth.annotation.Login;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName: LoginInterceptor
 * @Description: 登录拦截器
 * @Author jiaoxiangyu
 * @Date 2019/10/28
 * @Version 1.0
 * @Since JDK1.8
 */
public class LoginInterceptor extends HandlerInterceptorAdapter {
     @Override
     public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
         // 处理handler;
         if (handler instanceof HandlerMethod) {
             // 判断当前method上是否有Login注解
             HandlerMethod hm = (HandlerMethod) handler;
             if (hm.getMethodAnnotation(Login.class) != null) {
                 // 判断当前是否用户登录,如果没有登录,跳转到登录页面

                 //response.sendRedirect("/login.html");
                 //return false;
             }
         }
         return super.preHandle(request, response, handler);
     }

}
