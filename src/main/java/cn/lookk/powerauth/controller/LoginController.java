package cn.lookk.powerauth.controller;

import cn.lookk.powerauth.po.User;
import cn.lookk.powerauth.service.IUserService;
import cn.lookk.powerauth.util.IdWorker;
import cn.lookk.powerauth.util.RedisUtil;
import cn.wt.handleexception.exception.Assert;
import cn.wt.handleexception.util.ResultUtil;
import cn.wt.handleexception.vo.Result;
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

    @Value("${worker_id}")
    private static long workerId;

    @Value("${token_datacenter_id}")
    private static long tokenDataCenterId;

    //IdWorker暂时放在这里，实现单例服务uid唯一性，分布式时，使用一个独立服务生成唯一ID，也可以放在这个服务中，提供权限认证和唯一ID生成
    private static IdWorker idWorker = new IdWorker(workerId, tokenDataCenterId);

    /**
     * @title:  index
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
     * @title:  login
     * @description:  登录进入 index.html
     * @param modelAndView
     * @return  org.springframework.web.servlet.ModelAndView
     */
    @RequestMapping(value = "/index", method = RequestMethod.POST)
    public Result index(User user, HttpServletRequest request, HttpServletResponse response, ModelAndView modelAndView){
        logger.info("index, user={}", user);
        //获取token
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

        if (requestToken==null){
            //登录
            User loginUser = userService.login(user.getPhone(), user.getPwd());
            Assert.isNull(loginUser,4001,"手机号或密码错误");
            //更新登录时间
            loginUser.setUpdateTime(LocalDateTime.now());
            userService.updateLoginTime(loginUser);
            //生成token
            String token= String.valueOf(idWorker.nextId());
            redisUtil.set("token:" + token, user, 7200l);
            //token设置一级域名, 并放到response
            Cookie cookie = new Cookie("token", token);
            /*cookie.setDomain("lookk.cn");*/
            cookie.setMaxAge(7200);
            response.addCookie(cookie);

        }else {
            String userJson = (String) redisUtil.get("token:" + requestToken);
            logger.info("userJson={}",userJson);
        }

        return ResultUtil.success();
    }



    
}
