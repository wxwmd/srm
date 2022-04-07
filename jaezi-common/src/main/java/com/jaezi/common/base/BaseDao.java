package com.jaezi.common.base;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @author iuyy
 * @version v1.0
 * @corporation copyright by jaezi.com
 * @date 2019/4/19 17:12
 * @description 所有DAO的基类，定义了基础的增删改查
 */
public interface BaseDao<T extends BaseModel, V extends T> {

//    List<T> getAll(Map<String, String> filter);

    T getOneById(Serializable id);

    int add(T t);

    int update(T t);

    int remove(Serializable id);

    int delete(Serializable id);

    List<V> getAllVos(Map<String, String> filter);
}
