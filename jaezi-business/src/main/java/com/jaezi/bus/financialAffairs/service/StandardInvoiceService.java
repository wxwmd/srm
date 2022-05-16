package com.jaezi.bus.financialAffairs.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.jaezi.bus.financialAffairs.dao.StandardInvoiceOutDao;
import com.jaezi.bus.financialAffairs.dao.StandardInvoiceDao;
import com.jaezi.bus.financialAffairs.dao.StandardInvoiceOutInfoDao;
import com.jaezi.bus.financialAffairs.model.StandardInvoice;
import com.jaezi.bus.financialAffairs.model.StandardInvoiceOut;
import com.jaezi.bus.financialAffairs.model.StandardInvoiceOutInfo;
import com.jaezi.bus.financialAffairs.vo.StandardInvoiceVo;
import com.jaezi.common.base.BaseService;
import com.jaezi.common.bean.DataGrid;
import com.jaezi.common.util.IDUtil;
import com.jaezi.common.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yx
 * @version v1.0
 * @corporation copyright by jaezi.com
 * @date 2021/8/10  9:01:03
 * @description
 */
@Service
public class StandardInvoiceService extends BaseService<StandardInvoice, StandardInvoiceVo> {

    private StandardInvoiceDao standardInvoiceDao;

    private StandardInvoiceOutDao standardInvoiceOutDao;

    private StandardInvoiceOutInfoDao standardInvoiceOutInfoDao;

    @Autowired
    public void setBaseDao(StandardInvoiceDao standardInvoiceDao, StandardInvoiceOutDao standardInvoiceOutDao, StandardInvoiceOutInfoDao standardInvoiceOutInfoDao) {
        super.setBaseDao(standardInvoiceDao);
        this.standardInvoiceDao = standardInvoiceDao;
        this.standardInvoiceOutDao = standardInvoiceOutDao;
        this.standardInvoiceOutInfoDao = standardInvoiceOutInfoDao;
    }

    /**
     * 废弃已开发票
     *
     * @param id 标准物资发票id
     * @return int 删除个数
     * 删除发票后，将erp表中的总标准物资发票订单的未开票个数加一
     * @author wxw
     * @date 2022年5月10日
     * @since 2.0
     */
    @Override
    public int delete(Serializable id) {
        int result = -1;
        StandardInvoice standardInvoice = standardInvoiceDao.getOneById(id);
        // 发票不为空才能删除
        if (standardInvoice != null) {
            // 已挂帐的发票不让删除
            if (standardInvoice.getInvoiceStatus()<2){
                // 正常的暂存|已提交发票，可以删除
                List<StandardInvoiceOutInfo> byInterimInvoiceNumber = standardInvoiceOutInfoDao.findByInterimInvoiceNumber(standardInvoice.getInterimInvoiceNumber());
                if (byInterimInvoiceNumber.size() != 0) {
                    // 找到发票中包含的项，将项更改为未开票状态
                    for (StandardInvoiceOutInfo standardInvoiceOutInfo : byInterimInvoiceNumber) {
                        String purchaseOrder = standardInvoiceOutInfo.getPurchaseOrder();
                        System.out.println("===============================");
                        System.out.println(standardInvoiceOutInfo.toString());
                        System.out.println(standardInvoiceOutDao.getStandardByPOrderAndMat(purchaseOrder, standardInvoiceOutInfo.getMaterial()).toString());
                        StandardInvoiceOut standardInvoiceOutData = standardInvoiceOutDao.getStandardByPOrderAndMat(purchaseOrder, standardInvoiceOutInfo.getMaterial());
                        if (standardInvoiceOutData == null || standardInvoiceOutData.getNotOutInvoiceNumber() == null || standardInvoiceOutInfo.getQuantity() == null) {
                            result = -1;
                            return result;
                        }
                        standardInvoiceOutData.setNotOutInvoiceNumber(standardInvoiceOutData.getNotOutInvoiceNumber().add(standardInvoiceOutInfo.getQuantity()));
                        standardInvoiceOutData.setStatus(-1);
                        //设置开票单状态为未开票
                        standardInvoiceOutDao.update(standardInvoiceOutData);
                        standardInvoiceOutInfoDao.deleteByInterimInvoiceNumber(standardInvoice.getInterimInvoiceNumber());
                    }
                    return standardInvoiceDao.delete(id);
                }
            } else{
                result=0;
            }
        }
        return result;
    }

    /**
     * 标准物资发票维护
     *
     * @param standardInvoice 标准物资发票信息
     * @return int 修改条数
     * 标准物资发票维护完成之后即为已提交状态，其erp导入的信息表中的那条数据也修改为已提交状态
     * @author wxw
     * @date 2022年05月11日
     * @since 2.0
     */
    @Override
    public int update(StandardInvoice standardInvoice) {
        Map<String,String> filter = new HashMap<>();
        String interimInvoiceNumber = standardInvoice.getInterimInvoiceNumber().toString();
        filter.put("interimInvoiceNumber",interimInvoiceNumber);
        List<StandardInvoice> check = standardInvoiceDao.findAll(filter);
        if (check.size()>0){
            // 在更新前先check一下这张发票是否已被挂账
            if (check.get(0).getInvoiceStatus()==2){
                return -1;
            }

            //如果是供应商可设置发票状态为已维护
            if (JwtUtil.getUserType() != null && JwtUtil.getUserType() == 1) {
                LocalDate nowTime = LocalDate.now();

                standardInvoice.setInvoiceStatus(1);
                standardInvoice.setTaxRate(standardInvoice.getTaxRate().divide(new BigDecimal(100)));
                return standardInvoiceDao.update(standardInvoice);
            } else if (JwtUtil.getUserType() != null && JwtUtil.getUserType() == 3) {
                return standardInvoiceDao.update(standardInvoice);
            }
        }
        return 0;
    }


    /**
     * 查询标准物资发票
     *
     * @param filter 过滤条件
     * @return OutInvoice>
     * @author wxx
     * @date 2022年5月6日
     * @since 2.0
     */
    public DataGrid<StandardInvoice> findAll(Map<String, String> filter) {
        DataGrid<StandardInvoice> dg = new DataGrid<>();
        if (filter.get("limit") == null || filter.get("page") == null) {
            List<StandardInvoice> all = standardInvoiceDao.findAll(filter);
            dg.setRecords(all);
            return dg;
        }
        Page<StandardInvoice> page = PageHelper.startPage(Integer.parseInt(filter.get("page")), Integer.parseInt(filter.get("limit")));
        List<StandardInvoice> list = standardInvoiceDao.findAll(filter);
        for (StandardInvoice standardInvoice:list){
            standardInvoice.setTaxRate(standardInvoice.getTaxRate().multiply(new BigDecimal(100)));
        }
        dg.setRecords(list);
        dg.setTotal(page.getTotal());
        return dg;
    }

    /**
     * 标准物资拆票
     *
     * @param standardInvoices 标准物资开票对象
     * @return int 开票成功个数
     * @author yx
     * @date 2021年8月17日17:23:08
     * @since 1.0
     */
    public int addSplitInvoice(List<StandardInvoice> standardInvoices, BigDecimal aggregateAmount, Integer quota) {
        int numberData = 0;
        BigDecimal quotaData = new BigDecimal(String.valueOf(quota));
        StandardInvoice split = standardInvoices.get(0);
        //根据物料号，行项目，采购订单查询订单表
        StandardInvoiceOut standardInvoiceOut = standardInvoiceOutDao.getStandardByPOrderAndMat(String.valueOf(split.getPurchaseOrder()), split.getMaterial());
        if (standardInvoiceOut == null) {
            return numberData;
        }
        BigDecimal unitPriceData = standardInvoiceOut.getUnitPrice();
        //判断拆分订单的数量是否等于可开票数量
        BigDecimal money = new BigDecimal("0.00");
        BigDecimal number = new BigDecimal("0.00");
        for (StandardInvoice splitData : standardInvoices) {
            BigDecimal unitPrice = standardInvoiceOut.getUnitPrice();
            BigDecimal quantity = splitData.getNotOutInvoiceNumber();
            BigDecimal amount = unitPrice.multiply(quantity).setScale(2, BigDecimal.ROUND_HALF_UP);
            //判断每个发票的金额是否超过限额
            if (amount.compareTo(quotaData) > 0) {
                return numberData;
            }
            money = money.add(amount);
            number = number.add(quantity);
        }
        //判断填写的总金额是否等于每个拆分订单金额相加的总金额 判断未开票数量是否等于每个拆分订单内填写的数量和
        // todo 从比较未开票数量改为数量
        // int data = number.intValue();
        if (money.compareTo(aggregateAmount) != 0 || number.compareTo(standardInvoiceOut.getNotOutInvoiceNumber()) != 0) {
            return numberData;
        }
        //将拆分好的插入发票表
        for (StandardInvoice standardInvoice : standardInvoices) {
            //状态设置为已暂存
            standardInvoice.setInvoiceStatus(0);
            //生成创建时间
            standardInvoice.setCreateTime(String.valueOf(System.currentTimeMillis()));
            BigDecimal quantity = standardInvoice.getNotOutInvoiceNumber();
            BigDecimal multiply = quantity.multiply(unitPriceData);
            standardInvoice.setWithoutTaxAmount(multiply);
            //生成临时发票号 202108251722221530
            Integer interimInvoiceNumber = IDUtil.getId();
            standardInvoice.setInterimInvoiceNumber(interimInvoiceNumber);
            standardInvoice.setSupplierCode(standardInvoiceOut.getSupplierCode());
            LocalDate nowTime = LocalDate.now();
            standardInvoice.setOutInvoiceDate(String.valueOf(nowTime));
            standardInvoice.setInvoiceType(0);
            standardInvoiceDao.add(standardInvoice);
            StandardInvoiceOutInfo standardInvoiceOutInfo = new StandardInvoiceOutInfo();
            standardInvoiceOutInfo.setQuantity(standardInvoice.getNotOutInvoiceNumber());
            standardInvoiceOutInfo.setSerialNumber(standardInvoiceOut.getId());
            standardInvoiceOutInfo.setPurchaseOrder(standardInvoiceOut.getPurchaseOrder());
            standardInvoiceOutInfo.setHongProject(standardInvoiceOut.getHongProject());
            standardInvoiceOutInfo.setMaterialVoucher(standardInvoiceOut.getMaterialVoucher());
            standardInvoiceOutInfo.setVoucherProject(standardInvoiceOut.getVoucherProject());
            standardInvoiceOutInfo.setMaterial(standardInvoiceOut.getMaterial());
            standardInvoiceOutInfo.setMaterialName(standardInvoiceOut.getMaterialName());
            standardInvoiceOutInfo.setInterimInvoiceNumber(interimInvoiceNumber);
            standardInvoiceOutInfo.setSupplierCode(standardInvoiceOut.getSupplierCode());
            standardInvoiceOutInfoDao.add(standardInvoiceOutInfo);
        }
        return standardInvoices.size();
    }

    /**
     * 标准物资开票
     *
     * @param standardInvoices 合并的
     * @return StandardInvoice
     * @author wxw
     * @date 2022年4月18日
     * @since 2.0
     */
    public List<StandardInvoice> addMerge(List<StandardInvoice> standardInvoices, Integer quota, String username) {
        BigDecimal quotaData = new BigDecimal(String.valueOf(quota));
        BigDecimal withoutTaxAmountSum = new BigDecimal("0.00");
        BigDecimal taxAmountSum=new BigDecimal("0.00");
        BigDecimal totalAmountSum=new BigDecimal("0.00");
        List<StandardInvoice> standardInvoiceList = new ArrayList<>();
        List<StandardInvoiceOut> standardInvoiceOutList = new ArrayList<>();
        for (StandardInvoice standardInvoice : standardInvoices) {
            System.out.println(standardInvoice.toString());
            StandardInvoiceOut byPurchaseOrder = standardInvoiceOutDao.getStandardByPOrderAndMat(standardInvoice.getPurchaseOrder(), standardInvoice.getMaterial());
            if (byPurchaseOrder == null) {
                return standardInvoiceList;
            }
            standardInvoiceOutList.add(byPurchaseOrder);
            // 单价
            BigDecimal unitPrice = byPurchaseOrder.getUnitPrice();
            BigDecimal moneyData = unitPrice.multiply(byPurchaseOrder.getNotOutInvoiceNumber());
            /*
            * 判断填写的不含税金额书否正确
            * 对每行数据来说有两种情况数据是出错的：
            * 1. 总金额（不含税）！=单价*数量
            * 2. 总金额（税价合计）！=总金额（不含税）+税价
            * */
            if (moneyData.compareTo(standardInvoice.getWithoutTaxAmount()) != 0 || standardInvoice.getTotalAmount().compareTo(standardInvoice.getWithoutTaxAmount().add(standardInvoice.getTaxAmount()))!=0) {
                return null;
            }
            withoutTaxAmountSum=withoutTaxAmountSum.add(standardInvoice.getWithoutTaxAmount());
            taxAmountSum=taxAmountSum.add(standardInvoice.getTaxAmount());
            totalAmountSum=totalAmountSum.add(standardInvoice.getTotalAmount());
            standardInvoiceList.add(standardInvoice);
        }


        // 生成临时发票
        StandardInvoice standardInvoice = new StandardInvoice();
        standardInvoice.setInvoiceStatus(0);
        //生成临时发票号
        Integer interimInvoiceNumber = IDUtil.getId();
        standardInvoice.setWithoutTaxAmount(withoutTaxAmountSum);
        standardInvoice.setTaxAmount(taxAmountSum);
        standardInvoice.setTotalAmount(totalAmountSum);
        BigDecimal taxRate = taxAmountSum.divide(withoutTaxAmountSum);
        taxRate = taxRate.setScale(4,BigDecimal.ROUND_DOWN);
        standardInvoice.setTaxRate(taxRate);
        //生成发票创建时间
        standardInvoice.setCreateTime(String.valueOf(System.currentTimeMillis()));
        LocalDate nowTime = LocalDate.now();
        standardInvoice.setOutInvoiceDate(String.valueOf(nowTime));
        standardInvoice.setSupplierCode(username);
        //设置临时发票号
        standardInvoice.setInterimInvoiceNumber(interimInvoiceNumber);
        //设置发票状态为已暂存
        standardInvoice.setInvoiceStatus(0);
        standardInvoice.setInvoiceType(0);
        standardInvoiceDao.add(standardInvoice);
        //生成发票开票信息
        for (StandardInvoiceOut standardInvoiceOut : standardInvoiceOutList) {
            StandardInvoiceOutInfo standardInvoiceOutInfo = new StandardInvoiceOutInfo();
            standardInvoiceOutInfo.setSerialNumber(standardInvoiceOut.getId());
            standardInvoiceOutInfo.setPurchaseOrder(standardInvoiceOut.getPurchaseOrder());
            standardInvoiceOutInfo.setHongProject(standardInvoiceOut.getHongProject());
            standardInvoiceOutInfo.setMaterialVoucher(standardInvoiceOut.getMaterialVoucher());
            standardInvoiceOutInfo.setVoucherProject(standardInvoiceOut.getVoucherProject());
            standardInvoiceOutInfo.setMaterial(standardInvoiceOut.getMaterial());
            standardInvoiceOutInfo.setMaterialName(standardInvoiceOut.getMaterialName());
            standardInvoiceOutInfo.setInterimInvoiceNumber(interimInvoiceNumber);
            standardInvoiceOutInfo.setSupplierCode(standardInvoiceOut.getSupplierCode());
            standardInvoiceOutInfo.setQuantity(standardInvoiceOut.getNotOutInvoiceNumber());
            standardInvoiceOutInfoDao.add(standardInvoiceOutInfo);
        }
        for (StandardInvoiceOut standardInvoiceOut : standardInvoiceOutList) {
            //设置开票单状态为已开票
            standardInvoiceOut.setNotOutInvoiceNumber(new BigDecimal("0.00"));
            standardInvoiceOut.setStatus(0);
            standardInvoiceOutDao.update(standardInvoiceOut);
        }
        return standardInvoiceList;
    }


    public int updateAudit(Integer id, Integer invoiceStatus,Integer auditStatus) {
        int result = 0;
        StandardInvoice oneById = standardInvoiceDao.getOneById(id);
        if (oneById != null) {
            //设置状态
            if (auditStatus == 2) {
                oneById.setInvoiceStatus(invoiceStatus);
                return standardInvoiceDao.update(oneById);
            } else {
                return standardInvoiceDao.update(oneById);
            }
        }
        return result;
    }

}