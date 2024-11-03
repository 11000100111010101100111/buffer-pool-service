package com.kit.video.service.open.query;

import com.kit.video.domain.bo.VideoQueryBaseBo;
import com.kit.video.domain.vo.open.QueryVideoInfoVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 从热搜中获取视频列表的方式
 *
 * */
@Service
@Slf4j
public class QueryByHotList implements VideoQueryStage {
    @Override
    public List<QueryVideoInfoVo> query(VideoQueryBaseBo queryBo) {
        return null;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        STAGE.put(Type.HOT_LIST, this);
    }
}
