package com.kit.video.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.kit.common.annotation.Excel;
import com.kit.common.core.domain.BaseEntity;

/**
 * 视频的评论信息对象 user_video_comment_info
 *
 * @author xjh
 * @date 2024-10-20
 */
public class UserVideoCommentInfo extends BaseEntity
        {
private static final long serialVersionUID=1L;

    /** $column.columnComment */
    private String id;

    /** 视频ID */
            @Excel(name = "视频ID")
    private String videoId;

    /** 评论人ID */
            @Excel(name = "评论人ID")
    private String commentUserId;

    /** 父评论ID */
            @Excel(name = "父评论ID")
    private String fatherCommentId;

    /** 评论的内容 */
            @Excel(name = "评论的内容")
    private String comment;

    /** 评论的点赞数 */
            @Excel(name = "评论的点赞数")
    private Long likeTimes;

    /** 评论的不喜欢数 */
            @Excel(name = "评论的不喜欢数")
    private Long dislikeTimes;

    /** 评论的举报数 */
            @Excel(name = "评论的举报数")
    private Long reportTimes;

    /** 是否删除标记, 1:已删除，0：未删除 */
            @Excel(name = "是否删除标记, 1:已删除，0：未删除")
    private String delete;

    /** 状态，1:启用，0：停用 */
            @Excel(name = "状态，1:启用，0：停用")
    private String status;

    public void setId(String id)
            {
            this.id = id;
            }

    public String getId()
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
    public void setCommentUserId(String commentUserId)
            {
            this.commentUserId = commentUserId;
            }

    public String getCommentUserId()
            {
            return commentUserId;
            }
    public void setFatherCommentId(String fatherCommentId)
            {
            this.fatherCommentId = fatherCommentId;
            }

    public String getFatherCommentId()
            {
            return fatherCommentId;
            }
    public void setComment(String comment)
            {
            this.comment = comment;
            }

    public String getComment()
            {
            return comment;
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
    public void setReportTimes(Long reportTimes)
            {
            this.reportTimes = reportTimes;
            }

    public Long getReportTimes()
            {
            return reportTimes;
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
            .append("commentUserId",getCommentUserId())
            .append("fatherCommentId",getFatherCommentId())
            .append("comment",getComment())
            .append("likeTimes",getLikeTimes())
            .append("dislikeTimes",getDislikeTimes())
            .append("reportTimes",getReportTimes())
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
