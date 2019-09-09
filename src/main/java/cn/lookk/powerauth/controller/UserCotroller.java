package cn.lookk.powerauth.controller;

import cn.lookk.powerauth.po.User;
import cn.lookk.powerauth.service.IUserService;
import cn.lookk.powerauth.vo.PageResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
     * @return  org.springframework.web.servlet.ModelAndView
     */
    @RequestMapping(value = "find", method = RequestMethod.GET)
    public PageResult find() {
        List<User> data=userService.findAll();
        logger.info("users={}", data);
        return new PageResult(0,"SUCCESS", data, data.size());
    }




}
