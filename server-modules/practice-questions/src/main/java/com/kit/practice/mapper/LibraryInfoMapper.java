package com.kit.practice.mapper;


import com.kit.practice.domian.entity.LibraryInfo;
import com.kit.practice.domian.vo.LibraryInfoVo;

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
