package com.jaezi.bus.finance.service;

import com.jaezi.bus.finance.dao.BookBalanceDao;
import com.jaezi.bus.finance.model.BookBalance;
import com.jaezi.bus.finance.vo.BookBalanceVo;
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
 * @date 2021/8/6 14:50
 * @description
 * 账面余额的业务层
 */
@Service
public class BookBalanceService  extends BaseService<BookBalance, BookBalanceVo> {
    private BookBalanceDao bookBalanceDao;

    @Autowired
    public void setBaseDao(BookBalanceDao bookBalanceDao) {
        super.setBaseDao(bookBalanceDao);
        this.bookBalanceDao = bookBalanceDao;
    }

    /**
     * 导出excel表格
     * @since 1.0
     * @author whj
     * @date 2021/8/24
     * @param response
     * @param filter
     * @return void
     */
    public void export(HttpServletResponse response, Map<String,String> filter) throws Exception {
        List<BookBalanceVo> list = bookBalanceDao.getAllVos(filter);
        if (ObjectUtils.isEmpty(list)) {
            return;
        }
        ExcelUtil.export(response,list,"账面余额信息","模板", BookBalance.class);
    }
}
