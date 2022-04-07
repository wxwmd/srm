package com.jaezi.system.dao;

import com.jaezi.common.base.BaseDao;
import com.jaezi.system.model.Supplier;
import com.jaezi.system.vo.SupplierVo;
import org.apache.ibatis.annotations.MapKey;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author yzl
 * @version v1.0
 * @corporation
 * @date 2021/7/21 17:12
 * @description 供应商数据访问对象
 */
@Repository
public interface SupplierDao extends BaseDao<Supplier, SupplierVo> {

    /**
     * 获取所有供应商
     *
     * @return 对应列表
     */
    @MapKey("supplierId")
    List<Map> getAllSupplier(String type, String userType, String realName);

    /**
     * 根据用户id 修改手机号
     * @param phone 手机号
     * @param userId 用户id
     * @return
     */
    int updateSupplierPhoneByUserId(String phone, Integer userId);

    /**
     * 批量添加供应商信息
     *
     * @param list 供应商列表
     * @return int
     */
    int bathAdd(List<Supplier> list);
}
