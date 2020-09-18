package cn.lookk.powerauth.holder;

import cn.lookk.powerauth.constants.DataSourceType;
import lombok.extern.slf4j.Slf4j;

/**
 * @ClassName: DataSourceContextHolder
 * @Description: 数据源本地线程全局变量
 * @Author jiaoxiangyu
 * @Date 2020/7/27
 * @Version 1.0
 * @Since JDK1.8
 */
@Slf4j
public class DataSourceContextHolder  {
    private static final ThreadLocal<String> local = new ThreadLocal<String>();

    public static ThreadLocal<String> getLocal() {
        return local;
    }

    /**
     * 读可能是多个库
     */
    public static void read() {

        local.set(DataSourceType.read.getType());
    }

    /**
     * 写只有一个库
     */
    public static void write() {
        log.debug("writewritewrite");
        local.set(DataSourceType.write.getType());
    }

    public static String getJdbcType() {
        return local.get();
    }
}
