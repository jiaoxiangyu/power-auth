package cn.lookk.powerauth.mapper;

import cn.lookk.powerauth.po.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;
import java.util.List;


public interface UserMapper extends BaseMapper<User> {
    int add(User user);

    int update(User user);

    int updateLoginTime(User user);

    int delete(@Param("id")Long id, @Param("updateTime")LocalDateTime updateTime);

    User findOneById(@Param("id")Long id);

    int count();

    int countBySearch(@Param("search")String search);

    List<User> find(@Param("start")Integer start, @Param("limit")Integer limit);

    List<User> findBySearch(@Param("start")Integer start, @Param("limit")Integer limit, @Param("search")String search);

    User login(@Param("phone")String phone, @Param("pwd")String pwd);
}
