package cn.lookk.powerauth.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

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
     * @description:  TODO
     * @param 
     * @return  java.lang.String
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login(ModelAndView modelAndView){
        logger.info("login");
        modelAndView.setViewName("index");
        return modelAndView;
    }

    
}
