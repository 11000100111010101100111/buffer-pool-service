package com.kit.website.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface WebsiteMapper {

    public Integer loveCount();

    public Integer hasLoved(@Param("userIdOrIp") String userIdOrIp);

    public void loveOnce(@Param("userIdOrIp") String userIdOrIp,
                         @Param("userType") String userType,
                         @Param("loveType") String loveType,
                         @Param("id") String id,
                         @Param("msg") String msg);
}
