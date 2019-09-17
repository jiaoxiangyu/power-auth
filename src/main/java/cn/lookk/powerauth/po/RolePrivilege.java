package cn.lookk.powerauth.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
public class RolePrivilege {
    private Integer id;
    private Integer roleId;
    private Integer privilegeId;
    //是否开启
    private Boolean status;
}
