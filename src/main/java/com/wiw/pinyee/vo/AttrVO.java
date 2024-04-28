package com.wiw.pinyee.vo;

import lombok.Data;

@Data
public class AttrVO {

    private Long tableId;

    private String attrName;

    private String attrDbName;

    private Integer attrType;

    private Long attrConn;
}
