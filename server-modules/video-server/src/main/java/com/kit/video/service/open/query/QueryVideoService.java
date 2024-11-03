package com.kit.video.service.open.query;

import com.kit.video.domain.bo.VideoQueryBaseBo;
import com.kit.video.domain.vo.open.QueryVideoInfoVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class QueryVideoService {

    public List<QueryVideoInfoVo> query(VideoQueryBaseBo queryBo) {
        return VideoQueryStage.doStage(queryBo);
    }
}
