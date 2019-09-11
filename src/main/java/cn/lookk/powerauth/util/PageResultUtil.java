package cn.lookk.powerauth.util;

import cn.lookk.powerauth.vo.PageResult;
import cn.wt.handleexception.constants.ResultEnum;

/**
 * @ClassName: PageResultUtil
 * @Description: TODO
 * @Author jiaoxiangyu
 * @Date 2019/9/11
 * @Version 1.0
 * @Since JDK1.8
 */
public class PageResultUtil {
    public PageResultUtil() {
    }

    public static PageResult success(int total, Object data){
        PageResult pageResult=new PageResult(ResultEnum.SUCCESS.getCode(), ResultEnum.SUCCESS.getMsg());
        pageResult.setCount(total);
        pageResult.setData(data);
        return pageResult;
    }

    public static PageResult error(){
        return new PageResult(ResultEnum.SERVER_ERROR.getCode(), ResultEnum.SERVER_ERROR.getMsg());
    }

    public static PageResult error(ResultEnum resultEnum){
        return new PageResult(resultEnum.getCode(), resultEnum.getMsg());
    }

    public static PageResult error(int code, String msg){
        return new PageResult(code, msg);
    }
}
