package com.wiw.pinyee.entity;

import com.mybatisflex.annotation.Table;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Table("type_sel_config")
public class TypeSelConfig {

    private Long typeId;

    private String selName;
}
