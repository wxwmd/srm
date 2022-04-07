package com.jaezi.srminterface.config;

import com.alibaba.fastjson.JSONArray;
import com.jaezi.common.constant.WeChatConst;
import com.jaezi.common.util.FileUtil;
import com.jaezi.common.util.HttpUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.text.MessageFormat;
import java.util.Map;

/**
 * @author yzl
 * @version v1.0
 * @corporation
 * @date 2021/8/3  13:07
 * @description 消息配置类
 */
@Configuration
public class MsgConfig {

    @Value("${weChat.appId}")
    private String appId;

    @Value("${weChat.secret}")
    private String secret;

    @Value("${weChat.tokenUrl}")
    private String tokenUrl;

    @Value("${weChat.templateUrl}")
    private String templateUrl;

    @Value("${weChat.userUrl}")
    private String userUrl;

    @Value("${weChat.openId}")
    private String openId;

    @Value("${weChat.userInfoUrl}")
    private String userInfoUrl;

    public String getUserInfoUrl() {
        return userInfoUrl;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getUserUrl() {
        return userUrl;
    }

    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getAppId() {
        return appId;
    }

    public String getSecret() {
        return secret;
    }

    public String getTokenUrl() {
        return tokenUrl;
    }

    public String getTemplateUrl() {
        return templateUrl;
    }

    /**
     * 项目启动时，初始化字典到缓存
     */
    @PostConstruct
    public void init() {
        loadingWeChatToken();
    }

    /**
     * 项目启动时，读取WeChatToken缓存
     *
     * @return
     */
    public void loadingWeChatToken() {
        token = FileUtil.fileRead(this.getClass().getResourceAsStream(WeChatConst.FILE_NAME));
    }

    /**
     * 获取微信token
     *
     * @return String
     * TODO 方法逻辑
     */
    public String getToken(RestTemplate restTemplate) {
        String tokenUrl = MessageFormat.format(getTokenUrl(), getAppId(), getSecret());
        ResponseEntity<String> responseEntity = HttpUtil.get(tokenUrl, String.class, null, restTemplate);
        Map<String, Object> map = JSONArray.parseObject(responseEntity.getBody());

        String tokenPath = this.getClass().getClassLoader().getResource(WeChatConst.FILE_NAME).getPath();
        FileUtil.fileWrite(tokenPath, map.get(WeChatConst.ACCESS_TOKEN).toString());

        return map.get(WeChatConst.ACCESS_TOKEN).toString();
    }

}
