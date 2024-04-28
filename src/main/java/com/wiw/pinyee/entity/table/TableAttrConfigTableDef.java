package com.wiw.pinyee.entity.table;

import com.mybatisflex.core.query.QueryColumn;
import com.mybatisflex.core.table.TableDef;

import java.io.Serial;

/**
 * 表单属性配置表 表定义层。
 *
 * @author wiw
 * @since 2024-04-25
 */
public class TableAttrConfigTableDef extends TableDef {

    /**
     * 表单属性配置表
     */
    public static final TableAttrConfigTableDef TABLE_ATTR_CONFIG = new TableAttrConfigTableDef();
    @Serial
    private static final long serialVersionUID = 1L;
    public final QueryColumn ID = new QueryColumn(this, "id");

    /**
     * 表单id
     */
    public final QueryColumn TABLE_ID = new QueryColumn(this, "table_id");

    /**
     * 关联选项
     */
    public final QueryColumn ATTR_CONN = new QueryColumn(this, "attr_conn");

    /**
     * 属性名
     */
    public final QueryColumn ATTR_NAME = new QueryColumn(this, "attr_name");

    /**
     * 属性类型(10=填空,20=单选,30=多个)
     */
    public final QueryColumn ATTR_TYPE = new QueryColumn(this, "attr_type");

    /**
     * sql属性名
     */
    public final QueryColumn ATTR_DB_NAME = new QueryColumn(this, "attr_db_name");

    /**
     * 所有字段。
     */
    public final QueryColumn ALL_COLUMNS = new QueryColumn(this, "*");

    /**
     * 默认字段，不包含逻辑删除或者 large 等字段。
     */
    public final QueryColumn[] DEFAULT_COLUMNS = new QueryColumn[]{ID, TABLE_ID, ATTR_NAME, ATTR_DB_NAME, ATTR_TYPE, ATTR_CONN};

    public TableAttrConfigTableDef() {
        super("", "table_attr_config");
    }

    private TableAttrConfigTableDef(String schema, String name, String alisa) {
        super(schema, name, alisa);
    }

    public TableAttrConfigTableDef as(String alias) {
        String key = getNameWithSchema() + "." + alias;
        return getCache(key, k -> new TableAttrConfigTableDef("", "table_attr_config", alias));
    }

}
