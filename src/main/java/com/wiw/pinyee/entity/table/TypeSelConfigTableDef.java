package com.wiw.pinyee.entity.table;

import com.mybatisflex.core.query.QueryColumn;
import com.mybatisflex.core.table.TableDef;

import java.io.Serial;

/**
 * 类型选项表 表定义层。
 *
 * @author wiw
 * @since 2024-04-25
 */
public class TypeSelConfigTableDef extends TableDef {

    /**
     * 类型选项表
     */
    public static final TypeSelConfigTableDef TYPE_SEL_CONFIG = new TypeSelConfigTableDef();
    @Serial
    private static final long serialVersionUID = 1L;
    /**
     * 类型id
     */
    public final QueryColumn TYPE_ID = new QueryColumn(this, "type_id");

    /**
     * 选项名
     */
    public final QueryColumn SEL_NAME = new QueryColumn(this, "sel_name");

    /**
     * 所有字段。
     */
    public final QueryColumn ALL_COLUMNS = new QueryColumn(this, "*");

    /**
     * 默认字段，不包含逻辑删除或者 large 等字段。
     */
    public final QueryColumn[] DEFAULT_COLUMNS = new QueryColumn[]{TYPE_ID, SEL_NAME};

    public TypeSelConfigTableDef() {
        super("", "type_sel_config");
    }

    private TypeSelConfigTableDef(String schema, String name, String alisa) {
        super(schema, name, alisa);
    }

    public TypeSelConfigTableDef as(String alias) {
        String key = getNameWithSchema() + "." + alias;
        return getCache(key, k -> new TypeSelConfigTableDef("", "type_sel_config", alias));
    }

}
