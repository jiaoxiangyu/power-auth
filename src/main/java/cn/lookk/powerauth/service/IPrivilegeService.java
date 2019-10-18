package cn.lookk.powerauth.service;
import	java.util.List;

import cn.lookk.powerauth.po.Privilege;
import cn.lookk.powerauth.vo.PageHelp;

public interface IPrivilegeService {
    int add(Privilege privilege);

    int update(Privilege privilege);

    int delete(int id);

    List<Privilege> findAll();

    List<Privilege> findByIds(Integer[] ids);

    Privilege findOneById(int id);

    PageHelp find(String search, int page, int limit);
}
