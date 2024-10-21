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
import com.kit.video.domain.UserVideoLikeInfo;
import com.kit.video.service.manager.IUserVideoLikeInfoService;
import com.kit.common.utils.poi.ExcelUtil;
import com.kit.common.core.page.TableDataInfo;

/**
 * 视频的点赞信息Controller
 *
 * @author xjh
 * @date 2024-10-20
 */
@RestController
@RequestMapping("/video/like")
public class UserVideoLikeInfoController extends BaseController {
    @Autowired
    private IUserVideoLikeInfoService userVideoLikeInfoService;

/**
 * 查询视频的点赞信息列表
 */
@PreAuthorize("@ss.hasPermi('video:like:list')")
@GetMapping("/list")
    public TableDataInfo list(UserVideoLikeInfo userVideoLikeInfo) {
        startPage();
        List<UserVideoLikeInfo> list = userVideoLikeInfoService.selectUserVideoLikeInfoList(userVideoLikeInfo);
        return getDataTable(list);
    }

    /**
     * 导出视频的点赞信息列表
     */
    @PreAuthorize("@ss.hasPermi('video:like:export')")
    @Log(title = "视频的点赞信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, UserVideoLikeInfo userVideoLikeInfo) {
        List<UserVideoLikeInfo> list = userVideoLikeInfoService.selectUserVideoLikeInfoList(userVideoLikeInfo);
        ExcelUtil<UserVideoLikeInfo> util = new ExcelUtil<UserVideoLikeInfo>(UserVideoLikeInfo. class);
        util.exportExcel(response, list, "视频的点赞信息数据");
    }

    /**
     * 获取视频的点赞信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('video:like:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id) {
        return success(userVideoLikeInfoService.selectUserVideoLikeInfoById(id));
    }

    /**
     * 新增视频的点赞信息
     */
    @PreAuthorize("@ss.hasPermi('video:like:add')")
    @Log(title = "视频的点赞信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody UserVideoLikeInfo userVideoLikeInfo) {
        return toAjax(userVideoLikeInfoService.insertUserVideoLikeInfo(userVideoLikeInfo));
    }

    /**
     * 修改视频的点赞信息
     */
    @PreAuthorize("@ss.hasPermi('video:like:edit')")
    @Log(title = "视频的点赞信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody UserVideoLikeInfo userVideoLikeInfo) {
        return toAjax(userVideoLikeInfoService.updateUserVideoLikeInfo(userVideoLikeInfo));
    }

    /**
     * 删除视频的点赞信息
     */
    @PreAuthorize("@ss.hasPermi('video:like:remove')")
    @Log(title = "视频的点赞信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids) {
        return toAjax(userVideoLikeInfoService.deleteUserVideoLikeInfoByIds(ids));
    }
}
