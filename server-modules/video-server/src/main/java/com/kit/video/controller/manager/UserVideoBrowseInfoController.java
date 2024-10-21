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
import com.kit.video.domain.UserVideoBrowseInfo;
import com.kit.video.service.manager.IUserVideoBrowseInfoService;
import com.kit.common.utils.poi.ExcelUtil;
import com.kit.common.core.page.TableDataInfo;

/**
 * 视频的浏览信息Controller
 *
 * @author xjh
 * @date 2024-10-20
 */
@RestController
@RequestMapping("/video/browes")
public class UserVideoBrowseInfoController extends BaseController {
    @Autowired
    private IUserVideoBrowseInfoService userVideoBrowseInfoService;

    /**
     * 查询视频的浏览信息列表
     */
    @PreAuthorize("@ss.hasPermi('video:browes:list')")
    @GetMapping("/list")
    public TableDataInfo list(UserVideoBrowseInfo userVideoBrowseInfo) {
        startPage();
        List<UserVideoBrowseInfo> list = userVideoBrowseInfoService.selectUserVideoBrowseInfoList(userVideoBrowseInfo);
        return getDataTable(list);
    }

    /**
     * 导出视频的浏览信息列表
     */
    @PreAuthorize("@ss.hasPermi('video:browes:export')")
    @Log(title = "视频的浏览信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, UserVideoBrowseInfo userVideoBrowseInfo) {
        List<UserVideoBrowseInfo> list = userVideoBrowseInfoService.selectUserVideoBrowseInfoList(userVideoBrowseInfo);
        ExcelUtil<UserVideoBrowseInfo> util = new ExcelUtil<UserVideoBrowseInfo>(UserVideoBrowseInfo.class);
        util.exportExcel(response, list, "视频的浏览信息数据");
    }

    /**
     * 获取视频的浏览信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('video:browes:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id) {
        return success(userVideoBrowseInfoService.selectUserVideoBrowseInfoById(id));
    }

    /**
     * 新增视频的浏览信息
     */
    @PreAuthorize("@ss.hasPermi('video:browes:add')")
    @Log(title = "视频的浏览信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody UserVideoBrowseInfo userVideoBrowseInfo) {
        return toAjax(userVideoBrowseInfoService.insertUserVideoBrowseInfo(userVideoBrowseInfo));
    }

    /**
     * 修改视频的浏览信息
     */
    @PreAuthorize("@ss.hasPermi('video:browes:edit')")
    @Log(title = "视频的浏览信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody UserVideoBrowseInfo userVideoBrowseInfo) {
        return toAjax(userVideoBrowseInfoService.updateUserVideoBrowseInfo(userVideoBrowseInfo));
    }

    /**
     * 删除视频的浏览信息
     */
    @PreAuthorize("@ss.hasPermi('video:browes:remove')")
    @Log(title = "视频的浏览信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids) {
        return toAjax(userVideoBrowseInfoService.deleteUserVideoBrowseInfoByIds(ids));
    }
}
