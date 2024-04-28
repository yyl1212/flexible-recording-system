package com.wiw.pinyee.entity;

import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;
import com.mybatisflex.core.keygen.KeyGenerators;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Table("type_config")
public class TypeConfig {

    @Id(keyType = KeyType.Generator, value = KeyGenerators.flexId)
    private Long id;

    private String typeName;
}
