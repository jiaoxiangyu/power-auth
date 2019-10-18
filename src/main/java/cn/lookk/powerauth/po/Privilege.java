package cn.lookk.powerauth.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName: Privilege
 * @Description: TODO
 * @Author jiaoxiangyu
 * @Date 2019/9/16
 * @Version 1.0
 * @Since JDK1.8
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Privilege {
    private Integer id;
    private String name;
    private String url;
    private String description;
}
