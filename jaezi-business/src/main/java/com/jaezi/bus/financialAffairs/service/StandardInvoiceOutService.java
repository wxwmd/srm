package com.jaezi.bus.financialAffairs.service;

import com.alibaba.excel.EasyExcel;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.jaezi.bus.financialAffairs.dao.StandardInvoiceOutDao;
import com.jaezi.bus.financialAffairs.dao.StandardInvoiceDao;
import com.jaezi.bus.financialAffairs.dao.StandardInvoiceOutInfoDao;
import com.jaezi.bus.financialAffairs.dto.StandardInvoiceOutDto;
import com.jaezi.bus.financialAffairs.model.StandardInvoice;
import com.jaezi.bus.financialAffairs.model.StandardInvoiceOut;
import com.jaezi.bus.financialAffairs.model.StandardInvoiceOutInfo;
import com.jaezi.bus.financialAffairs.vo.StandardInvoiceOutVo;
import com.jaezi.bus.purchase.dao.PurchaseDao;
import com.jaezi.bus.purchase.model.Purchase;
import com.jaezi.common.base.BaseService;
import com.jaezi.common.bean.DataGrid;
import com.jaezi.common.manager.ThreadManager;
import com.jaezi.common.util.DateUtil;
import com.jaezi.common.util.ExcelUtil;
import com.jaezi.common.util.FileUtil;
import com.jaezi.common.util.IDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.*;

/**
 * @author yx
 * @version v1.0
 * @corporation copyright by jaezi.com
 * @date 2021/8/9  20:40:46
 * @description
 */
@Service
public class StandardInvoiceOutService extends BaseService<StandardInvoiceOut, StandardInvoiceOutVo> {

    private StandardInvoiceOutDao standardInvoiceOutDao;

    private PurchaseDao purchaseDao;

    private StandardInvoiceDao standardInvoiceDao;

    private StandardInvoiceOutInfoDao standardInvoiceOutInfoDao;

    @Autowired
    public void setBaseDao(StandardInvoiceOutDao standardInvoiceOutDao, StandardInvoiceDao standardInvoiceDao, PurchaseDao purchaseDao, StandardInvoiceOutInfoDao standardInvoiceOutInfoDao) {
        super.setBaseDao(standardInvoiceOutDao);
        this.standardInvoiceOutDao = standardInvoiceOutDao;
        this.purchaseDao = purchaseDao;
        this.standardInvoiceDao = standardInvoiceDao;
        this.standardInvoiceOutInfoDao = standardInvoiceOutInfoDao;
    }

    /**
     * ????????????????????????
     *
     * @param filter ????????????
     * @return OutInvoice>
     * @author yx
     * @date 2021???8???10??? 18:12:45
     * @since 1.0
     */
    public DataGrid<StandardInvoiceOut> findAll(Map<String, String> filter) throws ParseException {
        DataGrid<StandardInvoiceOut> dg = new DataGrid<>();
        if (filter.get("startTime") != null && filter.get("endTime") != null) {
            filter.put("startTime", DateUtil.timeTranslate(filter.get("startTime")));
            filter.put("endTime", DateUtil.timeTranslate(filter.get("endTime")));
        }
        if (filter.get("limit") == null || filter.get("page") == null) {
            List<StandardInvoiceOut> all = standardInvoiceOutDao.findAll(filter);
            dg.setRecords(all);
            return dg;
        }
        Page<StandardInvoiceOut> page = PageHelper.startPage(Integer.parseInt(filter.get("page")), Integer.parseInt(filter.get("limit")));
        List<StandardInvoiceOut> list = standardInvoiceOutDao.findAll(filter);
        dg.setRecords(list);
        dg.setTotal(page.getTotal());
        return dg;
    }

    /**
     * ????????????????????????
     *
     * @param standardInvoiceOuts ?????????????????????
     * @return int ????????????
     * @author yx
     * @date 2021???8???11???16:10:16
     * @since 1.0
     */
    public int addVerification(List<StandardInvoiceOut> standardInvoiceOuts, Integer quota) {
        int number = 0;
        BigDecimal quotaData = new BigDecimal(String.valueOf(quota));
        for (StandardInvoiceOut invoice : standardInvoiceOuts) {
            StandardInvoiceOut oneById = standardInvoiceOutDao.getOneById(invoice.getId());
            if (oneById == null) {
                return number;
            }
            BigDecimal unitPrice = oneById.getUnitPrice();
            BigDecimal quantity = oneById.getQuantity();
            BigDecimal money = unitPrice.multiply(quantity);
            String materialNumber = oneById.getMaterial();
            String pOrder = String.valueOf(oneById.getPurchaseOrder());
            Purchase purchaseData = purchaseDao.getByPurOrderAndMatNum(pOrder, materialNumber);
            if (purchaseData == null) {
                return number;
            }
            BigDecimal qty = purchaseData.getQty();
            BigDecimal price = purchaseData.getPrice();
            BigDecimal purchaseMoney = qty.multiply(price);
            //???????????????????????????
            Integer interimInvoiceNumber = IDUtil.getId();
            if (money.compareTo(purchaseMoney) == 0) {
                // ?????????????????????????????????????????????????????????????????????????????????????????????????????????
                if (money.compareTo(quotaData) <= 0) {
                    StandardInvoice standardInvoice = new StandardInvoice();
                    standardInvoice.setPurchaseOrder(oneById.getPurchaseOrder());
                    standardInvoice.setPlant(oneById.getPlant());
                    standardInvoice.setMaterialVoucher(oneById.getMaterialVoucher());
                    standardInvoice.setHongProject(oneById.getHongProject());
                    standardInvoice.setVoucherProject(oneById.getVoucherProject());
                    standardInvoice.setMaterial(oneById.getMaterial());
                    standardInvoice.setSupplierCode(oneById.getSupplierCode());
                    standardInvoice.setMaterialDescribe(oneById.getMaterialDescribe());
                    standardInvoice.setInterimInvoiceNumber(interimInvoiceNumber);
                    standardInvoice.setWithoutTaxAmount(money);
                    //??????????????????????????????
                    standardInvoice.setInvoiceStatus(0);
                    standardInvoice.setCreateTime(String.valueOf(System.currentTimeMillis()));
                    //??????????????????erp?????????????????????????????????????????????????????????
                    if (standardInvoiceDao.add(standardInvoice) != 0) {
                        //????????????????????????????????????
                        //???????????????????????????0
                        oneById.setNotOutInvoiceNumber(new BigDecimal("0.00"));
                        standardInvoiceOutDao.update(oneById);
                        StandardInvoiceOutInfo standardInvoiceOutInfo = new StandardInvoiceOutInfo();
                        standardInvoiceOutInfo.setSerialNumber(oneById.getId());
                        standardInvoiceOutInfo.setPurchaseOrder(oneById.getPurchaseOrder());
                        standardInvoiceOutInfo.setHongProject(oneById.getHongProject());
                        standardInvoiceOutInfo.setMaterialVoucher(oneById.getMaterialVoucher());
                        standardInvoiceOutInfo.setVoucherProject(oneById.getVoucherProject());
                        standardInvoiceOutInfo.setMaterial(oneById.getMaterial());
                        standardInvoiceOutInfo.setMaterialName(oneById.getMaterialName());
                        standardInvoiceOutInfo.setInterimInvoiceNumber(interimInvoiceNumber);
                        standardInvoiceOutInfo.setSupplierCode(oneById.getSupplierCode());
                        standardInvoiceOutInfo.setQuantity(oneById.getQuantity());
                        standardInvoiceOutInfoDao.add(standardInvoiceOutInfo);
                        oneById.setNotOutInvoiceNumber(new BigDecimal("0.00"));
                        standardInvoiceOutDao.update(oneById);
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
     * ????????????????????????????????????
     *
     * @param purchaseOrder ????????????
     * @return OutInvoice ????????????
     * @author yx
     * @date 2021???8???17???17:47:29
     * @since 1.0
     */
    public StandardInvoiceOut getByPurchaseOrder(String purchaseOrder, String material) {
        return standardInvoiceOutDao.getStandardByPOrderAndMat(purchaseOrder, material);
    }

    /**
     * ??????????????????????????????
     *
     * @param filter   ????????????
     * @param response response??????
     * @return void
     * @author yx
     * @date 2021???8???30???16:33:33
     * @since 1.0
     */
    public void export(HttpServletResponse response, Map<String, String> filter) throws Exception {
        List<StandardInvoiceOut> standardInvoiceOutInfo = standardInvoiceOutDao.findAll(filter);
        if (ObjectUtils.isEmpty(standardInvoiceOutInfo)) {
            return;
        }
        ExcelUtil.export(response, standardInvoiceOutInfo, "????????????????????????", "??????", StandardInvoiceOut.class);
    }


    /**
     * ??????????????????
     *
     * @param file
     * @return
     */
    public void excelImport(MultipartFile file) {
        ThreadManager.getInstance().syncExecute(() -> {
            try {
                EasyExcel.read(file.getInputStream(), StandardInvoiceOutDto.class, new StandardInvoiceOutImportListener(standardInvoiceOutDao)).sheet().doRead();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private final String filePath = "/template/std_list.xlsx";
    private final String fileName = "std_list.xlsx";

    /**
     * ????????????????????????
     *
     * @param response
     * @return
     */
    public void templateExport(HttpServletResponse response) {
        FileUtil.downLoad(this.getClass().getResourceAsStream(filePath), fileName, response);
    }

}
