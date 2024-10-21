package com.kit.video.controller.open;

import com.kit.common.core.controller.BaseController;
import com.kit.common.core.domain.AjaxResult;
import com.kit.video.service.open.OpenUserVideoDataInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    private OpenUserVideoDataInfoService videoDataInfoService;
    

    public AjaxResult query() {

        return success();
    }
}
