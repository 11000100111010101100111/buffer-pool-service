package com.kit.video.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.kit.common.annotation.Excel;
import com.kit.common.core.domain.BaseEntity;

/**
 * 视频的被收藏信息对象 user_video_collect_info
 *
 * @author xjh
 * @date 2024-10-20
 */
public class VideoUserVideoCollectInfo extends BaseEntity
        {
private static final long serialVersionUID=1L;

    /** $column.columnComment */
    private String id;

    /** 视频ID */
            @Excel(name = "视频ID")
    private String videoId;

    /** 收藏人ID */
            @Excel(name = "收藏人ID")
    private String collectUserId;

    /** 1:收藏，0：取消收藏 */
            @Excel(name = "1:收藏，0：取消收藏")
    private String collectType;

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
    public void setCollectUserId(String collectUserId)
            {
            this.collectUserId = collectUserId;
            }

    public String getCollectUserId()
            {
            return collectUserId;
            }
    public void setCollectType(String collectType)
            {
            this.collectType = collectType;
            }

    public String getCollectType()
            {
            return collectType;
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
            .append("collectUserId",getCollectUserId())
            .append("collectType",getCollectType())
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
