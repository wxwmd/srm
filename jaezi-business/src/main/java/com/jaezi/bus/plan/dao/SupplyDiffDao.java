package com.jaezi.bus.plan.dao;

import com.jaezi.bus.plan.dto.AbnormalDeliverySumDto;
import com.jaezi.bus.plan.dto.SupplyDiffSumDto;
import com.jaezi.bus.plan.model.SupplyDiff;
import com.jaezi.bus.plan.vo.SupplyDiffVo;
import com.jaezi.common.base.BaseDao;
import org.apache.ibatis.annotations.MapKey;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author yzl
 * @version v1.0
 * @corporation
 * @date 2021/8/4 15:39
 * @description 供需差异数据访问层
 */

@Repository
public interface SupplyDiffDao extends BaseDao<SupplyDiff, SupplyDiffVo> {
    /**
     * 查询所有供需差异数据
     *
     * @return SupplyDiff>
     */
    List<SupplyDiff> getAll();

    /**
     * 批量更新供需差异
     *
     * @param list
     * @return int
     */
    int updateBath(List<SupplyDiff> list);

    /**
     * 获取供需差异数据汇总
     *
     * @param userType 用户类型
     * @param realName 昵称
     * @param role     角色
     * @return SupplyDiffSum>
     */
    List<SupplyDiffSumDto> selectDiffSum(String userType, String realName, Integer role);

    /**
     * 交付异常汇总
     *
     * @param userType 用户类型
     * @param realName 昵称
     * @param role     角色
     * @return SupplyDiffSum>
     */
    List<AbnormalDeliverySumDto> selectAbnormalDeliverySum(String userType, String realName, Integer role);

    /**
     * 通过用户名获取用户信息
     *
     * @param username 用户名
     * @return User 用户信息
     */
    @MapKey("username")
    Map<String, Object> getUser(String username);
}
