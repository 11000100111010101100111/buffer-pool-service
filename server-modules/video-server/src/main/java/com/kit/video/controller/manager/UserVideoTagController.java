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
import com.kit.video.domain.UserVideoTag;
import com.kit.video.service.manager.IUserVideoTagService;
import com.kit.common.utils.poi.ExcelUtil;
import com.kit.common.core.page.TableDataInfo;

/**
 * 视频相关的话题（标签）信息Controller
 *
 * @author xjh
 * @date 2024-10-20
 */
@RestController
@RequestMapping("/video/tag-v1")
public class UserVideoTagController extends BaseController {
    @Autowired
    private IUserVideoTagService userVideoTagService;

    /**
     * 查询视频相关的话题（标签）信息列表
     */
    @PreAuthorize("@ss.hasPermi('video:tag:list')")
    @GetMapping("/list")
    public TableDataInfo list(UserVideoTag userVideoTag) {
        startPage();
        List<UserVideoTag> list = userVideoTagService.selectUserVideoTagList(userVideoTag);
        return getDataTable(list);
    }

    /**
     * 导出视频相关的话题（标签）信息列表
     */
    @PreAuthorize("@ss.hasPermi('video:tag:export')")
    @Log(title = "视频相关的话题（标签）信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, UserVideoTag userVideoTag) {
        List<UserVideoTag> list = userVideoTagService.selectUserVideoTagList(userVideoTag);
        ExcelUtil<UserVideoTag> util = new ExcelUtil<UserVideoTag>(UserVideoTag.class);
        util.exportExcel(response, list, "视频相关的话题（标签）信息数据");
    }

    /**
     * 获取视频相关的话题（标签）信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('video:tag:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id) {
        return success(userVideoTagService.selectUserVideoTagById(id));
    }

    /**
     * 新增视频相关的话题（标签）信息
     */
    @PreAuthorize("@ss.hasPermi('video:tag:add')")
    @Log(title = "视频相关的话题（标签）信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody UserVideoTag userVideoTag) {
        return toAjax(userVideoTagService.insertUserVideoTag(userVideoTag));
    }

    /**
     * 修改视频相关的话题（标签）信息
     */
    @PreAuthorize("@ss.hasPermi('video:tag:edit')")
    @Log(title = "视频相关的话题（标签）信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody UserVideoTag userVideoTag) {
        return toAjax(userVideoTagService.updateUserVideoTag(userVideoTag));
    }

    /**
     * 删除视频相关的话题（标签）信息
     */
    @PreAuthorize("@ss.hasPermi('video:tag:remove')")
    @Log(title = "视频相关的话题（标签）信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids) {
        return toAjax(userVideoTagService.deleteUserVideoTagByIds(ids));
    }
}
