package com.kit.video.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.kit.common.annotation.Excel;
import com.kit.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 视频与话题（标签）信息的关联对象 user_video_with_tag
 *
 * @author xjh
 * @date 2024-10-20
 */
@TableName("user_video_with_tag")
public class UserVideoWithTag extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * $column.columnComment
     */
    @TableId
    private Long id;

    /**
     * 视频ID
     */
    @Excel(name = "视频ID")
    @TableField("video_id")
    private String videoId;

    /**
     * 话题（标签）ID
     */
    @Excel(name = "话题", readConverterExp = "标=签")
    @TableField("tag_id")
    private Long tagId;

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

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }

    public String getVideoId() {
        return videoId;
    }

    public void setTagId(Long tagId) {
        this.tagId = tagId;
    }

    public Long getTagId() {
        return tagId;
    }

    public void setDelete(String delete) {
        this.delete = delete;
    }

    public String getDelete() {
        return delete;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("videoId", getVideoId())
                .append("tagId", getTagId())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("delete", getDelete())
                .append("updateTime", getUpdateTime())
                .append("updateBy", getUpdateBy())
                .append("remark", getRemark())
                .append("status", getStatus())
                .toString();
    }
}
