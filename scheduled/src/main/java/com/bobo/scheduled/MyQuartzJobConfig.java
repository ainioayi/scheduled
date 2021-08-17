package com.bobo.scheduled;

/**
 * @author Bobo
 * @date 2021/8/17
 * @apiNote Quartz配置类
 *
 * quartz实现分布式，基于数据库持久化这些配置的Identity实现
 * withIdentity("job1", "group1")
 * withIdentity("trigger1", "group1")
 */

import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyQuartzJobConfig {

    /**
     * 定时任务的详情 MyQuartzJob的executeInternal方法
     * @return
     */
    @Bean
    public JobDetail jobDetail(){
        JobDetail jobDetail = JobBuilder.newJob(MyQuartzJob.class)
                .withIdentity("job1", "group1")
                .storeDurably()
                .build();
        return jobDetail;
    }

    /**
     * 触发器
     * @return
     */
    @Bean
    public Trigger trigger(){
        Trigger trigger = TriggerBuilder.newTrigger()
                .forJob(jobDetail())
                .withIdentity("trigger1", "group1")
                .startNow()
                //相当于corn表达式
                .withSchedule(CronScheduleBuilder.cronSchedule("5 * * * * ?"))
                .build();
        return trigger;
    }
}
