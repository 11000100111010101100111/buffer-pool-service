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
import com.kit.video.domain.UserVideoWithTag;
import com.kit.video.service.manager.IUserVideoWithTagService;
import com.kit.common.utils.poi.ExcelUtil;
import com.kit.common.core.page.TableDataInfo;

/**
 * 视频与话题（标签）信息的关联Controller
 *
 * @author xjh
 * @date 2024-10-20
 */
@RestController
@RequestMapping("/video/tag")
public class UserVideoWithTagController extends BaseController {
    @Autowired
    private IUserVideoWithTagService userVideoWithTagService;

/**
 * 查询视频与话题（标签）信息的关联列表
 */
@PreAuthorize("@ss.hasPermi('video:tag:list')")
@GetMapping("/list")
    public TableDataInfo list(UserVideoWithTag userVideoWithTag) {
        startPage();
        List<UserVideoWithTag> list = userVideoWithTagService.selectUserVideoWithTagList(userVideoWithTag);
        return getDataTable(list);
    }

    /**
     * 导出视频与话题（标签）信息的关联列表
     */
    @PreAuthorize("@ss.hasPermi('video:tag:export')")
    @Log(title = "视频与话题（标签）信息的关联", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, UserVideoWithTag userVideoWithTag) {
        List<UserVideoWithTag> list = userVideoWithTagService.selectUserVideoWithTagList(userVideoWithTag);
        ExcelUtil<UserVideoWithTag> util = new ExcelUtil<UserVideoWithTag>(UserVideoWithTag. class);
        util.exportExcel(response, list, "视频与话题（标签）信息的关联数据");
    }

    /**
     * 获取视频与话题（标签）信息的关联详细信息
     */
    @PreAuthorize("@ss.hasPermi('video:tag:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return success(userVideoWithTagService.selectUserVideoWithTagById(id));
    }

    /**
     * 新增视频与话题（标签）信息的关联
     */
    @PreAuthorize("@ss.hasPermi('video:tag:add')")
    @Log(title = "视频与话题（标签）信息的关联", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody UserVideoWithTag userVideoWithTag) {
        return toAjax(userVideoWithTagService.insertUserVideoWithTag(userVideoWithTag));
    }

    /**
     * 修改视频与话题（标签）信息的关联
     */
    @PreAuthorize("@ss.hasPermi('video:tag:edit')")
    @Log(title = "视频与话题（标签）信息的关联", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody UserVideoWithTag userVideoWithTag) {
        return toAjax(userVideoWithTagService.updateUserVideoWithTag(userVideoWithTag));
    }

    /**
     * 删除视频与话题（标签）信息的关联
     */
    @PreAuthorize("@ss.hasPermi('video:tag:remove')")
    @Log(title = "视频与话题（标签）信息的关联", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(userVideoWithTagService.deleteUserVideoWithTagByIds(ids));
    }
}
