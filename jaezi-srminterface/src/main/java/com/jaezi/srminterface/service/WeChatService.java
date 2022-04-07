package com.jaezi.srminterface.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.api.client.repackaged.com.google.common.base.Strings;
import com.jaezi.common.base.BaseException;
import com.jaezi.common.constant.WeChatConst;
import com.jaezi.common.manager.ThreadManager;
import com.jaezi.common.util.HttpUtil;
import com.jaezi.srminterface.config.MsgConfig;
import com.jaezi.srminterface.model.WeChatData;
import com.jaezi.srminterface.model.WeChatFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.text.MessageFormat;
import java.util.Map;

/**
 * @author yzl
 * @version v1.0
 * @corporation
 * @date 2021/8/3  13:07
 * @description 微信逻辑层
 */

public class WeChatService implements MsgService {

    private final RestTemplate restTemplate;
    private final MsgConfig msgConfig;

    public WeChatService(RestTemplate restTemplate, MsgConfig msgConfig) {
        this.restTemplate = restTemplate;
        this.msgConfig = msgConfig;
    }

    @Override
    public String send() {
        ThreadManager.getInstance().syncExecute(() -> {
            try {
                JSONObject jsonObject = new JSONObject();
                /**
                 * 接收人
                 */
                jsonObject.put("touser", "o7n5d50Qy9XGilXeTCn9pFxDIU4U");
                /**
                 * 模板ID
                 */
                jsonObject.put("template_id", "SH3Qfru2FuxNsHRbMW3_DG4fK3WeI8Sm0rQhRoEyj7k");

                WeChatFormat weChatFormat = new WeChatFormat();
                weChatFormat.setValue("你好");

                WeChatData weChatData = new WeChatData();
                weChatData.setTitle(weChatFormat);
                //内容
                jsonObject.put("data", weChatData);

                ThreadWeChat(jsonObject);
            } catch (BaseException e) {
                e.printStackTrace();
            }
        });
        return "";
    }

    /**
     * 线程微信处理
     */
    public void ThreadWeChat(JSONObject jsonObject) throws BaseException {
        String customerUrl = weChatTemplateUrl();

        ResponseEntity<String> responseEntity = weChatTemplateMsg(customerUrl, String.class, jsonObject, restTemplate);
    }

    /**
     * 微信发送模板
     *
     * @return String
     * TODO 方法逻辑
     */
    public ResponseEntity weChatTemplateMsg(String url, Class responseType, Object object, RestTemplate restTemplate) throws BaseException {
        ResponseEntity<String> responseEntity = HttpUtil.post(url, responseType, object, restTemplate);
        Map<String, Object> map = JSONArray.parseObject(responseEntity.getBody());
        if (map.get(WeChatConst.ERR_CODE).toString().equals("42001")) {
            //如果发现token过期
            msgConfig.setToken(msgConfig.getToken(restTemplate));
            url = weChatTemplateUrl();

            return weChatTemplateMsg(url, String.class, object, restTemplate);
        } else if (map.get(WeChatConst.ERR_CODE).toString().equals("0")) {
            //发送成功后返回
            return responseEntity;
        } else {
            throw new BaseException("发送失败");
        }
    }

    /**
     * 微信发送模板url
     */
    public String weChatTemplateUrl() {
        if (Strings.isNullOrEmpty(msgConfig.getToken())) {
            msgConfig.setToken(msgConfig.getToken(restTemplate));
        }
        return MessageFormat.format(msgConfig.getTemplateUrl(), msgConfig.getToken());
    }

}
