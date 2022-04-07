package com.jaezi.srminterface.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.api.client.repackaged.com.google.common.base.Strings;
import com.jaezi.common.base.BaseException;
import com.jaezi.common.base.BaseService;
import com.jaezi.common.constant.WeChatConst;
import com.jaezi.common.util.HttpUtil;
import com.jaezi.srminterface.config.MsgConfig;
import com.jaezi.srminterface.dao.WeChatUserDao;
import com.jaezi.srminterface.model.WeChatUser;
import com.jaezi.srminterface.model.WeChatUserInfo;
import com.jaezi.srminterface.vo.WeChatUserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.client.RestTemplate;

import java.text.MessageFormat;
import java.util.*;

/**
 * @author yzl
 * @version v1.0
 * @corporation
 * @date 2021/8/3  13:07
 * @description 用户微信绑定信息逻辑层
 */

@Service
public class WeChatUserService extends BaseService<WeChatUser, WeChatUserVo> {

    private WeChatUserDao weChatUserDao;
    private RestTemplate restTemplate;
    private MsgConfig msgConfig;

    @Autowired
    public void setBaseDao(WeChatUserDao weChatUserDao, RestTemplate restTemplate, MsgConfig msgConfig) {
        this.weChatUserDao = weChatUserDao;
        this.restTemplate = restTemplate;
        this.msgConfig = msgConfig;
    }

    public String getCron() {
//        return "*/59 * * * * ?";
        return "0 0 */1 * * ?";
    }

    public void weChatTask() throws BaseException {
        ResponseEntity<JSONObject> responseEntity = weChatUser(weChatUserUrl(), JSONObject.class, null, restTemplate);
        if (responseEntity.getStatusCodeValue() == 200) {
//            if (weChatUserDao.count() == 0) {
//                WeChatUser weChatUser = new WeChatUser();
//                weChatUser.setOpenId(msgConfig.getOpenId());
//                weChatUserDao.add(weChatUser);
//            }
            JSONObject jsonObject = responseEntity.getBody();
            if (!ObjectUtils.isEmpty(jsonObject.get("data"))) {
                //判断输出对象的类型
                JSONObject dataJsonObject = JSONObject.parseObject(JSONObject.toJSONString(jsonObject.get("data")));
                List<String> openIdList = JSON.parseArray(dataJsonObject.get("openid").toString(), String.class);

                List<WeChatUserInfo> weChatUserInfoList = new Vector<>();
                WeChatUserInfo weChatUserInfo = new WeChatUserInfo();
                weChatUserInfo.setOpenid(msgConfig.getOpenId());
                weChatUserInfoList.add(weChatUserInfo);
                openIdList.parallelStream().forEach(s -> {
                    weChatUserInfo.setOpenid(s);

                    weChatUserInfoList.add(weChatUserInfo);
                });

                JSONObject jsonObj = new JSONObject();

                jsonObj.put("user_list", weChatUserInfoList);

                ResponseEntity<JSONObject> responseEntity1 = weChatUserInfo(weChatUserInfoUrl(), JSONObject.class, jsonObj, restTemplate);
                JSONArray jsonArray = responseEntity1.getBody().getJSONArray("user_info_list");
                int id = weChatUserDao.getMaxId();
                List<WeChatUser> weChatUserList = new Vector<>();
                for (int i = 0; i < jsonArray.size(); i++) {
                    WeChatUser weChatUser = new WeChatUser();
                    weChatUser.setOpenId(jsonArray.getJSONObject(i).get("openid").toString());
                    weChatUser.setNickName(jsonArray.getJSONObject(i).get("nickname").toString());
                    weChatUser.setId(++id);
                    weChatUserList.add(weChatUser);
                }

                if (!ObjectUtils.isEmpty(weChatUserList)) {
                    synchronized (this) {
                        weChatUserDao.addBatch(weChatUserList);
                        weChatUserDao.updateBatch(weChatUserList);
                    }
                }
            }
        }
    }

    /**
     * 微信获取用户列表
     *
     * @return String
     * TODO 方法逻辑
     */
    public ResponseEntity weChatUser(String url, Class responseType, Object object, RestTemplate restTemplate) throws BaseException {
        ResponseEntity<JSONObject> responseEntity = HttpUtil.get(url, responseType, object, restTemplate);
        if (!ObjectUtils.isEmpty(responseEntity.getBody().getString("total"))) {
            //发送成功后返回
            return responseEntity;
        } else if (responseEntity.getBody().getString(WeChatConst.ERR_CODE).equals("42001")) {
            //如果发现token过期
            msgConfig.setToken(msgConfig.getToken(restTemplate));
            url = weChatUserUrl();

            return weChatUser(url, responseType, object, restTemplate);
        } else {
            throw new BaseException("发送失败");
        }
    }

    /**
     * 微信获取用户列表
     *
     * @return String
     * TODO 方法逻辑
     */
    public ResponseEntity weChatUserInfo(String url, Class responseType, Object object, RestTemplate restTemplate) throws BaseException {
        ResponseEntity<JSONObject> responseEntity = HttpUtil.post(url, responseType, object, restTemplate);
        if (!ObjectUtils.isEmpty(responseEntity.getBody().getString("user_info_list"))) {
            //发送成功后返回
            return responseEntity;
        } else if (responseEntity.getBody().getString(WeChatConst.ERR_CODE).equals("42001")) {
            //如果发现token过期
            msgConfig.setToken(msgConfig.getToken(restTemplate));
            url = weChatUserInfoUrl();
            return weChatUserInfo(url, responseType, object, restTemplate);
        } else {
            throw new BaseException("发送失败");
        }
    }


    /**
     * 微信获取用户列表url
     */
    public String weChatUserInfoUrl() {
        if (Strings.isNullOrEmpty(msgConfig.getToken())) {
            msgConfig.setToken(msgConfig.getToken(restTemplate));
        }

        return MessageFormat.format(msgConfig.getUserInfoUrl(), msgConfig.getToken());
    }


    /**
     * 微信获取用户列表url
     */
    public String weChatUserUrl() {
        if (Strings.isNullOrEmpty(msgConfig.getToken())) {
            msgConfig.setToken(msgConfig.getToken(restTemplate));
        }

        String openId = weChatUserDao.getOpenIdByMaxId();
        if (!ObjectUtils.isEmpty(openId)) {
            msgConfig.setOpenId(openId);
        }

        return MessageFormat.format(msgConfig.getUserUrl(), msgConfig.getToken(), msgConfig.getOpenId());
    }
}
