package com.wiw.pinyee.service;

import com.mybatisflex.core.service.IService;
import com.wiw.pinyee.dto.TableAddDTO;
import com.wiw.pinyee.dto.TableAttrSubDTO;
import com.wiw.pinyee.entity.TableConfig;
import com.wiw.pinyee.vo.TableConfigVO;

import java.util.List;

public interface TableConfigService extends IService<TableConfig> {
    boolean tableAdd(TableAddDTO dto);

    void attrSub(TableAttrSubDTO dto);

    List<TableConfigVO> show();

    void tableDel(Long tableId);

    boolean tableCreate(Long tableId, String prefix);

    TableConfigVO showById(Long id);
}
