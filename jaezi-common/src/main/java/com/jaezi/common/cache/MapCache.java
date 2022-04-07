package com.jaezi.common.cache;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author yzl
 * @version v1.0
 * @corporation
 * @date 2021/7/23 15:42
 * @description map缓存类
 */
public class MapCache {

    //字典缓存Map
    private static Map<String, Object> dictMapCache = new ConcurrentHashMap();

    //字典缓存Map
    private static List<String> ipListCache = new LinkedList<>();

    //字典缓存Map
    private static Map<String, Long> ipMapCache = new ConcurrentHashMap();

    private MapCache() {
    }

    public static Map<String, Object> getDictInstance() {
        return dictMapCache;
    }

    public static List getIpListInstance() {
        return ipListCache;
    }

    public static Map<String, Long> getIpMapInstance() {
        return ipMapCache;
    }
}
