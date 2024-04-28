package com.wiw.pinyee.entity.table;

import com.mybatisflex.core.query.QueryColumn;
import com.mybatisflex.core.table.TableDef;

import java.io.Serial;

/**
 * 类型配置表 表定义层。
 *
 * @author wiw
 * @since 2024-04-25
 */
public class TypeConfigTableDef extends TableDef {

    /**
     * 类型配置表
     */
    public static final TypeConfigTableDef TYPE_CONFIG = new TypeConfigTableDef();
    @Serial
    private static final long serialVersionUID = 1L;
    public final QueryColumn ID = new QueryColumn(this, "id");

    /**
     * 类型名
     */
    public final QueryColumn TYPE_NAME = new QueryColumn(this, "type_name");

    /**
     * 所有字段。
     */
    public final QueryColumn ALL_COLUMNS = new QueryColumn(this, "*");

    /**
     * 默认字段，不包含逻辑删除或者 large 等字段。
     */
    public final QueryColumn[] DEFAULT_COLUMNS = new QueryColumn[]{ID, TYPE_NAME};

    public TypeConfigTableDef() {
        super("", "type_config");
    }

    private TypeConfigTableDef(String schema, String name, String alisa) {
        super(schema, name, alisa);
    }

    public TypeConfigTableDef as(String alias) {
        String key = getNameWithSchema() + "." + alias;
        return getCache(key, k -> new TypeConfigTableDef("", "type_config", alias));
    }

}
