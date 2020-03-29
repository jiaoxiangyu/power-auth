package cn.lookk.powerauth.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface ILoginService {

    //校验是否登录
    boolean isLogin(HttpServletRequest request, HttpServletResponse response);

    //登录
    int login();

}
