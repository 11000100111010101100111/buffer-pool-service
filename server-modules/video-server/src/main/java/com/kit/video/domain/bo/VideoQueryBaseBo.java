package com.kit.video.domain.bo;

import io.swagger.annotations.ApiParam;
import lombok.Data;

import java.util.List;

@Data
public class VideoQueryBaseBo {
    @ApiParam("视频描述文字，模糊搜索")
    String description;

    @ApiParam("视频标签，模糊搜索")
    String tagName;

    @ApiParam("视频类型，模糊搜索")
    String type;

    @ApiParam(name = "单次搜索数目，默认1，最小1，最大20", defaultValue = "1")
    Integer count;

    @ApiParam(name = "需要过滤调的视频ID")
    List<String> filterVideoIds;
}
