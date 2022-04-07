package com.jaezi.common.cache;

import java.util.List;
import java.util.Map;

/**
 * @author yzl
 * @version v1.0
 * @corporation
 * @date 2021/7/23 15:42
 * @description 字典工具类
 */

public class DictUtils {

    /**
     * 字典key-value存入map缓存
     *
     * @param key   字典key
     * @param value 字典value
     * @return
     */
    public static void setDictCache(String key, List value) {
        MapCache.getDictInstance().put(key, value);
    }

    /**
     * 清空字典缓存
     *
     * @return
     */
    public static void clearDictCache() {
        MapCache.getDictInstance().clear();
    }

    /**
     * 删除字典缓存
     *
     * @return
     */
    public static void deleteDictCache(String key) {
        MapCache.getDictInstance().remove(key);
    }

    /**
     * 获取全部缓存
     *
     * @return
     */
    public static Map getDictCache() {
        return MapCache.getDictInstance();
    }

    /**
     * 根据key获取全部缓存
     *
     * @return
     */
    public static Object getDictCache(String key) {
        return MapCache.getDictInstance().get(key);
    }

}
