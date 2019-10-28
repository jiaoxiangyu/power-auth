package cn.lookk.powerauth.dao;

import cn.lookk.powerauth.po.RolePrivilege;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RolePrivilegeMapper {
    int add(RolePrivilege rolePrivilege);

    int addBatch(@Param("rolePrivileges")List<RolePrivilege> rolePrivileges);

    int update(RolePrivilege rolePrivilege);

    int delete(int id);

    void deleteByRoleId(Integer role);

    RolePrivilege findOneById(int id);

    int count();

    int countBySearch(String search);

    List<RolePrivilege> findByRoleId(int roleId);

    List<RolePrivilege> find(int start, int limit);

    List<RolePrivilege> findBySearch(int start, int limit, String search);
}
