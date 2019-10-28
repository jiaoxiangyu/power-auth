package cn.lookk.powerauth.service;

import cn.lookk.powerauth.po.Role;
import cn.lookk.powerauth.po.RolePrivilege;
import cn.lookk.powerauth.vo.PageHelp;

import java.util.List;

public interface IRolePrivilegeService {

    int add(RolePrivilege rolePrivilege);

    int addBatch(List<RolePrivilege> rolePrivileges);

    void addOfRole(Role role, Integer[] privilegeIds);

    int update(RolePrivilege rolePrivilege);

    void updateByRole(Role role, Integer[] privilegeIds);

    int delete(int id);

    void deleteByRoleId(Integer roleId);

    RolePrivilege findOneById(int id);

    int count();

    int countBySearch(String search);

    List<RolePrivilege> findByRoleId(int roleId);

    PageHelp find(int page, int limit, String search);

}
