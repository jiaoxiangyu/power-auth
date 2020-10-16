package cn.lookk.powerauth.po;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
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
@TableName("tb_role")
public class Role implements Serializable {
    private Integer id;
    private String name;
    private String description;
    //创建时间
    private Date createTime;
    //跟新时间
    private Date updateTime;
}
