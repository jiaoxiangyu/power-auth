package cn.lookk.powerauth.service.impl;

import cn.lookk.powerauth.dao.UserMapper;
import cn.lookk.powerauth.po.User;
import cn.lookk.powerauth.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName: UserServiceImpl
 * @Description: TODO
 * @Author jiaoxiangyu
 * @Date 2019/9/7
 * @Version 1.0
 * @Since JDK1.8
 */
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public int add(User user) {
        return userMapper.add(user);
    }

    @Override
    public int update(User user) {
        return userMapper.update(user);
    }

    @Override
    public int delete(Long id) {
        return userMapper.delete(id);
    }

    @Override
    public User findOneById(Long id) {
        return userMapper.findOneById(id);
    }

    @Override
    public List<User> findAll() {
        return userMapper.findAll();
    }
}
