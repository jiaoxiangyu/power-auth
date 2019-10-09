package cn.lookk.powerauth.dao;

import cn.lookk.powerauth.po.Role;

import java.util.List;


public interface RoleMapper {
    int add(Role user);

    int update(Role user);

    int delete(Integer id);

    Role findOneById(Integer id);

    Role findOneByName(String name);

    int count();

    int countBySearch(String search);

    List<Role> findAll();

    List<Role> find(int start, int limit);

    List<Role> findBySearch(int start, int limit, String search);
}
