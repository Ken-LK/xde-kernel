package com.xde.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author <a href="mailto:liukailk.ken@gmail.com"> Ken </a>
 * @date 2020/11/10 8:48 上午
 **/
@SpringBootApplication
@EnableDiscoveryClient
public class AuthApplication {


    public static void main(String[] args) {
        SpringApplication.run(AuthApplication.class, args);
    }


}
