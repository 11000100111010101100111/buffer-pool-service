package com.kit.video.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.kit.common.annotation.Excel;
import com.kit.common.core.domain.BaseEntity;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 视频原始信息对象 user_video_metadata_info
 *
 * @author xjh
 * @date 2024-10-20
 */
@Data
@TableName("user_video_metadata_info")
public class UserVideoMetadataInfo extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * $column.columnComment
     */
    @TableId
    private String id;

    /**
     * 上传视频的用户ID
     */
    @Excel(name = "上传视频的用户ID")
    @TableField("user_id")
    private String userId;

    /**
     * 图片地址
     */
    @TableField("src")
    @Excel(name = "封面图片地址")
    private String src;

    /**
     * 图片上传到服务器的文件路径
     */
    @TableField("local_dir")
    @Excel(name = "图片上传到服务器的文件路径")
    private String localDir;

    /**
     * 文件是否已被系统压缩，0没有，1已压缩
     */
    @TableField("compressed")
    @Excel(name = "文件是否已被系统压缩，0没有，1已压缩")
    private String compressed;

    /**
     * 视频名称
     */
    @TableField("name")
    @Excel(name = "视频名称")
    private String name;

    /**
     * 视频类型
     */
    @TableField("file_type")
    @Excel(name = "视频类型")
    private String fileType;

    /**
     * 视频宽度
     */
    @TableField("width")
    @Excel(name = "视频宽度")
    private Long width;

    /**
     * 视频高度
     */
    @TableField("height")
    @Excel(name = "视频高度")
    private Long height;

    /**
     * 视频大小
     */
    @TableField("byte")
    @Excel(name = "视频大小")
    private Long byteCount;

    /**
     * 视频原始大小
     */
    @TableField("origin_byte")
    @Excel(name = "视频原始大小")
    private Long originByte;

    /**
     * 视频帧
     */
    @TableField("frame")
    @Excel(name = "视频帧")
    private Long frame;

    /**
     * 视频原始帧
     */
    @TableField("origin_frame")
    @Excel(name = "视频原始帧")
    private Long originFrame;

    /**
     * 视频时长
     */
    @TableField("video_duration")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "视频时长", width = 30, dateFormat = "yyyy-MM-dd")
    private Date videoDuration;

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

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public String getSrc() {
        return src;
    }

    public void setLocalDir(String localDir) {
        this.localDir = localDir;
    }

    public String getLocalDir() {
        return localDir;
    }

    public void setCompressed(String compressed) {
        this.compressed = compressed;
    }

    public String getCompressed() {
        return compressed;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getFileType() {
        return fileType;
    }

    public void setWidth(Long width) {
        this.width = width;
    }

    public Long getWidth() {
        return width;
    }

    public void setHeight(Long height) {
        this.height = height;
    }

    public Long getHeight() {
        return height;
    }

    public void setByteCount(Long byteCount) {
        this.byteCount = byteCount;
    }

    public Long getByteCount() {
        return byteCount;
    }

    public void setOriginByte(Long originByte) {
        this.originByte = originByte;
    }

    public Long getOriginByte() {
        return originByte;
    }

    public void setFrame(Long frame) {
        this.frame = frame;
    }

    public Long getFrame() {
        return frame;
    }

    public void setOriginFrame(Long originFrame) {
        this.originFrame = originFrame;
    }

    public Long getOriginFrame() {
        return originFrame;
    }

    public void setVideoDuration(Date videoDuration) {
        this.videoDuration = videoDuration;
    }

    public Date getVideoDuration() {
        return videoDuration;
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

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("userId", getUserId())
                .append("src", getSrc())
                .append("localDir", getLocalDir())
                .append("compressed", getCompressed())
                .append("name", getName())
                .append("fileType", getFileType())
                .append("width", getWidth())
                .append("height", getHeight())
                .append("byte", getByteCount())
                .append("originByte", getOriginByte())
                .append("frame", getFrame())
                .append("originFrame", getOriginFrame())
                .append("videoDuration", getVideoDuration())
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
