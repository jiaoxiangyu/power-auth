package cn.lookk.powerauth.service.impl;

import cn.lookk.powerauth.service.ILoginService;
import cn.lookk.powerauth.util.RedisUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName: ILoginServiceImpl
 * @Description: TODO
 * @Author jiaoxiangyu
 * @Date 2020/3/19
 * @Version 1.0
 * @Since JDK1.8
 */
public class ILoginServiceImpl implements ILoginService {
    
    private static final Logger logger = LoggerFactory.getLogger(ILoginServiceImpl.class);

    @Autowired
    private RedisUtil redisUtil;

    /**
     * @title:  isLogin
     * @description:  校验是否登录
     * @param request
     * @param response
     * @return  boolean
     */
    @Override
    public boolean isLogin(HttpServletRequest request, HttpServletResponse response) {
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

        //token过期
        if (StringUtils.isBlank(userJson)) {
            return false;
        }

        //校验权限


        return false;
    }

    @Override
    public int login() {
        return 0;
    }
}
