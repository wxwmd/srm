package com.jaezi.common.base;

import com.jaezi.common.bean.JsonResult;
import com.jaezi.common.bean.ResponseEnum;

/**
 * @author yzl
 * @version v1.0
 * @corporation
 * @date 2021/7/22 16:46
 * @description 基类API
 */

public class BaseApi {

    /**
     * 数据返回处理
     *
     * @param result 返回值
     * @return JsonResult
     */
    protected JsonResult returnIntResult(int result) {
        if (result > 0) {
            return JsonResult.SUCCESS;
        } else {
            return JsonResult.FAILURE;
        }
    }

    /**
     * 数据返回处理
     *
     * @param obj 返回值
     * @return JsonResult
     */
    protected JsonResult returnObjectResult(Object obj) {
        return null == obj ? new JsonResult(ResponseEnum.SUCCESS_NO_CONTENT) : new JsonResult(obj);
    }


}
