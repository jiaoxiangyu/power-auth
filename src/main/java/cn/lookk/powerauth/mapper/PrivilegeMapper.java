package cn.lookk.powerauth.mapper;

import cn.lookk.powerauth.po.Privilege;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PrivilegeMapper {
    int add(Privilege privilege);

    int update(Privilege privilege);

    int delete(@Param("id")Integer id);

    Privilege findOneById(@Param("id")Integer id);

    int count();

    int countBySearch(@Param("search")String search);

    List<Privilege> findAll();

    List<Privilege> findByIds(@Param("ids")Integer[] ids);

    List<Privilege> find(@Param("start")Integer start, @Param("limit")Integer limit);

    List<Privilege> findBySearch(@Param("start")int start, @Param("limit")int limit, @Param("search")String search);

}
