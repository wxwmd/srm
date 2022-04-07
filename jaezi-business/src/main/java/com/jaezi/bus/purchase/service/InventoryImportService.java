package com.jaezi.bus.purchase.service;

import com.alibaba.excel.EasyExcel;
import com.jaezi.bus.purchase.dao.InventoryDao;
import com.jaezi.bus.purchase.dao.InventoryImportDao;
import com.jaezi.bus.purchase.model.Inventory;
import com.jaezi.bus.purchase.model.InventoryImport;
import com.jaezi.bus.purchase.vo.InventoryImportVo;
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
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author yzl
 * @version v1.0
 * @corporation
 * @date 2021/8/4 15:39
 * @description 库存导入逻辑层
 */

@Service
public class InventoryImportService extends BaseService<InventoryImport, InventoryImportVo> {
    private InventoryImportDao inventoryImportDao;
    private InventoryDao inventoryDao;

    private final String filePath = "/template/库存导入模板.xlsx";

    private final String fileName = "库存导入模板.xlsx";

    @Autowired
    public void setBaseDao(InventoryImportDao inventoryImportDao, InventoryDao inventoryDao) {
        super.setBaseDao(inventoryImportDao);
        this.inventoryImportDao = inventoryImportDao;
        this.inventoryDao = inventoryDao;
    }

    /**
     * 库存导入
     *
     * @param file
     * @return
     */
    public JsonResult excelImport(MultipartFile file) {
        try {
            InventoryCheckImportListener inventoryCheckImportListener = new InventoryCheckImportListener();
            EasyExcel.read(file.getInputStream(), InventoryImport.class, inventoryCheckImportListener).sheet().doRead();
            checkInventory(inventoryCheckImportListener.getData());
        } catch (BaseException | IOException e) {
            return new JsonResult(ResponseEnum.FAILURE.getCode(), e.getMessage());
        }

        ThreadManager.getInstance().syncExecute(() -> {
            try {
                EasyExcel.read(file.getInputStream(), InventoryImport.class, new InventoryImportListener(inventoryImportDao)).sheet().doRead();
                //更新库存匹配
                inventoryDao.truncateInventory();
                inventoryDao.updateInventory();
                //进行库存匹配
                List<Inventory> inventoryList = inventoryDao.getInventoryOrderBy();
                //根据物料号分组
                Map<String, List<Inventory>> inventoryListMap = inventoryList.stream()
                        .collect(Collectors.groupingBy(Inventory::getMaterialNumber));
                //查询所有供应商库存
                List<InventoryImportVo> inventoryImportList = inventoryImportDao.getAllVos(null);
                for (Map.Entry<String, List<Inventory>> inventoryMap : inventoryListMap.entrySet()) {
                    String materialNumber = inventoryMap.getKey();

                    List<InventoryImportVo> inventoryImportVos = inventoryImportList.parallelStream()
                            .filter(inventoryImportVo -> inventoryImportVo.getMaterialNumber().equals(materialNumber))
                            .collect(Collectors.toList());
                    if (!ObjectUtils.isEmpty(inventoryImportVos)) {
                        InventoryImportVo inventoryImportVo = inventoryImportVos.get(0);
                        //取出供应商库存
                        BigDecimal supplierInventory = inventoryImportVo.getSupplierInventory();
                        List<Inventory> inventoryList1 = inventoryMap.getValue();
                        for (Inventory inventory : inventoryList1) {
                            supplierInventory = supplierInventory.subtract(inventory.getDefQty());
                            inventory.setInventoryMatching(supplierInventory);
                            //供应商库存和欠交库存匹配
                            if (supplierInventory.compareTo(BigDecimal.ZERO) >= 0) {
                                //如果大于等于欠交量，库存匹配为0，状态为绿灯
                                inventory.setStatus("green");
                            } else {
                                //如果小于欠交量，库存匹配为供应商库存-欠交库存，状态为红灯
                                inventory.setStatus("red");
                            }
                            inventoryDao.update(inventory);
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        return JsonResult.SUCCESS;
    }

    public void checkInventory(List<InventoryImport> inventoryImportList) throws BaseException {
        if (ObjectUtils.isEmpty(inventoryImportList)) {
            throw new BaseException("数据不合格要求,请检查");
        }
        List<String> materialNumberList = inventoryImportList.parallelStream().map(InventoryImport::getMaterialNumber).distinct().collect(Collectors.toList());
        Long materialNumberIsNullSum = materialNumberList.parallelStream().filter(materialNumber -> ObjectUtils.isEmpty(materialNumber)).count();
        if (materialNumberIsNullSum > 0) {
            throw new BaseException("物料数据为空,请检查");
        }
        String token = JwtUtil.getRequest().getHeader("Credential");
        String username = JwtUtil.getUsername(token);
        Long supplier = inventoryImportList.parallelStream().map(InventoryImport::getSupplierCode).distinct().filter(supplierCode -> !supplierCode.equals(username)).count();
        if (supplier > 0) {
            throw new BaseException("无此供应商库存信息,请检查");
        }
        List<String> supplierCodeList = inventoryImportList.parallelStream().map(InventoryImport::getSupplierCode).distinct().collect(Collectors.toList());
        Long supplierCodeIsNullSum = supplierCodeList.parallelStream().filter(materialNumber -> ObjectUtils.isEmpty(materialNumber)).count();
        if (supplierCodeIsNullSum > 0) {
            throw new BaseException("供应商数据为空,请检查");
        }
        List<String> inventoryIdList = inventoryImportDao.getInventoryByMatNumsAndSuppCodes(materialNumberList, supplierCodeList);
        Long planIdIsNullSum = inventoryIdList.parallelStream().filter(productionPlanId -> ObjectUtils.isEmpty(productionPlanId)).count();
        if (ObjectUtils.isEmpty(inventoryIdList) || planIdIsNullSum > 0 || inventoryIdList.size() != materialNumberList.size()) {
            throw new BaseException("无此供应商库存信息,请检查");
        }
    }

    /**
     * 库存模板导出
     *
     * @param response
     */
    public void templateExport(HttpServletResponse response) {
        FileUtil.downLoad(this.getClass().getResourceAsStream(filePath), fileName, response);
    }
}
