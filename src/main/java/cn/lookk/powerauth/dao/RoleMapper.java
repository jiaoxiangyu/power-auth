package cn.lookk.powerauth.dao;

import cn.lookk.powerauth.po.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface RoleMapper {
    int add(Role role);

    int update(Role role);

    int delete(@Param("id")Integer id);

    Role findOneById(@Param("id")Integer id);

    Role findOneByName(@Param("name")String name);

    int count();

    int countBySearch(@Param("search")String search);

    List<Role> findAll();

    List<Role> find(@Param("start")Integer start, @Param("limit")Integer limit);

    List<Role> findBySearch(@Param("start")Integer start, @Param("limit")Integer limit, @Param("search")String search);
}
