package com.wiw.pinyee.table;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import com.wiw.pinyee.common.Result;
import com.wiw.pinyee.controller.DataController;
import jakarta.annotation.Resource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class DataTest {

    @Resource
    private DataController dataController;

    @Test
    public void testAdd() {
        JSONObject dto = new JSONObject();
        dto.set("tableId", 141290841124659200L);
        dto.set("name", "张三");
        dto.set("remark", "zhang san");
        dto.set("status", "10");
        JSONArray childList = new JSONArray();
        JSONObject child = new JSONObject();
        child.set("tableId", 141348437441449984L);
        child.set("platform", "QQ");
        child.set("connection", "123");
        child.set("alias", "123");
        childList.add(child);
        dto.set("childList", childList);
        dataController.dataAdd(dto);
    }

    @Test
    public void testModify() {
        JSONObject dto = new JSONObject();
        dto.set("tableId", 141290841124659200L);
        dto.set("id", 1784459887393112064L);
        dto.set("name", "张三");
        dto.set("remark", "zhang san");
        dto.set("status", "20");
        JSONArray childList = new JSONArray();
        JSONObject child = new JSONObject();
        child.set("tableId", 141348437441449984L);
        child.set("platform", "QQ");
        child.set("connection", "123");
        child.set("alias", "123");
        childList.add(child);
        dto.set("childList", childList);
        dataController.dataModify(dto);
    }

    @Test
    public void testShow() {
        Result<JSONArray> result = dataController.dataShow(141290841124659200L);
        result.getData().forEach(System.out::println);
    }

    @Test
    public void testShowById() {
        Result<JSONArray> result = dataController.dataShow(141290841124659200L, 1784395470919958528L);
        result.getData().forEach(System.out::println);
    }

    @Test
    public void testDel() {
        dataController.dataDel(141290841124659200L, 1784395470919958528L);
    }
}
