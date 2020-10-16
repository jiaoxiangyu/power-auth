package cn.lookk.powerauth.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

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
@ToString
@TableName("tb_user")
public class User implements Serializable {
    @JsonSerialize(using= ToStringSerializer.class)
    private Long id;
    //名字
    private String name;
    //手机号
    private String phone;
    //密码
    private String pwd;
    //角色id
    private int roleId;
    //角色
    private String role;
    //创建时间
    private Date createTime;
    //跟新时间
    private Date updateTime;
    //删除标记
    private int del;
}
