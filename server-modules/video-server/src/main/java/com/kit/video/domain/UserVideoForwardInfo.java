package com.kit.video.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.kit.common.annotation.Excel;
import com.kit.common.core.domain.BaseEntity;

/**
 * 视频的转发信息对象 user_video_forward_info
 *
 * @author xjh
 * @date 2024-10-20
 */
public class UserVideoForwardInfo extends BaseEntity
        {
private static final long serialVersionUID=1L;

    /** $column.columnComment */
    private String id;

    /** 视频ID */
            @Excel(name = "视频ID")
    private String videoId;

    /** 转发人ID */
            @Excel(name = "转发人ID")
    private String forwardUserId;

    /** 被转发人ID */
            @Excel(name = "被转发人ID")
    private String forwardToUserId;

    /** 转发的类型，0:平台内转发，1：微信，2：qq, 3:其他 */
            @Excel(name = "转发的类型，0:平台内转发，1：微信，2：qq, 3:其他")
    private String forwardTyp;

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
    public void setForwardUserId(String forwardUserId)
            {
            this.forwardUserId = forwardUserId;
            }

    public String getForwardUserId()
            {
            return forwardUserId;
            }
    public void setForwardToUserId(String forwardToUserId)
            {
            this.forwardToUserId = forwardToUserId;
            }

    public String getForwardToUserId()
            {
            return forwardToUserId;
            }
    public void setForwardTyp(String forwardTyp)
            {
            this.forwardTyp = forwardTyp;
            }

    public String getForwardTyp()
            {
            return forwardTyp;
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
            .append("forwardUserId",getForwardUserId())
            .append("forwardToUserId",getForwardToUserId())
            .append("forwardTyp",getForwardTyp())
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
