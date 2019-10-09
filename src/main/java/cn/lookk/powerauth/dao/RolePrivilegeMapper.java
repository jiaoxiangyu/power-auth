package cn.lookk.powerauth.dao;

import cn.lookk.powerauth.po.RolePrivilege;

import java.util.List;

public interface RolePrivilegeMapper {
    int add(RolePrivilege rolePrivilege);

    int update(RolePrivilege rolePrivilege);

    int delete(int id);

    RolePrivilege findOneById(int id);

    int count();

    int countBySearch(String search);

    List<RolePrivilege> findByRoleId(int roleId);

    List<RolePrivilege> find(int start, int limit);

    List<RolePrivilege> findBySearch(int start, int limit, String search);
}
