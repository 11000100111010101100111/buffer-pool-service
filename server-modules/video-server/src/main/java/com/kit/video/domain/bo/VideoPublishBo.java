package com.kit.video.domain.bo;

import io.swagger.annotations.ApiParam;
import lombok.Data;

import java.util.List;

@Data
public class VideoPublishBo {
    String title;
    String description;
    String tags;
    VideoOfLocationBo location;

    @ApiParam(name = "与上传视频一一对应，表示视频的类型：封面（cover），视频（video），图片（img）")
    List<String> videoInfo;
}
