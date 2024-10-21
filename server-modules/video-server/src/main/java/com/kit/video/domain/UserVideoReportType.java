package com.kit.video.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.kit.common.annotation.Excel;
import com.kit.common.core.domain.BaseEntity;

/**
 * 举报类型信息对象 user_report_type
 *
 * @author xjh
 * @date 2024-10-20
 */
public class UserVideoReportType extends BaseEntity
        {
private static final long serialVersionUID=1L;

    /** $column.columnComment */
    private String id;

    /** 举报父类型，一般就两层 */
            @Excel(name = "举报父类型，一般就两层")
    private String fatherType;

    /** 举报类型 */
            @Excel(name = "举报类型")
    private String type;

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
    public void setFatherType(String fatherType)
            {
            this.fatherType = fatherType;
            }

    public String getFatherType()
            {
            return fatherType;
            }
    public void setType(String type)
            {
            this.type = type;
            }

    public String getType()
            {
            return type;
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
            .append("fatherType",getFatherType())
            .append("type",getType())
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
