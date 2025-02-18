package cn.lookk.powerauth.service;

import cn.lookk.powerauth.po.User;
import cn.lookk.powerauth.vo.PageHelp;

import java.util.List;

public interface IUserService {
    int add(User user);

    int update(User user);

    int updateLoginTime(User user);

    int delete(Long id);

    User findOneById(Long id);

    PageHelp find(String search, int page, int limit);

    User login(String phone, String pwd);
}
