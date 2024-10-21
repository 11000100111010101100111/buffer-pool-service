
create table ai_img_wait_queue_info
(
    `id`         int PRIMARY KEY AUTO_INCREMENT,
    `ip_or_user_id`    varchar(100) not null DEFAULT '',
    `process_id`       varchar(36)  not null DEFAULT '',
    `result`        char(1)  not null DEFAULT '0' COMMENT '处理结果，0：等待处理，1：处理中，2：处理完成，-1：处理失败',
    `result_message` varchar(100)   not null DEFAULT '状态信息',
    `img_url` varchar(200) not null DEFAULT '',
    `text`   varchar(50)          not null default 0 COMMENT '生成信息描述文本, 30字以内',
    `width`      int          not null default 512 COMMENT '图片宽度，(0，1920],默认512',
    `height`     int          not null default 512 COMMENT '图片高度，(0,1080]，默认512',
    `createBy`   varchar(64) COMMENT '创建人ID',
    `createTime` datetime              DEFAULT now() COMMENT '创建时间',
    `delete`     char(1)               DEFAULT '0' COMMENT '是否删除标记, 1:已删除，0：未删除',
    `updateTime` datetime              DEFAULT now() COMMENT '删除时间',
    `updateBy`   varchar(64) COMMENT '删除人ID',
    `remark`     varchar(200)          DEFAULT '' COMMENT '备注信息',
    `status`     char(1)               DEFAULT '1' COMMENT '状态，1:启用，0：停用'
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT = 'AI图片生成信息'
  ROW_FORMAT = Dynamic;

create table ai_img_wait_queue_step_info
(
    `id`         int PRIMARY KEY AUTO_INCREMENT,
    `process_id`    varchar(36) not null DEFAULT '' COMMENT '作业ID',
    `type`        char(1)  not null DEFAULT '0' COMMENT '处理结果，0：等待处理，1：处理中，2：处理完成，-1：处理失败',
    `title` varchar(100)   not null DEFAULT '状态信息',
    `percentage`   varchar(10)          not null default '1' COMMENT '进度百分比',
    `mark`      varchar(100)          not null default '' COMMENT '进度说明',
    `currentStep`     varchar(10)          not null default '-' COMMENT '当前执行到第几步骤',
    `totalSteps`     varchar(10)          not null default '-' COMMENT '总共步骤数',
    `estimatedTime`     varchar(10)          not null default'-' COMMENT '预计剩余时间',
    `elapsedTime`     varchar(10)          not null default '-' COMMENT '已执行时间',
    `time`       bigint not null default 0 COMMENT '当前时间',
    `createBy`   varchar(64) COMMENT '创建人ID',
    `createTime` datetime              DEFAULT now() COMMENT '创建时间',
    `delete`     char(1)               DEFAULT '0' COMMENT '是否删除标记, 1:已删除，0：未删除',
    `updateTime` datetime              DEFAULT now() COMMENT '删除时间',
    `updateBy`   varchar(64) COMMENT '删除人ID',
    `remark`     varchar(200)          DEFAULT '' COMMENT '备注信息',
    `status`     char(1)               DEFAULT '1' COMMENT '状态，1:启用，0：停用'
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT = 'AI图片生成进度信息'
  ROW_FORMAT = Dynamic;