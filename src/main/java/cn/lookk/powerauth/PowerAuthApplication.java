package cn.lookk.powerauth;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@MapperScan(basePackages = "cn.lookk.powerauth.mapper")
@ComponentScan("cn.lookk.*")
public class PowerAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(PowerAuthApplication.class, args);
    }

}
