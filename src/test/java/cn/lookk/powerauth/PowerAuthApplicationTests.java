package cn.lookk.powerauth;

import cn.lookk.powerauth.dao.RoleMapper;
import cn.lookk.powerauth.po.Role;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest

public class PowerAuthApplicationTests {

    @Autowired
    private RoleMapper roleMapper;

  /*  @Test
    public void contextLoads() {
        Role role = new Role();
        role.setName("名称");
        role.setDescription("描述");
        roleMapper.add(role);
    }*/

}
