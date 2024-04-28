package com.wiw.pinyee.dto;

import lombok.Data;

import java.util.List;

@Data
public class TableAttrSubDTO {

    private Long tableId;

    private List<AttrSubDTO> attrList;
}
