package com.kit.video.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.kit.common.annotation.Excel;
import com.kit.common.core.domain.BaseEntity;

/**
 * 视频与话题（标签）信息的关联对象 user_video_with_tag
 *
 * @author xjh
 * @date 2024-10-20
 */
public class UserVideoWithTag extends BaseEntity
        {
private static final long serialVersionUID=1L;

    /** $column.columnComment */
    private Long id;

    /** 视频ID */
            @Excel(name = "视频ID")
    private String videoId;

    /** 话题（标签）ID */
            @Excel(name = "话题", readConverterExp = "标=签")
    private String tagId;

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
    public void setTagId(String tagId)
            {
            this.tagId = tagId;
            }

    public String getTagId()
            {
            return tagId;
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
            .append("tagId",getTagId())
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
