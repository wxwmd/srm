package com.jaezi.common.base;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.jaezi.common.bean.DataGrid;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @author iuyy
 * @version v1.0
 * @corporation Copyright by jaezi.com
 * @date 2020/3/27 20:44
 * @description 基础服务接口的实现类
 */
public abstract class BaseService<T extends BaseModel, V extends T> {

    private BaseDao<T, V> baseDao;

    public void setBaseDao(BaseDao<T, V> baseDao) {
        this.baseDao = baseDao;
    }

    public DataGrid<V> getAll(Map<String, String> filter) {
        DataGrid<V> dg = new DataGrid<>();
        if (filter == null || filter.get("limit") == null || filter.get("page") == null) {
            List<V> all = baseDao.getAllVos(filter);
            dg.setRecords(all);
            return dg;
        }
        Page<V> page = PageHelper.startPage(Integer.parseInt(filter.get("page")), Integer.parseInt(filter.get("limit")));
        List<V> list = baseDao.getAllVos(filter);
        dg.setRecords(list);
        dg.setTotal(page.getTotal());
        return dg;
    }

    public T getOneById(Serializable id) {
        return baseDao.getOneById(id);
    }

    public int add(T t) {
        return baseDao.add(t);
    }

    public int update(T t) {
        return baseDao.update(t);
    }

    public int remove(Serializable id) {
        return baseDao.remove(id);
    }

    public int delete(Serializable id) {
        return baseDao.delete(id);
    }
}
