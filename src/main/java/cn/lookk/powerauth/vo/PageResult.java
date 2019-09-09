package cn.lookk.powerauth.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName: PageResult
 * @Description: TODO
 * @Author jiaoxiangyu
 * @Date 2019/9/10
 * @Version 1.0
 * @Since JDK1.8
 */
@Data
@AllArgsConstructor
public class PageResult implements Serializable {
    private Integer code;
    private String msg;
    private Object data;
    private Integer count;

}
