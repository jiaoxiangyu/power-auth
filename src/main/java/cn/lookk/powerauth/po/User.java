package cn.lookk.powerauth.po;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

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
@ToString
public class User {
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
    private LocalDateTime createTime;
    //跟新时间
    private LocalDateTime updateTime;
    //删除标记
    private int del;
}
