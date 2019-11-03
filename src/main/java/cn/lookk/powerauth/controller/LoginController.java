package cn.lookk.powerauth.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

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

    /**
     * @title:  login
     * @description:  to login.html
     * @param 
     * @return  java.lang.String
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login(ModelAndView modelAndView){
        logger.info("login");
        modelAndView.setViewName("login");
        return modelAndView;
    }
    /**
     * @title:  index
     * @description:  登录进入 index.html
     * @param modelAndView
     * @return  org.springframework.web.servlet.ModelAndView
     */
    @RequestMapping(value = "/index", method = RequestMethod.POST)
    public ModelAndView index(HttpServletRequest request, ModelAndView modelAndView){
        //获取token
        String token=null;
        Cookie[] cookies =  request.getCookies();
        if(cookies != null){
            for(Cookie cookie : cookies){
                if(cookie.getName().equals("token")){
                    token= cookie.getValue();
                }
            }
        }

        if (token!=null){

        }


        modelAndView.setViewName("index");
        return modelAndView;
    }



    
}
