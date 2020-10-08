package cn.lookk.powerauth.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * @ClassName: Role
 * @Description: TODO
 * @Author jiaoxiangyu
 * @Date 2019/9/12
 * @Version 1.0
 * @Since JDK1.8
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Role {
    private Integer id;
    private String name;
    private String description;
    //创建时间
    private Date createTime;
    //跟新时间
    private Date updateTime;
}
