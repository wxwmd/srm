package com.jaezi.common.cache;

import java.util.Map;

/**
 * @author yzl
 * @version v1.0
 * @corporation
 * @date 2021/7/23 15:42
 * @description IP工具类
 */

public class IpUtils {

    /**
     * 限制ip登录限制次数
     */
    private static final Long ipLimited = 5L;
    /**
     * 限制ip登录过期时间（毫秒）十分钟
     */
    private static final Long ipExpiration = 6000000L;

    public static Long getIpLimited() {
        return ipLimited;
    }

    public static Long getIpExpiration() {
        return ipExpiration;
    }

    /**
     * ip存入List缓存
     *
     * @param key ip
     * @return
     */
    public synchronized static void setIpListCache(String key) {
        MapCache.getIpListInstance().add(key);
    }

    /**
     * 删除IP缓存List
     *
     * @return
     */
    public synchronized static void deleteIpListCache(String key) {
        MapCache.getIpListInstance().remove(key);
    }

    /**
     * ip在List缓存中计数
     *
     * @param ip
     * @return
     */
    public synchronized static Long getIpListCacheSum(String ip) {
        return MapCache.getIpListInstance().stream().filter(s -> s.equals(ip)).count();
    }

    /**
     * IP key-value存入map缓存
     *
     * @param key   字典key
     * @param value 字典value
     * @return
     */
    public static void setIpMapCache(String key, Long value) {
        MapCache.getIpMapInstance().put(key, value);
    }

    /**
     * 删除IP Map缓存
     *
     * @return
     */
    public static void deleteIpMapCache(String key) {
        MapCache.getIpMapInstance().remove(key);
    }

    /**
     * 获取全部IP缓存
     *
     * @return
     */
    public static Map<String, Long> getIpCache() {
        return MapCache.getIpMapInstance();
    }

    /**
     * 获取指定IP的时间戳
     *
     * @param key 字典key
     * @return
     */
    public static Long getIpValueCache(String key) {
        return MapCache.getIpMapInstance().get(key);
    }
}
