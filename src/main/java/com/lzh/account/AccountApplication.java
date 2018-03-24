package com.lzh.account;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.ApplicationContext;

import com.lzh.common.annotation.MyBatisRepository;

@SpringCloudApplication
@EnableEurekaClient
//@EnableFeignClients
@MapperScan(basePackages = "com.lzh.account.persistence", annotationClass = MyBatisRepository.class)
public class AccountApplication {

    public static void main(String[] args) {
    	
    	ApplicationContext context =  SpringApplication.run(AccountApplication.class, args);
        
    }
}
