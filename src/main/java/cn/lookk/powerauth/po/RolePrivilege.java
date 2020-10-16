package cn.lookk.powerauth.po;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @ClassName: RolePrivilege
 * @Description: TODO
 * @Author jiaoxiangyu
 * @Date 2019/9/16
 * @Version 1.0
 * @Since JDK1.8
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("tb_rolePrivilege")
public class RolePrivilege implements Serializable {
    private Integer id;
    private Integer roleId;
    private String roleName;
    private Integer privilegeId;
    private String privilegeName;
    private String privilegeUrl;
    //是否开启
    private Boolean status;
    //创建时间
    private Date createTime;
    //跟新时间
    private Date updateTime;
}
