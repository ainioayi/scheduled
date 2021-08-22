package com.bobo.scheduled;

import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.context.XxlJobHelper;
import com.xxl.job.core.handler.annotation.XxlJob;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import sun.rmi.runtime.Log;

import java.util.Arrays;
import java.util.List;

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
    public ReturnT<String> myXxlJobHandler() throws Exception {
//        XxlJobHelper.log("XXL-JOB, Hello World.");//这条日志会被记录到xxl-job中
//        log.info("demoJobHandler...");

        /**
         * 分片广播
         * 1、分片任务场景：10个执行器的集群来处理10w条数据，每台机器只需要处理1w条数据，耗时降低10倍；
         * 2、广播任务场景：广播执行器机器运行shell脚本、广播集群节点进行缓存更新等
         *
         * index：当前分片序号(从0开始)，执行器集群列表中当前执行器的序号；
         * total：总分片数，执行器集群的总机器数量；
         *
         *
         *
         */
        int shardIndex = XxlJobHelper.getShardIndex();
        int shardTotal = XxlJobHelper.getShardTotal();

        List<Integer> userIdList = Arrays.asList(1, 2, 3, 4);
        for (int i = 0; i < userIdList.size(); i++) {

            /*
            分片广播，拿到当前执行器的序号和执行器集群的总机器数量做求余，实现平均分配
            假设有2台服务器
                     * 0/2=0
                     * 1/2=1
                     * 2/2=0
                     * 3/2=1
                     效果就是，1,3user会在第一台服务器执行,2,4 会在第二台服务器执行
             */
            if(i%shardTotal==shardIndex){
                //这条日志会被记录到xxl-job中
                XxlJobHelper.log("myXxlJobHandler excute... user={},shardIndex={},shardTotal={}",userIdList.get(i),shardIndex,shardTotal);
            }
        }
        return ReturnT.SUCCESS;
    }
}
