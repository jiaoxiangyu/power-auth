package cn.lookk.powerauth.controller;

import cn.lookk.handleexception.exception.Assert;
import cn.lookk.handleexception.util.ResultUtil;
import cn.lookk.handleexception.vo.Result;
import cn.lookk.powerauth.annotation.Login;
import cn.lookk.powerauth.po.Privilege;
import cn.lookk.powerauth.po.Role;
import cn.lookk.powerauth.po.RolePrivilege;
import cn.lookk.powerauth.service.IPrivilegeService;
import cn.lookk.powerauth.service.IRolePrivilegeService;
import cn.lookk.powerauth.service.IRoleService;
import cn.lookk.powerauth.util.PageResultUtil;
import cn.lookk.powerauth.util.StringUtil;
import cn.lookk.powerauth.vo.PageHelp;
import cn.lookk.powerauth.vo.PageResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @ClassName: RoleController
 * @Description: TODO
 * @Author jiaoxiangyu
 * @Date 2019/9/17
 * @Version 1.0
 * @Since JDK1.8
 */
@RestController
@RequestMapping(value = "/role/")
public class RoleController {


    private static final Logger logger = LoggerFactory.getLogger(RoleController.class);

    @Autowired
    private IRoleService roleService;
    @Autowired
    private IPrivilegeService privilegeService;
    @Autowired
    private IRolePrivilegeService rolePrivilegeService;

    @Login
    @RequestMapping(value = "find", method = RequestMethod.GET)
    public PageResult find(@RequestParam(defaultValue = "1", name = "page") int page,
                           @RequestParam(defaultValue = "10", name = "limit") int limit,
                           @RequestParam(defaultValue = "", name = "search") String search) {
        logger.info("page={}, limit={}, search={}", page, limit,search);
        PageHelp pageHelp =roleService.find(search, page, limit);
        logger.info("pageHelp={}", pageHelp);
        return PageResultUtil.success(pageHelp.getTotal(),pageHelp.getData());
    }

    /**
     * @title:  findAll
     * @description:  TODO
     * @param 
     * @return  cn.wt.handleexception.vo.Result
     */
    @Login
    @RequestMapping(value = "findAll", method = RequestMethod.GET)
    public Result findAll(){
        List<Role> roles = roleService.findAll();
        return ResultUtil.success(roles);
    }

    /**
     * @title:  add
     * @description:  TODO
     * @param role
     * @return  cn.wt.handleexception.vo.Result
     */
    @Login
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public Result add(Role role, String privilege){
        System.out.println("privilege="+privilege);
        int roleId=roleService.add(role, StringUtil.toInts(privilege.split(",")));

        return ResultUtil.success();
    }

    /**
     * @title:  delete
     * @description:  删除
     * @param id
     * @return  cn.wt.handleexception.vo.Result
     */
    @Login
    @RequestMapping(value = "del/{id}", method = RequestMethod.GET)
    public Result delete(@PathVariable("id")int id){
        Assert.isLessThanOrEqualZero(id, 412, "id is null");
        logger.info("delete id={}", id);
        roleService.delete(id);
        return ResultUtil.success();
    }

    /**
     * @title:  update
     * @description:  更新
     * @param role
     * @return  cn.wt.handleexception.vo.Result
     */
    @Login
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public Result update(Role role, String privilege){
        logger.info("update role={}", role);
        roleService.update(role, StringUtil.toInts(privilege.split(",")));
        return ResultUtil.success();
    }

}
