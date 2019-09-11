package cn.lookk.powerauth.service.impl;

import cn.lookk.powerauth.dao.UserMapper;
import cn.lookk.powerauth.po.User;
import cn.lookk.powerauth.service.IUserService;
import cn.lookk.powerauth.util.PageUtil;
import cn.lookk.powerauth.vo.PageHelp;
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
    public PageHelp findAll(String search, int page, int limit) {
        int total=userMapper.count();
        if (total==0){
            return new PageHelp();
        }
        int start=PageUtil.pageStart(page, limit, total);

        List<User> data=userMapper.findAll(start,limit);
        return new PageHelp(total, data);
    }
}
