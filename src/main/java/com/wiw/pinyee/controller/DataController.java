package com.wiw.pinyee.controller;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import com.wiw.pinyee.common.Result;
import com.wiw.pinyee.constant.ResultEnum;
import com.wiw.pinyee.service.impl.DataServiceImpl;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("data")
@RequiredArgsConstructor
@Tag(name = "数据配置")
public class DataController {

    private final DataServiceImpl dataService;

    @PostMapping("add")
    public Result<?> dataAdd(@RequestBody JSONObject dto) {
        if (dataService.dataAdd(dto, -1L, "auto")) {
            return Result.success();
        }
        return Result.ok(ResultEnum.DATA_ADD_FAIL);
    }

    @GetMapping("show")
    public Result<JSONArray> dataShow(@RequestParam Long tableId) {
        return Result.success(dataService.dataShow(tableId, "auto"));
    }

    @GetMapping("show/{id}")
    public Result<JSONArray> dataShow(@RequestParam Long tableId, @PathVariable Long id) {
        return Result.success(dataService.dataShow(tableId, id, "auto"));
    }

    @DeleteMapping("{id}")
    public Result<?> dataDel(@RequestParam Long tableId, @PathVariable Long id) {
        dataService.dataDel(tableId, id, "auto");
        return Result.success();
    }
}
