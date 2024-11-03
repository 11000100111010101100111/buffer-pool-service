package com.kit.video.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.kit.common.core.domain.BaseEntity;
import lombok.Data;

@Data
@TableName("user_video_examine_info")
public class UserVideoExamineInfo extends BaseEntity {
    private static final long serialVersionUID = 1L;

    @TableId("id")
    String id;

    @TableField("video_id")
    String videoId;

    @TableField("examine_type")
    Integer examineType;

    @TableField("examine_message")
    String examineMessage;

    @TableField("examine_result")
    String examineResult;
}
