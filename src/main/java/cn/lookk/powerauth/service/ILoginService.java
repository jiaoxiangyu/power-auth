package cn.lookk.powerauth.service;

import cn.lookk.powerauth.po.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface ILoginService {

    //校验是否登录
    boolean isLogin(HttpServletRequest request);
    //登录
    int login(HttpServletResponse response, User user);

}
