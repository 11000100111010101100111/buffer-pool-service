package com.kit.video.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.kit.common.annotation.Excel;
import com.kit.common.core.domain.BaseEntity;

/**
 * 视频相关的话题（标签）信息对象 user_video_tag
 *
 * @author xjh
 * @date 2024-10-20
 */
public class UserVideoTag extends BaseEntity
        {
private static final long serialVersionUID=1L;

    /** $column.columnComment */
    private String id;

    /** 话题（标签）名称 */
            @Excel(name = "话题", readConverterExp = "标=签")
    private String name;

    /** 话题（标签）被使用次数 */
            @Excel(name = "话题", readConverterExp = "标=签")
    private Long useTimes;

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
    public void setName(String name)
            {
            this.name = name;
            }

    public String getName()
            {
            return name;
            }
    public void setUseTimes(Long useTimes)
            {
            this.useTimes = useTimes;
            }

    public Long getUseTimes()
            {
            return useTimes;
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
            .append("name",getName())
            .append("useTimes",getUseTimes())
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
