package com.bobo.scheduled;

import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.context.XxlJobHelper;
import com.xxl.job.core.handler.annotation.XxlJob;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import sun.rmi.runtime.Log;

/**
 * @author Bobo
 * @date 2021/8/17
 * @apiNote
 */
@Component
@Slf4j
public class MyXxlJob {

    /**
     * 2021-08-17 20:24:05.365  INFO 8172 --- [           main] c.xxl.job.core.executor.XxlJobExecutor   : >>>>>>>>>>> xxl-job register jobhandler success, name:myXxlJobHandler, jobHandler:com.xxl.job.core.handler.impl.MethodJobHandler@2367b8[class com.bobo.scheduled.MyXxlJob#demoJobHandler]
     * @throws Exception
     */
    @XxlJob("myXxlJobHandler")
    public ReturnT<String> demoJobHandler() throws Exception {
        XxlJobHelper.log("XXL-JOB, Hello World.");
        log.info("demoJobHandler...");
        return ReturnT.SUCCESS;
    }
}
