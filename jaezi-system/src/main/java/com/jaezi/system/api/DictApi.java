package com.jaezi.system.api;

import com.jaezi.common.base.BaseApi;
import com.jaezi.common.base.BaseException;
import com.jaezi.common.bean.JsonResult;
import com.jaezi.common.bean.ResponseEnum;
import com.jaezi.common.cache.DictUtils;
import com.jaezi.system.model.DictData;
import com.jaezi.system.model.DictType;
import com.jaezi.system.service.DictDataService;
import com.jaezi.system.service.DictTypeService;
import com.jaezi.system.vo.DictTypeVo;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;

/**
 * @author yzl
 * @version v1.0
 * @corporation
 * @date 2020/4/16 17:12
 * @description 字典API
 */
@RestController
@RequestMapping("/sys/dict")
public class DictApi extends BaseApi {

    private final DictTypeService dictTypeService;
    private final DictDataService dictDataService;

    public DictApi(DictTypeService dictTypeService, DictDataService dictDataService) {
        this.dictTypeService = dictTypeService;
        this.dictDataService = dictDataService;
    }

    /**
     * 项目启动时，初始化字典到缓存
     */
    @PostConstruct
    public void init() {
        loadingDictCache();
    }

    /**
     * 项目启动时，初始化字典到缓存
     *
     * @return
     */
    public void loadingDictCache() {
        List<DictTypeVo> dictTypeList = dictTypeService.getAllVos();
        for (DictType dictType : dictTypeList) {
            List<DictData> dictDataList = dictDataService.selectDictDataByTypeAndStatus(dictType.getDictType(), 0);
            DictUtils.setDictCache(dictType.getDictType(), dictDataList);
        }
    }

    /**
     * 刷新缓存
     */
    @GetMapping("/refresh")
    public JsonResult refreshDictCache() {
        DictUtils.clearDictCache();
        loadingDictCache();
        return JsonResult.SUCCESS;
    }

    /**
     * 根据类型获取数据信息
     *
     * @param dictType 类型
     * @return 对象详情
     */
    @GetMapping("/{dictType}")
    public JsonResult getDictByType(@PathVariable("dictType") String dictType) {
        List<DictData> dictDataList = (List<DictData>) DictUtils.getDictCache(dictType);
        if (ObjectUtils.isEmpty(dictDataList)) {
            dictDataList = dictDataService.selectDictDataByTypeAndStatus(dictType, 0);
            if (!ObjectUtils.isEmpty(dictDataList)) {
                //如果不为空,添加进缓存
                DictUtils.setDictCache(dictType, dictDataList);
            }
        }
        return returnObjectResult(dictDataList);
    }

    /**
     * 字典类型列表（分页）
     *
     * @param filter 分页对象
     * @return 字典类型列表
     */
    @GetMapping("/type")
    public JsonResult getDictTypeAll(@RequestParam Map<String, String> filter) {
        return new JsonResult(dictTypeService.getAll(filter));
    }

    /**
     * 字典数据列表（分页）
     *
     * @param filter 分页对象
     * @return 字典数据列表
     */
    @GetMapping("/data")
    public JsonResult getDictDataAll(@RequestParam Map<String, String> filter) {
        return new JsonResult(dictDataService.getAll(filter));
    }

    /**
     * 字典类型根据id获取
     *
     * @param id ID
     * @return 对象详情
     */
    @GetMapping("/type/{id}")
    public JsonResult getDictTypeOneById(@PathVariable("id") Integer id) {
        return returnObjectResult(dictTypeService.getOneById(id));
    }

    /**
     * 字典数据根据id获取
     *
     * @param id ID
     * @return 对象详情
     */
    @GetMapping("/data/{id}")
    public JsonResult getDictDataOneById(@PathVariable("id") Integer id) {
        return returnObjectResult(dictDataService.getOneById(id));
    }

    /**
     * 字典类型添加
     *
     * @param dictType 对象
     * @return 操作是否成功
     */
    @PostMapping("/type")
    public JsonResult addDictType(@RequestBody DictType dictType) {
        return returnIntResult(dictTypeService.add(dictType));
    }

    /**
     * 字典数据添加
     *
     * @param dictData 对象
     * @return 操作是否成功
     */
    @PostMapping("/data")
    public JsonResult addDictData(@RequestBody DictData dictData) {
        int rest = dictDataService.add(dictData);
        if (rest > 0) {
            commonCache(dictData.getDictType(), dictData.getDictType());
        }

        return returnIntResult(rest);
    }

    /**
     * 字典类型修改
     * @param dictType 对象
     * @return 操作是否成功
     */
    @PutMapping("/type")
    public JsonResult updateDictType(@RequestBody DictType dictType) throws BaseException {
        DictType oldDictType = dictTypeService.getOneById(dictType.getDictId());
        if (dictTypeService.update(dictType) != 0) {
            List<DictData> dictDataList = dictDataService.selectDictDataByTypeAndStatus(oldDictType.getDictType(), null);
            for (DictData dictData : dictDataList) {
                dictData.setDictType(dictType.getDictType());
                if (dictType.getStatus().equals("1")) {
                    dictData.setStatus("1");
                } else {
                    dictData.setStatus("0");
                }
                int rest1 = dictDataService.update(dictData);
                if (rest1 <= 0) {
                    throw new BaseException("数据字典修改失败");
                }
            }
            commonCache(oldDictType.getDictType(), dictType.getDictType());
            return JsonResult.SUCCESS;
        } else {
            return new JsonResult(ResponseEnum.UPDATE);
        }
    }

    /**
     * 字典数据修改
     *
     * @param dictData 对象
     * @return 操作是否成功
     */
    @PutMapping("/data")
    public JsonResult updateDictData(@RequestBody DictData dictData) {
        int rest = dictDataService.update(dictData);
        if (rest <= 0) {
            return new JsonResult(ResponseEnum.FAILURE_UPDATE_REPETITION);
        }

        commonCache(dictData.getDictType(), dictData.getDictType());

        return JsonResult.SUCCESS;
    }

    /**
     * 字典类型删除
     *
     * @param id 对象ID
     * @return 操作是否成功
     */
    @DeleteMapping("/type/{id}")
    public JsonResult deleteDictDataByType(@PathVariable Integer id) throws BaseException {
        //类型和数据表联动删除
        DictType dictType = dictTypeService.getOneById(id);
        if (ObjectUtils.isEmpty(dictType)) {
            return JsonResult.FAILURE;
        }
        dictDataService.deleteDictDataByType(dictType.getDictType());
        int rest1 = dictTypeService.delete(id);
        if (rest1 <= 0) {
            throw new BaseException("数据字典删除失败");
        }
        //同时删除缓存
        DictUtils.deleteDictCache(dictType.getDictType());

        return JsonResult.SUCCESS;
    }

    /**
     * 字典数据删除
     *
     * @param id 对象ID
     * @return 操作是否成功
     */
    @DeleteMapping("/data/{id}")
    public JsonResult deleteDictData(@PathVariable Integer id) {
        //类型和数据表联动删除
        DictData dictData = dictDataService.getOneById(id);
        if (ObjectUtils.isEmpty(dictData)) {
            return JsonResult.FAILURE;
        }
        dictDataService.delete(id);

        commonCache(dictData.getDictType(), dictData.getDictType());

        return JsonResult.SUCCESS;
    }

    /**
     * 公共缓存操作
     */
    public void commonCache(String oldDictType, String dictType) {
        //同时删除缓存
        DictUtils.deleteDictCache(oldDictType);

        List<DictData> dictDataList = dictDataService.selectDictDataByTypeAndStatus(dictType, 0);
        if (!ObjectUtils.isEmpty(dictDataList)) {
            //如果从表不为空,添加进缓存
            DictUtils.setDictCache(dictType, dictDataList);
        }
    }

}
