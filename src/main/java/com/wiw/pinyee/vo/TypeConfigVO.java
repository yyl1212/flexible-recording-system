package com.wiw.pinyee.vo;

import com.mybatisflex.annotation.RelationOneToMany;
import lombok.Data;

import java.util.List;

@Data
public class TypeConfigVO {

    private Long id;

    private String typeName;

    @RelationOneToMany(selfField = "id", targetTable = "type_sel_config", targetField = "typeId", valueField = "selName")
    private List<String> selNameList;
}
