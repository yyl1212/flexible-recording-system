package com.wiw.pinyee.dto;

import lombok.Data;

import java.util.List;

@Data
public class TypeSelSubDTO {

    private Long typeId;

    private List<String> selNameList;
}
