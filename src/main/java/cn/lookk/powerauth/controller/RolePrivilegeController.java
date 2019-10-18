package cn.lookk.powerauth.controller;

import cn.lookk.powerauth.po.Privilege;
import cn.lookk.powerauth.po.RolePrivilege;
import cn.lookk.powerauth.service.IPrivilegeService;
import cn.lookk.powerauth.service.IRolePrivilegeService;
import cn.lookk.powerauth.util.PageResultUtil;
import cn.lookk.powerauth.vo.PageHelp;
import cn.lookk.powerauth.vo.PageResult;
import cn.wt.handleexception.exception.Assert;
import cn.wt.handleexception.util.ResultUtil;
import cn.wt.handleexception.vo.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

/**
 * @ClassName: PrivilegeController
 * @Description: TODO
 * @Author jiaoxiangyu
 * @Date 2019/10/18
 * @Version 1.0
 * @Since JDK1.8
 */
@RestController
@RequestMapping(value = "/rolePrivilege/")
public class RolePrivilegeController {

    private static final Logger logger = LoggerFactory.getLogger(RoleController.class);


    @Autowired
    private IRolePrivilegeService rolePrivilegeService;

    @RequestMapping(value = "find", method = RequestMethod.GET)
    public PageResult find(@RequestParam(defaultValue = "1", name = "page") int page,
                           @RequestParam(defaultValue = "10", name = "limit") int limit,
                           @RequestParam(defaultValue = "", name = "search") String search) {
        logger.info("page={}, limit={}, search={}", page, limit,search);
        PageHelp pageHelp =rolePrivilegeService.find(page, limit, search);
        logger.info("pageHelp={}", pageHelp);
        return PageResultUtil.success(pageHelp.getTotal(),pageHelp.getData());
    }


    /**
     * @title:  delete
     * @description:  删除
     * @param id
     * @return  cn.wt.handleexception.vo.Result
     */
    @RequestMapping(value = "del/{id}", method = RequestMethod.GET)
    public Result delete(@PathVariable("id")int id){
        Assert.isLessThanOrEqualZero(id, 412, "id is null");
        logger.info("delete id={}", id);
        rolePrivilegeService.delete(id);
        return ResultUtil.success();
    }

    /**
     * @title:  toUpdate
     * @description:  TODO
     * @param id
     * @param modelAndView
     * @return  org.springframework.web.servlet.ModelAndView
     */
    @RequestMapping(value = "toUpdate/{id}", method = RequestMethod.GET)
    public ModelAndView toUpdate(@PathVariable int id, ModelAndView modelAndView) {
        RolePrivilege rolePrivilege=rolePrivilegeService.findOneById(id);
        logger.info("rolePrivilege={}",rolePrivilege);
        modelAndView.addObject("rolePrivilege",rolePrivilege);
        modelAndView.setViewName("rolePrivilege/rolePrivilegeUpdate");
        return modelAndView;
    }

    /**
     * @title:  update
     * @description:  TODO
     * @param rolePrivilege
     * @return  cn.wt.handleexception.vo.Result
     */
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public Result update(RolePrivilege rolePrivilege){
        logger.info("update rolePrivilege={}", rolePrivilege);
        rolePrivilegeService.update(rolePrivilege);
        return ResultUtil.success();
    }
}
