package com.jaezi.bus.purchase.service;

import com.alibaba.excel.EasyExcel;
import com.jaezi.bus.purchase.dao.PurchaseDao;
import com.jaezi.bus.purchase.dto.PurchaseDto;
import com.jaezi.bus.purchase.model.Purchase;
import com.jaezi.bus.purchase.vo.PurchaseVo;
import com.jaezi.common.base.BaseService;
import com.jaezi.common.manager.ThreadManager;
import com.jaezi.common.util.ExcelUtil;
import com.jaezi.common.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author yzl
 * @version v1.0
 * @corporation copyright by jaezi.com
 * @date 2021/7/26 17:16
 * @description 采购订单逻辑处理类
 */

@Service
public class PurchaseService extends BaseService<Purchase, PurchaseVo> {

    private final String filePath = "/template/order.xlsx";
    private final String fileName = "order.xlsx";

    private PurchaseDao purchaseDao;

    @Autowired
    public void setBaseDao(PurchaseDao purchaseDao) {
        super.setBaseDao(purchaseDao);
        this.purchaseDao = purchaseDao;
    }

    /**
     * 根据条件导出excel到浏览器
     *
     * @param filter 采购订单
     * @return 操作是否成功
     */
    public void export(HttpServletResponse response, Map<String, String> filter) throws Exception {
        List<PurchaseVo> purchaseVoList = purchaseDao.getAllVos(filter).parallelStream().peek(purchaseVo -> {
            if (Optional.ofNullable(purchaseVo.getStatus()).orElse("0").equals("1")) {
                purchaseVo.setStatusFormat("确认");
            } else {
                purchaseVo.setStatusFormat("未确认");
            }
        }).collect(Collectors.toList());

        if (ObjectUtils.isEmpty(purchaseVoList)) {
            return;
        }
        ExcelUtil.export(response, purchaseVoList, "采购订单信息", "模板", Purchase.class);
    }

    /**
     * 订单导入
     *
     * @param file
     * @return
     */
    public void excelImport(MultipartFile file) {
        ThreadManager.getInstance().syncExecute(() -> {
            try {
                EasyExcel.read(file.getInputStream(), PurchaseDto.class, new PurchaseImportListener(purchaseDao)).sheet().doRead();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * 订单模板导出
     *
     * @param response
     * @return
     */
    public void templateExport(HttpServletResponse response) {
        FileUtil.downLoad(this.getClass().getResourceAsStream(filePath), fileName, response);
    }

    /**
     * 订单未确认条数
     *
     * @param
     * @return int
     */
    public int unconfirmed(Map<String, String> filter) {
        return purchaseDao.unconfirmed(filter);
    }
}
