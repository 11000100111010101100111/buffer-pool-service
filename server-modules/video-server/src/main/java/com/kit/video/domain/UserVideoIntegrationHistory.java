package com.kit.video.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("user_video_integration_history")
public class UserVideoIntegrationHistory {
    @TableId("id")
    String id;
    @TableField("video_id")
    String videoId;
    @TableField("integration")
    Long integration;
    @TableField("type")
    String type;
}
