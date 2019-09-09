package cn.lookk.powerauth.dao;

import cn.lookk.powerauth.po.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.Mapping;

import java.util.List;


public interface UserMapper {
    int add(User user);

    int update(User user);

    int delete(Long id);

    User findOneById(Long id);

    List<User> findAll();
}
