package cn.lookk.powerauth.controller;

import cn.lookk.powerauth.po.Privilege;
import cn.lookk.powerauth.po.Role;
import cn.lookk.powerauth.service.IPrivilegeService;
import cn.lookk.powerauth.service.IRoleService;
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
     * @title:  toAdd
     * @description:  TODO
     * @param modelAndView
     * @return  org.springframework.web.servlet.ModelAndView
     */
    @RequestMapping(value = "toAdd", method = RequestMethod.GET)
    public ModelAndView toAdd(ModelAndView modelAndView){
        List<Privilege> privileges = privilegeService.findAll();
        logger.info("privileges={}", privileges);
        modelAndView.addObject("privileges", privileges);
        modelAndView.setViewName("role/roleAdd");
        return modelAndView;
    }
    
    /**
     * @title:  add
     * @description:  TODO
     * @param rloe
     * @return  cn.wt.handleexception.vo.Result
     */
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public Result add(Role rloe){
        roleService.add(rloe);
        return ResultUtil.success();
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
        roleService.delete(id);
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
        Role role=roleService.findOneById(id);
        logger.info("role={}",role);
        List<Privilege> privileges = privilegeService.findAll();
        logger.info("privileges={}", privileges);
        modelAndView.addObject("role",role);
        modelAndView.addObject("privileges",privileges);
        modelAndView.setViewName("role/roleUpdate");
        return modelAndView;
    }

    /**
     * @title:  update
     * @description:  TODO
     * @param role
     * @return  cn.wt.handleexception.vo.Result
     */
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public Result update(Role role){
        logger.info("update role={}", role);
        roleService.update(role);
        return ResultUtil.success();
    }

}
