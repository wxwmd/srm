package com.jaezi.bus.inventory.api;

import com.jaezi.bus.inventory.model.ConsignmentGoodsSDTR;
import com.jaezi.bus.inventory.service.ConsignmentGoodsSDTRParticularService;
import com.jaezi.bus.inventory.service.ConsignmentGoodsSDTRService;
import com.jaezi.common.base.BaseApi;
import com.jaezi.common.bean.JsonResult;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author yx
 * @version v1.0
 * @corporation copyright by jaezi.com
 * @date 2021/11/15  15:59:55
 * @description
 */
@RestController
@RequestMapping("/bus/consignment/goodssdtr/particular")
public class ConsignmentGoodsSDTRParticularApi extends BaseApi {

    private final ConsignmentGoodsSDTRParticularService consignmentGoodsSDTRParticularService;

    private final ConsignmentGoodsSDTRService consignmentGoodsSDTRService;

    public ConsignmentGoodsSDTRParticularApi(ConsignmentGoodsSDTRParticularService consignmentGoodsSDTRParticularService,
                                             ConsignmentGoodsSDTRService consignmentGoodsSDTRService) {
        this.consignmentGoodsSDTRParticularService = consignmentGoodsSDTRParticularService;
        this.consignmentGoodsSDTRService = consignmentGoodsSDTRService;
    }

    /**
     * 根据SDTRId 查询单条
     *
     * @param filter
     * @return ConsignmentGoodsSDTR
     */
    @GetMapping
    public JsonResult getBySDTRId(@RequestParam Map<String, String> filter) {
        Integer id = Integer.valueOf(filter.get("id"));
        ConsignmentGoodsSDTR oneById = consignmentGoodsSDTRService.getOneById(id);
        if (oneById != null) {
            filter.put("line", String.valueOf(oneById.getLine()));
            filter.put("materialNumber", oneById.getMaterialNumber());
            return returnObjectResult(consignmentGoodsSDTRParticularService.getAll(filter));
        }
        return JsonResult.FAILURE;
    }
}
