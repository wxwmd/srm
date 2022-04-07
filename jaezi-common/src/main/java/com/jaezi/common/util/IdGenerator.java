package com.jaezi.common.util;

import java.util.UUID;

/**
 * @author iuyy
 * @version v1.0
 * @corporation copyright by jaezi.com
 * @date 2020/11/11 17:24
 * @description
 */
public class IdGenerator {

    public static final String PREFIX_INSTANCE = "ai-";
    public static final String PREFIX_CAMERA = "ca-";

    public static String id(String prefix){
        String uuid = UUID.randomUUID().toString();
        return prefix + uuid.substring(0,uuid.lastIndexOf("-"));
    }

    public static String appKey(String prefix){
        String uuid = UUID.randomUUID().toString();
        return prefix + uuid.substring(uuid.lastIndexOf("-") + 1);
    }
}
