package cn.lookk.powerauth.controller;

import cn.lookk.powerauth.constants.ResponseEnum;
import cn.lookk.powerauth.po.User;
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

    /**
     * @title:  add
     * @description:  TODO
     * @param user
     * @param modelAndView
     * @return  org.springframework.web.servlet.ModelAndView
     */
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public ModelAndView add(@RequestBody User user, ModelAndView modelAndView){
        int add = userService.add(user);

        //Assert.is
        /*if (add==1){
            modelAndView.addObject("msg","")
        }*/
        modelAndView.setViewName("userAdd");
       return modelAndView;
    }

    /**
     * @title:  findById
     * @description:  TODO
     * @param id
     * @param modelAndView
     * @return  org.springframework.web.servlet.ModelAndView
     */
    @RequestMapping(value = "findById", method = RequestMethod.GET)
    public ModelAndView findById(Long id, ModelAndView modelAndView) {
        modelAndView.addObject("user", userService.findOneById(id));
        modelAndView.setViewName("userMge");
        return modelAndView;
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
        PageHelp pageHelp =userService.findAll(search, page, limit);
        return PageResultUtil.success(pageHelp.getTotal(),pageHelp.getData());
    }

    @RequestMapping(value = "del/{id}", method = RequestMethod.GET)
    public Result delete(@PathVariable("id")Long id){
        Assert.isLessThanOrEqualZero(id, 412, "id is null");
        userService.delete(id);
        return ResultUtil.success();
    }



}
