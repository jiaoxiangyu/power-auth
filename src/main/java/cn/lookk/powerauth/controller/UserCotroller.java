package cn.lookk.powerauth.controller;

import cn.lookk.powerauth.po.Role;
import cn.lookk.powerauth.po.User;
import cn.lookk.powerauth.service.IRoleService;
import cn.lookk.powerauth.service.IUserService;
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
 * @ClassName: UserCotroller
 * @Description: TODO
 * @Author jiaoxiangyu
 * @Date 2019/9/7
 * @Version 1.0
 * @Since JDK1.8
 */
@RestController
@RequestMapping(value = "/user/")
public class UserCotroller {

    private static final Logger logger = LoggerFactory.getLogger(UserCotroller.class);

    @Autowired
    private IUserService userService;

    @Autowired
    private IRoleService roleService;

    /**
     * @title:  toAdd
     * @description:  TODO
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
     * @description:  TODO
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
     * @description:  TODO
     * @param id
     * @return  org.springframework.web.servlet.ModelAndView
     */
    @RequestMapping(value = "findOne/{id}", method = RequestMethod.GET)
    public Result findOne(@PathVariable Long id) {
        return ResultUtil.success(userService.findOneById(id));
    }

    /**
     * @title:  find
     * @description:  TODO
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
     * @title:  toUpdate
     * @description:  TODO
     * @param id
     * @param modelAndView
     * @return  org.springframework.web.servlet.ModelAndView
     */
    @RequestMapping(value = "toUpdate/{id}", method = RequestMethod.GET)
    public ModelAndView toUpdate(@PathVariable Long id, ModelAndView modelAndView) {
        User user=userService.findOneById(id);
        logger.info("user={}",user);
        modelAndView.addObject("user",user);
        List<Role> roles=roleService.findAll();
        logger.info("roles={}",roles);
        modelAndView.addObject("roles",roles);
        modelAndView.setViewName("user/userUpdate");
        return modelAndView;
    }

    /**
     * @title:  update
     * @description:  TODO
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
