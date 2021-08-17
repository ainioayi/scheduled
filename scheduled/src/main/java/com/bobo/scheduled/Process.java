package com.bobo.scheduled;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @author Bobo
 * @date 2021/8/17
 * @apiNote
 */
@Component
@Slf4j
public class Process {

    @Async
    public void process(){
        log.info("process......start");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("process......end");
    }
}
