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
import com.kit.video.domain.UserVideoForwardInfo;
import com.kit.video.service.manager.IUserVideoForwardInfoService;
import com.kit.common.utils.poi.ExcelUtil;
import com.kit.common.core.page.TableDataInfo;

/**
 * 视频的转发信息Controller
 *
 * @author xjh
 * @date 2024-10-20
 */
@RestController
@RequestMapping("/video/forward")
public class UserVideoForwardInfoController extends BaseController {
    @Autowired
    private IUserVideoForwardInfoService userVideoForwardInfoService;

/**
 * 查询视频的转发信息列表
 */
@PreAuthorize("@ss.hasPermi('video:forward:list')")
@GetMapping("/list")
    public TableDataInfo list(UserVideoForwardInfo userVideoForwardInfo) {
        startPage();
        List<UserVideoForwardInfo> list = userVideoForwardInfoService.selectUserVideoForwardInfoList(userVideoForwardInfo);
        return getDataTable(list);
    }

    /**
     * 导出视频的转发信息列表
     */
    @PreAuthorize("@ss.hasPermi('video:forward:export')")
    @Log(title = "视频的转发信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, UserVideoForwardInfo userVideoForwardInfo) {
        List<UserVideoForwardInfo> list = userVideoForwardInfoService.selectUserVideoForwardInfoList(userVideoForwardInfo);
        ExcelUtil<UserVideoForwardInfo> util = new ExcelUtil<UserVideoForwardInfo>(UserVideoForwardInfo. class);
        util.exportExcel(response, list, "视频的转发信息数据");
    }

    /**
     * 获取视频的转发信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('video:forward:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id) {
        return success(userVideoForwardInfoService.selectUserVideoForwardInfoById(id));
    }

    /**
     * 新增视频的转发信息
     */
    @PreAuthorize("@ss.hasPermi('video:forward:add')")
    @Log(title = "视频的转发信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody UserVideoForwardInfo userVideoForwardInfo) {
        return toAjax(userVideoForwardInfoService.insertUserVideoForwardInfo(userVideoForwardInfo));
    }

    /**
     * 修改视频的转发信息
     */
    @PreAuthorize("@ss.hasPermi('video:forward:edit')")
    @Log(title = "视频的转发信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody UserVideoForwardInfo userVideoForwardInfo) {
        return toAjax(userVideoForwardInfoService.updateUserVideoForwardInfo(userVideoForwardInfo));
    }

    /**
     * 删除视频的转发信息
     */
    @PreAuthorize("@ss.hasPermi('video:forward:remove')")
    @Log(title = "视频的转发信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids) {
        return toAjax(userVideoForwardInfoService.deleteUserVideoForwardInfoByIds(ids));
    }
}
