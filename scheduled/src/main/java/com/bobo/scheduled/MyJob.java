package com.bobo.scheduled;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author Bobo
 * @date 2021/8/17
 * @apiNote
 */
@Slf4j
@Component
public class MyJob {

    @Autowired
    private Process process;

    /**
     * initialDelay = 5000 启动后5s再执行定时任务
     *
     * 2021-08-17 15:12:26.932  INFO 8484 --- [           main] com.bobo.scheduled.ScheduledApplication  : Started ScheduledApplication in 1.013 seconds (JVM running for 1.339)
     * 2021-08-17 15:12:31.938  INFO 8484 --- [   scheduling-1] com.bobo.scheduled.MyJob                 : doProcess......start
     *
     * fixedDelay = 3000 上个任务结束3s后。fixedDelay 的间隔是前次任务的结束与下次任务的开始
     *
     *
     *
     * 单线程
     2021-08-17 15:12:31.938  INFO 8484 --- [   scheduling-1] com.bobo.scheduled.MyJob                 : doProcess......start
     2021-08-17 15:12:31.938  INFO 8484 --- [   scheduling-1] com.bobo.scheduled.Process               : process......start
     2021-08-17 15:12:36.946  INFO 8484 --- [   scheduling-1] com.bobo.scheduled.Process               : process......end
     2021-08-17 15:12:36.946  INFO 8484 --- [   scheduling-1] com.bobo.scheduled.MyJob                 : doProcess......end
     2021-08-17 15:12:39.951  INFO 8484 --- [   scheduling-1] com.bobo.scheduled.MyJob                 : doProcess......start

     多线程异步下，会认为主线程立马运行完成，不会阻塞等待
     2021-08-17 15:16:21.626  INFO 6616 --- [   scheduling-1] com.bobo.scheduled.MyJob                 : doProcess......start
     2021-08-17 15:16:21.628  INFO 6616 --- [   scheduling-1] com.bobo.scheduled.MyJob                 : doProcess......end
     2021-08-17 15:16:21.639  INFO 6616 --- [         task-1] com.bobo.scheduled.Process               : process......start
     2021-08-17 15:16:24.633  INFO 6616 --- [   scheduling-1] com.bobo.scheduled.MyJob                 : doProcess......start
     2021-08-17 15:16:24.633  INFO 6616 --- [   scheduling-1] com.bobo.scheduled.MyJob                 : doProcess......end
     2021-08-17 15:16:24.633  INFO 6616 --- [         task-2] com.bobo.scheduled.Process               : process......start
     2021-08-17 15:16:26.650  INFO 6616 --- [         task-1] com.bobo.scheduled.Process               : process......end

     fixedRate 任务两次执行时间间隔是任务的开始点
     单线程，因为业务用时较长，超过时间间隔，会立马开始
     2021-08-17 15:24:09.290  INFO 3416 --- [   scheduling-1] com.bobo.scheduled.MyJob                 : doProcess......start
     2021-08-17 15:24:09.290  INFO 3416 --- [   scheduling-1] com.bobo.scheduled.Process               : process......start
     2021-08-17 15:24:14.298  INFO 3416 --- [   scheduling-1] com.bobo.scheduled.Process               : process......end
     2021-08-17 15:24:14.298  INFO 3416 --- [   scheduling-1] com.bobo.scheduled.MyJob                 : doProcess......end
     2021-08-17 15:24:14.298  INFO 3416 --- [   scheduling-1] com.bobo.scheduled.MyJob                 : doProcess......start
     多线程异步下
     2021-08-17 15:24:55.627  INFO 9080 --- [   scheduling-1] com.bobo.scheduled.MyJob                 : doProcess......start
     2021-08-17 15:24:55.629  INFO 9080 --- [   scheduling-1] com.bobo.scheduled.MyJob                 : doProcess......end
     2021-08-17 15:24:55.638  INFO 9080 --- [         task-1] com.bobo.scheduled.Process               : process......start
     2021-08-17 15:24:58.632  INFO 9080 --- [   scheduling-1] com.bobo.scheduled.MyJob                 : doProcess......start
     2021-08-17 15:24:58.632  INFO 9080 --- [   scheduling-1] com.bobo.scheduled.MyJob                 : doProcess......end
     2021-08-17 15:24:58.632  INFO 9080 --- [         task-2] com.bobo.scheduled.Process               : process......start
     2021-08-17 15:25:00.646  INFO 9080 --- [         task-1] com.bobo.scheduled.Process               : process......end
     */
    @Scheduled(fixedRate = 3000,initialDelay = 5000)
    public void doProcess(){
        log.info("doProcess......start");
        process.process();
        log.info("doProcess......end");
    }

}
