package com.kit.video.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.kit.common.annotation.Excel;
import com.kit.common.core.domain.BaseEntity;
import lombok.Data;

import java.util.Date;

@Data
@TableName("user_video_block_info")
public class UserVideoBlockInfo extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * $column.columnComment
     */
    @TableId
    private String id;

    /**
     * 视频ID
     */
    @Excel(name = "视频ID")
    @TableField("video_id")
    private String videoId;

    /**
     * 资源路径
     */
    @Excel(name = "资源路径")
    private String src;

    /**
     * 上传到服务器的分块路径
     */
    @Excel(name = "0上传到服务器的分块路径")
    @TableField("local_dir")
    private String localDir;

    /**
     * 视频块排序号
     */
    @Excel(name = "视频块排序号")
    @TableField("block_index")
    private Integer blockIndex;

    /**
     * 视频类型
     */
    @Excel(name = "视频类型")
    @TableField("file_type")
    private String fileType;

    /**
     * 是否删除标记, 1:已删除，0：未删除
     */
    @Excel(name = "是否删除标记, 1:已删除，0：未删除")
    private String delete;

    /**
     * 状态，1:启用，0：停用
     */
    @Excel(name = "状态，1:启用，0：停用")
    private String status;
}
