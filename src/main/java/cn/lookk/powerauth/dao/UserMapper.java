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

    int count();

    int countBySearch(String search);

    List<User> find(int start, int limit);

    List<User> findBySearch(int start, int limit, String search);
}
