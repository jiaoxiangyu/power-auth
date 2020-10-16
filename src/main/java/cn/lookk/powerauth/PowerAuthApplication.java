package cn.lookk.powerauth;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import com.alibaba.dubbo.config.spring.context.annotation.EnableDubboConfig;
import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
@EnableDubboConfiguration
@SpringBootApplication
@MapperScan(basePackages = "cn.lookk.powerauth.mapper")
@ComponentScan("cn.lookk.*")
public class PowerAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(PowerAuthApplication.class, args);
    }

}
