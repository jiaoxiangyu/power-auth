package cn.lookk.powerauth.util;

import cn.lookk.powerauth.po.User;
import cn.lookk.powerauth.service.ILoginService;
import com.alibaba.fastjson.JSONObject;
import org.apache.naming.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @ClassName: Test
 * @Description: TODO
 * @Author jiaoxiangyu
 * @Date 2019/9/11
 * @Version 1.0
 * @Since JDK1.8
 */
public class Test {

    public static void main(String[] args) throws IllegalAccessException, InstantiationException {
        Class clazz = User.class;
        User user = (User) clazz.newInstance();
        user.setId(1l);

        System.out.println(JSONObject.toJSON(user.getId()));
    }
}
