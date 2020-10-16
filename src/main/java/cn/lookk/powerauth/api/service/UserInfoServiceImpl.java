package cn.lookk.powerauth.api.service;

import cn.lookk.powerauth.po.User;
import cn.lookk.powerauth.service.IUserService;
import cn.lookk.powerauth.vo.PageHelp;
import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @ClassName: UserServiceImpl
 * @Description: TODO
 * @Author jiaoxiangyu
 * @Date 2019/9/7
 * @Version 1.0
 * @Since JDK1.8
 */
@Component
@Service(version = "1.0.0", interfaceClass = UserInfoService.class)
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    private IUserService userService;

    @Override
    public int add(User user) {
      return userService.add(user);
    }

    @Override
    public int update(User user) {
        return userService.update(user);
    }

    @Override
    public int updateLoginTime(User user) {
        return userService.updateLoginTime(user);
    }

    @Override
    public int delete(Long id) {
        return userService.delete(id);
    }

    @Override
    public User findOneById(Long id) {
        return userService.findOneById(id);
    }

    @Override
    public PageHelp find(String search, int page, int limit) {
        return userService.find(search, page, limit);
    }

    @Override
    public User login(String phone, String pwd) {
        return userService.login(phone,pwd);
    }
}
