package com.wiw.pinyee.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.generator.SnowflakeGenerator;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.mybatisflex.core.row.Db;
import com.wiw.pinyee.service.TableConfigService;
import com.wiw.pinyee.vo.AttrVO;
import com.wiw.pinyee.vo.TableConfigVO;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.exceptions.PersistenceException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DataServiceImpl {

    private final TableConfigService tableConfigService;

    @Transactional(rollbackFor = Exception.class)
    public boolean dataAdd(JSONObject dto, Long parentId, String prefix) {
        Long tableId = dto.get("tableId", Long.class);
        TableConfigVO tableConfigVO = tableConfigService.showById(tableId);
        StringBuilder stringBuilder = new StringBuilder();
        String tableName = prefix + "_" + tableConfigVO.getTableDbName();
        stringBuilder.append("insert into ").append(tableName).append(" (");
        if (tableConfigVO.getParentId() != -1) stringBuilder.append("main_id,");
        for (AttrVO attr : tableConfigVO.getAttrList()) {
            int attrType = attr.getAttrType() / 10;
            if (attrType != 3) {
                stringBuilder.append(attr.getAttrDbName()).append(",");
            }
        }
        stringBuilder.delete(stringBuilder.length() - 1, stringBuilder.length());
        stringBuilder.append(") values (");
        if (tableConfigVO.getParentId() != -1) stringBuilder.append(parentId).append(",");
        SnowflakeGenerator snowflakeGenerator = new SnowflakeGenerator();
        Long id = snowflakeGenerator.next();
        for (AttrVO attr : tableConfigVO.getAttrList()) {
            String attrStr = (String) dto.getOrDefault(attr.getAttrDbName(), "");
            int dataType = attr.getAttrType() % 10;
            switch (dataType) {
                case 0:
                    stringBuilder.append(id).append(",");
                    break;
                case 1:
                    stringBuilder.append(Integer.parseInt(attrStr)).append(",");
                    break;
                case 2:
                    stringBuilder.append(Long.parseLong(attrStr)).append(",");
                    break;
                case 5:
                    stringBuilder.append(DateUtil.parse(attrStr)).append(",");
                    break;
                default:
                    stringBuilder.append("'").append(attrStr).append("',");
            }
        }
        stringBuilder.delete(stringBuilder.length() - 1, stringBuilder.length());
        stringBuilder.append(")");
        try {
            Db.insertBySql(stringBuilder.toString());
        } catch (PersistenceException e) {
            return false;
        }
        JSONArray childList = dto.getJSONArray("childList");
        if (childList != null && !childList.isEmpty()) {
            childList.forEach(child -> {
                JSONObject childDto = (JSONObject) child;
                dataAdd(childDto, id, tableName);
            });
        }
        return true;
    }

    public JSONArray dataShow(Long tableId, String prefix) {
        TableConfigVO tableConfigVO = tableConfigService.showById(tableId);
        if (tableConfigVO != null) {
            StringBuilder stringBuilder = new StringBuilder();
            String tableName = prefix + "_" + tableConfigVO.getTableDbName();
            stringBuilder.append("select * from ").append(tableName);
            return JSONUtil.parseArray(Db.selectListBySql(stringBuilder.toString()));
        }
        return new JSONArray();
    }

    public JSONArray dataShow(Long tableId, Long id, String prefix) {
        TableConfigVO tableConfigVO = tableConfigService.showById(tableId);
        if (tableConfigVO != null) {
            StringBuilder stringBuilder = new StringBuilder();
            String tableName = prefix + "_" + tableConfigVO.getTableDbName();
            stringBuilder.append("select * from ").append(tableName);
            if (tableConfigVO.getParentId() == -1) {
                stringBuilder.append(" where id = ").append(id);
            } else {
                stringBuilder.append(" where main_id = ").append(id);
            }
            JSONArray data = JSONUtil.parseArray(Db.selectListBySql(stringBuilder.toString()));
            if (data != null && !data.isEmpty()
                    && tableConfigVO.getChildList() != null
                    && !tableConfigVO.getChildList().isEmpty()) {
                JSONObject temp = (JSONObject) data.get(0);
                for (TableConfigVO child : tableConfigVO.getChildList()) {
                    JSONArray child1 = dataShow(child.getId(), temp.getLong("id"), tableName);
                    temp.set(tableName + "_" + child.getTableDbName(), child1);
                }
            }
            return data;
        }
        return new JSONArray();
    }

    @Transactional(rollbackFor = Exception.class)
    public void dataDel(Long tableId, Long id, String prefix) {
        TableConfigVO tableConfigVO = tableConfigService.showById(tableId);
        if (tableConfigVO != null) {
            StringBuilder stringBuilder = new StringBuilder();
            String tableName = prefix + "_" + tableConfigVO.getTableDbName();
            stringBuilder.append("delete from ").append(tableName);
            if (tableConfigVO.getParentId() == -1) {
                stringBuilder.append(" where id = ").append(id);
            } else {
                stringBuilder.append(" where main_id = ").append(id);
            }
            Db.deleteBySql(stringBuilder.toString());
            if (tableConfigVO.getChildList() != null
            && !tableConfigVO.getChildList().isEmpty()) {
                tableConfigVO.getChildList().forEach(child -> {
                    dataDel(child.getId(), id, tableName);
                });
            }
        }
    }
}
