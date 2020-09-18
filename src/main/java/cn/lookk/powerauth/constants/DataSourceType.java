package cn.lookk.powerauth.constants;

import lombok.Getter;
/**
 * @ClassName: DataSourceType
 * @Description: 数据源枚举
 * @Author jiaoxiangyu
 * @Date 2020/7/27
 * @Version 1.0
 * @Since JDK1.8
 */
public enum DataSourceType {
    read("read", "从库"),
    write("write", "主库");
    @Getter
    private String type;
    @Getter
    private String name;

    DataSourceType(String type, String name) {
        this.type = type;
        this.name = name;
    }
}
