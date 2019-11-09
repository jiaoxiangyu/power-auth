package cn.lookk.powerauth.service.impl;

import cn.lookk.powerauth.dao.RoleMapper;
import cn.lookk.powerauth.dao.UserMapper;
import cn.lookk.powerauth.po.Role;
import cn.lookk.powerauth.po.User;
import cn.lookk.powerauth.service.IUserService;
import cn.lookk.powerauth.util.IdWorker;
import cn.lookk.powerauth.util.PageUtil;
import cn.lookk.powerauth.vo.PageHelp;
import cn.wt.handleexception.exception.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.xml.crypto.Data;
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

    @Value("${worker_id}")
    private static long workerId;

    @Value("${user_datacenter_id}")
    private static long userDataCenterId;

    //IdWorker暂时放在这里，实现单例服务uid唯一性，分布式时，使用一个独立服务生成唯一ID，也可以放在这个服务中，提供权限认证和唯一ID生成
    private static IdWorker idWorker = new IdWorker(workerId, userDataCenterId);

    @Override
    public int add(User user) {
        user.setId(idWorker.nextId());
        user.setCreateTime(LocalDateTime.now());
        user.setUpdateTime(LocalDateTime.now());
        return userMapper.add(user);
    }

    @Override
    public int update(User user) {
        Role role = roleMapper.findOneById(user.getRoleId());
        Assert.isNull(role, 413, "role is null");
        user.setRole(role.getName());
        user.setUpdateTime(LocalDateTime.now());
        return userMapper.update(user);
    }

    @Override
    public int updateLoginTime(User user) {
        return userMapper.updateLoginTime(user);
    }

    @Override
    public int delete(Long id) {
        return userMapper.delete(id, LocalDateTime.now());
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

    @Override
    public User login(String phone, String pwd) {
        return userMapper.login(phone,pwd);
    }
}
