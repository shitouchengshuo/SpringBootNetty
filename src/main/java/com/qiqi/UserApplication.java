package com.qiqi;

import com.qiqi.service.NettyBootstrap;
import com.qiqi.service.SpringHandlerManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class UserApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(UserApplication.class, args);
        SpringHandlerManager.register(context);
        context.getBean(NettyBootstrap.class).start(10006);
    }

}

