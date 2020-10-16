package cn.lookk.powerauth.api.service;

import cn.lookk.powerauth.po.User;
import cn.lookk.powerauth.vo.PageHelp;

public interface UserInfoService {
    int add(User user);

    int update(User user);

    int updateLoginTime(User user);

    int delete(Long id);

    User findOneById(Long id);

    PageHelp find(String search, int page, int limit);

    User login(String phone, String pwd);
}
