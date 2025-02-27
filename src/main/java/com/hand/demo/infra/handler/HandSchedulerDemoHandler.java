package com.hand.demo.infra.handler;

import com.hand.demo.app.service.SoHeaderService;
import org.hzero.boot.scheduler.infra.annotation.JobHandler;
import org.hzero.boot.scheduler.infra.enums.ReturnT;
import org.hzero.boot.scheduler.infra.handler.IJobHandler;
import org.hzero.boot.scheduler.infra.tool.SchedulerTool;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

@JobHandler("HandSchedulerDemoHandler")
public class HandSchedulerDemoHandler implements IJobHandler {
    @Autowired
    private SoHeaderService soHeaderService;

    @Autowired
    private HandMessageService handMessageService;

    @Override
    public ReturnT execute(Map<String, String> map, SchedulerTool tool) {
        tool.info("=======开始任务======");
        try {
            soHeaderService.stateChange();
        }catch (Exception e){
            tool.error("=======任务失败======");
            return ReturnT.FAILURE;
        }
        try{
            handMessageService.message(map);
        }catch (Exception e){
            tool.error("=======消息发送失败======");
            return ReturnT.FAILURE;
        }
        tool.info("=======消息发送成功======");
        tool.info("=======任务成功======");
        return ReturnT.SUCCESS;
    }
}
