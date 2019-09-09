package cn.lookk.powerauth.service;

import cn.lookk.powerauth.po.User;

import java.util.List;

public interface IUserService {
    int add(User user);

    int update(User user);

    int delete(Long id);

    User findOneById(Long id);

    List<User> findAll();
}
