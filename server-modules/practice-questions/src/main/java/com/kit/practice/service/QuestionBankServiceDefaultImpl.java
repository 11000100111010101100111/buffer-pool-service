package com.kit.practice.service;

import com.kit.common.core.domain.model.LoginUser;
import com.kit.practice.domian.entity.LibraryInfo;
import com.kit.practice.domian.library.Column;
import com.kit.practice.domian.library.ReadResult;
import com.kit.practice.domian.vo.LibraryImportVo;
import com.kit.practice.domian.vo.LibraryInfoVo;
import com.kit.practice.mapper.LibraryInfoMapper;
import com.kit.practice.mapper.QuestionBankMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by IntelliJ IDEA.
 *
 * @author xjh
 * @since 2024/8/3
 * Time: 20:14
 **/
@Service
public class QuestionBankServiceDefaultImpl implements QuestionBankService {
    @Autowired
    private QuestionBankMapper questionBankMapper;
    @Autowired
    private ExcelReader excelReader;
    @Autowired
    private LibraryInfoMapper libraryInfoMapper;

    @Override
    @Transactional
    public LibraryImportVo importLibrary(MultipartFile file, LoginUser user) {
        try {
            List<ReadResult> read = excelReader.read(file, user);
            for (ReadResult result : read) {
                String tableName = result.getTableName();
                List<Column> columns = Column.sql(result.getColumns());
                questionBankMapper.createTable(tableName, result.getOriginName(), columns);
                LibraryInfo libraryInfo = new LibraryInfo();
                libraryInfo.setTableName(tableName);
                libraryInfo.setOriginName(result.getOriginName());
                libraryInfo.setRemark(result.getOriginName());
                libraryInfo.setCreateBy(user.getUsername());
                libraryInfo.setUpdateBy(user.getUsername());
                libraryInfoMapper.addLibraryInfo(libraryInfo);
                List<Map<String, Object>> cellData = result.getCellData();
                List<String> names = columns.stream()
                        .filter(n -> !"create_time".equals(n.getName()) && !"update_time".equals(n.getName()))
                        .map(Column::getName)
                        .collect(Collectors.toList());
                List<List<Object>> dataList = new ArrayList<>();
                cellData.forEach(data -> dataList.add(names.stream().map(data::get).collect(Collectors.toList())));
                if (!dataList.isEmpty()) {
                    questionBankMapper.batchInsert(tableName, names, dataList);
                }
            }
            return new LibraryImportVo(read.stream().map(ReadResult::getTableName).collect(Collectors.toList()));
        } catch (Exception e) {
            //@todo log: can not read excel file
            throw new RuntimeException(e);
        }
    }

    @Override
    @Transactional
    public LibraryImportVo importSystemInfo(MultipartFile file, String tableName, LoginUser user) {
        try {
            List<ReadResult> read = excelReader.read(file, user);
            for (ReadResult result : read) {
                List<String> names = Column.sql(result.getColumns()).stream()
                        .filter(n -> !"create_time".equals(n.getName()) && !"update_time".equals(n.getName()))
                        .map(Column::getName)
                        .collect(Collectors.toList());
                List<List<Object>> dataList = new ArrayList<>();
                result.getCellData().forEach(data -> dataList.add(names.stream().map(data::get).collect(Collectors.toList())));

                for (int i = names.size() - 1; i > 3; i--) {
                    names.remove(i);
                }
                names.set(0, "code");
                names.set(1, "name");
                names.set(2, "day_name");
                names.set(3, "night_name");
                dataList.remove(0);
                dataList.forEach(d -> {
                    for (int i = d.size() - 1; i > 3; i--) {
                        d.remove(i);
                    }
                });

                if (!dataList.isEmpty()) {
                    questionBankMapper.batchInsert(tableName, names, dataList);
                }
                break;
            }
            return new LibraryImportVo(read.stream().map(ReadResult::getTableName).collect(Collectors.toList()));
        } catch (Exception e) {
            //@todo log: can not read excel file
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<LibraryInfoVo> selectLibraryInfoList(LibraryInfo info) {
        List<LibraryInfoVo> result = Optional.ofNullable(libraryInfoMapper.selectLibraryInfoList(info))
                .orElse(new ArrayList<>());
        if (result.isEmpty()) {
            return result;
        }

        Map<String, LibraryInfoVo> collect = result.stream()
                .filter(Objects::nonNull)
                .filter(infoVo -> Objects.nonNull(infoVo.getTableName()))
                .collect(Collectors.toMap(LibraryInfoVo::getTableName, infoVo -> infoVo, (i1, i2) -> i1));
        if (collect.isEmpty()) {
            return result;
        }

        //查询表记录行
        Optional.ofNullable(questionBankMapper.findTableCountByTableIds(new ArrayList<>(collect.keySet())))
                .ifPresent(infos -> {
                    if (infos.isEmpty()) {
                        return;
                    }
                    infos.forEach(countTableMap -> Optional.ofNullable(collect.get(countTableMap.getTableName()))
                            .ifPresent(vo -> vo.setCountInfo(countTableMap)));
                });
        return this.hiddenTableName(result);
    }

    /**
     * 返回结果中隐藏表名称信息
     */
    public LibraryInfoVo hiddenTableName(LibraryInfoVo vo) {
        Optional.ofNullable(vo).ifPresent(v -> vo.setTableName(""));
        return vo;
    }

    /**
     * 返回结果中隐藏表名称信息
     */
    public List<LibraryInfoVo> hiddenTableName(List<LibraryInfoVo> vo) {
        Optional.ofNullable(vo)
                .ifPresent(vos -> vos.stream()
                        .filter(Objects::nonNull)
                        .forEach(this::hiddenTableName)
                );
        return vo;
    }
}
