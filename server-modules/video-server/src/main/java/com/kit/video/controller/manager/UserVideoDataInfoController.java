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
import com.kit.video.domain.UserVideoDataInfo;
import com.kit.video.service.manager.IUserVideoDataInfoService;
import com.kit.common.utils.poi.ExcelUtil;
import com.kit.common.core.page.TableDataInfo;

/**
 * 视频产生的相关数据Controller
 *
 * @author xjh
 * @date 2024-10-20
 */
@RestController
@RequestMapping("/video/data")
public class UserVideoDataInfoController extends BaseController {
    @Autowired
    private IUserVideoDataInfoService userVideoDataInfoService;

/**
 * 查询视频产生的相关数据列表
 */
@PreAuthorize("@ss.hasPermi('video:data:list')")
@GetMapping("/list")
    public TableDataInfo list(UserVideoDataInfo userVideoDataInfo) {
        startPage();
        List<UserVideoDataInfo> list = userVideoDataInfoService.selectUserVideoDataInfoList(userVideoDataInfo);
        return getDataTable(list);
    }

    /**
     * 导出视频产生的相关数据列表
     */
    @PreAuthorize("@ss.hasPermi('video:data:export')")
    @Log(title = "视频产生的相关数据", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, UserVideoDataInfo userVideoDataInfo) {
        List<UserVideoDataInfo> list = userVideoDataInfoService.selectUserVideoDataInfoList(userVideoDataInfo);
        ExcelUtil<UserVideoDataInfo> util = new ExcelUtil<UserVideoDataInfo>(UserVideoDataInfo. class);
        util.exportExcel(response, list, "视频产生的相关数据数据");
    }

    /**
     * 获取视频产生的相关数据详细信息
     */
    @PreAuthorize("@ss.hasPermi('video:data:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return success(userVideoDataInfoService.selectUserVideoDataInfoById(id));
    }

    /**
     * 新增视频产生的相关数据
     */
    @PreAuthorize("@ss.hasPermi('video:data:add')")
    @Log(title = "视频产生的相关数据", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody UserVideoDataInfo userVideoDataInfo) {
        return toAjax(userVideoDataInfoService.insertUserVideoDataInfo(userVideoDataInfo));
    }

    /**
     * 修改视频产生的相关数据
     */
    @PreAuthorize("@ss.hasPermi('video:data:edit')")
    @Log(title = "视频产生的相关数据", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody UserVideoDataInfo userVideoDataInfo) {
        return toAjax(userVideoDataInfoService.updateUserVideoDataInfo(userVideoDataInfo));
    }

    /**
     * 删除视频产生的相关数据
     */
    @PreAuthorize("@ss.hasPermi('video:data:remove')")
    @Log(title = "视频产生的相关数据", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(userVideoDataInfoService.deleteUserVideoDataInfoByIds(ids));
    }
}
