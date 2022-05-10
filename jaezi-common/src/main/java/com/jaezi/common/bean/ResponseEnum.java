package com.jaezi.common.bean;

/**
 * @author iuyy
 * @version v1.0
 * @corporation copyright by jaezi.com
 * @date 2020/10/16 16:50
 * @description API返回数据的代码与消息的对应关系
 * 系统类：1000 - 1999
 *      |-- 认证类： 1000
 *      |-- 用户类： 1100
 *      |-- 角色类： 1200
 *      |-- 权限类： 1300
 *      |-- API类： 1500
 *      |-- 菜单类： 1600
 *      |-- 元素类： 1700
 *
 * 商品类：2000 - 2999
 */
public enum ResponseEnum {

    SUCCESS(200,"操作成功"),
    SUCCESS_NO_CONTENT(200,"No Content"),
    SUCCESS_RESET_PASSWORD(200,"密码重置成功"),
    FAILURE(400,"Bad Request"),
    FAILURE_UNAUTHORIZED(401,"未授权或Token过期，请重新登陆！"),
    FAILURE_FORBIDDEN(403,"Forbidden,权限不足！"),
    FAILURE_NOT_FOUND(404,"Not Found"),
    FAILURE_METHOD_NOT_ALLOWED(405,"方法错误"),
    FAILURE_REQUEST_TIMEOUT(408,"请求超时!"),
    FAILURE_SIGN_ERROR(410, "sign错误"),
    FAILURE_REPEAT_REQUEST(411, "请勿重复请求！"),
    FAILURE_PRECONDITION_FAILED(412, "您的账号已在别处登录！"),
    FAILURE_UPDATE_REPETITION(435, "修改的内容重复！"),
    ACCOUNT_LOCK(416, "您的IP已被锁定,请稍后再试！"),
    FAILURE_PARAMETER_TYPE_WRONG(420,"错误的参数类型"),
    FAILURE_PARAMETER_IS_NULL(421,"参数为空"),
    FAILURE_PASSWORD_WRONG(450,"用户名或密码错误！"),
    NOT_ALLOWED_TO_USE(454,"此功能仅允许供应商用户使用！"),
    ISSUE_OR_ANSWER_INEXISTENCE(456,"提示问题或答案不存在"),
    FAILURE_USER_LOCKED(451,"用户已锁定，请联系管理员！"),
    FAILURE_USER_NOT_EXIST(452,"用户不存在！"),
    FAILURE_SAME_PASSWORD(453,"新旧密码相同！"),
    FAILURE_FILE_TYPE_WRONG(467,"文件格式不符合"),
    FAILURE_DOWNLOAD_FILE_URL_NULL(475,"下载的文件路径为空！"),
    FAILURE_DIRECTORIES_HAVE_SUBMENUS(469,"目录下有子菜单，暂时不可删除!"),
    FAILURE_NOT_FOREIGN_COMMON_API(473,"不是对外开放的API"),
    UNKNOWN(999, "未知错误！"),
    ROLE_HAS_USER(1200, "该角色下存在有用户！"),

    PERMISSION_HAS_MENU(413,"该权限下存在有菜单!"),
    CAMERA_LINKED_INSTANCE(600, "此监控已与实例关联！"),
    CAMERA_LINKED_ALGORITHM(601, "此监控已与算法关联！"),
    TECHNICAL_INFORMATION_SUCCESS(200,"文件上传成功"),
    TECHNICAL_INFORMATION_FAILURE(474,"文件上传成功"),
    REPORT_NOT_HAVE_VISIBLE(475,"请设置报告可见性！"),
    ORDER_SUM_ERROR(501,"订单金额或数量错误！"),
    MERGE_ORDER_AGGREGATE_AMOUNT_ERROR(503,"填写金额与订单实际金额不符！"),
    ORDER_AGGREGATE_AMOUNT_ERROR(504,"请与相关人员线下沟通！"),
    NO_QUOTA(507,"未设置限额，请设置限额再进行操作！"),
    AGGREGATE_AMOUNT_ERROR(505,"总金额已超过限额或不存在该订单！"),
    ORDER_UNSELECTED(502,"订单未选中！"),
    INVOICE_INEXISTENCE(509,"审核！"),
    KEEP_IN_UNDELETABLE(508,"该发票已挂帐，不可废弃！"),
    INVOICE_DATA_HAVE_PROBLEM(513,"该发票数据存在问题,删除失败！"),
    Do_NOT_LOOK_AT(510,"不可查看！"),
    NEED_DISCOUNT_REASON(511,"请填写折扣原因！"),
    ORDER_INFO_BLANK(514,"该开票信息所属的订单不存在"),
    SUPER_ENTERPRISE_USER(1203,"只允许超级企业用户操作！"),
    HAVE_MINUS(512,"折扣发票不能包含负数区间"),
    UPDATE(200,"未修改内容");


    private int code;
    private String msg;
    private ResponseEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public static ResponseEnum getByCode(int code){
        for (ResponseEnum constants : values()) {
            if (constants.getCode() == code) {
                return constants;
            }
        }
        return UNKNOWN;
    }
}
