package com.kit.system.domain.vo;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 *
 * @author xjh
 * @since 2024/8/4
 * Time: 17:06
 **/
public class LibraryImportVo {
    List<String> tableName;

    public List<String> getTableName() {
        return tableName;
    }

    public void setTableName(List<String> tableName) {
        this.tableName = tableName;
    }

    public LibraryImportVo(List<String> tableName) {
        this.tableName = tableName;
    }
}
