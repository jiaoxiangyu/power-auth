package cn.lookk.powerauth.service.impl;

import cn.lookk.powerauth.dao.PrivilegeMapper;
import cn.lookk.powerauth.po.Privilege;
import cn.lookk.powerauth.po.Role;
import cn.lookk.powerauth.service.IPrivilegeService;
import cn.lookk.powerauth.util.PageUtil;
import cn.lookk.powerauth.vo.PageHelp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName: PrivilegeService
 * @Description: TODO
 * @Author jiaoxiangyu
 * @Date 2019/9/29
 * @Version 1.0
 * @Since JDK1.8
 */
@Service
public class PrivilegeServiceImpl implements IPrivilegeService {

    @Autowired
    private PrivilegeMapper privilegeMapper;

    @Override
    public int add(Privilege privilege) {
        return privilegeMapper.add(privilege);
    }

    @Override
    public int update(Privilege privilege) {
        return privilegeMapper.update(privilege);
    }

    @Override
    public int delete(int id) {
        return privilegeMapper.delete(id);
    }

    @Override
    public List<Privilege> findAll() {
        return privilegeMapper.findAll();
    }

    @Override
    public List<Privilege> findByIds(Integer[] ids) {
        return privilegeMapper.findByIds(ids);
    }

    @Override
    public Privilege findOneById(int id) {
        return privilegeMapper.findOneById(id);
    }

    @Override
    public PageHelp find(String search, int page, int limit) {
        if (search.equals("")) {
            int total = privilegeMapper.count();
            if (total == 0) {
                return new PageHelp();
            }
            int start = PageUtil.pageStart(page, limit, total);

            List<Privilege> data = privilegeMapper.find(start, limit);
            return new PageHelp(total, data);
        }else {
            int total = privilegeMapper.countBySearch(search);
            if (total == 0) {
                return new PageHelp();
            }
            int start = PageUtil.pageStart(page, limit, total);
            List<Privilege> data = privilegeMapper.findBySearch(start, limit, search);
            return new PageHelp(total, data);
        }
    }
}
