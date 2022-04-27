package com.jaezi.bus.financialAffairs.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.jaezi.bus.financialAffairs.dao.ConsignmentSalesInvoiceDao;
import com.jaezi.bus.financialAffairs.dao.ConsignmentSalesInvoiceOutDao;
import com.jaezi.bus.financialAffairs.dto.ConsignmentSalesInvoiceOutDto;
import com.jaezi.bus.financialAffairs.model.ConsignmentSalesInvoice;
import com.jaezi.bus.financialAffairs.model.ConsignmentSalesInvoiceOut;
import com.jaezi.bus.financialAffairs.vo.ConsignmentSalesInvoiceOutVo;
import com.jaezi.bus.purchase.dao.PurchaseDao;
import com.jaezi.bus.purchase.model.Purchase;
import com.jaezi.common.base.BaseService;
import com.jaezi.common.bean.DataGrid;
import com.jaezi.common.util.ExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author yx
 * @version v1.0
 * @corporation copyright by jaezi.com
 * @date 2021/8/10  16:13:31
 * @description
 */
@Service
public class ConsignmentSalesInvoiceOutService extends BaseService<ConsignmentSalesInvoiceOut, ConsignmentSalesInvoiceOutVo> {

    private ConsignmentSalesInvoiceOutDao consignmentSalesInvoiceOutDao;

    private ConsignmentSalesInvoiceDao consignmentSalesInvoiceDao;

    private PurchaseDao purchaseDao;

    @Autowired
    public void setBaseDao(ConsignmentSalesInvoiceOutDao consignmentSalesInvoiceOutDao, ConsignmentSalesInvoiceDao consignmentSalesInvoiceDao, PurchaseDao purchaseDao) {
        super.setBaseDao(consignmentSalesInvoiceOutDao);
        this.consignmentSalesInvoiceOutDao = consignmentSalesInvoiceOutDao;
        this.consignmentSalesInvoiceDao = consignmentSalesInvoiceDao;
        this.purchaseDao = purchaseDao;
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
    public DataGrid<ConsignmentSalesInvoiceOutDto> findAll(Map<String, String> filter) throws ParseException {
        DataGrid<ConsignmentSalesInvoiceOutDto> dg = new DataGrid<>();
        BigDecimal aggregateAmount = new BigDecimal("0.00");
        BigDecimal taxAmount = new BigDecimal("0.00");
        ConsignmentSalesInvoiceOutDto consignmentSalesInvoiceOutDto = new ConsignmentSalesInvoiceOutDto();
        List<ConsignmentSalesInvoiceOutDto> list1 = new ArrayList<>();
        List<BigDecimal> resultList = new ArrayList<>();
        //如果携带invoiceGroup条件，则计算不含税金额，税额，税价合计返回给前端
        if (filter.get("invoiceGroup") != null || filter.get("startTime") != null || filter.get("endTime") != null) {
            Page<ConsignmentSalesInvoiceOut> page = PageHelper.startPage(Integer.parseInt(filter.get("page")), Integer.parseInt(filter.get("limit")));
            List<ConsignmentSalesInvoiceOut> all = consignmentSalesInvoiceOutDao.findAll(filter);
            for (ConsignmentSalesInvoiceOut consignmentSalesInvoiceOut : all) {
                //只计算未开票
                if (consignmentSalesInvoiceOut.getStatus() == -1) {
                    BigDecimal unitPrice = consignmentSalesInvoiceOut.getUnitPrice();

                    /*
                    * 如果有不含税金额，直接使用不含税金额
                    * 如果没有，再使用单价*未开票数量
                    * */
                    BigDecimal amount=null;
                    if (consignmentSalesInvoiceOut.getAmount()!=null){
                        amount=consignmentSalesInvoiceOut.getAmount().setScale(2, BigDecimal.ROUND_HALF_UP);
                    } else {
                        amount = unitPrice.multiply(consignmentSalesInvoiceOut.getQuantity()).setScale(2, BigDecimal.ROUND_HALF_UP);
                    }
                    BigDecimal taxPrice = amount.multiply(consignmentSalesInvoiceOut.getTaxRate()).setScale(2, BigDecimal.ROUND_HALF_UP);
                    taxAmount = taxAmount.add(taxPrice).setScale(2, BigDecimal.ROUND_HALF_UP);
                    aggregateAmount = aggregateAmount.add(amount).setScale(2, BigDecimal.ROUND_HALF_UP);
                }
            }
            resultList.add(aggregateAmount);
            resultList.add(taxAmount);
            resultList.add(aggregateAmount.add(taxAmount));
            consignmentSalesInvoiceOutDto.setConsignmentSalesInvoiceOuts(all);
            consignmentSalesInvoiceOutDto.setResultList(resultList);
            list1.add(consignmentSalesInvoiceOutDto);
            dg.setRecords(list1);
            dg.setTotal(page.getTotal());
            return dg;
        }
        if (filter.get("limit") == null || filter.get("page") == null) {
            List<ConsignmentSalesInvoiceOut> all = consignmentSalesInvoiceOutDao.findAll(filter);
            consignmentSalesInvoiceOutDto.setConsignmentSalesInvoiceOuts(all);
            list1.add(consignmentSalesInvoiceOutDto);
            dg.setRecords(list1);
            return dg;
        }
        Page<ConsignmentSalesInvoiceOut> page = PageHelper.startPage(Integer.parseInt(filter.get("page")), Integer.parseInt(filter.get("limit")));
        List<ConsignmentSalesInvoiceOut> list = consignmentSalesInvoiceOutDao.findAll(filter);
        consignmentSalesInvoiceOutDto.setConsignmentSalesInvoiceOuts(list);
        list1.add(consignmentSalesInvoiceOutDto);
        dg.setRecords(list1);
        dg.setTotal(page.getTotal());
        return dg;
    }

    /**
     * 获取所有本供应商的开票区间
     * @since 1.0
     * @author yx
     * @date 2021年8月31日14:24:17
     * @param filter 搜索条件
     * @return String> 开票区间集合
     */
    public List<String> getInterval(Map<String, String> filter) {
        List<ConsignmentSalesInvoiceOut> consignmentSalesInvoiceOutList = consignmentSalesInvoiceOutDao.findAll(filter);
        List<String> outInvoicePeriodList = new ArrayList<>();
        if (consignmentSalesInvoiceOutList.size() == 0) {
            return outInvoicePeriodList;
        }
        for (ConsignmentSalesInvoiceOut consignmentSalesInvoiceOut : consignmentSalesInvoiceOutList) {
            if (consignmentSalesInvoiceOut.getStatus() == -1){
                outInvoicePeriodList.add(consignmentSalesInvoiceOut.getInvoiceGroup());
            }
        }
        return outInvoicePeriodList.stream().distinct().collect(Collectors.toList());
    }

    /**
     * 根据采购订单查询标准订单
     *
     * @param purchaseOrder 采购订单
     * @return OutInvoice 标准订单
     * @author yx
     * @date 2021年8月17日17:47:29
     * @since 1.0
     */
    public ConsignmentSalesInvoiceOut getByPurchaseOrder(String purchaseOrder,String materialNumber) {
        return consignmentSalesInvoiceOutDao.getConsignmentByPOrderAndMatNum(purchaseOrder,materialNumber);
    }

    /**
     * 校验采购订单金额
     *
     * @param consignmentSalesInvoiceOuts 开票对象集合
     * @return int 校验个数
     * @author yx
     * @date 2021年8月11日16:10:16
     * @since 1.0
     */
    public int addVerification(List<ConsignmentSalesInvoiceOut> consignmentSalesInvoiceOuts, Integer quota) {
        int number = 0;
        BigDecimal quotaData = new BigDecimal(String.valueOf(quota));
        for (ConsignmentSalesInvoiceOut consignmentSalesInvoiceOut : consignmentSalesInvoiceOuts) {
            ConsignmentSalesInvoiceOut oneById = consignmentSalesInvoiceOutDao.getOneById(consignmentSalesInvoiceOut.getId());
            if (oneById == null) {
                return number;
            }
            BigDecimal unitPrice = oneById.getUnitPrice();
            BigDecimal quantity = oneById.getQuantity();
            BigDecimal money = unitPrice.multiply(quantity);
            Purchase purchaseData = purchaseDao.getByPurOrderAndMatNum(String.valueOf(oneById.getPurchaseOrder()), oneById.getMaterial());
            if (purchaseData == null) {
                return number;
            }
            BigDecimal qty = purchaseData.getQty();
            BigDecimal price = purchaseData.getPrice();
            BigDecimal purchaseMoney = qty.multiply(price);
            //判断总金额是否相等
            if (money.compareTo(purchaseMoney) == 0) {
                //判断是否大于限额，如果大于就提示需要拆分，如果小于等于就直接插入发票表
                if (money.compareTo(quotaData) <= 0) {
                    ConsignmentSalesInvoice consignmentSalesInvoice = new ConsignmentSalesInvoice();
                    consignmentSalesInvoice.setPlant(oneById.getPlant());
                    consignmentSalesInvoice.setPurchaseOrder(oneById.getPurchaseOrder());
                    consignmentSalesInvoice.setMaterial(oneById.getMaterial());
                    consignmentSalesInvoice.setSupplierCode(oneById.getSupplierCode());
                    consignmentSalesInvoice.setMaterialDescribe(oneById.getMaterialDescribe());
                    consignmentSalesInvoice.setAmount(money);
                    //设置状态为已暂存
                    consignmentSalesInvoice.setInvoiceStatus(0);
                    consignmentSalesInvoice.setCreateTime(String.valueOf(System.currentTimeMillis()));
                    //通过验证就将erp导入数据表中的此条订单状态设置为已开票
                    if (consignmentSalesInvoiceDao.add(consignmentSalesInvoice) != 0) {
                        //设置开票订单状态为已开票
                        oneById.setStatus(0);
                        //将未开票个数设置为0
                        oneById.setNotOutInvoiceNumber(0);
                        consignmentSalesInvoiceOutDao.update(oneById);
                    }
                    number++;
                } else {
                    return number;
                }
            }
        }
        return number;
    }

    /**
     * 标准物资开票信息导出
     *
     * @param filter   搜索条件
     * @param response response对象
     * @return void
     * @author yx
     * @date 2021年8月30日16:33:33
     * @since 1.0
     */
    public void export(HttpServletResponse response, Map<String, String> filter) throws Exception {
        List<ConsignmentSalesInvoiceOutVo> consignmentSalesInvoiceOutVos = consignmentSalesInvoiceOutDao.getAllVos(filter);
        if (ObjectUtils.isEmpty(consignmentSalesInvoiceOutVos)) {
            return;
        }
        ExcelUtil.export(response, consignmentSalesInvoiceOutVos, "寄售物资开票信息", "模板", ConsignmentSalesInvoiceOut.class);
    }

    /**
     * 根据寄售物资id集合查询不含税金额，税额，税价合计
     * @since 2.0
     * @author wxw
     * @date 2022年4月26日
     * @param ids 寄售物资开票id
     * @return String> 不含税金额，税额，税价合计
     */
    public List<String> getMoney(List<String> ids) {
        ArrayList<String> resultList = new ArrayList<>();
        BigDecimal aggregateAmount = new BigDecimal("0.00");
        BigDecimal taxAmount = new BigDecimal("0.00");
        BigDecimal taxRate = new BigDecimal("0.00");
        for (String id : ids) {
            ConsignmentSalesInvoiceOut consignmentSalesInvoiceOut = consignmentSalesInvoiceOutDao.getOneById(Integer.parseInt(id));
            if (consignmentSalesInvoiceOut == null) {
                return resultList;
            }
            BigDecimal unitPrice = consignmentSalesInvoiceOut.getUnitPrice();
            BigDecimal amount = unitPrice.multiply(consignmentSalesInvoiceOut.getQuantity()).setScale(2, BigDecimal.ROUND_HALF_UP);
            BigDecimal taxPrice = amount.multiply(consignmentSalesInvoiceOut.getTaxRate()).setScale(2, BigDecimal.ROUND_HALF_UP);
            taxAmount = taxAmount.add(taxPrice).setScale(2, BigDecimal.ROUND_HALF_UP);
            aggregateAmount = aggregateAmount.add(amount).setScale(2, BigDecimal.ROUND_HALF_UP);
        }
        resultList.add(String.valueOf(aggregateAmount));
        resultList.add(String.valueOf(taxAmount));
        resultList.add(String.valueOf(aggregateAmount.add(taxAmount)));

        // 加上税率
        taxRate=taxAmount.multiply(new BigDecimal(100)).divide(aggregateAmount);
        taxRate=taxRate.setScale(2, BigDecimal.ROUND_HALF_UP);
        resultList.add(String.valueOf(taxRate));

        return resultList;
    }

    /**
     * yyyy-MM-dd格式时间转为毫秒值
     * @since 1.0
     * @author yx
     * @date 2021年8月31日14:56:04
     * @param time yyyy-MM-dd
     * @return String 毫秒值
     */
    public String timeTranslate(String time) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = simpleDateFormat.parse(time);
        return String.valueOf(date.getTime());
    }

}
