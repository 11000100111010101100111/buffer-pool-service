package com.kit.video.service.open.query;

import com.kit.common.utils.StringUtils;
import com.kit.video.domain.bo.VideoQueryBaseBo;
import com.kit.video.domain.vo.open.QueryVideoInfoVo;
import org.springframework.beans.factory.InitializingBean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface VideoQueryStage extends InitializingBean {
    public static final Map<Type, VideoQueryStage> STAGE = new HashMap<>();

    public static List<QueryVideoInfoVo> doStage(VideoQueryBaseBo queryBo) {
        final Type type = Type.byTypeName(queryBo.getType());
        final VideoQueryStage videoQueryStage = STAGE.get(type);
        return videoQueryStage.query(queryBo);
    }

    List<QueryVideoInfoVo> query(VideoQueryBaseBo queryBo);

    public static enum Type {
        RECOMMENDED("recommended", "推荐"),
        HOT_LIST("hot_list", "热榜"),
        ;
        String typeName;
        String mark;
        Type(String typeName, String mark) {
            this.typeName = typeName;
            this.mark = mark;
        }

        public static Type byTypeName(String typeName) {
            if (StringUtils.isEmpty(typeName)) {
                return RECOMMENDED;
            }
            for (Type value : values()) {
                if (value.typeName.equals(typeName)) {
                    return value;
                }
            }
            return RECOMMENDED;
        }
    }
}
