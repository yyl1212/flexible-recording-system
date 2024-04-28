package com.wiw.pinyee.vo;

import com.mybatisflex.annotation.RelationOneToMany;
import lombok.Data;

import java.util.List;

@Data
public class TableConfigVO {

    private Long id;

    private Long parentId;

    private String tableName;

    private String tableDbName;

    @RelationOneToMany(selfField = "id", targetTable = "table_attr_config", targetField = "tableId")
    private List<AttrVO> attrList;

    @RelationOneToMany(selfField = "id", targetTable = "table_config", targetField = "parentId")
    private List<TableConfigVO> childList;
}
