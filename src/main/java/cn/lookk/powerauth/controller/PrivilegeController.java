package cn.lookk.powerauth.controller;

import cn.lookk.handleexception.exception.Assert;
import cn.lookk.handleexception.util.ResultUtil;
import cn.lookk.handleexception.vo.Result;
import cn.lookk.powerauth.po.Privilege;
import cn.lookk.powerauth.service.IPrivilegeService;
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
 * @ClassName: PrivilegeController
 * @Description: TODO
 * @Author jiaoxiangyu
 * @Date 2019/10/18
 * @Version 1.0
 * @Since JDK1.8
 */
@RestController
@RequestMapping(value = "/privilege/")
public class PrivilegeController {

    private static final Logger logger = LoggerFactory.getLogger(RoleController.class);


    @Autowired
    private IPrivilegeService privilegeService;

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
        PageHelp pageHelp =privilegeService.find(search, page, limit);
        logger.info("pageHelp={}", pageHelp);
        return PageResultUtil.success(pageHelp.getTotal(),pageHelp.getData());
    }
    
    /**
     * @title:  add
     * @description:  TODO
     * @param privilege
     * @return  cn.wt.handleexception.vo.Result
     */
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public Result add(Privilege privilege){
        System.out.println("privilege="+privilege);
        privilegeService.add(privilege);

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
        privilegeService.delete(id);
        return ResultUtil.success();
    }

    /**
     * @title:  update
     * @description:  TODO
     * @param privilege
     * @return  cn.wt.handleexception.vo.Result
     */
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public Result update(Privilege privilege){
        logger.info("update privilege={}", privilege);
        privilegeService.update(privilege);
        return ResultUtil.success();
    }

    /**
     * @title:  getAll
     * @description:  TODO
     * @param
     * @return  cn.wt.handleexception.vo.Result
     */
    @RequestMapping(value = "getAll", method = RequestMethod.GET)
    public Result getAll(){
        List<Privilege> privileges = privilegeService.findAll();
        return ResultUtil.success(privileges);
    }
}
