package cn.lookk.powerauth.util;

/**
 * @ClassName: PageUtil
 * @Description: TODO
 * @Author jiaoxiangyu
 * @Date 2019/9/10
 * @Version 1.0
 * @Since JDK1.8
 */
public class PageUtil {

    public static int pageStart(int page, int limit, int total){
        int start=(page-1)*limit;
        if (start>=total){
            start=start-limit;
        }
        return start;
    }
}
