package com.wiw.pinyee.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.mybatisflex.core.query.QueryChain;
import com.mybatisflex.core.row.Db;
import com.mybatisflex.core.update.UpdateChain;
import com.mybatisflex.spring.service.impl.ServiceImpl;
import com.wiw.pinyee.dto.TypeSelSubDTO;
import com.wiw.pinyee.entity.TypeConfig;
import com.wiw.pinyee.entity.TypeSelConfig;
import com.wiw.pinyee.mapper.TypeConfigMapper;
import com.wiw.pinyee.mapper.TypeSelConfigMapper;
import com.wiw.pinyee.service.TypeConfigService;
import com.wiw.pinyee.vo.TypeConfigVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.wiw.pinyee.entity.table.TypeConfigTableDef.TYPE_CONFIG;
import static com.wiw.pinyee.entity.table.TypeSelConfigTableDef.TYPE_SEL_CONFIG;

@Service
public class TypeConfigServiceImpl extends ServiceImpl<TypeConfigMapper, TypeConfig> implements TypeConfigService {
    @Override
    public Boolean typeAdd(String typeName) {
        if (StrUtil.isBlank(typeName) || QueryChain.of(TypeConfig.class)
                .where(TYPE_CONFIG.TYPE_NAME.eq(typeName))
                .exists()) {
            return false;
        }
        mapper.insert(TypeConfig.builder()
                .typeName(typeName)
                .build());
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void selSub(TypeSelSubDTO dto) {
        if (!QueryChain.of(TypeConfig.class)
                .where(TYPE_CONFIG.ID.eq(dto.getTypeId()))
                .exists()) {
            return;
        }
        UpdateChain.of(TypeSelConfig.class)
                .where(TYPE_SEL_CONFIG.TYPE_ID.eq(dto.getTypeId()))
                .remove();
        if (CollUtil.isNotEmpty(dto.getSelNameList())) {
            Db.executeBatch(dto.getSelNameList().size(), 1000, TypeSelConfigMapper.class, (mapper, index) -> {
                String selName = dto.getSelNameList().get(index);
                mapper.insert(TypeSelConfig.builder()
                        .typeId(dto.getTypeId())
                        .selName(selName)
                        .build());
            });
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void typeDel(Long typeId) {
        UpdateChain.of(TypeSelConfig.class)
                .where(TYPE_SEL_CONFIG.TYPE_ID.eq(typeId))
                .remove();
        UpdateChain.of(TypeConfig.class)
                .where(TYPE_CONFIG.ID.eq(typeId))
                .remove();
    }

    @Override
    public List<TypeConfigVO> type() {
        return QueryChain.of(TypeConfig.class)
                .select()
                .withRelations()
                .listAs(TypeConfigVO.class);
    }
}
