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
import com.kit.video.domain.UserVideoReportImgInfo;
import com.kit.video.service.manager.IUserVideoReportImgInfoService;
import com.kit.common.utils.poi.ExcelUtil;
import com.kit.common.core.page.TableDataInfo;

/**
 * 视频的举报信息Controller
 *
 * @author xjh
 * @date 2024-10-20
 */
@RestController
@RequestMapping("/video/report")
public class UserVideoReportImgInfoController extends BaseController {
    @Autowired
    private IUserVideoReportImgInfoService userVideoReportImgInfoService;

/**
 * 查询视频的举报信息列表
 */
@PreAuthorize("@ss.hasPermi('video:report:list')")
@GetMapping("/list")
    public TableDataInfo list(UserVideoReportImgInfo userVideoReportImgInfo) {
        startPage();
        List<UserVideoReportImgInfo> list = userVideoReportImgInfoService.selectUserVideoReportImgInfoList(userVideoReportImgInfo);
        return getDataTable(list);
    }

    /**
     * 导出视频的举报信息列表
     */
    @PreAuthorize("@ss.hasPermi('video:report:export')")
    @Log(title = "视频的举报信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, UserVideoReportImgInfo userVideoReportImgInfo) {
        List<UserVideoReportImgInfo> list = userVideoReportImgInfoService.selectUserVideoReportImgInfoList(userVideoReportImgInfo);
        ExcelUtil<UserVideoReportImgInfo> util = new ExcelUtil<UserVideoReportImgInfo>(UserVideoReportImgInfo. class);
        util.exportExcel(response, list, "视频的举报信息数据");
    }

    /**
     * 获取视频的举报信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('video:report:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id) {
        return success(userVideoReportImgInfoService.selectUserVideoReportImgInfoById(id));
    }

    /**
     * 新增视频的举报信息
     */
    @PreAuthorize("@ss.hasPermi('video:report:add')")
    @Log(title = "视频的举报信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody UserVideoReportImgInfo userVideoReportImgInfo) {
        return toAjax(userVideoReportImgInfoService.insertUserVideoReportImgInfo(userVideoReportImgInfo));
    }

    /**
     * 修改视频的举报信息
     */
    @PreAuthorize("@ss.hasPermi('video:report:edit')")
    @Log(title = "视频的举报信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody UserVideoReportImgInfo userVideoReportImgInfo) {
        return toAjax(userVideoReportImgInfoService.updateUserVideoReportImgInfo(userVideoReportImgInfo));
    }

    /**
     * 删除视频的举报信息
     */
    @PreAuthorize("@ss.hasPermi('video:report:remove')")
    @Log(title = "视频的举报信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids) {
        return toAjax(userVideoReportImgInfoService.deleteUserVideoReportImgInfoByIds(ids));
    }
}
