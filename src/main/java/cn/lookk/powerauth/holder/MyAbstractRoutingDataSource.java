package cn.lookk.powerauth.holder;

import cn.lookk.powerauth.constants.DataSourceType;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @ClassName: MyAbstractRoutingDataSource
 * @Description: 多数据源切换路由
 * @Author jiaoxiangyu
 * @Date 2020/7/27
 * @Version 1.0
 * @Since JDK1.8
 */
public class MyAbstractRoutingDataSource extends AbstractRoutingDataSource {
    //从库数量
    private final int dataSourceNumber;
    //读库次数统计,用于负载
    private AtomicInteger count = new AtomicInteger(0);

    public MyAbstractRoutingDataSource(int dataSourceNumber) {
        this.dataSourceNumber = dataSourceNumber;
    }

    @Override
    protected Object determineCurrentLookupKey() {
        String typeKey = DataSourceContextHolder.getJdbcType();
        //主库 写
        if (typeKey.equals(DataSourceType.write.getType())) {
            return DataSourceType.write.getType();
        }

        //从库 读 简单负载均衡
        int number = count.getAndAdd(1);
        int lookupKey = number % dataSourceNumber;
        return new Integer(lookupKey);
    }
}
