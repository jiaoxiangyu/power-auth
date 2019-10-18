package cn.lookk.powerauth.service.impl;

import cn.lookk.powerauth.dao.RolePrivilegeMapper;
import cn.lookk.powerauth.po.Privilege;
import cn.lookk.powerauth.po.Role;
import cn.lookk.powerauth.po.RolePrivilege;
import cn.lookk.powerauth.service.IPrivilegeService;
import cn.lookk.powerauth.service.IRolePrivilegeService;
import cn.lookk.powerauth.service.IRoleService;
import cn.lookk.powerauth.util.PageUtil;
import cn.lookk.powerauth.vo.PageHelp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: RolePrivilegeServiceImpl
 * @Description: TODO
 * @Author jiaoxiangyu
 * @Date 2019/9/30
 * @Version 1.0
 * @Since JDK1.8
 */
@Service
public class RolePrivilegeServiceImpl implements IRolePrivilegeService {
    @Autowired
    private RolePrivilegeMapper rolePrivilegeMapper;
    @Autowired
    private IRoleService roleService;
    @Autowired
    private IPrivilegeService privilegeService;

    @Override
    public int add(RolePrivilege rolePrivilege) {
        return rolePrivilegeMapper.add(rolePrivilege);
    }

    @Override
    public int addBatch(List<RolePrivilege> rolePrivileges) {
        return rolePrivilegeMapper.addBatch(rolePrivileges);
    }

    @Override
    public void addOfRole(int roleId, Integer[] privilegeIds) {
        Role role = roleService.findOneById(roleId);
        List<Privilege> privileges=privilegeService.findByIds(privilegeIds);
        List<RolePrivilege> rolePrivileges = new ArrayList<>();
        for (Privilege privilege : privileges) {
            RolePrivilege rolePrivilege = new RolePrivilege();
            rolePrivilege.setRoleId(roleId);
            rolePrivilege.setRoleName(role.getName());
            rolePrivilege.setPrivilegeId(privilege.getId());
            rolePrivilege.setPrivilegeName(privilege.getName());
            rolePrivilege.setPrivilegeUrl(privilege.getUrl());
            rolePrivilege.setStatus(true);
            rolePrivileges.add(rolePrivilege);
        }
        addBatch(rolePrivileges);
    }

    @Override
    public int update(RolePrivilege rolePrivilege) {
        rolePrivilege.setUpdateTime(LocalDateTime.now());
        return rolePrivilegeMapper.update(rolePrivilege);
    }

    @Override
    public int delete(int id) {
        return rolePrivilegeMapper.delete(id);
    }

    @Override
    public RolePrivilege findOneById(int id) {
        return rolePrivilegeMapper.findOneById(id);
    }

    @Override
    public int count() {
        return rolePrivilegeMapper.count();
    }

    @Override
    public int countBySearch(String search) {
        return rolePrivilegeMapper.countBySearch(search);
    }

    @Override
    public List<RolePrivilege> findByRoleId(int roleId) {
        return rolePrivilegeMapper.findByRoleId(roleId);
    }

    @Override
    public PageHelp find(int page, int limit, String search) {
        if (search.equals("")) {
            int total = rolePrivilegeMapper.count();
            if (total == 0) {
                return new PageHelp();
            }
            int start = PageUtil.pageStart(page, limit, total);

            List<RolePrivilege> data = rolePrivilegeMapper.find(start, limit);
            return new PageHelp(total, data);
        }else {
            int total = rolePrivilegeMapper.countBySearch(search);
            if (total == 0) {
                return new PageHelp();
            }
            int start = PageUtil.pageStart(page, limit, total);
            List<RolePrivilege> data = rolePrivilegeMapper.findBySearch(start, limit, search);
            return new PageHelp(total, data);
        }
    }

}
