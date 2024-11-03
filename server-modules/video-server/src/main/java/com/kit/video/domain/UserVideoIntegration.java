package com.kit.video.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.kit.common.core.domain.BaseEntity;
import lombok.Data;

@TableName("user_video_integration")
@Data
public class UserVideoIntegration extends BaseEntity {
    private static final long serialVersionUID = 1L;

    @TableId("video_id")
    String videoId;

    @TableField("integration")
    Long integration;
}
