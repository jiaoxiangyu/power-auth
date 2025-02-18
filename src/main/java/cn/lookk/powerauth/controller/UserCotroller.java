package cn.lookk.powerauth.controller;

import cn.lookk.handleexception.exception.Assert;
import cn.lookk.handleexception.util.ResultUtil;
import cn.lookk.handleexception.vo.Result;
import cn.lookk.powerauth.annotation.Login;
import cn.lookk.powerauth.po.Role;
import cn.lookk.powerauth.po.User;
import cn.lookk.powerauth.service.IRoleService;
import cn.lookk.powerauth.service.IUserService;
import cn.lookk.powerauth.util.PageResultUtil;
import cn.lookk.powerauth.vo.PageHelp;
import cn.lookk.powerauth.vo.PageResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


/**
 * @ClassName: UserCotroller
 * @Description: TODO
 * @Author jiaoxiangyu
 * @Date 2019/9/7
 * @Version 1.0
 * @Since JDK1.8
 */
@RestController
@RequestMapping(value = "/user/")
@Login
public class UserCotroller {

    private static final Logger logger = LoggerFactory.getLogger(UserCotroller.class);

    @Autowired
    private IUserService userService;

    @Autowired
    private IRoleService roleService;

    /**
     * @title:  toAdd
     * @description:  跳转添加页面
     * @param modelAndView
     * @return  org.springframework.web.servlet.ModelAndView
     */
    @RequestMapping(value = "toAdd", method = RequestMethod.GET)
    public ModelAndView toAdd(ModelAndView modelAndView){
        List<Role> roles=roleService.findAll();
        logger.info("roles={}", roles);
        modelAndView.addObject("roles", roles);
        modelAndView.setViewName("user/userAdd");
        return modelAndView;
    }

    /**
     * @title:  add
     * @description:  添加
     * @param user
     * @return  cn.wt.handleexception.vo.Result
     */
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public Result add(User user){
        userService.add(user);
        return ResultUtil.success();
    }

    /**
     * @title:  findById
     * @description:  根据id查询
     * @param id
     * @return  org.springframework.web.servlet.ModelAndView
     */
    @RequestMapping(value = "findOne/{id}", method = RequestMethod.GET)
    public Result findOne(@PathVariable Long id) {
        return ResultUtil.success(userService.findOneById(id));
    }

    /**
     * @title:  find
     * @description:  分页条件查询
     * @param page
     * @param limit
     * @param search
     * @return  cn.lookk.powerauth.vo.PageResult
     */
    @RequestMapping(value = "find", method = RequestMethod.GET)
    public PageResult find(@RequestParam(defaultValue = "1", name = "page") int page,
                           @RequestParam(defaultValue = "10", name = "limit") int limit,
                           @RequestParam(defaultValue = "", name = "search") String search) {
        logger.info("page={}, limit={}, search={}", page, limit,search);
        PageHelp pageHelp =userService.find(search, page, limit);
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
    public Result delete(@PathVariable("id")Long id){
        Assert.isLessThanOrEqualZero(id, 412, "id is null");
        logger.info("delete id={}", id);
        userService.delete(id);
        return ResultUtil.success();
    }

    /**
     * @title:  update
     * @description:  更新
     * @param user
     * @return  cn.wt.handleexception.vo.Result
     */
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public Result update(User user){
        logger.info("update user={}", user);
        userService.update(user);
        return ResultUtil.success();
    }


}
