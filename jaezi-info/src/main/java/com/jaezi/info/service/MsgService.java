package com.jaezi.info.service;

import com.jaezi.common.base.BaseService;
import com.jaezi.common.util.JwtUtil;
import com.jaezi.info.dao.MidMsgPersonDao;
import com.jaezi.info.dao.MsgDao;
import com.jaezi.info.dto.MsgDto;
import com.jaezi.info.model.MidMsgPerson;
import com.jaezi.info.model.Msg;
import com.jaezi.info.vo.MsgVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.*;

/**
 * @author yzl
 * @version v1.0
 * @corporation
 * @date 2021/7/22 15:49
 * @description 公告信息服务接口实现类
 */
@Service
public class MsgService extends BaseService<Msg, MsgVo> {

    private MsgDao msgDao;
    private MidMsgPersonDao midMsgPersonDao;

    @Autowired
    public void setBaseDao(MsgDao msgDao, MidMsgPersonDao midMsgPersonDao) {
        super.setBaseDao(msgDao);
        this.msgDao = msgDao;
        this.midMsgPersonDao = midMsgPersonDao;
    }

    /**
     * 添加新闻，并设置可见性
     *
     * @param msgDto 新闻信息和可见人员信息id
     * @return int  添加成功数
     * @author yzl
     * @date 2021/9/1
     * @since 1.0
     */
    public int add(MsgDto msgDto) {
        Msg msg = new Msg();
        BeanUtils.copyProperties(msgDto, msg);
        msg.setCreateTime(System.currentTimeMillis());
        msg.setCreateUser(JwtUtil.getRealName());
        List<Integer> visiblePersonList = msgDto.getVisiblePersonList();
        Integer visible = Optional.ofNullable(msg.getVisible()).orElse(2);
        int result = msgDao.add(msg);
        if (result > 0) {
            if (visible == 0) {
                List<MidMsgPerson> midMsgPersonList = new LinkedList<>();
                for (Integer personId : visiblePersonList) {
                    MidMsgPerson midMsgPerson = new MidMsgPerson();
                    midMsgPerson.setPersonId(personId);
                    midMsgPerson.setMsgId(msg.getId());
                    midMsgPersonList.add(midMsgPerson);
                }
                if (!ObjectUtils.isEmpty(midMsgPersonList)) {
                    midMsgPersonDao.saveBath(midMsgPersonList);
                }
            }
        }
        return result;
    }

    /**
     * 新闻置顶/取消
     *
     * @param id 公告id
     * @return
     */
    public int updateTop(Integer id, Integer sort) {
        return msgDao.updateTop(id, sort);
    }

    /**
     * 查询首页新闻
     *
     * @param
     * @return JsonResult 企业新闻 enterpriseNews 5条 企业公告 enterpriseAnnouncement 5条 行业动态 industryDynamics 10条
     * @author yx
     * @date 2021年9月26日15:27:24
     * @since 1.0
     */
    public Map<String, List<Msg>> getNewest() {
        Map<String, List<Msg>> result = new HashMap<>();
        List<Msg> enterpriseNews = msgDao.getNewest(1);
        List<Msg> enterpriseAnnouncement;

        Integer userType = JwtUtil.getUserType();
        if (userType == 1) {
            Map<String, String> filter = new HashMap<>();
            filter.put("userType", String.valueOf(userType));
            filter.put("userId", String.valueOf(JwtUtil.getUserId(JwtUtil.getRequest().getHeader("Credential"))));

            enterpriseAnnouncement = msgDao.findByVisible(filter);
        } else {
            enterpriseAnnouncement = msgDao.getNewest(2);
        }

        List<Msg> industryDynamics = msgDao.getNewest(3);
        //企业新闻
        result.put("enterpriseNews", enterpriseNews);
        //企业公告
        result.put("enterpriseAnnouncement", enterpriseAnnouncement);
        //行业动态
        result.put("industryDynamics", industryDynamics);
        return result;
    }

}
