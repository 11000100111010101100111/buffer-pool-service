package com.kit.video.controller.open;

import com.alibaba.fastjson2.JSON;
import com.kit.common.core.controller.BaseController;
import com.kit.common.core.domain.AjaxResult;
import com.kit.common.core.domain.model.LoginUser;
import com.kit.video.domain.bo.VideoPublishBo;
import com.kit.video.service.open.publish.VideoPublishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * OPEN-api 视频的浏览信息Controller
 *
 * @author xjh
 * @date 2024-10-20
 */
@RestController
@RequestMapping("/video/open-api/publish")
public class VideoPublishController extends BaseController {
    @Autowired
    private VideoPublishService videoPublishService;

    @PostMapping
    public AjaxResult publish(@RequestParam("videos") List<MultipartFile> file,
                              @RequestParam("data") String data) {
        VideoPublishBo publishBo = JSON.parseObject(data, VideoPublishBo.class);
        final LoginUser loginUser = getLoginUser();
        try {
            return success(videoPublishService.publish(file, publishBo, loginUser));
        } catch (IOException e) {
            return error(e.getMessage());
        }
    }
}
