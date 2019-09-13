package cn.lookk.powerauth.service;

import cn.lookk.powerauth.po.Role;
import cn.lookk.powerauth.vo.PageHelp;

import java.util.List;

public interface IRoleService {
    int add(Role user);

    int update(Role user);

    int delete(Integer id);

    Role findOneById(Integer id);

    Role findOneByName(String name);

    PageHelp find(String search, int page, int limit);

    List<Role> findAll();
}
