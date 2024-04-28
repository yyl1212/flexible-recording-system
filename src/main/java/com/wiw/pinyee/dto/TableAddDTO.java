package com.wiw.pinyee.dto;

import lombok.Data;

@Data
public class TableAddDTO {

    private Long parentId;

    private String tableName;

    private String tableDbName;
}
