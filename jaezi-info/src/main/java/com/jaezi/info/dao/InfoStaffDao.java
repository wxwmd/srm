package com.jaezi.info.dao;

import com.jaezi.common.base.BaseDao;
import com.jaezi.info.model.InfoStaff;
import com.jaezi.info.vo.InfoStaffVo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author yzl
 * @version v1.0
 * @corporation
 * @date 2018/11/19 15:39
 * @description 消息信息抄送人数据访问对象
 */
@Repository
public interface InfoStaffDao extends BaseDao<InfoStaff, InfoStaffVo> {
    /**
     * 批量添加消息抄送人信息
     *
     * @param infoStaffList 消息抄送数据
     * @return
     */
    int addBath(List<InfoStaff> infoStaffList);
}
