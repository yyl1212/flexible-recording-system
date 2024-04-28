package com.wiw.pinyee.constant;

public enum ResultEnum {
    TYPE_ALREADY_EXIST("100000", "类型配置失败,类型名重复"),

    TABLE_ALREADY_EXIST("101000", "表单配置失败,表单名重复"),
    TABLE_CREATE_REPEAT("101001", "表单创建失败,存在重复创建"),

    DATA_ADD_FAIL("102000", "数据新增失败");

    public final String code;
    public final String msg;

    ResultEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
