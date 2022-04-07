package com.jaezi.bus.purchase.service;

import com.alibaba.excel.EasyExcel;
import com.jaezi.bus.purchase.dao.ProductionPlanImportDao;
import com.jaezi.bus.purchase.model.InventoryImport;
import com.jaezi.bus.purchase.model.ProductionPlanImport;
import com.jaezi.bus.purchase.vo.ProductionPlanImportVo;
import com.jaezi.common.base.BaseException;
import com.jaezi.common.base.BaseService;
import com.jaezi.common.bean.JsonResult;
import com.jaezi.common.bean.ResponseEnum;
import com.jaezi.common.manager.ThreadManager;
import com.jaezi.common.util.FileUtil;
import com.jaezi.common.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author yzl
 * @version v1.0
 * @corporation
 * @date 2018/11/19 15:39
 * @description 生产计划导入逻辑层
 */

@Service
public class ProductionPlanImportService extends BaseService<ProductionPlanImport, ProductionPlanImportVo> {
    private ProductionPlanImportDao productionPlanImportDao;

    private final String filePath = "/template/生厂计划导入模板.xlsx";

    private final String fileName = "生厂计划导入模板.xlsx";

    @Autowired
    public void setBaseDao(ProductionPlanImportDao productionPlanImportDao) {
        super.setBaseDao(productionPlanImportDao);
        this.productionPlanImportDao = productionPlanImportDao;
    }

    /**
     * 生产计划导入
     *
     * @param file
     * @return
     */
    public JsonResult excelImport(MultipartFile file) {
        try {
            ProductionPlanCheckImportListener productionPlanCheckImportListener = new ProductionPlanCheckImportListener();
            EasyExcel.read(file.getInputStream(), ProductionPlanImport.class, productionPlanCheckImportListener).sheet().doRead();
            checkProductionPlan(productionPlanCheckImportListener.getData());
        } catch (BaseException | IOException e) {
            return new JsonResult(ResponseEnum.FAILURE.getCode(), e.getMessage());
        }

        ThreadManager.getInstance().syncExecute(() -> {
            try {
                EasyExcel.read(file.getInputStream(), ProductionPlanImport.class, new ProductionPlanImportListener(productionPlanImportDao)).sheet().doRead();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        return JsonResult.SUCCESS;
    }

    public void checkProductionPlan(List<ProductionPlanImport> productionPlanImportList) throws BaseException {
        if (ObjectUtils.isEmpty(productionPlanImportList)) {
            throw new BaseException("数据不合格要求,请检查");
        }
        List<String> materialNumberList = productionPlanImportList.parallelStream().map(ProductionPlanImport::getMaterialNumber).distinct().collect(Collectors.toList());
        Long materialNumberIsNullSum = materialNumberList.parallelStream().filter(materialNumber -> ObjectUtils.isEmpty(materialNumber)).count();
        if (materialNumberIsNullSum > 0) {
            throw new BaseException("物料数据为空,请检查");
        }
        String token = JwtUtil.getRequest().getHeader("Credential");
        String username = JwtUtil.getUsername(token);
        Long supplier = productionPlanImportList.parallelStream().map(ProductionPlanImport::getSupplierCode).distinct().filter(supplierCode -> !supplierCode.equals(username)).count();
        if (supplier > 0) {
            throw new BaseException("无此供应商生成计划信息,请检查");
        }
        List<String> supplierCodeList = productionPlanImportList.parallelStream().map(ProductionPlanImport::getSupplierCode).distinct().collect(Collectors.toList());
        Long supplierCodeIsNullSum = supplierCodeList.parallelStream().filter(materialNumber -> ObjectUtils.isEmpty(materialNumber)).count();
        if (supplierCodeIsNullSum > 0) {
            throw new BaseException("供应商数据为空,请检查");
        }
        List<String> productionPlanIdList = productionPlanImportDao.getProductionPlanBy(materialNumberList, supplierCodeList);
        Long planIdIsNullSum = productionPlanIdList.parallelStream().filter(productionPlanId -> ObjectUtils.isEmpty(productionPlanId)).count();
        if (ObjectUtils.isEmpty(productionPlanIdList) || planIdIsNullSum > 0 || productionPlanIdList.size() != materialNumberList.size()) {
            throw new BaseException("无此供应商生成计划信息,请检查");
        }
    }

    /**
     * 生产计划导出
     *
     * @param response
     */
    public void templateExport(HttpServletResponse response) {
        FileUtil.downLoad(this.getClass().getResourceAsStream(filePath), fileName, response);
    }
}
