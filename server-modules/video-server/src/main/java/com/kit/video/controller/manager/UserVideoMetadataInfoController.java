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
import com.kit.video.domain.UserVideoMetadataInfo;
import com.kit.video.service.manager.IUserVideoMetadataInfoService;
import com.kit.common.utils.poi.ExcelUtil;
import com.kit.common.core.page.TableDataInfo;

/**
 * 视频原始信息Controller
 *
 * @author xjh
 * @date 2024-10-20
 */
@RestController
@RequestMapping("/video/metadata")
public class UserVideoMetadataInfoController extends BaseController {
    @Autowired
    private IUserVideoMetadataInfoService userVideoMetadataInfoService;

/**
 * 查询视频原始信息列表
 */
@PreAuthorize("@ss.hasPermi('video:metadata:list')")
@GetMapping("/list")
    public TableDataInfo list(UserVideoMetadataInfo userVideoMetadataInfo) {
        startPage();
        List<UserVideoMetadataInfo> list = userVideoMetadataInfoService.selectUserVideoMetadataInfoList(userVideoMetadataInfo);
        return getDataTable(list);
    }

    /**
     * 导出视频原始信息列表
     */
    @PreAuthorize("@ss.hasPermi('video:metadata:export')")
    @Log(title = "视频原始信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, UserVideoMetadataInfo userVideoMetadataInfo) {
        List<UserVideoMetadataInfo> list = userVideoMetadataInfoService.selectUserVideoMetadataInfoList(userVideoMetadataInfo);
        ExcelUtil<UserVideoMetadataInfo> util = new ExcelUtil<UserVideoMetadataInfo>(UserVideoMetadataInfo. class);
        util.exportExcel(response, list, "视频原始信息数据");
    }

    /**
     * 获取视频原始信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('video:metadata:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id) {
        return success(userVideoMetadataInfoService.selectUserVideoMetadataInfoById(id));
    }

    /**
     * 新增视频原始信息
     */
    @PreAuthorize("@ss.hasPermi('video:metadata:add')")
    @Log(title = "视频原始信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody UserVideoMetadataInfo userVideoMetadataInfo) {
        return toAjax(userVideoMetadataInfoService.insertUserVideoMetadataInfo(userVideoMetadataInfo));
    }

    /**
     * 修改视频原始信息
     */
    @PreAuthorize("@ss.hasPermi('video:metadata:edit')")
    @Log(title = "视频原始信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody UserVideoMetadataInfo userVideoMetadataInfo) {
        return toAjax(userVideoMetadataInfoService.updateUserVideoMetadataInfo(userVideoMetadataInfo));
    }

    /**
     * 删除视频原始信息
     */
    @PreAuthorize("@ss.hasPermi('video:metadata:remove')")
    @Log(title = "视频原始信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids) {
        return toAjax(userVideoMetadataInfoService.deleteUserVideoMetadataInfoByIds(ids));
    }
}
