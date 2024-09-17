package com.kit.system.domain.library;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Created by IntelliJ IDEA.
 *
 * @author xjh
 * @since 2024/8/4
 * Time: 12:59
 **/
public class Column {
    String name;
    String sqlType;
    int length;
    Boolean isPk;
    String defaultValue;
    String mark;

    public Column setPk() {
        this.isPk = true;
        return this;
    }

    public Column setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
        return this;
    }

    public Column setMark(String mark) {
        this.mark = mark;
        return this;
    }

    public Column(String name, String sqlType, int length) {
        this.name = name;
        this.sqlType = sqlType;
        this.length = length;
    }

    public Column() {

    }

    public String getName() {
        return name;
    }

    public String getSqlType() {
        return sqlType;
    }

    public int getLength() {
        return length;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSqlType(String sqlType) {
        this.sqlType = sqlType;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public static List<Column> sql(Map<String, Column> columnMap) {
        List<Column> columns = new ArrayList<>();
        columnMap.forEach((name, info) -> {
            String type = info.getSqlType();
            if ("varchar".equalsIgnoreCase(type) || "char".equalsIgnoreCase(type)) {
                info.sqlType = String.format("%s(%d)", type, info.getLength());
            }

            String defaultVal = info.defaultValue;
            if ("varchar".equalsIgnoreCase(type) || "char".equalsIgnoreCase(type)) {
                info.defaultValue = String.format("'%s'", Optional.ofNullable(defaultVal).orElse(""));
            }

            String mark = info.mark;
            if (null == mark) {
                info.mark = "-";
            }
            columns.add(info);
        });
        columns.sort(Comparator.comparing(Column::getName));
        return columns;
    }
}
