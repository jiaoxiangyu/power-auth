package cn.lookk.powerauth.service.impl;

import cn.lookk.powerauth.dao.RoleMapper;
import cn.lookk.powerauth.dao.UserMapper;
import cn.lookk.powerauth.po.Role;
import cn.lookk.powerauth.po.User;
import cn.lookk.powerauth.service.IUserService;
import cn.lookk.powerauth.util.PageUtil;
import cn.lookk.powerauth.vo.PageHelp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.time.LocalDateTime;
import java.util.Date;
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

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public int add(User user) {
        return userMapper.add(user);
    }

    @Override
    public int update(User user) {
        Role role=roleMapper.findOneByName(user.getRole());
        //Assert.isNull(role, 413, "role is null");
        user.setRoleId(role.getId());
        user.setUpdateTime(LocalDateTime.now());
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
    public PageHelp find(String search, int page, int limit) {
        if (search.equals("")) {
            int total = userMapper.count();
            if (total == 0) {
                return new PageHelp();
            }
            int start = PageUtil.pageStart(page, limit, total);

            List<User> data = userMapper.find(start, limit);
            return new PageHelp(total, data);
        }else {
            int total = userMapper.countBySearch(search);
            if (total == 0) {
                return new PageHelp();
            }
            int start = PageUtil.pageStart(page, limit, total);
            List<User> data = userMapper.findBySearch(start, limit, search);
            return new PageHelp(total, data);
        }
    }
}
