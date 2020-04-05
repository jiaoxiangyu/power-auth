package cn.lookk.powerauth.interceptor;

import cn.lookk.powerauth.annotation.Login;
import cn.lookk.powerauth.service.ILoginService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

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
@Component
@Aspect
public class LoginInterceptor{

    private static final Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);

    @Autowired
    private ILoginService loginService;

    // 登录校验Controller层切点
    //@Pointcut("@annotation(cn.lookk.powerauth.annotation.Login) && execution(* cn.lookk..*(..))")
    @Pointcut("@annotation(cn.lookk.powerauth.annotation.Login)")
    public void loginAspect() {
    }

    /**
     * 用于拦截Controller层校验登录
     *
     */
    @Around("loginAspect()")
    public Object isLogin(ProceedingJoinPoint pjp) throws Throwable {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();

        // 判断当前是否用户登录,如果没有登录,跳转到登录页面
        boolean isLogin = loginService.isLogin(request);
        if (!isLogin){
            response.sendRedirect("/toLogin");
        }

        return pjp.proceed();
    }


}
