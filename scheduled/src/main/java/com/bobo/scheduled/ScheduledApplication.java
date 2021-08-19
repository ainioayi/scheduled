package com.bobo.scheduled;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

//@EnableScheduling   //开启任务调度-单机
@EnableAsync        //开启异步
@SpringBootApplication
public class ScheduledApplication {

    public static void main(String[] args) {
        SpringApplication.run(ScheduledApplication.class, args);
    }

}
