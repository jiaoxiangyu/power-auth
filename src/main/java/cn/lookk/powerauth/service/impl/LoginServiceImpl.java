package cn.lookk.powerauth.service.impl;

import cn.lookk.handleexception.exception.Assert;
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
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @ClassName: ILoginServiceImpl
 * @Description: 登录Service
 * @Author jiaoxiangyu
 * @Date 2020/3/19
 * @Version 1.0
 * @Since JDK1.8
 */
@Service
public class LoginServiceImpl implements ILoginService {
    
    private static final Logger logger = LoggerFactory.getLogger(LoginServiceImpl.class);

    @Value("${worker_id}")
    private static long workerId;
    @Value("${token_datacenter_id}")
    private static long tokenDataCenterId;
    //IdWorker暂时放在这里，实现单例服务uid唯一性，分布式时，使用一个独立服务生成唯一ID，也可以放在这个服务中，提供权限认证和唯一ID生成
    private static IdWorker idWorker = new IdWorker(workerId, tokenDataCenterId);

    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private IUserService userService;

    /**
     * @title:  isLogin
     * @description:  校验是否登录
     * @param request
     * @return  boolean
     */
    @Override
    public boolean isLogin(HttpServletRequest request) {
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

        //未登录过
        if (requestToken==null) {
            return false;
        }

        //登录过, 校验token
        String userJson = (String) redisUtil.get("token:" + requestToken);
        logger.info("userJson={}",userJson);

        //token过期, 登录失效
        if (StringUtils.isBlank(userJson)) {
            return false;
        }

        //校验权限 TODO

        return true;
    }

    /**
     * @title:  login
     * @description:  登录
     * @param response
     * @param user
     * @return  int 跳转到哪个服务
     *          1: 登录成功
     */
    @Override
    public int login(HttpServletResponse response, User user) {
        //登录
        User loginUser = userService.login(user.getPhone(), user.getPwd());
        Assert.isNull(loginUser,4001,"手机号或密码错误");
        //更新登录时间
        loginUser.setUpdateTime(new Date());
        userService.updateLoginTime(loginUser);
        //生成token
        String token = String.valueOf(idWorker.nextId());
        redisUtil.set("token:" + token, JSONObject.toJSON(loginUser).toString(), 7200l);
        //token设置一级域名, 并放到response
        Cookie cookie = new Cookie("token", token);
        /*cookie.setDomain("lookk.cn");*/
        cookie.setMaxAge(7200);
        response.addCookie(cookie);

        return 1;
    }
}
