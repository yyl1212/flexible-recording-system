package com.wiw.pinyee.table;

import cn.hutool.core.collection.CollUtil;
import com.wiw.pinyee.common.Result;
import com.wiw.pinyee.controller.TableController;
import com.wiw.pinyee.dto.AttrSubDTO;
import com.wiw.pinyee.dto.TableAddDTO;
import com.wiw.pinyee.dto.TableAttrSubDTO;
import com.wiw.pinyee.vo.TableConfigVO;
import jakarta.annotation.Resource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TableTest {

    @Resource
    private TableController tableController;

    @Test
    public void testAdd() {
        TableAddDTO dto = new TableAddDTO();
        dto.setTableName("客户");
        dto.setTableDbName("customer");
        tableController.tableAdd(dto);
    }

    @Test
    public void testAdd1() {
        TableAddDTO dto = new TableAddDTO();
        dto.setParentId(141290841124659200L);
        dto.setTableName("联系方式");
        dto.setTableDbName("conn");
        tableController.tableAdd(dto);
    }

    @Test
    public void testAttrSub() {
        TableAttrSubDTO dto = new TableAttrSubDTO();
        dto.setTableId(141290841124659200L);
        ArrayList<AttrSubDTO> attrList = CollUtil.newArrayList();
        AttrSubDTO attr = new AttrSubDTO();
        attr.setAttrName("id");
        attr.setAttrDbName("id");
        attr.setAttrType(10);
        attrList.add(attr);
        AttrSubDTO attr1 = new AttrSubDTO();
        attr1.setAttrName("姓名");
        attr1.setAttrDbName("name");
        attr1.setAttrType(13);
        attrList.add(attr1);
        AttrSubDTO attr2 = new AttrSubDTO();
        attr2.setAttrName("备注");
        attr2.setAttrDbName("remark");
        attr2.setAttrType(14);
        attrList.add(attr2);
        AttrSubDTO attr3 = new AttrSubDTO();
        attr3.setAttrName("状态");
        attr3.setAttrDbName("status");
        attr3.setAttrType(21);
        attr3.setAttrConn(33687220747000149L);
        attrList.add(attr3);
        dto.setAttrList(attrList);
        tableController.attrSub(dto);
    }

    @Test
    public void testAttrSub2() {
        TableAttrSubDTO dto = new TableAttrSubDTO();
        dto.setTableId(141348437441449984L);
        ArrayList<AttrSubDTO> attrList = CollUtil.newArrayList();
        AttrSubDTO attr = new AttrSubDTO();
        attr.setAttrName("id");
        attr.setAttrDbName("id");
        attr.setAttrType(10);
        attrList.add(attr);
        AttrSubDTO attr1 = new AttrSubDTO();
        attr1.setAttrName("平台");
        attr1.setAttrDbName("platform");
        attr1.setAttrType(13);
        attrList.add(attr1);
        AttrSubDTO attr2 = new AttrSubDTO();
        attr2.setAttrName("联系方式");
        attr2.setAttrDbName("connection");
        attr2.setAttrType(13);
        attrList.add(attr2);
        AttrSubDTO attr3 = new AttrSubDTO();
        attr3.setAttrName("昵称");
        attr3.setAttrDbName("alias");
        attr3.setAttrType(13);
        attrList.add(attr3);
        dto.setAttrList(attrList);
        tableController.attrSub(dto);
    }

    @Test
    public void testShow() {
        Result<List<TableConfigVO>> result = tableController.show();
        result.getData().forEach(System.out::println);
    }

    @Test
    public void testCreate() {
        tableController.tableCreate(141290841124659200L);
    }
}
