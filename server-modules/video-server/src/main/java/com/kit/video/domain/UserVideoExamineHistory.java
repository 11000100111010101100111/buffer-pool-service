package com.kit.video.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.kit.common.core.domain.BaseEntity;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("user_video_examine_history")
public class UserVideoExamineHistory extends BaseEntity {
    private static final long serialVersionUID = 1L;

    @TableId("id")
    String id;

    @TableField("examine_id")
    String examineId;

    @TableField("examine_result")
    String examineResult;

    @TableField("examine_result_message")
    String examineResultMessage;

    @TableField("examine_result_user")
    String examineResultUser;

    @TableField("examine_result_time")
    LocalDateTime examineResultTime;
}
