package com.kit.video.domain;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.kit.common.annotation.Excel;
import com.kit.common.core.domain.BaseEntity;

/**
 * 视频的浏览信息对象 user_video_browse_info
 *
 * @author xjh
 * @date 2024-10-20
 */
public class UserVideoBrowseInfo extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * $column.columnComment
     */
    private String id;

    /**
     * 视频ID
     */
    @Excel(name = "视频ID")
    private String videoId;

    /**
     * 浏览人ID
     */
    @Excel(name = "浏览人ID")
    private String browseUserId;

    /**
     * 0:推荐页，1：主页，2：分享，3：搜索
     */
    @Excel(name = "0:推荐页，1：主页，2：分享，3：搜索")
    private String browseType;

    /**
     * 视频的单次播放时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "视频的单次播放时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date browseTime;

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

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }

    public String getVideoId() {
        return videoId;
    }

    public void setBrowseUserId(String browseUserId) {
        this.browseUserId = browseUserId;
    }

    public String getBrowseUserId() {
        return browseUserId;
    }

    public void setBrowseType(String browseType) {
        this.browseType = browseType;
    }

    public String getBrowseType() {
        return browseType;
    }

    public void setBrowseTime(Date browseTime) {
        this.browseTime = browseTime;
    }

    public Date getBrowseTime() {
        return browseTime;
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
                .append("browseUserId", getBrowseUserId())
                .append("browseType", getBrowseType())
                .append("browseTime", getBrowseTime())
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
