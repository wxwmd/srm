package com.jaezi.common.util;

import java.util.Random;
import java.util.UUID;

/**
 * @author iuyy
 * @version v1.0
 * @corporation copyright by iuyy.net
 * @date 2018/5/18 11:36
 * @description
 */

public class RandomUtil {
    public static Double randomDouble(){
        return (new Random().nextInt(99)+1)/100.0;
    }

    /**
     * 随机生成一个UUID
     * @return UUID
     */
    public static String randomUUID(){
        return UUID.randomUUID().toString().replace("-", "");
    }

    public static String fixedFour(){
        long round = Math.round((Math.random() + 1) * 1000);
        return String.valueOf(round);
    }

    public static String fixedTwo(){
        Random random = new Random();
        return String.valueOf(random.nextInt(90) + 10);
    }

    public static String pwd(){
        int  maxNum = 36;
        int i;
        int count = 0;
        char[] str = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K',
                'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W',
                'X', 'Y', 'Z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };
        StringBuffer pwd = new StringBuffer("");
        Random r = new Random();
        while(count < 8){
            i = Math.abs(r.nextInt(maxNum));
            pwd.append(str[i]);
            count ++;
        }
        return pwd.toString();
    }
}
