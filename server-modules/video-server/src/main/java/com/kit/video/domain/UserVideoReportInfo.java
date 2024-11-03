package com.kit.video.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.kit.common.annotation.Excel;
import com.kit.common.core.domain.BaseEntity;

/**
 * 视频的举报信息对象 user_video_report_info
 *
 * @author xjh
 * @date 2024-10-20
 */
public class UserVideoReportInfo extends BaseEntity
        {
private static final long serialVersionUID=1L;

    /** $column.columnComment */
    private String id;

    /** 视频ID */
            @Excel(name = "视频ID")
    private String videoId;

    /** report人ID */
            @Excel(name = "report人ID")
    private String reportUserId;

    /** 举报对象，1：视频，2：评论，3：用户 */
            @Excel(name = "举报对象，1：视频，2：评论，3：用户")
    private String reportItem;

    /** 举报类型ID */
            @Excel(name = "举报类型ID")
    private Long reportTypeId;

    /** 200字内的举报文字 */
            @Excel(name = "200字内的举报文字")
    private String reportMessage;

    /** 举报状态，0：已提交，1：审核中，2：通过，3：举报不成立，4：已取消 */
            @Excel(name = "举报状态，0：已提交，1：审核中，2：通过，3：举报不成立，4：已取消")
    private String reportResult;

    /** 审核意见，200字内 */
            @Excel(name = "审核意见，200字内")
    private String reportResultMessage;

    /** 审核人ID */
            @Excel(name = "审核人ID")
    private String reportResultUser;

    /** 审核时间 */
            @JsonFormat(pattern = "yyyy-MM-dd")
            @Excel(name = "审核时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date reportResultTime;

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
    public void setReportUserId(String reportUserId)
            {
            this.reportUserId = reportUserId;
            }

    public String getReportUserId()
            {
            return reportUserId;
            }
    public void setReportItem(String reportItem)
            {
            this.reportItem = reportItem;
            }

    public String getReportItem()
            {
            return reportItem;
            }
    public void setReportTypeId(Long reportTypeId)
            {
            this.reportTypeId = reportTypeId;
            }

    public Long getReportTypeId()
            {
            return reportTypeId;
            }
    public void setReportMessage(String reportMessage)
            {
            this.reportMessage = reportMessage;
            }

    public String getReportMessage()
            {
            return reportMessage;
            }
    public void setReportResult(String reportResult)
            {
            this.reportResult = reportResult;
            }

    public String getReportResult()
            {
            return reportResult;
            }
    public void setReportResultMessage(String reportResultMessage)
            {
            this.reportResultMessage = reportResultMessage;
            }

    public String getReportResultMessage()
            {
            return reportResultMessage;
            }
    public void setReportResultUser(String reportResultUser)
            {
            this.reportResultUser = reportResultUser;
            }

    public String getReportResultUser()
            {
            return reportResultUser;
            }
    public void setReportResultTime(Date reportResultTime)
            {
            this.reportResultTime = reportResultTime;
            }

    public Date getReportResultTime()
            {
            return reportResultTime;
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
            .append("reportUserId",getReportUserId())
            .append("reportItem",getReportItem())
            .append("reportTypeId",getReportTypeId())
            .append("reportMessage",getReportMessage())
            .append("reportResult",getReportResult())
            .append("reportResultMessage",getReportResultMessage())
            .append("reportResultUser",getReportResultUser())
            .append("reportResultTime",getReportResultTime())
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
