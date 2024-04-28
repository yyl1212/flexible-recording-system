package com.wiw.pinyee.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.mybatisflex.core.query.QueryChain;
import com.mybatisflex.core.row.Db;
import com.mybatisflex.core.update.UpdateChain;
import com.mybatisflex.spring.service.impl.ServiceImpl;
import com.wiw.pinyee.dto.AttrSubDTO;
import com.wiw.pinyee.dto.TableAddDTO;
import com.wiw.pinyee.dto.TableAttrSubDTO;
import com.wiw.pinyee.entity.TableAttrConfig;
import com.wiw.pinyee.entity.TableConfig;
import com.wiw.pinyee.mapper.TableAttrConfigMapper;
import com.wiw.pinyee.mapper.TableConfigMapper;
import com.wiw.pinyee.service.TableConfigService;
import com.wiw.pinyee.vo.AttrVO;
import com.wiw.pinyee.vo.TableConfigVO;
import org.apache.ibatis.exceptions.PersistenceException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.wiw.pinyee.entity.table.TableAttrConfigTableDef.TABLE_ATTR_CONFIG;
import static com.wiw.pinyee.entity.table.TableConfigTableDef.TABLE_CONFIG;

@Service
public class TableConfigServiceImpl extends ServiceImpl<TableConfigMapper, TableConfig> implements TableConfigService {
    @Override
    public boolean tableAdd(TableAddDTO dto) {
        if (StrUtil.isBlank(dto.getTableDbName())
                || QueryChain.of(TableConfig.class)
                .where(TABLE_CONFIG.TABLE_DB_NAME.eq(dto.getTableDbName()))
                .exists()) {
            return false;
        }
        mapper.insert(TableConfig.builder()
                .parentId(ObjectUtil.isEmpty(dto.getParentId()) ? -1 : dto.getParentId())
                .tableName(dto.getTableName())
                .tableDbName(dto.getTableDbName())
                .build());
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void attrSub(TableAttrSubDTO dto) {
        if (!QueryChain.of(TableConfig.class)
                .where(TABLE_CONFIG.ID.eq(dto.getTableId()))
                .exists()) {
            return;
        }
        UpdateChain.of(TableAttrConfig.class)
                .where(TABLE_ATTR_CONFIG.TABLE_ID.eq(dto.getTableId()))
                .remove();
        if (CollUtil.isNotEmpty(dto.getAttrList())) {
            Db.executeBatch(dto.getAttrList().size(), 1000, TableAttrConfigMapper.class, (mapper, index) -> {
                AttrSubDTO attr = dto.getAttrList().get(index);
                mapper.insert(TableAttrConfig.builder()
                        .tableId(dto.getTableId())
                        .attrName(attr.getAttrName())
                        .attrDbName(attr.getAttrDbName())
                        .attrType(attr.getAttrType())
                        .attrConn(attr.getAttrConn())
                        .build());
            });
        }
    }

    @Override
    public List<TableConfigVO> show() {
        return QueryChain.of(TableConfig.class)
                .select()
                .withRelations()
                .listAs(TableConfigVO.class);
    }

    @Override
    @Transactional
    public void tableDel(Long tableId) {
        UpdateChain.of(TableAttrConfig.class)
                .where(TABLE_ATTR_CONFIG.TABLE_ID.eq(tableId))
                .remove();
        UpdateChain.of(TableConfig.class)
                .where(TABLE_CONFIG.ID.eq(tableId))
                .remove();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean tableCreate(Long tableId, String prefix) {
        TableConfigVO tableConfigVO = showById(tableId);
        String tableName = prefix + "_" + tableConfigVO.getTableDbName();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("create table ").append(tableName).append(" (");
        if (tableConfigVO.getParentId() != -1) {
            stringBuilder.append("main_id bigint,");
        }
        for (AttrVO attr : tableConfigVO.getAttrList()) {
            int dataType = attr.getAttrType() % 10;
            String dataTypeStr = chooseDataType(dataType);
            stringBuilder.append(attr.getAttrDbName()).append(" ").append(dataTypeStr).append(",");
        }
        stringBuilder.delete(stringBuilder.length() - 1, stringBuilder.length());
        stringBuilder.append(")");
        try {
            Db.insertBySql(stringBuilder.toString());
        } catch (PersistenceException e) {
            return false;
        }
        if (CollUtil.isNotEmpty(tableConfigVO.getChildList())) {
            tableConfigVO.getChildList().forEach(child -> tableCreate(child.getId(), tableName));
        }
        return true;
    }

    @Override
    public TableConfigVO showById(Long id) {
        return QueryChain.of(TableConfig.class)
                .where(TABLE_CONFIG.ID.eq(id))
                .withRelations()
                .oneAs(TableConfigVO.class);
    }

    private String chooseDataType(int dataType) {
        return switch (dataType) {
            case 0 -> "bigint primary key";
            case 1 -> "int";
            case 2 -> "bigint";
            case 3 -> "varchar(50)";
            case 4 -> "longtext";
            case 5 -> "datetime";
            default -> "varchar(255)";
        };
    }
}
