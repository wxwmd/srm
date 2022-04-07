package com.jaezi.srminterface.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.jaezi.common.base.BaseException;
import com.jaezi.common.manager.ThreadManager;
import com.jaezi.common.util.DateUtil;
import com.jaezi.common.util.SHA256;
import com.jaezi.srminterface.dao.*;
import com.jaezi.srminterface.model.*;
import com.jaezi.srminterface.vo.SqlConfigVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ObjectUtils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.stream.Collectors;

/**
 * @author yzl
 * @version v1.0
 * @corporation
 * @date 2021/8/3  13:07
 * @description erp公共服务类
 */
public class InterfaceCommService {

    private static final Logger LOGGER = LoggerFactory.getLogger(InterfaceCommService.class);

    private final InterfaceServerDao interfaceServerDao;
    private final TableConfigDao tableConfigDao;
    private final FiledConfigDao filedConfigDao;
    private final SqlConfigDao sqlConfigDao;
    private final MaterialSupplierDao materialSupplierDao;

    public InterfaceCommService(InterfaceServerDao interfaceServerDao, TableConfigDao tableConfigDao, SqlConfigDao sqlConfigDao,
                                MaterialSupplierDao materialSupplierDao, FiledConfigDao filedConfigDao) {
        this.interfaceServerDao = interfaceServerDao;
        this.tableConfigDao = tableConfigDao;
        this.sqlConfigDao = sqlConfigDao;
        this.materialSupplierDao = materialSupplierDao;
        this.filedConfigDao = filedConfigDao;
    }

    /**
     * 动态表服务方法
     *
     * @param jsonData
     */
    public void tableService(String tableName, String jsonData) throws BaseException {
        List<Map<String, Object>> mapList = JSONArray.parseObject(jsonData, List.class);
        //数据属性检查
        tableCheckService(tableName, mapList);

        ThreadManager.getInstance().syncExecute(() -> tableService(tableName, mapList));
    }

    public void tableCheckService(String tableName, List<Map<String, Object>> mapList) throws BaseException {
        List<String> filedList = new LinkedList<>();

        for (Map.Entry<String, Object> m : mapList.get(0).entrySet()) {
            filedList.add(m.getKey());

            Map<String, String> filter = new ConcurrentHashMap<>(2);
            filter.put("tableName", tableName);
            filter.put("filedName", m.getKey());
            List<FiledConfig> tableConfigList1 = filedConfigDao.getAll(filter);
            if (ObjectUtils.isEmpty(tableConfigList1)) {
                throw new BaseException("配置表未配置,请检查");
            }
        }
    }

    /**
     * 动态表服务方法
     *
     * @param mapList
     * @return String
     */
    public void tableService(String tableName, List<Map<String, Object>> mapList) {
        List<FiledConfig> tableConfigList = new Vector<>();
        List<String> filedList = new Vector<>();

        StringBuffer tableNameSb = new StringBuffer();
        tableNameSb.append("`");
        tableNameSb.append(tableName);
        tableNameSb.append("`");

        try {
            for (Map.Entry<String, Object> m : mapList.get(0).entrySet()) {
                filedList.add(m.getKey());

                Map<String, String> filter = new ConcurrentHashMap<>(1);
                filter.put("tableName", tableName);
                filter.put("filedName", m.getKey());
                List<FiledConfig> tableConfigList1 = filedConfigDao.getAll(filter);
                if (tableConfigList1.size() > 1) {
                    tableConfigList1 = tableConfigList1.subList(0, 1);
                }
                tableConfigList1.stream().forEach(tableConfig -> {
                    if (ObjectUtils.isEmpty(tableConfig.getIsPk())) {
                        tableConfig.setIsPk(0);
                    }
                    if (!ObjectUtils.isEmpty(tableConfig.getComments())) {
                        StringBuffer sb = new StringBuffer();
                        sb.append("'");
                        sb.append(tableConfig.getComments());
                        sb.append("'");
                        tableConfig.setComments(sb.toString());
                    }
                    if (!ObjectUtils.isEmpty(tableConfig.getDefaultValues())) {
                        StringBuffer sb = new StringBuffer();
                        sb.append("'");
                        sb.append(tableConfig.getDefaultValues());
                        sb.append("'");
                        tableConfig.setDefaultValues(sb.toString());
                    }
                    StringBuffer sb = new StringBuffer();
                    sb.append("`");
                    sb.append(tableConfig.getFiledName());
                    sb.append("`");
                    tableConfig.setFiledName(sb.toString());
                });

                tableConfigList.addAll(tableConfigList1);
            }

            Long isPk = tableConfigList.stream().filter(tableConfigVo -> tableConfigVo.getIsPk() > 0).count();
            StringBuffer sb = new StringBuffer();
            sb.append("'");
            sb.append(tableConfigDao.getCommentsByTableName(tableName));
            sb.append("'");
            //创建表
            synchronized (this) {
                tableConfigDao.dropTable(tableNameSb.toString());

                interfaceServerDao.createTable(tableNameSb.toString(), sb.toString(), tableConfigList, isPk);
            }
//            StringBuffer sb1 = new StringBuffer();
//            sb1.append("'");
//            sb1.append(tableName);
//            sb1.append("'");
//            //获取表列名
//            List<String> tableFiledList = interfaceServerDao.getTableFiled(sb1.toString());
//            //求差集
//            List<String> list = filedList.stream().filter(t -> !tableFiledList.contains(t)).collect(Collectors.toList());
//
//            for (String filedName : list) {
//                Map<String, String> filter = new ConcurrentHashMap(2);
//                filter.put("tableName", tableName);
//                filter.put("filedName", filedName);
//                FiledConfig tableConfig = filedConfigDao.getAll(filter).get(0);
//                if (ObjectUtils.isEmpty(tableConfig.getIsPk())) {
//                    tableConfig.setIsPk(0);
//                }
//                if (!ObjectUtils.isEmpty(tableConfig.getComments())) {
//                    StringBuffer sb2 = new StringBuffer();
//                    sb2.append("'");
//                    sb2.append(tableConfig.getComments());
//                    sb2.append("'");
//                    tableConfig.setComments(sb2.toString());
//                }
//                StringBuffer sb3 = new StringBuffer();
//                sb3.append("`");
//                sb3.append(tableConfig.getFiledName());
//                sb3.append("`");
//                tableConfig.setFiledName(sb3.toString());
//
//                //添加列名
//                synchronized (this) {
//                    interfaceServerDao.alterFiled(tableNameSb.toString(), tableConfig);
//                }
//            }

            synchronized (this) {
                //数据写入数据表
                interfaceServerDao.saveTable(tableNameSb.toString(), filedList, mapList);
            }

            //无主键不进行数据更新
//            if (isPk > 0) {
//                //数据更新数据表
//                List<String> pkList = new Vector<>();
//                StringBuffer sb3 = new StringBuffer();
//
//                int m = 0;
//                for (FiledConfig tableConfig : tableConfigList) {
//                    if (tableConfig.getIsPk() == 1) {
//                        String filedName = tableConfig.getFiledName().substring(tableConfig.getFiledName().indexOf("`") + 1, tableConfig.getFiledName().lastIndexOf("`"));
//
//                        pkList.add(filedName);
//
//                        if (m > 0) {
//                            sb3.append(" and ");
//                        }
//                        sb3.append(tableConfig.getFiledName() + " in ( ");
//
//                        for (int n = 0; n < mapList.size(); n++) {
//                            if (!ObjectUtils.isEmpty(mapList.get(n).get(filedName))) {
//                                String filedNameValue = mapList.get(n).get(filedName).toString();
//
//                                if (n == mapList.size() - 1) {
//                                    if (!ObjectUtils.isEmpty(filedNameValue)) {
//                                        sb3.append("\"" + filedNameValue + "\"");
//                                    }
//                                } else {
//                                    if (!ObjectUtils.isEmpty(filedNameValue)) {
//                                        sb3.append("\"" + filedNameValue + "\",");
//                                    }
//                                }
//                            }
//                        }
//                        sb3.append(" )");
//                        m++;
//                    }
//                }
//
//                StringBuffer sb4 = new StringBuffer();
//                sb4.append("update " + tableNameSb + " set ");
//                for (int k = 0; k < filedList.size(); k++) {
//                    sb4.append("`" + filedList.get(k) + "` = case");
//
//                    for (Map<String, Object> map : mapList) {
//                        sb4.append(" when ");
//                        StringBuffer sb2 = new StringBuffer();
//                        for (int p = 0; p < pkList.size(); p++) {
//                            if (p == 0) {
//                                sb2.append("`" + pkList.get(p) + "` = \"" + map.get(pkList.get(p)) + "\"");
//                            } else {
//                                sb2.append(" and " + "`" + pkList.get(p) + "` = \"" + map.get(pkList.get(p)) + "\"");
//                            }
//                        }
//
//                        sb4.append(sb2);
//                        sb4.append(" then ");
//
//                        sb4.append("\"" + map.get(filedList.get(k)) + "\"");
//                    }
//                    if (k == filedList.size() - 1) {
//                        sb4.append(" end ");
//                    } else {
//                        sb4.append(" end, ");
//                    }
//                }
//
//                if (!ObjectUtils.isEmpty(sb3)) {
//                    sb4.append(" where ");
//                    sb4.append(sb3);
//                }
//
//                synchronized (this) {
//                    interfaceServerDao.updateTables(sb4.toString());
//                }
//            }
        } catch (Exception e) {
            LOGGER.error("ERP数据导入异常" + e.getMessage());
        }
        LOGGER.info("数据表导入成功！");
    }

    public String getCron() {
//        return "0 0/7 * * * ?";
        return "0 0 1 * * ?";
    }

    private static final int BATCH_COUNT = 3000;

    static Class materialClazz = null;
    static Class userClazz = null;
    static Class supplierClazz = null;
    static Class supplierDtoClazz = null;
    static Class purchaseClazz = null;
    static Class standardInvoiceOutClazz = null;
    static Class supplyDiffClazz = null;
    static Class supplyDiffDtoClazz = null;

    static Method getBuyerNumber = null;
    static Method setUsername = null;
    static Method setPassword = null;
    static Method setRealName = null;
    static Method setRoleId = null;
    static Method setStatus = null;
    static Method setUserType = null;
    static Method setCreateTime = null;
    static Method getUsername = null;
    static Method getSupplierDtoUsername = null;
    static Method getCompanyName = null;
    static Method getUserUsername = null;
    static Method getUserId = null;
    static Method setExamineStatus = null;
    static Method setCompanyName = null;
    static Method setUserId = null;
    static Method setPurchaseId = null;
    static Method setStandardInvoiceOutId = null;
    static Method setStandardInvoiceOutCreateTime = null;
    static Method getPurchaseMaterial = null;
    static Method getPurchaseSupplierCode = null;
    static Method setSupplyDiffMaterialNumber = null;
    static Method setSupplyDiffType = null;
    static Method setSupplyDiffSupplierCode = null;

    static {
        try {
            materialClazz = Class.forName("com.jaezi.system.model.Material");
            userClazz = Class.forName("com.jaezi.system.model.User");
            supplierClazz = Class.forName("com.jaezi.system.model.Supplier");
            supplierDtoClazz = Class.forName("com.jaezi.system.dto.SupplierDto");
            purchaseClazz = Class.forName("com.jaezi.bus.purchase.model.Purchase");
            standardInvoiceOutClazz = Class.forName("com.jaezi.bus.financialAffairs.model.StandardInvoiceOut");
            supplyDiffClazz = Class.forName("com.jaezi.bus.plan.model.SupplyDiff");
            supplyDiffDtoClazz = Class.forName("com.jaezi.bus.plan.dto.SupplyDiffDto");
            try {
                getBuyerNumber = materialClazz.getDeclaredMethod("getBuyerNumber");
                setUsername = userClazz.getDeclaredMethod("setUsername", String.class);
                setPassword = userClazz.getDeclaredMethod("setPassword", String.class);
                setRealName = userClazz.getDeclaredMethod("setRealName", String.class);
                setRoleId = userClazz.getDeclaredMethod("setRoleId", Integer.class);
                setStatus = userClazz.getDeclaredMethod("setStatus", Integer.class);
                setUserType = userClazz.getDeclaredMethod("setUserType", Integer.class);
                setCreateTime = userClazz.getDeclaredMethod("setCreateTime", Long.class);
                getUsername = supplierDtoClazz.getDeclaredMethod("getUsername");
                getSupplierDtoUsername = supplierDtoClazz.getDeclaredMethod("getUsername");
                getCompanyName = supplierDtoClazz.getDeclaredMethod("getCompanyName");
                getUserUsername = userClazz.getDeclaredMethod("getUsername");
                getUserId = userClazz.getDeclaredMethod("getId");
                setExamineStatus = supplierClazz.getDeclaredMethod("setExamineStatus", Integer.class);
                setCompanyName = supplierClazz.getDeclaredMethod("setCompanyName", String.class);
                setUserId = supplierClazz.getDeclaredMethod("setUserId", Integer.class);
                setPurchaseId = purchaseClazz.getDeclaredMethod("setId", Integer.class);
                setStandardInvoiceOutId = standardInvoiceOutClazz.getDeclaredMethod("setId", Integer.class);
                setStandardInvoiceOutCreateTime = standardInvoiceOutClazz.getDeclaredMethod("setCreateTime", String.class);
                getPurchaseMaterial = purchaseClazz.getDeclaredMethod("getMaterialNumber");
                getPurchaseSupplierCode = purchaseClazz.getDeclaredMethod("getSupplierCode");
                setSupplyDiffMaterialNumber = supplyDiffClazz.getDeclaredMethod("setMaterialNumber", String.class);
                setSupplyDiffType = supplyDiffClazz.getDeclaredMethod("setType", Integer.class);
                setSupplyDiffSupplierCode = supplyDiffClazz.getDeclaredMethod("setSupplierCode", String.class);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void updateData() {
        List<SqlConfigVo> sqlConfigList = sqlConfigDao.getAllVos(null);
        if (!ObjectUtils.isEmpty(sqlConfigList)) {
            for (SqlConfig sqlConfig : sqlConfigList) {
                if (!ObjectUtils.isEmpty(sqlConfig.getSqlContent())) {
//                    if (sqlConfig.getFunctionName().equals("物料")) {
//                        //物料操作
//                        List<Map<String, Object>> mapList = null;
//                        try {
//                            mapList = interfaceServerDao.getSqlData(sqlConfig.getSqlContent());
//                        } catch (Exception e) {
//                        }
//
//                        if (ObjectUtils.isEmpty(mapList)) {
//                            continue;
//                        }
//
//                        List<Object> materialInterfaceList = new Vector<>();
//                        for (Map<String, Object> maps : mapList) {
//                            try {
//                                Object materialObject = materialClazz.newInstance();
//                                copyMapToObject(maps, materialObject);
//
//                                //将差集填入扩充字段
//                                materialData(maps, materialObject);
//
//                                LOGGER.info(JSON.toJSONString(materialObject));
//                                materialInterfaceList.add(materialObject);
//                                // 达到BATCH_COUNT了，需要去存储一次数据库，防止数据几万条数据在内存，容易OOM
//                                if (materialInterfaceList.size() >= BATCH_COUNT) {
//                                    //存入数据表
//                                    updateData(materialInterfaceList, sqlConfig.getFunctionName());
//                                    // 存储完成清理 list
//                                    materialInterfaceList.clear();
//                                }
//                            } catch (InstantiationException | IllegalAccessException e) {
//                                e.printStackTrace();
//                            }
//                        }
//                        //剩余数据存入物料
//                        if (!ObjectUtils.isEmpty(materialInterfaceList)) {
//                            updateData(materialInterfaceList, sqlConfig.getFunctionName());
//                        }
//                    } else if (!sqlConfig.getFunctionName().equals("物料供需")) {
//                        //公共数据表操作
//                        List<Map<String, Object>> mapList = null;
//                        try {
//                            mapList = interfaceServerDao.getSqlData(sqlConfig.getSqlContent());
//                        } catch (Exception e) {
//                        }
//
//                        if (ObjectUtils.isEmpty(mapList)) {
//                            continue;
//                        }
//
//                        List<Object> commonList = new Vector<>();
//                        for (Map<String, Object> maps : mapList) {
//                            Object commonObject = null;
//                            //公共数据操作
//                            commonObject = commonData(commonObject, maps, sqlConfig.getFunctionName());
//                            if (null != commonObject) {
//                                commonList.add(commonObject);
//                            }
//                            // 达到BATCH_COUNT了，需要去存储一次数据库，防止数据几万条数据在内存，容易OOM
//                            if (commonList.size() >= BATCH_COUNT) {
//                                //存入表
//                                updateData(commonList, sqlConfig.getFunctionName());
//                                // 存储完成清理 list
//                                commonList.clear();
//                            }
//                        }
//                        //剩余数据存入供应商
//                        if (!ObjectUtils.isEmpty(commonList)) {
//                            updateData(commonList, sqlConfig.getFunctionName());
//                        }
//                    }

                    //除物料供需外，以执行sql方式运行
//                    if (!sqlConfig.getFunctionName().equals("物料供需")) {
//                        interfaceServerDao.sqlManualRun(sqlConfig.getSqlContent());
//                    }

//                    if (sqlConfig.getFunctionName().equals("物料供需")) {
//                        //公共数据表操作
//                        List<MaterialSupplyDemand> materialSupplyDemandList = null;
//                        try {
//                            materialSupplyDemandList = interfaceServerDao.getMaterialSupplyData(sqlConfig.getSqlContent());
//                        } catch (Exception e) {
//                        }
//
//                        if (ObjectUtils.isEmpty(materialSupplyDemandList)) {
//                            continue;
//                        }
//
//                        updateMaterialSupplyDemandData(materialSupplyDemandList);
//                    }
                    try {
                        //sql执行
                        interfaceServerDao.sqlManualRun(sqlConfig.getSqlContent());
                    } catch (Exception e) {
                        LOGGER.error("SQL运行异常" + e.getMessage());
                    }
                }
            }
        }
    }

    /**
     * map数据映射
     *
     * @param commonObject
     * @param maps
     * @param functionName
     */
    public Object commonData(Object commonObject, Map<String, Object> maps, String functionName) {
        try {
            if (functionName.equals("供应商")) {
                commonObject = supplierDtoClazz.newInstance();
            } else if (functionName.equals("采购订单")) {
                commonObject = purchaseClazz.newInstance();
            } else if (functionName.equals("标准物资开票")) {
                commonObject = standardInvoiceOutClazz.newInstance();
            }

            copyMapToObject(maps, commonObject);
            LOGGER.info(JSON.toJSONString(commonObject));
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return commonObject;
    }

    /**
     * 表数据操作
     */
    public void updateData(List<Object> commonList, String functionName) {
        if (functionName.equals("物料")) {
            updateMaterialData(commonList);
        } else if (functionName.equals("供应商")) {
            updateSupplierData(commonList);
        } else if (functionName.equals("采购订单")) {
            updatePurchaseData(commonList);
        } else if (functionName.equals("标准物资开票")) {
            updateStandardInvoiceOutData(commonList);
        }
    }

    /**
     * 差集填充至扩展字段
     *
     * @param maps
     * @param materialObject
     * @return void
     */
    public void materialData(Map<String, Object> maps, Object materialObject) {
        Set<String> set = maps.keySet();

        Field[] fields = materialClazz.getDeclaredFields();

        CopyOnWriteArraySet<String> stringSet = new CopyOnWriteArraySet<>();
        for (Field f : fields) {
            stringSet.add(f.getName());
        }
        //将差集填入扩充字段
        Set<String> collect = set.stream().filter(s -> !stringSet.contains(s)).collect(Collectors.toSet());
        int i = 1;
        for (String s : collect) {
            if (i > 10) {
                break;
            }
            //利用反射进行set
            String filed = "utf" + i;
            for (Field f : fields) {
                if (f.getName().equals(filed)) {
                    try {
                        filed = filed.substring(0, 1).toUpperCase() + filed.substring(1);
                        Method setField = materialClazz.getDeclaredMethod("set" + filed, f.getType());
                        setField.invoke(materialObject, String.valueOf(maps.get(s)));
                        break;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            i++;
        }
    }

    /**
     * 物料数据操作
     *
     * @param materialList
     */
    public void updateMaterialData(List<Object> materialList) {
        synchronized (this) {
            interfaceServerDao.saveMaterialBath(materialList);
        }

        try {
            List<String> buyerNumberList = new Vector<>();
            for (Object materialObject : materialList) {
                //获取值
                buyerNumberList.add(getBuyerNumber.invoke(materialObject).toString());
            }

            List<Object> userList = new Vector<>();

            for (String buyerNumber : buyerNumberList.parallelStream().distinct().collect(Collectors.toList())) {
                if (null == interfaceServerDao.getUserByUsername(buyerNumber)) {
                    Object userObject = userClazz.newInstance();

                    setUsername.invoke(userObject, buyerNumber);

                    setPassword.invoke(userObject, SHA256.hash(buyerNumber));

                    setRealName.invoke(userObject, buyerNumber);

                    setRoleId.invoke(userObject, 5);

                    setStatus.invoke(userObject, 0);

                    setUserType.invoke(userObject, 0);

                    setCreateTime.invoke(userObject, System.currentTimeMillis());

                    userList.add(userObject);
                }
            }
            if (!ObjectUtils.isEmpty(userList)) {
                synchronized (this) {
                    interfaceServerDao.saveUserBath(userList);
                }
            }
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    /**
     * 供应商批量插入
     */
    public synchronized void updateSupplierData(List<Object> supplierDtoList) {
        try {
            List<Object> userList = new LinkedList<>();
            for (Object supplierDto : supplierDtoList) {

                if (null == interfaceServerDao.getUserByUsername(getUsername.invoke(supplierDto).toString())) {
                    Object userObject = userClazz.newInstance();

                    Long usernameSum = userList.parallelStream().filter(userObject1 -> {
                        try {
                            return getUserUsername.invoke(userObject1).toString().equals(getUsername.invoke(supplierDto));
                        } catch (IllegalAccessException | InvocationTargetException e) {
                            e.printStackTrace();
                        }
                        return false;
                    }).count();

                    if (usernameSum >= 1) {
                        continue;
                    }

                    setUsername.invoke(userObject, getUsername.invoke(supplierDto).toString());

                    setPassword.invoke(userObject, SHA256.hash(getUsername.invoke(supplierDto).toString()));

                    setRealName.invoke(userObject, getUsername.invoke(supplierDto).toString());

                    setRoleId.invoke(userObject, 3);

                    setStatus.invoke(userObject, 0);

                    setUserType.invoke(userObject, 1);

                    setCreateTime.invoke(userObject, System.currentTimeMillis());

                    userList.add(userObject);
                }
            }
            if (!ObjectUtils.isEmpty(userList)) {
                int rest = interfaceServerDao.saveUserBath(userList);
                if (rest > 0) {
                    List<Object> supplierList = new LinkedList<>();
                    for (Object supplierDto : supplierDtoList) {

                        for (Object user : userList) {
                            if (getUserUsername.invoke(user).toString().equals(getSupplierDtoUsername.invoke(supplierDto).toString()) && !ObjectUtils.isEmpty(getUserId.invoke(user))) {
                                Object supplier = supplierClazz.newInstance();

                                if (null == interfaceServerDao.getSupplierByUserId(getUserId.invoke(user))) {
                                    setExamineStatus.invoke(supplier, 1);

                                    setCompanyName.invoke(supplier, getCompanyName.invoke(supplierDto).toString());

                                    setUserId.invoke(supplier, getUserId.invoke(user));

                                    supplierList.add(supplier);
                                    break;
                                }
                            }
                        }
                    }
                    if (!ObjectUtils.isEmpty(supplierList)) {
                        interfaceServerDao.saveSupplierBath(supplierList);
                    }
                }
            }
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    /**
     * 采购订单批量插入
     */
    public synchronized void updatePurchaseData(List<Object> purchaseList) {
        int maxId = interfaceServerDao.getPurchaseMaxId();
        List<MaterialSupplier> materialSupplierList = new LinkedList<>();
        try {
            for (Object purchaseObject : purchaseList) {
                setPurchaseId.invoke(purchaseObject, ++maxId);

                MaterialSupplier materialSupplier = new MaterialSupplier();
                materialSupplier.setMaterialNumber(getPurchaseMaterial.invoke(purchaseObject).toString());
                materialSupplier.setSupplierCode(getPurchaseSupplierCode.invoke(purchaseObject).toString());
                materialSupplierList.add(materialSupplier);
            }
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        interfaceServerDao.savePurchaseBath(purchaseList);

        if (!ObjectUtils.isEmpty(materialSupplierList)) {
            materialSupplierDao.saveBath(materialSupplierList);
        }
    }

    /**
     * 标准物资开票批量插入
     */
    public synchronized void updateStandardInvoiceOutData(List<Object> standardInvoiceOutList) {
        int maxId = interfaceServerDao.getStandardInvoiceOutMaxId();

        try {
            for (Object standardInvoiceOutObject : standardInvoiceOutList) {
                setStandardInvoiceOutId.invoke(standardInvoiceOutObject, ++maxId);
                setStandardInvoiceOutCreateTime.invoke(standardInvoiceOutObject, DateUtil.getCurrentDate("YYYY/MM/dd"));
            }
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        interfaceServerDao.saveStandardInvoiceOutBath(standardInvoiceOutList);
    }

    /**
     * 物料供需批量插入
     *
     * @param materialSupplyDemandList
     */
    public synchronized void updateMaterialSupplyDemandData(List<MaterialSupplyDemand> materialSupplyDemandList) {
        // 先删除原来数据，再添加
        interfaceServerDao.deleteSupplyDiff();

        try {
            List<Object> supplyDiffObjectList = new LinkedList<>();

            Map<String, List<MaterialSupplyDemand>> materialSupplyDemandListMap = materialSupplyDemandList.parallelStream().collect(Collectors.groupingBy(MaterialSupplyDemand::getMaterialNumber));

            for (Map.Entry<String, List<MaterialSupplyDemand>> materialSupplyDemandMap : materialSupplyDemandListMap.entrySet()) {
                String materialSupplyDemand = materialSupplyDemandMap.getKey();
                String supplierCode = null;
                List<MaterialSupplyDemand> materialSupplyDemandList1 = materialSupplyDemandMap.getValue();

                for (MaterialSupplyDemand materialSupplyDemand1 : materialSupplyDemandList1) {
                    Object supplyDiffObject = supplyDiffClazz.newInstance();

                    setSupplyDiffMaterialNumber.invoke(supplyDiffObject, materialSupplyDemand);
                    setSupplyDiffType.invoke(supplyDiffObject, materialSupplyDemand1.getType());

                    if (ObjectUtils.isEmpty(materialSupplyDemand1.getSupplierCode())) {
                        if (ObjectUtils.isEmpty(supplierCode)) {
                            supplierCode = materialSupplierDao.getSupplierByMaterial(materialSupplyDemand);
                        }
                        setSupplyDiffSupplierCode.invoke(supplyDiffObject, supplierCode);
                    } else {
                        setSupplyDiffSupplierCode.invoke(supplyDiffObject, materialSupplyDemand1.getSupplierCode());
                    }

                    Field[] fields = supplyDiffDtoClazz.getDeclaredFields();
                    String set = "set";
                    int i = 0;
                    for (Field f : fields) {
                        String filed = f.getName().substring(0, 1).toUpperCase() + f.getName().substring(1);
                        Method setField = supplyDiffClazz.getDeclaredMethod(set + filed, f.getType());
                        if (i == 0) {
                            if (materialSupplyDemand1.getSupplyDemandDate().equals(DateUtil.getAssignDate("YYYYMMdd", i))) {
                                setField.invoke(supplyDiffObject, materialSupplyDemand1.getQty().intValue());
                            }
                        } else {
                            if (materialSupplyDemand1.getSupplyDemandDate().equals(DateUtil.getAssignDate("YYYYMMdd", i))) {
                                setField.invoke(supplyDiffObject, materialSupplyDemand1.getQty().intValue());
                            }
                        }
                        i++;
                    }

                    supplyDiffObjectList.add(supplyDiffObject);
                    // 达到BATCH_COUNT了，需要去存储一次数据库，防止数据几万条数据在内存，容易OOM
                    if (supplyDiffObjectList.size() >= BATCH_COUNT) {
                        //存入数据表
                        interfaceServerDao.saveSupplyDiffBath(supplyDiffObjectList);
                        // 存储完成清理 list
                        supplyDiffObjectList.clear();
                    }
                }
            }

            // 批量插入供需差异数据
            if (!ObjectUtils.isEmpty(supplyDiffObjectList)) {
                interfaceServerDao.saveSupplyDiffBath(supplyDiffObjectList);
            }
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    /**
     * map复制到实体类
     */
    public static void copyMapToObject(Map<String, Object> map, Object o) {
        Set set = map.keySet();
        Class c = o.getClass();
        Field[] fields = c.getDeclaredFields();
        for (Field f : fields) {
            f.setAccessible(true);
            if (set.contains(f.getName())) {
                try {
                    f.set(o, map.get(f.getName()));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
