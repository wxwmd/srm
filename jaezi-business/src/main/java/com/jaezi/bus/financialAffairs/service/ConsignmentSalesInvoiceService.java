package com.jaezi.bus.financialAffairs.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.jaezi.bus.financialAffairs.dao.ConsignmentSalesInvoiceOutDao;
import com.jaezi.bus.financialAffairs.dao.ConsignmentSalesInvoiceDao;
import com.jaezi.bus.financialAffairs.dao.ConsignmentSalesInvoiceOutInfoDao;
import com.jaezi.bus.financialAffairs.dto.ConsignmentSalesInvoiceDto;
import com.jaezi.bus.financialAffairs.model.ConsignmentSalesInvoice;
import com.jaezi.bus.financialAffairs.model.ConsignmentSalesInvoiceOut;
import com.jaezi.bus.financialAffairs.model.ConsignmentSalesInvoiceOutInfo;
import com.jaezi.bus.financialAffairs.vo.ConsignmentSalesInvoiceVo;
import com.jaezi.common.base.BaseService;
import com.jaezi.common.bean.DataGrid;
import com.jaezi.common.util.IDUtil;
import com.jaezi.common.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wxx
 * @version v2.0
 * @corporation copyright by jaezi.com
 * @date 2021/5/9
 * @description
 */
@Service
public class ConsignmentSalesInvoiceService extends BaseService<ConsignmentSalesInvoice, ConsignmentSalesInvoiceVo> {

    private ConsignmentSalesInvoiceDao consignmentSalesInvoiceDao;

    private ConsignmentSalesInvoiceOutDao consignmentSalesInvoiceOutDao;

    private ConsignmentSalesInvoiceOutInfoDao consignmentSalesInvoiceOutInfoDao;

    @Autowired
    public void setBaseDao(ConsignmentSalesInvoiceDao consignmentSalesInvoiceDao, ConsignmentSalesInvoiceOutDao consignmentSalesInvoiceOutDao, ConsignmentSalesInvoiceOutInfoDao consignmentSalesInvoiceOutInfoDao) {
        super.setBaseDao(consignmentSalesInvoiceDao);
        this.consignmentSalesInvoiceDao = consignmentSalesInvoiceDao;
        this.consignmentSalesInvoiceOutDao = consignmentSalesInvoiceOutDao;
        this.consignmentSalesInvoiceOutInfoDao = consignmentSalesInvoiceOutInfoDao;
    }

    /**
     * 废弃已开发票
     *
     * @param id 寄售物资发票id
     * @return int 删除个数
     * 删除发票后，将erp表中的总寄售物资发票订单的未开票个数加一
     * @author yx
     * @date 2021年8月19日11:49:02
     * @since 1.0
     */
    @Override
    public int delete(Serializable id) {
        int result = 0;
        ConsignmentSalesInvoice oneById = consignmentSalesInvoiceDao.getOneById(id);
        if (oneById != null) {
            if (oneById.getInvoiceStatus() == 0) {
                String purchaseOrder = oneById.getPurchaseOrder();
                if (purchaseOrder == null){
                    result = -1;
                    return result;
                }
                ConsignmentSalesInvoiceOut consignmentSalesInvoiceOut = consignmentSalesInvoiceOutDao.getConsignmentByPOrderAndMatNum(purchaseOrder, oneById.getMaterialNumber());
                Integer notOutInvoiceNumber = consignmentSalesInvoiceOut.getNotOutInvoiceNumber();
                consignmentSalesInvoiceOut.setNotOutInvoiceNumber(notOutInvoiceNumber + 1);
                consignmentSalesInvoiceOut.setStatus(-1);
                consignmentSalesInvoiceOutDao.update(consignmentSalesInvoiceOut);
                return consignmentSalesInvoiceDao.delete(id);
            }
            return result;
        }
        return result;
    }

    /**
     * 寄售物资发票维护
     *
     * @param consignmentSalesInvoice 寄售物资发票信息
     * @return int 修改条数
     * 寄售物资发票维护完成之后即为已提交状态，其erp导入的信息表中的那条数据也修改为已提交状态
     * @author yx
     * @date 2021年8月19日09:24:19
     * @since 1.0
     */
    @Override
    public int update(ConsignmentSalesInvoice consignmentSalesInvoice) {
        int result = 0;
        //todo 先判断是否填写了折扣原因，然后填写了折扣原因，就判断这张发票包含的订单中是否含有负数的金额（amount），如果有就不能维护成功并提示有负数金额
        if (!"".equals(consignmentSalesInvoice.getDiscountCause())) {
            Map<String, String> filter = new HashMap<>();
            filter.put("invoiceNumber", String.valueOf(consignmentSalesInvoice.getInvoiceNumber()));
            List<ConsignmentSalesInvoiceOutInfo> all = consignmentSalesInvoiceOutInfoDao.findAll(filter);
            for (ConsignmentSalesInvoiceOutInfo consignmentSalesInvoiceOutInfo : all) {
                ConsignmentSalesInvoiceOut byPurchaseOrder = consignmentSalesInvoiceOutDao.getConsignmentByPOrderAndMatNum(consignmentSalesInvoiceOutInfo.getPurchaseOrder(),consignmentSalesInvoiceOutInfo.getMaterialNumber());
                if ((byPurchaseOrder.getAmount()).signum() == -1) {
                    result = 2;
                    return result;
                }
            }
        }
        //如果是供应商可设置发票状态为已提交
        BigDecimal taxRate = consignmentSalesInvoice.getTaxRate().divide(new BigDecimal(100));
        taxRate.setScale(4,BigDecimal.ROUND_DOWN);
        consignmentSalesInvoice.setTaxRate(taxRate);
        if (JwtUtil.getUserType() != null && JwtUtil.getUserType() == 1) {
            consignmentSalesInvoice.setInvoiceStatus(0);
            return consignmentSalesInvoiceDao.update(consignmentSalesInvoice);
        } else if (JwtUtil.getUserType() != null && JwtUtil.getUserType() == 3) {
            consignmentSalesInvoice.setInvoiceStatus(1);
            return consignmentSalesInvoiceDao.update(consignmentSalesInvoice);
        }
        return result;
    }

    /**
     * 查询寄售货物发票
     *
     * @param filter 过滤条件
     * @return ConsignmentSalesInvoiceQuery>
     * @author yx
     * @date 2021年8月10日17:07:20
     * @since 1.0
     */
    public DataGrid<ConsignmentSalesInvoice> findAll(Map<String, String> filter) {
        DataGrid<ConsignmentSalesInvoice> dg = new DataGrid<>();
        if (filter.get("limit") == null || filter.get("page") == null) {
            List<ConsignmentSalesInvoice> all = consignmentSalesInvoiceDao.findAll(filter);
            dg.setRecords(all);
            return dg;
        }
        Page<ConsignmentSalesInvoice> page = PageHelper.startPage(Integer.parseInt(filter.get("page")), Integer.parseInt(filter.get("limit")));
        List<ConsignmentSalesInvoice> list = consignmentSalesInvoiceDao.findAll(filter);
        for (ConsignmentSalesInvoice invoice:list){
            invoice.setTaxRate(invoice.getTaxRate().multiply(new BigDecimal(100)));
        }
        dg.setRecords(list);
        dg.setTotal(page.getTotal());
        return dg;
    }

    /**
     * 寄售物资拆票
     *
     * @param consignmentSalesInvoices 寄售物资开票对象
     * @return int 开票成功个数
     * @author yx
     * @date 2021年8月17日17:23:08
     * @since 1.0
     */
    public List<ConsignmentSalesInvoice> addSplitInvoice(List<ConsignmentSalesInvoice> consignmentSalesInvoices, Integer quota) {
        BigDecimal quotaData = new BigDecimal(String.valueOf(quota));
        ConsignmentSalesInvoice split = consignmentSalesInvoices.get(0);
        //根据物料号，行项目，采购订单查询订单表
        List<ConsignmentSalesInvoice> resultList = new ArrayList<>();
        ConsignmentSalesInvoiceOut consignmentSalesInvoiceOut = consignmentSalesInvoiceOutDao.getConsignmentByPOrderAndMatNum(split.getPurchaseOrder(), split.getMaterialNumber());
        if (consignmentSalesInvoiceOut == null) {
            return resultList;
        }
        for (ConsignmentSalesInvoice salesInvoice : consignmentSalesInvoices) {
            BigDecimal unitPrice = consignmentSalesInvoiceOut.getUnitPrice();
            BigDecimal amount = unitPrice.multiply(salesInvoice.getQuantity()).setScale(2, BigDecimal.ROUND_HALF_UP);
            BigDecimal amount1 = salesInvoice.getAmount();
            //判断每个发票的金额是否超过限额
            if (amount.compareTo(quotaData) > 0 || amount1.compareTo(quotaData) > 0) {
                //如果超过了，直接返回超过的那条数据
                resultList.add(salesInvoice);
                return resultList;
            }
        }
        //将拆分好的插入发票表
        for (ConsignmentSalesInvoice salesInvoiceData : consignmentSalesInvoices) {
            //设置状态为已提交
            salesInvoiceData.setInvoiceStatus(0);
            //生成创建时间
            salesInvoiceData.setCreateTime(String.valueOf(System.currentTimeMillis()));
            salesInvoiceData.setSupplierCode(consignmentSalesInvoiceOut.getSupplierCode());
            consignmentSalesInvoiceDao.add(salesInvoiceData);
            ConsignmentSalesInvoiceOutInfo consignmentSalesInvoiceOutInfo = new ConsignmentSalesInvoiceOutInfo();
            consignmentSalesInvoiceOutInfo.setInvoiceNumber(salesInvoiceData.getInvoiceNumber());
            consignmentSalesInvoiceOutInfo.setPlant(salesInvoiceData.getPlant());
            consignmentSalesInvoiceOutInfo.setMaterialNumber(salesInvoiceData.getMaterialNumber());
            consignmentSalesInvoiceOutInfo.setQuantity(salesInvoiceData.getQuantity());
            consignmentSalesInvoiceOutInfo.setOutInvoicePeriod(salesInvoiceData.getOutInvoicePeriod());
            consignmentSalesInvoiceOutInfo.setMaterialDescribe(salesInvoiceData.getMaterialDescribe());
            consignmentSalesInvoiceOutInfo.setSupplierCode(salesInvoiceData.getSupplierCode());
            consignmentSalesInvoiceOutInfoDao.add(consignmentSalesInvoiceOutInfo);
            consignmentSalesInvoiceOut.setStatus(0);
            consignmentSalesInvoiceOut.setNotOutInvoiceNumber(0);
            consignmentSalesInvoiceOutDao.update(consignmentSalesInvoiceOut);
            resultList.add(salesInvoiceData);
        }
        return resultList;
    }

    /**
     * 寄售物资合票
     * @since 2.0
     * @author wxx
     * @date 2022年4月24日
     * @param consignmentSalesInvoiceDto consignmentSalesInvoiceDto
     * @param quota 供应商开票限额
     * @param username 用户名
     * @return int
     */
    public int addMerge(ConsignmentSalesInvoiceDto consignmentSalesInvoiceDto, Integer quota, String username) {
        BigDecimal quotaData = new BigDecimal(String.valueOf(quota));
        int result = 0;
        List<Map<String, String>> combinedData = consignmentSalesInvoiceDto.getCombinedData();
        Map<String, BigDecimal> resultList = consignmentSalesInvoiceDto.getResultList();
        BigDecimal amount = resultList.get("truthAmount");

        System.out.println(amount);
        if (amount.compareTo(quotaData) > 0) {
            return result;
        }

        //如果金额为负数的情况
        List<Map<String, String>> combined = consignmentSalesInvoiceDto.getCombinedData();
        List<ConsignmentSalesInvoiceOut> consignmentSalesInvoiceOutMoneyData = new ArrayList<>();
        for (Map<String, String> dataMap : combined) {
            ConsignmentSalesInvoiceOut consignmentSalesInvoiceOutData = consignmentSalesInvoiceOutDao.getOneById(Integer.parseInt(dataMap.get("id")));
            consignmentSalesInvoiceOutMoneyData.add(consignmentSalesInvoiceOutData);
        }
        BigDecimal totalMoney = new BigDecimal("0.00");
        for (ConsignmentSalesInvoiceOut c:consignmentSalesInvoiceOutMoneyData){
            System.out.println(c.toString());
        }
        if (consignmentSalesInvoiceOutMoneyData.size() == 1){
            ConsignmentSalesInvoiceOut consignmentSalesInvoiceOut = consignmentSalesInvoiceOutMoneyData.get(0);
            totalMoney = consignmentSalesInvoiceOut.getAmount();
        }else {
            totalMoney = consignmentSalesInvoiceOutMoneyData.stream().map(ConsignmentSalesInvoiceOut::getAmount).reduce(BigDecimal.ZERO, BigDecimal::add);
        }

        ConsignmentSalesInvoice consignmentSalesInvoice = new ConsignmentSalesInvoice();
        consignmentSalesInvoice.setInvoiceStatus(0);
        consignmentSalesInvoice.setPlant(consignmentSalesInvoiceDto.getPlant());
        consignmentSalesInvoice.setOutInvoicePeriod(consignmentSalesInvoiceDto.getOutInvoicePeriod());
        consignmentSalesInvoice.setAmount(totalMoney);
        consignmentSalesInvoice.setTaxAmount(resultList.get("truthTaxAmount"));
        consignmentSalesInvoice.setTaxPriceTotal(resultList.get("truthTaxPriceTotal"));
        consignmentSalesInvoice.setCreateTime(String.valueOf(System.currentTimeMillis()));
        consignmentSalesInvoice.setSupplierCode(username);
        consignmentSalesInvoice.setInvoiceCode(consignmentSalesInvoiceDto.getInvoiceCode());
        consignmentSalesInvoice.setInvoiceNumber(consignmentSalesInvoiceDto.getInvoiceNumber());
        consignmentSalesInvoice.setOutInvoiceDate(consignmentSalesInvoiceDto.getOutInvoiceDate());
        consignmentSalesInvoice.setTaxRate(resultList.get("truthTaxRate").divide(new BigDecimal(100)));
        consignmentSalesInvoice.setPurchaseOrder(consignmentSalesInvoiceDto.getPurchaseOrder());
        consignmentSalesInvoice.setInvoiceDate(consignmentSalesInvoiceDto.getInvoiceDate());
        Integer interimInvoiceNumber = IDUtil.getId();
        consignmentSalesInvoice.setInterimInvoiceNumber(interimInvoiceNumber);
        System.out.println(consignmentSalesInvoice.toString());
        consignmentSalesInvoiceDao.add(consignmentSalesInvoice);
        for (int i = 0; i < combinedData.size(); i++) {
            Map<String, String> stringStringMap = combinedData.get(i);
            BigDecimal quantity = new BigDecimal(String.valueOf(stringStringMap.get("quantity")));
            ConsignmentSalesInvoiceOutInfo consignmentSalesInvoiceOutInfo = new ConsignmentSalesInvoiceOutInfo();
            consignmentSalesInvoiceOutInfo.setPlant(consignmentSalesInvoiceDto.getPlant());
            String materialNumber = stringStringMap.get("materialNumber");
            consignmentSalesInvoiceOutInfo.setMaterialNumber(materialNumber);
            consignmentSalesInvoiceOutInfo.setQuantity(quantity);
            consignmentSalesInvoiceOutInfo.setOutInvoicePeriod(consignmentSalesInvoiceDto.getOutInvoicePeriod());
            consignmentSalesInvoiceOutInfo.setMaterialDescribe(stringStringMap.get("materialDescribe"));
            String purchaseOrder = stringStringMap.get("purchaseOrder");
            consignmentSalesInvoiceOutInfo.setPurchaseOrder(purchaseOrder);
            consignmentSalesInvoiceOutInfo.setSupplierCode(username);
            consignmentSalesInvoiceOutInfo.setInvoiceNumber(consignmentSalesInvoiceDto.getInvoiceNumber());
            consignmentSalesInvoiceOutInfoDao.add(consignmentSalesInvoiceOutInfo);
            ConsignmentSalesInvoiceOut materialDescribe = consignmentSalesInvoiceOutDao.getConsignmentByPOrderAndMatNum(purchaseOrder, materialNumber);
            if (materialDescribe == null) {
                return result;
            }
            materialDescribe.setStatus(0);
            materialDescribe.setNotOutInvoiceNumber(0);
            consignmentSalesInvoiceOutDao.update(materialDescribe);
            result++;
        }
        return result;
    }
}
