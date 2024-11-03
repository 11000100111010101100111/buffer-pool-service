package com.kit.video.controller.open;

import com.kit.common.annotation.RateLimiter;
import com.kit.common.core.controller.BaseController;
import com.kit.common.core.domain.AjaxResult;
import com.kit.common.enums.LimitType;
import com.kit.video.domain.bo.VideoQueryBaseBo;
import com.kit.video.service.open.query.QueryVideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * OPEN-api 视频的浏览信息Controller
 *
 * @author xjh
 * @date 2024-10-20
 */
@RestController
@RequestMapping("/video/open-api/bowers")
public class VideoQueryController extends BaseController {
    @Autowired
    private QueryVideoService queryVideoService;

    @GetMapping("videos")
    @RateLimiter(key = "#ip", count = 180, time = 60, limitType = LimitType.IP)
    public AjaxResult queryByRecommended(
            @RequestParam("filterIds") String filterIdsStr,
            @RequestParam("count") Integer count) {
        VideoQueryBaseBo queryBo = new VideoQueryBaseBo();
        if (null != filterIdsStr) {
            final String[] split = filterIdsStr.split(",");
            if (split.length > 0) {
                List<String> ids = new ArrayList<>(split.length);
                ids.addAll(Arrays.asList(split));
                queryBo.setFilterVideoIds(ids);
            }
        }
        if (null == count || count < 0 || count > 20) {
            count = 10;
        }
        queryBo.setCount(count);
        //
        return success(queryVideoService.query(queryBo));
    }
}
