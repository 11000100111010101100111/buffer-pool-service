package com.kit.video.domain;

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
public class UserVideoDataInfo extends BaseEntity
        {
private static final long serialVersionUID=1L;

    /** $column.columnComment */
    private Long id;

    /** 视频ID */
            @Excel(name = "视频ID")
    private String videoId;

    /** 点赞次数 */
            @Excel(name = "点赞次数")
    private Long likeTimes;

    /** 视频的不喜欢数 */
            @Excel(name = "视频的不喜欢数")
    private Long dislikeTimes;

    /** 视频的浏览量 */
            @Excel(name = "视频的浏览量")
    private Long browseTimes;

    /** 视频的评论数 */
            @Excel(name = "视频的评论数")
    private Long commentTimes;

    /** 视频的收藏数 */
            @Excel(name = "视频的收藏数")
    private Long collectTimes;

    /** 视频的转发数 */
            @Excel(name = "视频的转发数")
    private Long forwardTimes;

    /** 视频的举报数 */
            @Excel(name = "视频的举报数")
    private Long reportTimes;

    /** 视频的描述 */
            @Excel(name = "视频的描述")
    private String description;

    /** 视频作者ID */
            @Excel(name = "视频作者ID")
    private String belong;

    /** 视频审核状态，审核通过其他人才能可见/可以被分享，0：待审核，1：审核中，2审核通过，3审核不通过，4：视频已删除 */
            @Excel(name = "视频审核状态，审核通过其他人才能可见/可以被分享，0：待审核，1：审核中，2审核通过，3审核不通过，4：视频已删除")
    private String examineStatus;

    /** 视频审核意见，200字内 */
            @Excel(name = "视频审核意见，200字内")
    private String examineMessage;

    /** 视频审核人 */
            @Excel(name = "视频审核人")
    private String examineUser;

    /** 是否删除标记, 1:已删除，0：未删除 */
            @Excel(name = "是否删除标记, 1:已删除，0：未删除")
    private String delete;

    /** 状态，1:启用，0：停用 */
            @Excel(name = "状态，1:启用，0：停用")
    private String status;

    public void setId(Long id)
            {
            this.id = id;
            }

    public Long getId()
            {
            return id;
            }
    public void setVideoId(String videoId)
            {
            this.videoId = videoId;
            }

    public String getVideoId()
            {
            return videoId;
            }
    public void setLikeTimes(Long likeTimes)
            {
            this.likeTimes = likeTimes;
            }

    public Long getLikeTimes()
            {
            return likeTimes;
            }
    public void setDislikeTimes(Long dislikeTimes)
            {
            this.dislikeTimes = dislikeTimes;
            }

    public Long getDislikeTimes()
            {
            return dislikeTimes;
            }
    public void setBrowseTimes(Long browseTimes)
            {
            this.browseTimes = browseTimes;
            }

    public Long getBrowseTimes()
            {
            return browseTimes;
            }
    public void setCommentTimes(Long commentTimes)
            {
            this.commentTimes = commentTimes;
            }

    public Long getCommentTimes()
            {
            return commentTimes;
            }
    public void setCollectTimes(Long collectTimes)
            {
            this.collectTimes = collectTimes;
            }

    public Long getCollectTimes()
            {
            return collectTimes;
            }
    public void setForwardTimes(Long forwardTimes)
            {
            this.forwardTimes = forwardTimes;
            }

    public Long getForwardTimes()
            {
            return forwardTimes;
            }
    public void setReportTimes(Long reportTimes)
            {
            this.reportTimes = reportTimes;
            }

    public Long getReportTimes()
            {
            return reportTimes;
            }
    public void setDescription(String description)
            {
            this.description = description;
            }

    public String getDescription()
            {
            return description;
            }
    public void setBelong(String belong)
            {
            this.belong = belong;
            }

    public String getBelong()
            {
            return belong;
            }
    public void setExamineStatus(String examineStatus)
            {
            this.examineStatus = examineStatus;
            }

    public String getExamineStatus()
            {
            return examineStatus;
            }
    public void setExamineMessage(String examineMessage)
            {
            this.examineMessage = examineMessage;
            }

    public String getExamineMessage()
            {
            return examineMessage;
            }
    public void setExamineUser(String examineUser)
            {
            this.examineUser = examineUser;
            }

    public String getExamineUser()
            {
            return examineUser;
            }
    public void setDelete(String delete)
            {
            this.delete = delete;
            }

    public String getDelete()
            {
            return delete;
            }
    public void setStatus(String status)
            {
            this.status = status;
            }

    public String getStatus()
            {
            return status;
            }

@Override
public String toString(){
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id",getId())
            .append("videoId",getVideoId())
            .append("likeTimes",getLikeTimes())
            .append("dislikeTimes",getDislikeTimes())
            .append("browseTimes",getBrowseTimes())
            .append("commentTimes",getCommentTimes())
            .append("collectTimes",getCollectTimes())
            .append("forwardTimes",getForwardTimes())
            .append("reportTimes",getReportTimes())
            .append("description",getDescription())
            .append("belong",getBelong())
            .append("examineStatus",getExamineStatus())
            .append("examineMessage",getExamineMessage())
            .append("examineUser",getExamineUser())
            .append("createBy",getCreateBy())
            .append("createTime",getCreateTime())
            .append("delete",getDelete())
            .append("updateTime",getUpdateTime())
            .append("updateBy",getUpdateBy())
            .append("remark",getRemark())
            .append("status",getStatus())
        .toString();
        }
        }
