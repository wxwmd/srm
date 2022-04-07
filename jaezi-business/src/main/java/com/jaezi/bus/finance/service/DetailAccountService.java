package com.jaezi.bus.finance.service;

import com.jaezi.bus.finance.dao.DetailAccountDao;
import com.jaezi.bus.finance.model.DetailAccount;
import com.jaezi.bus.finance.vo.DetailAccountVo;
import com.jaezi.common.base.BaseService;
import com.jaezi.common.util.ExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * @author wanghaojie
 * @version v1.0
 * @corporation copyright by jaezi.com
 * @date 2021/8/6 15:00
 * @description
 * 明细账业务层
 */
@Service
public class DetailAccountService extends BaseService<DetailAccount, DetailAccountVo> {

    private DetailAccountDao detailAccountDao;

    @Autowired
    public void setBaseDao(DetailAccountDao detailAccountDao) {
        super.setBaseDao(detailAccountDao);
        this.detailAccountDao = detailAccountDao;
    }

    /**
     * 明细账导出
     * @since 1.0
     * @author whj
     * @date 2021/8/25
     * @param response
     * @param filter
     * @return void
     */
    public void export(HttpServletResponse response, Map<String, String> filter) throws Exception {
        List<DetailAccountVo> list = detailAccountDao.getAllVos(filter);
        if (ObjectUtils.isEmpty(list)) {
            return;
        }
        ExcelUtil.export(response,list,"明细账信息","模板", DetailAccount.class);
    }
}
