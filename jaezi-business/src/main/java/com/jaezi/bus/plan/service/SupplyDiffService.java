package com.jaezi.bus.plan.service;

import com.jaezi.bus.plan.dao.SupplyDiffDao;
import com.jaezi.bus.plan.dto.AbnormalDeliverySumDto;
import com.jaezi.bus.plan.dto.SupplyDiffDto;
import com.jaezi.bus.plan.dto.SupplyDiffSumDto;
import com.jaezi.bus.plan.model.SupplyDiff;
import com.jaezi.bus.plan.vo.SupplyDiffVo;
import com.jaezi.common.base.BaseService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author yzl
 * @version v1.0
 * @corporation
 * @date 2021/8/4 15:39
 * @description 供需差异逻辑层
 */

@Service
public class SupplyDiffService extends BaseService<SupplyDiff, SupplyDiffVo> {

    private SupplyDiffDao supplyDiffDao;

    @Autowired
    public void setBaseDao(SupplyDiffDao supplyDiffDao) {
        super.setBaseDao(supplyDiffDao);
        this.supplyDiffDao = supplyDiffDao;
    }

    /**
     * 通过物料可用时间对数据进行处理
     */
    public void updateSupply() {
        List<SupplyDiff> list = supplyDiffDao.getAll();

        List<SupplyDiff> supplyDiffList1 = new Vector<>();

        Map<String, List<SupplyDiff>> supplyDiffListMap = list.parallelStream().collect(Collectors.groupingBy(SupplyDiff::getMaterialNumber));
        for (Map.Entry<String, List<SupplyDiff>> supplyDiffMap : supplyDiffListMap.entrySet()) {
            List<SupplyDiff> supplyDiffList = supplyDiffMap.getValue();
            supplyDiffList = supplyDiffList.parallelStream().sorted(Comparator.comparing(SupplyDiff::getType)).collect(Collectors.toList());

            SupplyDiff supplyDiff1 = supplyDiffList.get(0);
            SupplyDiff supplyDiff2 = supplyDiffList.get(1);
            SupplyDiff supplyDiff3 = supplyDiffList.get(2);

            Class<?> clazz = SupplyDiffDto.class;
            Field[] fields = clazz.getDeclaredFields();

            //获取class
            Class clz = SupplyDiff.class;

            //属性复制
            SupplyDiff supplyDiff4 = new SupplyDiff();
            BeanUtils.copyProperties(supplyDiff2, supplyDiff4);
            //物料可用时间
            int materialUpTime = supplyDiff1.getMaterialUpTime();
            //订单数量
            int orderQty = 0;
            //通过物料可用时间对数据进行处理
            for (Field field : fields) {
                if (orderQty > 0 && materialUpTime > 0) {
                    //如果订单数量大于零,根据物料可用时间将该列订单数量后移
                    materialUpTime--;
                    continue;
                }

                String fieldName = field.getName();
                fieldName = fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);

                try {
                    if (materialUpTime == 0) {
                        //设置值
                        Method setField = clz.getDeclaredMethod("set" + fieldName, field.getType());
                        setField.invoke(supplyDiff4, orderQty);

                        //处理完订单数量,还原初始值
                        orderQty = 0;
                        materialUpTime = supplyDiff1.getMaterialUpTime();
                        continue;
                    }

                    //获取值
                    Method getField = clz.getDeclaredMethod("get" + fieldName);

                    orderQty = Integer.valueOf(getField.invoke(supplyDiff4).toString());
                    //如果订单数量大于零
                    if (orderQty > 0) {
                        //设置值
                        Method setField = clz.getDeclaredMethod("set" + fieldName, field.getType());
                        setField.invoke(supplyDiff4, 0);
                        materialUpTime--;
                        continue;
                    }
                } catch (Exception e) {

                }
            }

            //前一天的预计库存
            Integer qty = 0;
            int i = 0;
            for (Field field : fields) {
                String fieldName = field.getName();
                fieldName = fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);

                try {
                    //获取值
                    Method getField = clz.getDeclaredMethod("get" + fieldName);

                    //第二天的预计库存=前一天预计库存+订单数量-物料需求
                    qty = qty + Integer.valueOf(getField.invoke(supplyDiff4).toString()) - Integer.valueOf(getField.invoke(supplyDiff1).toString());

                    if (i == 0) {
                        i++;
                        continue;
                    }
                    //设置值
                    Method setField = clz.getDeclaredMethod("set" + fieldName, field.getType());
                    setField.invoke(supplyDiff3, qty);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            supplyDiffList1.add(supplyDiff3);
        }
        //批量更新
        if (!ObjectUtils.isEmpty(supplyDiffList1)) {
            supplyDiffDao.updateBath(supplyDiffList1);
        }
    }

    public String getCron() {
        return "*/59 * * * * ?";
    }

    /**
     * 获取时间
     */
    public Map getTime() {
        LocalDate date = LocalDate.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MM/dd");

        Map map = new LinkedHashMap();

        Class<?> clazz = SupplyDiffDto.class;
        Field[] fields = clazz.getDeclaredFields();
        int i = 0;
        for (Field field : fields) {
            if (i != 0) {
                date = date.plusDays(1);
            }
            map.put(field.getName(), date.format(dateTimeFormatter));
            i++;
        }
        return map;
    }

    /**
     * 供需差异汇总
     *
     * @param userType
     * @param realName
     * @return SupplyDiffSumDto
     */
    public List<SupplyDiffSumDto> selectDiffSum(String userType, String realName, Integer role) {
        return supplyDiffDao.selectDiffSum(userType, realName, role).parallelStream().filter(supplyDiffSumDto ->
                !(supplyDiffSumDto.getPastDue() == 0 && supplyDiffSumDto.getNextDay() == 0 &&
                        supplyDiffSumDto.getThirdDay() == 0 && supplyDiffSumDto.getFourDay() == 0 &&
                        supplyDiffSumDto.getFiveDay() == 0 && supplyDiffSumDto.getSixDay() == 0 &&
                        supplyDiffSumDto.getSeventhDay() == 0 && supplyDiffSumDto.getEighthDay() == 0 &&
                        supplyDiffSumDto.getNinthDay() == 0 && supplyDiffSumDto.getTenDay() == 0 &&
                        supplyDiffSumDto.getEleventhDay() == 0 && supplyDiffSumDto.getTwelfthDay() == 0 &&
                        supplyDiffSumDto.getFourteenthDay() == 0 && supplyDiffSumDto.getThirdWeek() == 0 &&
                        supplyDiffSumDto.getFourthWeek() == 0 && supplyDiffSumDto.getFifthWeek() == 0 &&
                        supplyDiffSumDto.getSixthWeek() == 0 && supplyDiffSumDto.getSum() == 0)).collect(Collectors.toList());
    }

    /**
     * 交付异常汇总
     *
     * @param userType
     * @param realName
     * @return AbnormalDeliverySumDto
     */
    public List<AbnormalDeliverySumDto> selectAbnormalDeliverySum(String userType, String realName, Integer role) {
        return supplyDiffDao.selectAbnormalDeliverySum(userType, realName, role).parallelStream().filter(supplyDiffSumDto ->
                !(supplyDiffSumDto.getPurchaseYellow() == 0 && supplyDiffSumDto.getPurchaseRed() == 0 &&
                        supplyDiffSumDto.getInventoryYellow() == 0 && supplyDiffSumDto.getInventoryRed() == 0 &&
                        supplyDiffSumDto.getTruckYellow() == 0 && supplyDiffSumDto.getTruckRed() == 0 &&
                        supplyDiffSumDto.getFactoryYellow() == 0 && supplyDiffSumDto.getFactoryRed() == 0 &&
                        supplyDiffSumDto.getPostponeYellow() == 0 && supplyDiffSumDto.getPostponeRed() == 0)).collect(Collectors.toList());
    }

    /**
     * 通过用户名获取用户信息
     *
     * @param username
     * @return User
     */
    public Map<String, Object> getUser(String username) {
        return supplyDiffDao.getUser(username);
    }
}
