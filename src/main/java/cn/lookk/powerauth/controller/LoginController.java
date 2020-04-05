package cn.lookk.powerauth.controller;

import cn.lookk.handleexception.exception.Assert;
import cn.lookk.handleexception.util.ResultUtil;
import cn.lookk.handleexception.vo.Result;
import cn.lookk.powerauth.po.User;
import cn.lookk.powerauth.service.ILoginService;
import cn.lookk.powerauth.service.IUserService;
import cn.lookk.powerauth.util.IdWorker;
import cn.lookk.powerauth.util.RedisUtil;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;

/**
 * @ClassName: LoginController
 * @Description: TODO
 * @Author jiaoxiangyu
 * @Date 2019/9/6
 * @Version 1.0
 * @Since JDK1.8
 */
@RestController
public class LoginController {


    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private IUserService userService;
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private ILoginService loginService;

    /**
     * @title:  login
     * @description:  to login.html
     * @param 
     * @return  java.lang.String
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login(ModelAndView modelAndView){
        modelAndView.setViewName("login");
        return modelAndView;
    }

    /**
     * @title:  toLogin
     * @description:  to toLogin.html
     * @param
     * @return  java.lang.String
     */
    @RequestMapping(value = "/toLogin", method = RequestMethod.GET)
    public ModelAndView toLogin(ModelAndView modelAndView){
        modelAndView.setViewName("toLogin");
        return modelAndView;
    }

    /**
     * @title:  login
     * @description:  登录进入 index.html
     * @param request
     * @param response
     * @return  org.springframework.web.servlet.ModelAndView
     */
    @RequestMapping(value = "/index", method = RequestMethod.POST)
    public Result index(User user, HttpServletRequest request, HttpServletResponse response){
        //校验登录
        boolean isLogin = loginService.isLogin(request);
        if (!isLogin) {
            int toIndex = loginService.login(response, user);
        }

        return ResultUtil.success();
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public ModelAndView logout(HttpServletRequest request, ModelAndView modelAndView){
        //获取request token
        String requestToken=null;
        Cookie[] cookies =  request.getCookies();
        if(cookies != null){
            for(Cookie cookie : cookies){
                if(cookie.getName().equals("token")){
                    requestToken= cookie.getValue();
                }
            }
        }
        logger.info("requestToken={}",requestToken);
        if (StringUtils.isNotBlank(requestToken)) {
            redisUtil.del("token:" + requestToken);
        }

        modelAndView.setViewName("login");
        return modelAndView;
    }





    
}
