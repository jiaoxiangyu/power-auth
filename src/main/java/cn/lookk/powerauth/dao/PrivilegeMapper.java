package cn.lookk.powerauth.dao;

import cn.lookk.powerauth.po.Privilege;

import java.util.List;

public interface PrivilegeMapper {
    int add(Privilege user);

    int update(Privilege user);

    int delete(int id);

    Privilege findOneById(int id);

    int count();

    int countBySearch(String search);

    List<Privilege> findAll();

    List<Privilege> find(int page, int limit);

    List<Privilege> findBySearch(int start, int limit, String search);

}
