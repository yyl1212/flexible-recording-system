package com.wiw.pinyee.entity.table;

import com.mybatisflex.core.query.QueryColumn;
import com.mybatisflex.core.table.TableDef;

import java.io.Serial;

/**
 * 表单配置表 表定义层。
 *
 * @author wiw
 * @since 2024-04-25
 */
public class TableConfigTableDef extends TableDef {

    /**
     * 表单配置表
     */
    public static final TableConfigTableDef TABLE_CONFIG = new TableConfigTableDef();
    @Serial
    private static final long serialVersionUID = 1L;
    public final QueryColumn ID = new QueryColumn(this, "id");

    public final QueryColumn PARENT_ID = new QueryColumn(this, "parent_id");

    /**
     * 表名
     */
    public final QueryColumn TABLE_NAME = new QueryColumn(this, "table_name");

    /**
     * sql表名
     */
    public final QueryColumn TABLE_DB_NAME = new QueryColumn(this, "table_db_name");

    /**
     * 所有字段。
     */
    public final QueryColumn ALL_COLUMNS = new QueryColumn(this, "*");

    /**
     * 默认字段，不包含逻辑删除或者 large 等字段。
     */
    public final QueryColumn[] DEFAULT_COLUMNS = new QueryColumn[]{ID, PARENT_ID, TABLE_NAME, TABLE_DB_NAME};

    public TableConfigTableDef() {
        super("", "table_config");
    }

    private TableConfigTableDef(String schema, String name, String alisa) {
        super(schema, name, alisa);
    }

    public TableConfigTableDef as(String alias) {
        String key = getNameWithSchema() + "." + alias;
        return getCache(key, k -> new TableConfigTableDef("", "table_config", alias));
    }

}
