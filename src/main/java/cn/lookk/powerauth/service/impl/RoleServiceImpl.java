package cn.lookk.powerauth.service.impl;

import cn.lookk.powerauth.dao.RoleMapper;
import cn.lookk.powerauth.po.Role;
import cn.lookk.powerauth.po.User;
import cn.lookk.powerauth.service.IRolePrivilegeService;
import cn.lookk.powerauth.service.IRoleService;
import cn.lookk.powerauth.util.PageUtil;
import cn.lookk.powerauth.util.StringUtil;
import cn.lookk.powerauth.vo.PageHelp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @ClassName: RoleServiceImpl
 * @Description: TODO
 * @Author jiaoxiangyu
 * @Date 2019/9/7
 * @Version 1.0
 * @Since JDK1.8
 */
@Service
public class RoleServiceImpl implements IRoleService {

    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private IRolePrivilegeService rolePrivilegeService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int add(Role role, Integer[] privilegeIds) {
        int roleId=roleMapper.add(role);
        role.setId(roleId);
        rolePrivilegeService.addOfRole(role, privilegeIds);
        return 1;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Role role, Integer[] privilegeIds) {
        roleMapper.update(role);
        //更新权限
        rolePrivilegeService.updateByRole(role, privilegeIds);
    }

    @Override
    public int delete(Integer id) {
        return roleMapper.delete(id);
    }

    @Override
    public Role findOneById(Integer id) {
        return roleMapper.findOneById(id);
    }

    @Override
    public Role findOneByName(String name) {
        return roleMapper.findOneByName(name);
    }

    @Override
    public PageHelp find(String search, int page, int limit) {

        if (search.equals("")) {
            int total = roleMapper.count();
            if (total == 0) {
                return new PageHelp();
            }
            int start = PageUtil.pageStart(page, limit, total);

            List<Role> data = roleMapper.find(start, limit);
            return new PageHelp(total, data);
        }else {
            int total = roleMapper.countBySearch(search);
            if (total == 0) {
                return new PageHelp();
            }
            int start = PageUtil.pageStart(page, limit, total);
            List<Role> data = roleMapper.findBySearch(start, limit, search);
            return new PageHelp(total, data);
        }
    }

    @Override
    public List<Role> findAll() {
        return roleMapper.findAll();
    }
}
