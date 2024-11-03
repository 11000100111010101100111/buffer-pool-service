package com.kit.video.domain.vo.open;

import lombok.Data;

@Data
public class VideoWithTagInfoVo {
    String videoId;
    String tagId;
    String tagName;
    Integer useTimes;
}
