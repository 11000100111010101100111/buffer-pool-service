package com.kit.practice.domian.library;

import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 *
 * @author xjh
 * @since 2024/8/4
 * Time: 13:27
 **/
public class ReadResult {
    String tableName;
    Map<String, Column> columns;
    List<Map<String, Object>> cellData;
    String originName;

    public Map<String, Column> getColumns() {
        return columns;
    }

    public void setColumns(Map<String, Column> columns) {
        this.columns = columns;
    }

    public List<Map<String, Object>> getCellData() {
        return cellData;
    }

    public void setCellData(List<Map<String, Object>> cellData) {
        this.cellData = cellData;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getOriginName() {
        return originName;
    }

    public void setOriginName(String originName) {
        this.originName = originName;
    }
}
