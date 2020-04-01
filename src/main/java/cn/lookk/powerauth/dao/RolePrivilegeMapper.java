package cn.lookk.powerauth.dao;

import cn.lookk.powerauth.po.RolePrivilege;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RolePrivilegeMapper {

    int add(RolePrivilege rolePrivilege);

    int addBatch(@Param("rolePrivileges")List<RolePrivilege> rolePrivileges);

    int update(RolePrivilege rolePrivilege);

    int delete(@Param("id")Integer id);

    void deleteByRoleId(@Param("roleId")Integer roleId);

    RolePrivilege findOneById(@Param("id")Integer id);

    int count();

    int countBySearch(@Param("search")String search);

    List<RolePrivilege> findByRoleId(@Param("roleId")Integer roleId);

    List<RolePrivilege> find(@Param("start")Integer start, @Param("limit")Integer limit);

    List<RolePrivilege> findBySearch(@Param("start")Integer start, @Param("limit")Integer limit, @Param("search")String search);
}
