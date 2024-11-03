package com.kit.video.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.kit.common.annotation.Excel;
import com.kit.common.core.domain.BaseEntity;

/**
 * 视频产生的相关数据对象 user_video_data_info
 *
 * @author xjh
 * @date 2024-10-20
 */
@Data
@TableName("user_video_data_info")
public class UserVideoDataInfo extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 视频ID
     */
    @TableId("video_id")
    @Excel(name = "视频ID")
    private String videoId;

    /**
     * 点赞次数
     */
    @TableField("like_times")
    @Excel(name = "点赞次数")
    private Integer likeTimes;

    /**
     * 视频的不喜欢数
     */
    @TableField("dislike_times")
    @Excel(name = "视频的不喜欢数")
    private Integer dislikeTimes;

    /**
     * 视频的浏览量
     */
    @TableField("browse_times")
    @Excel(name = "视频的浏览量")
    private Integer browseTimes;

    /**
     * 视频的评论数
     */
    @TableField("comment_times")
    @Excel(name = "视频的评论数")
    private Integer commentTimes;

    /**
     * 视频的收藏数
     */
    @TableField("collect_times")
    @Excel(name = "视频的收藏数")
    private Integer collectTimes;

    /**
     * 视频的转发数
     */
    @TableField("forward_times")
    @Excel(name = "视频的转发数")
    private Integer forwardTimes;

    /**
     * 视频的举报数
     */
    @TableField("report_times")
    @Excel(name = "视频的举报数")
    private Integer reportTimes;

    /**
     * 视频的描述
     */
    @TableField("title")
    @Excel(name = "视频的标题")
    private String title;

    /**
     * 视频的描述
     */
    @TableField("description")
    @Excel(name = "视频的描述")
    private String description;

    /**
     * 发布视频的地址信息
     */
    @TableField("location")
    @Excel(name = "发布视频的地址信息")
    private String location;

    /**
     * 视频作者ID
     */
    @TableField("belong")
    @Excel(name = "视频作者ID")
    private String belong;

    /**
     * 视频审核状态，审核通过其他人才能可见/可以被分享，0：待审核，1：审核中，2审核通过，3审核不通过，4：视频已删除
     */
    @TableField("examine_status")
    @Excel(name = "视频审核状态，审核通过其他人才能可见/可以被分享，0：待审核，1：审核中，2审核通过，3审核不通过，4：视频已删除")
    private String examineStatus;

    /**
     * 视频审核意见，200字内
     */
    @TableField("examine_message")
    @Excel(name = "视频审核意见，200字内")
    private String examineMessage;

    /**
     * 视频审核人
     */
    @TableField("examine_user")
    @Excel(name = "视频审核人")
    private String examineUser;

    /**
     * 是否删除标记, 1:已删除，0：未删除
     */
    @TableField("delete")
    @Excel(name = "是否删除标记, 1:已删除，0：未删除")
    private String delete;

    /**
     * 状态，1:启用，0：停用
     */
    @TableField("status")
    @Excel(name = "状态，1:启用，0：停用")
    private String status;

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }

    public String getVideoId() {
        return videoId;
    }

    public void setLikeTimes(Integer likeTimes) {
        this.likeTimes = likeTimes;
    }

    public Integer getLikeTimes() {
        return likeTimes;
    }

    public void setDislikeTimes(Integer dislikeTimes) {
        this.dislikeTimes = dislikeTimes;
    }

    public Integer getDislikeTimes() {
        return dislikeTimes;
    }

    public void setBrowseTimes(Integer browseTimes) {
        this.browseTimes = browseTimes;
    }

    public Integer getBrowseTimes() {
        return browseTimes;
    }

    public void setCommentTimes(Integer commentTimes) {
        this.commentTimes = commentTimes;
    }

    public Integer getCommentTimes() {
        return commentTimes;
    }

    public void setCollectTimes(Integer collectTimes) {
        this.collectTimes = collectTimes;
    }

    public Integer getCollectTimes() {
        return collectTimes;
    }

    public void setForwardTimes(Integer forwardTimes) {
        this.forwardTimes = forwardTimes;
    }

    public Integer getForwardTimes() {
        return forwardTimes;
    }

    public void setReportTimes(Integer reportTimes) {
        this.reportTimes = reportTimes;
    }

    public Integer getReportTimes() {
        return reportTimes;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setBelong(String belong) {
        this.belong = belong;
    }

    public String getBelong() {
        return belong;
    }

    public void setExamineStatus(String examineStatus) {
        this.examineStatus = examineStatus;
    }

    public String getExamineStatus() {
        return examineStatus;
    }

    public void setExamineMessage(String examineMessage) {
        this.examineMessage = examineMessage;
    }

    public String getExamineMessage() {
        return examineMessage;
    }

    public void setExamineUser(String examineUser) {
        this.examineUser = examineUser;
    }

    public String getExamineUser() {
        return examineUser;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("videoId", getVideoId())
                .append("likeTimes", getLikeTimes())
                .append("dislikeTimes", getDislikeTimes())
                .append("browseTimes", getBrowseTimes())
                .append("commentTimes", getCommentTimes())
                .append("collectTimes", getCollectTimes())
                .append("forwardTimes", getForwardTimes())
                .append("reportTimes", getReportTimes())
                .append("title", getTitle())
                .append("description", getDescription())
                .append("belong", getBelong())
                .append("examineStatus", getExamineStatus())
                .append("examineMessage", getExamineMessage())
                .append("examineUser", getExamineUser())
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
