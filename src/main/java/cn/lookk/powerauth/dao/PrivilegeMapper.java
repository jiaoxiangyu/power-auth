package cn.lookk.powerauth.dao;

import cn.lookk.powerauth.po.Privilege;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PrivilegeMapper {
    int add(Privilege user);

    int update(Privilege user);

    int delete(int id);

    Privilege findOneById(int id);

    int count();

    int countBySearch(String search);

    List<Privilege> findAll();

    List<Privilege> findByIds(@Param("ids") Integer[] ids);

    List<Privilege> find(int start, int limit);

    List<Privilege> findBySearch(@Param("start")int start, @Param("limit")int limit, @Param("search")String search);

}
