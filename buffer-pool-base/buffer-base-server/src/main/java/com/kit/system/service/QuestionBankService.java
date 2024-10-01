package com.kit.system.service;

import com.kit.common.core.domain.model.LoginUser;
import com.kit.system.domain.entity.LibraryInfo;
import com.kit.system.domain.vo.LibraryImportVo;
import com.kit.system.domain.vo.LibraryInfoVo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 *
 * @author xjh
 * @since 2024/8/3
 * Time: 20:13
 **/
public interface QuestionBankService {

    /**
     * 导入题库，动态生成题库表，保存题库信息
     */
    LibraryImportVo importLibrary(MultipartFile file, LoginUser user);

    LibraryImportVo importSystemInfo(MultipartFile file, String tableName, LoginUser user);

    /**
     * 查询题库信息列表（分页）
     */
    List<LibraryInfoVo> selectLibraryInfoList(LibraryInfo info);
}
