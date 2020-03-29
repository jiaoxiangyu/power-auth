package cn.lookk.powerauth.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @ClassName: ActionController
 * @Description: TODO
 * @Author jiaoxiangyu
 * @Date 2019/9/8
 * @Version 1.0
 * @Since JDK1.8
 */
@Controller
@RequestMapping(value = "/action")
public class ActionController {


    private static final Logger logger = LoggerFactory.getLogger(ActionController.class);

    @RequestMapping(value = "/{pageName}", method = RequestMethod.GET)
    public String actionView(@PathVariable(name = "pageName") String pageName) {
        logger.info("pageName={}",pageName);
        return pageName;
    }

    @RequestMapping(value = "/{pathName}/{pageName}", method = RequestMethod.GET)
    public String actionPathView(
            @PathVariable(name = "pathName") String pathName,
            @PathVariable(name = "pageName") String pageName) {
        logger.info("pathName={}, pageName={}",pathName,pageName);
        return pathName+"/"+pageName;
    }

}
