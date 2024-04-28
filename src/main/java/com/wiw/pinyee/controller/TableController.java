package com.wiw.pinyee.controller;

import com.wiw.pinyee.common.Result;
import com.wiw.pinyee.constant.ResultEnum;
import com.wiw.pinyee.dto.TableAddDTO;
import com.wiw.pinyee.dto.TableAttrSubDTO;
import com.wiw.pinyee.service.TableConfigService;
import com.wiw.pinyee.vo.TableConfigVO;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("table")
@RequiredArgsConstructor
@Tag(name = "表单配置")
public class TableController {

    private final TableConfigService tableConfigService;

    @PostMapping("add")
    public Result<?> tableAdd(@RequestBody TableAddDTO dto) {
        if (tableConfigService.tableAdd(dto)) {
            return Result.success();
        }
        return Result.ok(ResultEnum.TABLE_ALREADY_EXIST);
    }

    @PostMapping("attr/sub")
    public Result<?> attrSub(@RequestBody TableAttrSubDTO dto) {
        tableConfigService.attrSub(dto);
        return Result.success();
    }

    @GetMapping("show")
    public Result<List<TableConfigVO>> show() {
        return Result.success(tableConfigService.show());
    }

    @GetMapping("show/{id}")
    public Result<TableConfigVO> showById(@PathVariable Long id) {
        return Result.success(tableConfigService.showById(id));
    }

    @DeleteMapping
    public Result<?> tableDel(@RequestParam Long tableId) {
        tableConfigService.tableDel(tableId);
        return Result.success();
    }

    @GetMapping("create")
    public Result<?> tableCreate(@RequestParam Long tableId) {
        if (tableConfigService.tableCreate(tableId, "auto")) {
            return Result.success();
        }
        return Result.ok(ResultEnum.TABLE_CREATE_REPEAT);
    }
}
