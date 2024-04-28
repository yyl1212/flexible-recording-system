package com.wiw.pinyee.entity;

import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;
import com.mybatisflex.core.keygen.KeyGenerators;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Table("table_attr_config")
public class TableAttrConfig {

    @Id(keyType = KeyType.Generator, value = KeyGenerators.snowFlakeId)
    private Long id;

    private Long tableId;

    private String attrName;

    private String attrDbName;

    private Integer attrType;

    private Long attrConn;
}
