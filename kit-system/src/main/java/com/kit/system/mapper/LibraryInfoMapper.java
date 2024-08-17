package com.kit.system.mapper;

import com.kit.system.domain.entity.LibraryInfo;
import com.kit.system.domain.vo.LibraryInfoVo;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 *
 * @author xjh
 * @since 2024/8/4
 * Time: 17:25
 **/
public interface LibraryInfoMapper {

    int addLibraryInfo(LibraryInfo info);

    List<LibraryInfoVo> selectLibraryInfoList(LibraryInfo info);
}
