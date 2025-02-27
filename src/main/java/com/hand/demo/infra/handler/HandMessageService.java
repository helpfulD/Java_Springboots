package com.hand.demo.infra.handler;

import org.hzero.boot.message.MessageClient;
import org.hzero.boot.message.entity.Receiver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
@Component
public class HandMessageService {
    @Autowired
    private MessageClient messageClient;

    public void message(Map<String, String> map){
        long tenantId = 0L;
        String serverCode = "HZERO_ORDER_1653_STATE";
        String messageTemplateCode = "HZERO_ORDER_1653_STATE";
        List<Receiver> receiverList = new ArrayList<>();
        Receiver receiver = new Receiver();
        receiver.setEmail("helpful888@163.com");
        receiverList.add(receiver);
        map.put("state", "已关闭");
        messageClient.sendEmail(tenantId, serverCode, messageTemplateCode,
                receiverList, map, null);
    }
}
