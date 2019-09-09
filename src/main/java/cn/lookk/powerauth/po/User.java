package cn.lookk.powerauth.po;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @ClassName: User
 * @Description: TODO
 * @Author jiaoxiangyu
 * @Date 2019/9/7
 * @Version 1.0
 * @Since JDK1.8
 */
@Data
@NoArgsConstructor
public class User {
    private Long id;
    //名字
    private String name;
    //创建时间
    private LocalDateTime createTime;
    //跟新时间
    private LocalDateTime updateTime;
    //删除标记
    private int delete;
}
