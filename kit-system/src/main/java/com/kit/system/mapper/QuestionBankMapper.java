package com.kit.system.mapper;

import com.kit.system.domain.library.Column;
import com.kit.system.domain.library.CountTableMap;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 *
 * @author xjh
 * @since 2024/8/3
 * Time: 20:10
 **/
public interface QuestionBankMapper {
    /**
     *
     *org.springframework.jdbc.BadSqlGrammarException: ### Error updating database. Cause: java.sql.SQLSyntaxErrorException: You have an error in your SQL syntax; check the manual that corresponds to your MySQL server version for the right syntax to use near 'COMMENT '内容字段' , `create_time` datetime P' at line 3 ### The error may exist in file [D:\kit\idea\ZheTiWoYeHui\kit-system\target\classes\mapper\system\QuestionBankMapper.xml] ### The error may involve com.kit.system.mapper.QuestionBankMapper.createTable-Inline ### The error occurred while setting parameters ### SQL: CREATE TABLE `06df1800_da29_4fd3_8883_a51cfb28e081` ( `C10` varchar(1) PRIMARY KEY DEFAULT COMMENT '内容字段' , `create_time` datetime PRIMARY KEY DEFAULT now() COMMENT '创建时间' , `index` int PRIMARY KEY DEFAULT COMMENT '主键' , `delete` char(1) PRIMARY KEY DEFAULT '0' COMMENT '是否删除标记, 1:已删除，0：未删除' , `C1` varchar(6) PRIMARY KEY DEFAULT COMMENT '内容字段' , `C2` varchar(1) PRIMARY KEY DEFAULT COMMENT '内容字段' , `C3` varchar(1039) PRIMARY KEY DEFAULT COMMENT '内容字段' , `create_by` bigint PRIMARY KEY DEFAULT COMMENT '创建人ID' , `C4` varchar(204) PRIMARY KEY DEFAULT COMMENT '内容字段' , `C5` varchar(205) PRIMARY KEY DEFAULT COMMENT '内容字段' , `update_time` datetime PRIMARY KEY DEFAULT now() COMMENT '删除时间' , `C6` varchar(219) PRIMARY KEY DEFAULT COMMENT '内容字段' , `C7` varchar(295) PRIMARY KEY DEFAULT COMMENT '内容字段' , `C8` varchar(4) PRIMARY KEY DEFAULT COMMENT '内容字段' , `C9` varchar(1) PRIMARY KEY DEFAULT COMMENT '内容字段' , `update_by` bigint PRIMARY KEY DEFAULT COMMENT '删除人ID' , `mark` varchar(200) PRIMARY KEY DEFAULT COMMENT '备注信息' , `status` char(1) PRIMARY KEY DEFAULT '1' COMMENT '状态，1:启用，0：停用' ) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '题库' ROW_FORMAT = Dynamic ### Cause: java.sql.SQLSyntaxErrorException: You have an error in your SQL syntax; check the manual that corresponds to your MySQL server version for the right syntax to use near 'COMMENT '内容字段' , `create_time` datetime P' at line 3 ; bad SQL grammar []; nested exception is java.sql.SQLSyntaxErrorException: You have an error in your SQL syntax; check the manual that corresponds to your MySQL server version for the right syntax to use near 'COMMENT '内容字段' , `create_time` datetime P' at line 3
     * */

    public int createTable(@Param("tableName") String tableName, @Param("originName") String originName, @Param(value = "columns") List<Column> columns);

    /**
     * basic sql
     * @param tableName 表名
     * @param columns 列字段集合
     * @param list 值集合
     */
    public int batchInsert(@Param("tableName") String tableName,
                           @Param("columns") List<String> columns,
                           @Param("list") List<List<Object>> list);
    /**
     * 根据表名称列表查询对应表的行数
     * */
    public List<CountTableMap> findTableCountByTableIds(@Param("tables") List<String> tables);
}
