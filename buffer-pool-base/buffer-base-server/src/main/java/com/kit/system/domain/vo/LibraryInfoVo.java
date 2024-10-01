package com.kit.system.domain.vo;

import com.kit.system.domain.entity.LibraryInfo;
import com.kit.system.domain.library.CountTableMap;

import java.util.Optional;

/**
 * Created by IntelliJ IDEA.
 *
 * @author xjh
 * @since 2024/8/10
 * Time: 11:52
 **/
public class LibraryInfoVo extends LibraryInfo {
    int count;
    String createUserName;
    String updateUserName;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getCreateUserName() {
        return createUserName;
    }

    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName;
    }

    public String getUpdateUserName() {
        return updateUserName;
    }

    public void setUpdateUserName(String updateUserName) {
        this.updateUserName = updateUserName;
    }

    public void setCountInfo(CountTableMap countInfo) {
        Optional.ofNullable(countInfo).ifPresent(count -> this.count = countInfo.getCount());
    }
}
