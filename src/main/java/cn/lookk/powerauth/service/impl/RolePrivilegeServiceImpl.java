package cn.lookk.powerauth.service.impl;

import cn.lookk.powerauth.dao.RolePrivilegeMapper;
import cn.lookk.powerauth.po.RolePrivilege;
import cn.lookk.powerauth.service.IRolePrivilegeService;
import cn.lookk.powerauth.util.PageUtil;
import cn.lookk.powerauth.vo.PageHelp;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @ClassName: RolePrivilegeServiceImpl
 * @Description: TODO
 * @Author jiaoxiangyu
 * @Date 2019/9/30
 * @Version 1.0
 * @Since JDK1.8
 */
public class RolePrivilegeServiceImpl implements IRolePrivilegeService {
    @Autowired
    private RolePrivilegeMapper rolePrivilegeMapper;

    @Override
    public int add(RolePrivilege rolePrivilege) {
        return rolePrivilegeMapper.add(rolePrivilege);
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
