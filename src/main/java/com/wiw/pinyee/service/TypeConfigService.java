package com.wiw.pinyee.service;

import com.mybatisflex.core.service.IService;
import com.wiw.pinyee.dto.TypeSelSubDTO;
import com.wiw.pinyee.entity.TypeConfig;
import com.wiw.pinyee.vo.TypeConfigVO;

import java.util.List;

public interface TypeConfigService extends IService<TypeConfig> {
    Boolean typeAdd(String typeName);

    void selSub(TypeSelSubDTO dto);

    void typeDel(Long typeId);

    List<TypeConfigVO> type();
}
