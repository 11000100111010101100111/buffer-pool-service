package com.kit.practice.service;

import com.kit.common.core.domain.model.LoginUser;
import com.kit.practice.domian.library.Column;
import com.kit.practice.domian.library.ReadResult;
import jxl.Cell;
import jxl.CellFeatures;
import jxl.CellType;
import jxl.Sheet;
import jxl.SheetSettings;
import jxl.Workbook;
import jxl.format.CellFormat;
import jxl.read.biff.BiffException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

/**
 * Created by IntelliJ IDEA.
 *
 * @author xjh
 * @since 2024/8/4
 * Time: 10:14
 **/
@Service
public class ExcelReader {
    public List<ReadResult> read(MultipartFile file, LoginUser user) throws IOException, BiffException {
        Workbook workbook = null;
        String name = file.getOriginalFilename();
        try (InputStream fileInputStream = file.getInputStream()) {
            workbook = Workbook.getWorkbook(fileInputStream);
            List<ReadResult> allSheets = new ArrayList<>();
            Workbook finalWorkbook = workbook;
            Optional.ofNullable(workbook.getSheets())
                    .filter(s -> s.length > 0)
                    .ifPresent(s -> {
                        for (int index = 0; index < s.length; index++) {
                            // 默认获取第一张工作表，可以自定义
                            Sheet sheet = finalWorkbook.getSheet(index);
                            SheetSettings settings = sheet.getSettings();
                            int columns = sheet.getColumns();

                            String sheetID = UUID.randomUUID().toString().replaceAll("-", "_");
                            ReadResult sheetInfo = new ReadResult();
                            sheetInfo.setTableName(String.format("library_%s", sheetID));
                            sheetInfo.setOriginName(String.format("%s-%s", name, sheet.getName()));
                            allSheets.add(sheetInfo);

                            Map<String, Map<String, Column>> columnsMap = new HashMap<>();
                            Map<String, Column> sheetColumns = columnsMap.computeIfAbsent(sheetID, key -> new HashMap<>());
                            for (int columnIndex = 0; columnIndex < columns; columnIndex++) {
                                String columnName = String.format("C%d", sheetColumns.size() + 1);
                                sheetColumns.computeIfAbsent(columnName, key -> new Column(columnName, "varchar", 1).setMark("内容字段"));
                            }
                            sheetColumns.put("index", new Column("index", "int", 0).setPk().setMark("主键"));
                            sheetColumns.put("delete", new Column("delete", "char", 1).setMark("是否删除标记, 1:已删除，0：未删除").setDefaultValue("0"));
                            sheetColumns.put("status", new Column("status", "char", 1).setMark("状态，1:启用，0：停用").setDefaultValue("1"));
                            sheetColumns.put("mark", new Column("mark", "varchar", 200).setMark("备注信息"));
                            sheetColumns.put("createTime", new Column("createTime", "datetime", 0).setMark("创建时间").setDefaultValue("now()"));
                            sheetColumns.put("updateTime", new Column("updateTime", "datetime", 0).setMark("删除时间").setDefaultValue("now()"));
                            sheetColumns.put("createBy", new Column("createBy", "varchar", 20).setMark("创建人名称"));
                            sheetColumns.put("updateBy", new Column("updateBy", "varchar", 20).setMark("删除人名称"));
                            sheetInfo.setColumns(sheetColumns);

                            // 循环获取每一行数据 因为默认第一行为标题行，我们可以从 1 开始循环，如果没有标题行，i从 0 开始
                            // sheet.getRows() 获取总行数

                            int rows = sheet.getRows();
                            List<Map<String, Object>> cellData = new ArrayList<>(rows);
                            sheetInfo.setCellData(cellData);
                            for (int i = 1; i < rows; i++) {
                                Cell[] row = sheet.getRow(i);
                                if (null == row || row.length <= 0) continue;
                                Map<String, Object> cells = new HashMap<>();
                                for (int cellIndex = 0; cellIndex < row.length; cellIndex++) {
                                    String columnName = String.format("C%d", cellIndex + 1);
                                    Column columnInfo = sheetColumns.computeIfAbsent(columnName, k -> new Column(columnName, "varchar", 1));
                                    Cell cell = row[cellIndex];
                                    CellType type = cell.getType();
                                    CellFormat cellFormat = cell.getCellFormat();
                                    CellFeatures cellFeatures = cell.getCellFeatures();
                                    String contents = cell.getContents();

                                    columnInfo.setLength(Math.max(((Number) (contents.length() * 1.5)).intValue(), columnInfo.getLength()));
                                    cells.put(columnName, contents);
                                }
                                cells.put("index", i);
                                cells.put("delete", "0");
                                cells.put("status", "1");
                                cells.put("mark", "");
                                cells.put("createBy", user.getUsername());
                                cells.put("updateBy", user.getUsername());
                                cellData.add(i - 1, cells);
                            }
                        }
                    });
            return allSheets;
        } finally {
            Optional.ofNullable(workbook).ifPresent(Workbook::close);
        }
    }
}
