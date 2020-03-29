package cn.lookk.powerauth.util;

import cn.lookk.powerauth.po.User;
import com.alibaba.fastjson.JSONObject;

/**
 * @ClassName: Test
 * @Description: TODO
 * @Author jiaoxiangyu
 * @Date 2019/9/11
 * @Version 1.0
 * @Since JDK1.8
 */
public class Test {

    public static void main(String[] args) {
        User user = new User();
        user.setRoleId(1);
        user.setId(1l);

        System.out.println(JSONObject.toJSON(user));
    }
}
