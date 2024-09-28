-- 视频相关Start

CREATE TABLE user_video_metadata_info (
    `id` varchar(36) PRIMARY KEY,
    `user_id` VARCHAR(50) NOT NULL COMMENT '上传视频的用户ID',
    `src` varchar(256) DEFAULT '' COMMENT '视频封面图片地址',
    `local_dir` varchar(256) DEFAULT '' COMMENT '视频封面图片上传到服务器的文件路径',
    `compressed` char(1) DEFAULT '' COMMENT '文件是否已被系统压缩，0没有，1已压缩',
    `name` varchar(128) DEFAULT '' COMMENT '视频名称',
    `file_type` varchar(10) DEFAULT '' COMMENT '视频类型',
    `width` int DEFAULT 0 COMMENT '视频宽度',
    `height` int DEFAULT 0 COMMENT '视频高度',
    `byte` bigint DEFAULT 0 COMMENT '视频大小',
    `origin_byte` int DEFAULT 0 COMMENT '视频原始大小',
    `frame` int DEFAULT 0 COMMENT '视频帧',
    `origin_frame` int DEFAULT 0 COMMENT '视频原始帧',
    `video_duration` time DEFAULT '00:00:00' COMMENT '视频时长',
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
  COLLATE = utf8mb4_general_ci COMMENT = '视频原始信息'
  ROW_FORMAT = Dynamic;

CREATE TABLE user_video_block_info (
    `id` varchar(36) PRIMARY KEY,
    `video_id` VARCHAR(50) NOT NULL COMMENT '视频ID',
    `src` varchar(256) DEFAULT '' COMMENT '分块地址',
    `local_dir` varchar(256) DEFAULT '' COMMENT '上传到服务器的分块路径',
    `block_index` int DEFAULT 0 COMMENT '视频块排序号',
    `file_type` varchar(10) DEFAULT '' COMMENT '视频类型',
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
  COLLATE = utf8mb4_general_ci COMMENT = '视频块信息'
  ROW_FORMAT = Dynamic;

CREATE TABLE user_video_tag (
    `id` varchar(36) PRIMARY KEY,
    `name` VARCHAR(20) DEFAULT '' COMMENT '话题（标签）名称',
    use_times bigint DEFAULT 0 COMMENT '话题（标签）被使用次数',
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
  COLLATE = utf8mb4_general_ci COMMENT = '视频相关的话题（标签）信息'
  ROW_FORMAT = Dynamic;

CREATE TABLE user_video_data_info (
    `id` BIGINT AUTO_INCREMENT PRIMARY KEY,
    `video_id` VARCHAR(36) DEFAULT '' COMMENT '视频ID',
     like_times bigint DEFAULT 0 COMMENT '点赞次数',
    `dislike_times` bigint DEFAULT 0 COMMENT '视频的不喜欢数',
     `browse_times` bigint DEFAULT 0 COMMENT '视频的浏览量',
     `comment_times` bigint DEFAULT 0 COMMENT '视频的评论数',
     `collect_times` bigint DEFAULT 0 COMMENT '视频的收藏数',
     `forward_times` bigint DEFAULT 0 COMMENT '视频的转发数',
     `report_times` bigint DEFAULT 0 COMMENT '视频的举报数',
    `description` varchar(512) DEFAULT '' COMMENT '视频的描述',
    `belong` varchar(36) DEFAULT '' COMMENT '视频作者ID',

    `examine_status` char(1) DEFAULT '0' COMMENT '视频审核状态，审核通过其他人才能可见/可以被分享，0：待审核，1：审核中，2审核通过，3审核不通过，4：视频已删除',
    `examine_message` varchar(200) DEFAULT '' COMMENT '视频审核意见，200字内',
    `examine_user` varchar(36) DEFAULT '' COMMENT '视频审核人',

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
  COLLATE = utf8mb4_general_ci COMMENT = '视频产生的相关数据'
  ROW_FORMAT = Dynamic;

CREATE TABLE user_video_browse_info (
    `id` varchar(36) PRIMARY KEY,
    `video_id` VARCHAR(36) DEFAULT '' COMMENT '视频ID',
    `browse_user_id` VARCHAR(36) DEFAULT '' COMMENT '浏览人ID',
     `browse_type` char(1) DEFAULT '0' COMMENT '0:推荐页，1：主页，2：分享，3：搜索',
     `browse_time` time DEFAULT '00:00:00' COMMENT '视频的单次播放时间',
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
  COLLATE = utf8mb4_general_ci COMMENT = '视频的浏览信息表'
  ROW_FORMAT = Dynamic;

CREATE TABLE user_video_forward_info (
    `id` varchar(36) PRIMARY KEY,
    `video_id` VARCHAR(36) DEFAULT '' COMMENT '视频ID',
    `forward_user_id` VARCHAR(36) DEFAULT '' COMMENT '转发人ID',
     `forward_to_user_id` char(1) DEFAULT '0' COMMENT '被转发人ID',
     `forward_typ` char(1) DEFAULT '0' COMMENT '转发的类型，0:平台内转发，1：微信，2：qq, 3:其他',
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
  COLLATE = utf8mb4_general_ci COMMENT = '视频的转发信息表'
  ROW_FORMAT = Dynamic;

CREATE TABLE user_report_type (
  `id` varchar(36) PRIMARY KEY,
  `father_type` VARCHAR(36) DEFAULT '' COMMENT '举报父类型，一般就两层',
  `type` VARCHAR(36) DEFAULT '' COMMENT '举报类型',
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
  COLLATE = utf8mb4_general_ci COMMENT = '举报类型信息表'
  ROW_FORMAT = Dynamic;

CREATE TABLE user_video_report_info (
    `id` varchar(36) PRIMARY KEY,
    `video_id` VARCHAR(36) DEFAULT '' COMMENT '视频ID',
    `report_user_id` VARCHAR(36) DEFAULT '' COMMENT 'report人ID',
    `report_item` char(1) DEFAULT '0' COMMENT '举报对象，1：视频，2：评论，3：用户',
     `report_type_id` int DEFAULT '0' COMMENT '举报类型ID',
     `report_message` varchar(200) DEFAULT '0' COMMENT '200字内的举报文字',
     `report_result` char(1) DEFAULT '0' COMMENT '举报状态，0：已提交，1：审核中，2：通过，3：举报不成立，4：已取消',
     `report_result_message` varchar(200) DEFAULT '' COMMENT '审核意见，200字内',
     `report_result_user` varchar(36) DEFAULT '' COMMENT '审核人ID',
     `report_result_time` datetime DEFAULT null COMMENT '审核时间',
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
  COLLATE = utf8mb4_general_ci COMMENT = '视频的举报信息表'
  ROW_FORMAT = Dynamic;

CREATE TABLE user_video_report_img_info (
    `id` varchar(36) PRIMARY KEY,
    `video_id` VARCHAR(36) DEFAULT '' COMMENT '视频ID',
    `report_id` VARCHAR(36) DEFAULT '' COMMENT 'report人ID',
     `img_url` varchar(200) DEFAULT '0' COMMENT '举报人上传的图片',
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
  COLLATE = utf8mb4_general_ci COMMENT = '视频的举报信息表'
  ROW_FORMAT = Dynamic;

CREATE TABLE user_video_comment_info (
    `id` varchar(36) PRIMARY KEY,
    `video_id` VARCHAR(36) DEFAULT '' COMMENT '视频ID',
    `comment_user_id` VARCHAR(36) DEFAULT '' COMMENT '评论人ID',
     `father_comment_id` char(36) DEFAULT '' COMMENT '父评论ID',
     `comment` varchar(1000) DEFAULT '' COMMENT '评论的内容',
     `like_times` int default 0 COMMENT '评论的点赞数',
     `dislike_times` int default 0 COMMENT '评论的不喜欢数',
     `report_times` int default 0 COMMENT '评论的举报数',
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
  COLLATE = utf8mb4_general_ci COMMENT = '视频的评论信息表'
  ROW_FORMAT = Dynamic;

CREATE TABLE user_video_collect_info (
    `id` varchar(36) PRIMARY KEY,
    `video_id` VARCHAR(36) DEFAULT '' COMMENT '视频ID',
    `collect_user_id` VARCHAR(36) DEFAULT '' COMMENT '收藏人ID',
     `collect_type` char(1) DEFAULT '0' COMMENT '1:收藏，0：取消收藏',
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
  COLLATE = utf8mb4_general_ci COMMENT = '视频的被收藏信息表'
  ROW_FORMAT = Dynamic;

CREATE TABLE user_video_like_info (
    `id` varchar(36) PRIMARY KEY,
    `video_id` VARCHAR(36) DEFAULT '' COMMENT '视频ID',
    `like_user_id` VARCHAR(36) DEFAULT '' COMMENT '点赞/取消点赞人ID',
    `like_item` char(1) DEFAULT '0' COMMENT '点赞/取消点赞的对象，0：视频，1：评论，3：用户',
     `like_type` char(1) DEFAULT '0' COMMENT '0:点赞，1：取消点赞，2：不喜欢，3：取消不喜欢',
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
  COLLATE = utf8mb4_general_ci COMMENT = '视频的点赞信息表'
  ROW_FORMAT = Dynamic;


CREATE TABLE user_video_with_tag (
    `id` BIGINT AUTO_INCREMENT PRIMARY KEY,
    `video_id` VARCHAR(36) DEFAULT '' COMMENT '视频ID',
    `tag_id` VARCHAR(36) DEFAULT '' COMMENT '话题（标签）ID',
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
  COLLATE = utf8mb4_general_ci COMMENT = '视频与话题（标签）信息的关联表'
  ROW_FORMAT = Dynamic;

-- 视频相关Start
server {
    listen 8000;
    server_name 8.134.183.234;

    location /public/video/ {
        root /apps/home/video;
        try_files $uri $uri/ =404;
    }

    location /public/ {
        root /apps/home/video;
        try_files $uri $uri/ =404;
    }

    location / {
        proxy_pass http://8.134.183.234:8080;
        proxy_set_header Host $host;
        proxy_set_header X-Real-IP $remote_addr;
        proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
        proxy_set_header X-Forwarded-Proto $scheme;
    }
}
