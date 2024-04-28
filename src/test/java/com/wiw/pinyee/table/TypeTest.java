package com.wiw.pinyee.table;

import cn.hutool.core.collection.CollUtil;
import com.wiw.pinyee.common.Result;
import com.wiw.pinyee.controller.TypeController;
import com.wiw.pinyee.dto.TypeAddDTO;
import com.wiw.pinyee.dto.TypeSelSubDTO;
import com.wiw.pinyee.vo.TypeConfigVO;
import jakarta.annotation.Resource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TypeTest {

    @Resource
    private TypeController typeController;

    @Test
    public void testAdd() {
        TypeAddDTO dto = new TypeAddDTO();
        dto.setTypeName("客户状态");
        typeController.typeAdd(dto);
    }

    @Test
    public void testSelSub() {
        TypeSelSubDTO dto = new TypeSelSubDTO();
        dto.setTypeId(33687220747000149L);
        ArrayList<String> selList = CollUtil.newArrayList();
        selList.add("10-未建立联系客户");
        selList.add("20-已发送联系未回复客户");
        selList.add("30-已建立联系未回复客户");
        selList.add("40-已建立联系无意向客户");
        selList.add("50-已建立联系有意向客户");
        selList.add("60-已建立联系合作中客户");
        selList.add("70-已建立联系合作结束客户");
        dto.setSelNameList(selList);
        typeController.selSub(dto);
    }

    @Test
    public void testShow() {
        Result<List<TypeConfigVO>> result = typeController.type();
        result.getData().forEach(System.out::println);
    }
}
