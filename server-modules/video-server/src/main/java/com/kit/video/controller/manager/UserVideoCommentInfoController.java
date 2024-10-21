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
import com.kit.video.domain.UserVideoCommentInfo;
import com.kit.video.service.manager.IUserVideoCommentInfoService;
import com.kit.common.utils.poi.ExcelUtil;
import com.kit.common.core.page.TableDataInfo;

/**
 * 视频的评论信息Controller
 *
 * @author xjh
 * @date 2024-10-20
 */
@RestController
@RequestMapping("/video/comment")
public class UserVideoCommentInfoController extends BaseController {
    @Autowired
    private IUserVideoCommentInfoService userVideoCommentInfoService;

/**
 * 查询视频的评论信息列表
 */
@PreAuthorize("@ss.hasPermi('video:comment:list')")
@GetMapping("/list")
    public TableDataInfo list(UserVideoCommentInfo userVideoCommentInfo) {
        startPage();
        List<UserVideoCommentInfo> list = userVideoCommentInfoService.selectUserVideoCommentInfoList(userVideoCommentInfo);
        return getDataTable(list);
    }

    /**
     * 导出视频的评论信息列表
     */
    @PreAuthorize("@ss.hasPermi('video:comment:export')")
    @Log(title = "视频的评论信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, UserVideoCommentInfo userVideoCommentInfo) {
        List<UserVideoCommentInfo> list = userVideoCommentInfoService.selectUserVideoCommentInfoList(userVideoCommentInfo);
        ExcelUtil<UserVideoCommentInfo> util = new ExcelUtil<UserVideoCommentInfo>(UserVideoCommentInfo. class);
        util.exportExcel(response, list, "视频的评论信息数据");
    }

    /**
     * 获取视频的评论信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('video:comment:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id) {
        return success(userVideoCommentInfoService.selectUserVideoCommentInfoById(id));
    }

    /**
     * 新增视频的评论信息
     */
    @PreAuthorize("@ss.hasPermi('video:comment:add')")
    @Log(title = "视频的评论信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody UserVideoCommentInfo userVideoCommentInfo) {
        return toAjax(userVideoCommentInfoService.insertUserVideoCommentInfo(userVideoCommentInfo));
    }

    /**
     * 修改视频的评论信息
     */
    @PreAuthorize("@ss.hasPermi('video:comment:edit')")
    @Log(title = "视频的评论信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody UserVideoCommentInfo userVideoCommentInfo) {
        return toAjax(userVideoCommentInfoService.updateUserVideoCommentInfo(userVideoCommentInfo));
    }

    /**
     * 删除视频的评论信息
     */
    @PreAuthorize("@ss.hasPermi('video:comment:remove')")
    @Log(title = "视频的评论信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids) {
        return toAjax(userVideoCommentInfoService.deleteUserVideoCommentInfoByIds(ids));
    }
}
