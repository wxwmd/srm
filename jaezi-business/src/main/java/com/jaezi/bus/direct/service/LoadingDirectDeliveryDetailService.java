package com.jaezi.bus.direct.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.jaezi.bus.direct.dao.LoadingDirectDeliveryDao;
import com.jaezi.bus.direct.dao.LoadingDirectDeliveryDetailDao;
import com.jaezi.bus.direct.model.LoadingDirectDelivery;
import com.jaezi.bus.direct.model.LoadingDirectDeliveryDetail;
import com.jaezi.bus.direct.vo.LoadingDirectDeliveryDetailVo;
import com.jaezi.common.base.BaseService;
import com.jaezi.common.bean.DataGrid;
import com.jaezi.common.util.ExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * @author yzl
 * @version v1.0
 * @corporation
 * @date 2021/8/17 17:06
 * @description
 * 直达装车单明细业务层
 */
@Service
public class LoadingDirectDeliveryDetailService extends BaseService<LoadingDirectDeliveryDetail, LoadingDirectDeliveryDetailVo> {

    private LoadingDirectDeliveryDetailDao loadingDirectDeliveryDetailDao;

    @Autowired
    public void setBaseDao(LoadingDirectDeliveryDetailDao loadingDirectDeliveryDetailDao) {
        super.setBaseDao(loadingDirectDeliveryDetailDao);
        this.loadingDirectDeliveryDetailDao = loadingDirectDeliveryDetailDao;
    }
}
