package com.kit.video.controller.manager;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.kit.common.annotation.Log;
import com.kit.common.core.controller.BaseController;
import com.kit.common.core.domain.AjaxResult;
import com.kit.common.enums.BusinessType;
import com.kit.video.domain.VideoUserVideoCollectInfo;
import com.kit.video.service.manager.IVideoUserVideoCollectInfoService;
import com.kit.common.utils.poi.ExcelUtil;
import com.kit.common.core.page.TableDataInfo;

/**
 * 视频的被收藏信息Controller
 *
 * @author xjh
 * @date 2024-10-20
 */
@RestController
@RequestMapping("/video/collect")
public class VideoUserVideoCollectInfoController extends BaseController {
    @Autowired
    private IVideoUserVideoCollectInfoService videoUserVideoCollectInfoService;

/**
 * 查询视频的被收藏信息列表
 */
@PreAuthorize("@ss.hasPermi('video:collect:list')")
@GetMapping("/list")
    public TableDataInfo list(VideoUserVideoCollectInfo videoUserVideoCollectInfo) {
        startPage();
        List<VideoUserVideoCollectInfo> list = videoUserVideoCollectInfoService.selectVideoUserVideoCollectInfoList(videoUserVideoCollectInfo);
        return getDataTable(list);
    }

    /**
     * 导出视频的被收藏信息列表
     */
    @PreAuthorize("@ss.hasPermi('video:collect:export')")
    @Log(title = "视频的被收藏信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, VideoUserVideoCollectInfo videoUserVideoCollectInfo) {
        List<VideoUserVideoCollectInfo> list = videoUserVideoCollectInfoService.selectVideoUserVideoCollectInfoList(videoUserVideoCollectInfo);
        ExcelUtil<VideoUserVideoCollectInfo> util = new ExcelUtil<VideoUserVideoCollectInfo>(VideoUserVideoCollectInfo. class);
        util.exportExcel(response, list, "视频的被收藏信息数据");
    }

    /**
     * 获取视频的被收藏信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('video:collect:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id) {
        return success(videoUserVideoCollectInfoService.selectVideoUserVideoCollectInfoById(id));
    }

    /**
     * 新增视频的被收藏信息
     */
    @PreAuthorize("@ss.hasPermi('video:collect:add')")
    @Log(title = "视频的被收藏信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody VideoUserVideoCollectInfo videoUserVideoCollectInfo) {
        return toAjax(videoUserVideoCollectInfoService.insertVideoUserVideoCollectInfo(videoUserVideoCollectInfo));
    }

    /**
     * 修改视频的被收藏信息
     */
    @PreAuthorize("@ss.hasPermi('video:collect:edit')")
    @Log(title = "视频的被收藏信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody VideoUserVideoCollectInfo videoUserVideoCollectInfo) {
        return toAjax(videoUserVideoCollectInfoService.updateVideoUserVideoCollectInfo(videoUserVideoCollectInfo));
    }

    /**
     * 删除视频的被收藏信息
     */
    @PreAuthorize("@ss.hasPermi('video:collect:remove')")
    @Log(title = "视频的被收藏信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids) {
        return toAjax(videoUserVideoCollectInfoService.deleteVideoUserVideoCollectInfoByIds(ids));
    }
}
