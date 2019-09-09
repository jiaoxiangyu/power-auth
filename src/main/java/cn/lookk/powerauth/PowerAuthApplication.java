package cn.lookk.powerauth;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "cn.lookk.powerauth.dao")
public class PowerAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(PowerAuthApplication.class, args);
    }

}
