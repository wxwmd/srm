package com.jaezi.info.dao;

import com.jaezi.common.base.BaseDao;
import com.jaezi.info.model.Msg;
import com.jaezi.info.vo.MsgVo;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author yzl
 * @version v1.0
 * @corporation
 * @date 2021/7/22 17:12
 * @description 信息数据访问对象
 */
@Repository
public interface MsgDao extends BaseDao<Msg, MsgVo> {

    /**
     * 新闻置顶
     *
     * @param id   公告ID
     * @param sort 排序
     * @return
     */
    int updateTop(Integer id, Integer sort);

    /**
     * 查询首页新闻
     *
     * @param type 新闻类型
     * @return Msg>
     * @author yx
     * @date 2021年9月26日15:29:18
     * @since 1.0
     */
    List<Msg> getNewest(Integer type);

    /**
     * 新闻可见性查询
     *
     * @param filter type 类型
     *               title 标题
     *               status 状态
     *               type 类型
     *               createUser 创建人
     * @return Msg>
     */
    List<Msg> findByVisible(Map<String, String> filter);
}
