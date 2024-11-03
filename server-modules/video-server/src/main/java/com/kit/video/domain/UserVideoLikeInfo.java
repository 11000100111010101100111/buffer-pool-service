package com.kit.video.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.kit.common.annotation.Excel;
import com.kit.common.core.domain.BaseEntity;

/**
 * 视频的点赞信息对象 user_video_like_info
 *
 * @author xjh
 * @date 2024-10-20
 */
public class UserVideoLikeInfo extends BaseEntity
        {
private static final long serialVersionUID=1L;

    /** $column.columnComment */
    private String id;

    /** 视频ID */
            @Excel(name = "视频ID")
    private String videoId;

    /** 点赞/取消点赞人ID */
            @Excel(name = "点赞/取消点赞人ID")
    private String likeUserId;

    /** 点赞/取消点赞的对象，0：视频，1：评论，3：用户 */
            @Excel(name = "点赞/取消点赞的对象，0：视频，1：评论，3：用户")
    private String likeItem;

    /** 0:点赞，1：取消点赞，2：不喜欢，3：取消不喜欢 */
            @Excel(name = "0:点赞，1：取消点赞，2：不喜欢，3：取消不喜欢")
    private String likeType;

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
    public void setLikeUserId(String likeUserId)
            {
            this.likeUserId = likeUserId;
            }

    public String getLikeUserId()
            {
            return likeUserId;
            }
    public void setLikeItem(String likeItem)
            {
            this.likeItem = likeItem;
            }

    public String getLikeItem()
            {
            return likeItem;
            }
    public void setLikeType(String likeType)
            {
            this.likeType = likeType;
            }

    public String getLikeType()
            {
            return likeType;
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
            .append("likeUserId",getLikeUserId())
            .append("likeItem",getLikeItem())
            .append("likeType",getLikeType())
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
