package com.wiw.pinyee.controller;

import com.wiw.pinyee.common.Result;
import com.wiw.pinyee.constant.ResultEnum;
import com.wiw.pinyee.dto.TypeAddDTO;
import com.wiw.pinyee.dto.TypeSelSubDTO;
import com.wiw.pinyee.service.TypeConfigService;
import com.wiw.pinyee.vo.TypeConfigVO;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("type")
@RequiredArgsConstructor
@Tag(name = "类型配置")
public class TypeController {

    private final TypeConfigService typeConfigService;

    @PostMapping("add")
    public Result<?> typeAdd(@RequestBody TypeAddDTO dto) {
        if (typeConfigService.typeAdd(dto.getTypeName())) {
            return Result.success();
        }
        return Result.ok(ResultEnum.TYPE_ALREADY_EXIST);
    }

    @PostMapping("sel/sub")
    public Result<?> selSub(@RequestBody TypeSelSubDTO dto) {
        typeConfigService.selSub(dto);
        return Result.success();
    }

    @GetMapping
    public Result<List<TypeConfigVO>> type() {
        return Result.success(typeConfigService.type());
    }

    @DeleteMapping
    public Result<?> typeDel(@RequestParam Long typeId) {
        typeConfigService.typeDel(typeId);
        return Result.success();
    }
}
