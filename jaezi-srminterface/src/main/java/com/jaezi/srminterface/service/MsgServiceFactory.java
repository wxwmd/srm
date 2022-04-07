package com.jaezi.srminterface.service;

import com.jaezi.common.constant.WeChatConst;
import com.jaezi.srminterface.config.MsgConfig;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @author yzl
 * @version v1.0
 * @corporation
 * @date 2021/8/3  13:07
 * @description 消息工厂
 */

@Service
public class MsgServiceFactory {

    private final RestTemplate restTemplate;
    private final MsgConfig msgConfig;

    public MsgServiceFactory(RestTemplate restTemplate, MsgConfig msgConfig) {
        this.restTemplate = restTemplate;
        this.msgConfig = msgConfig;
    }

    public MsgService getMsg(String shapeType) {
        if (shapeType == null) {
            return null;
        }
        if (shapeType.equalsIgnoreCase(WeChatConst.MSG_TYPE)) {
            return new WeChatService(restTemplate, msgConfig);
        }
        return null;
    }

}
