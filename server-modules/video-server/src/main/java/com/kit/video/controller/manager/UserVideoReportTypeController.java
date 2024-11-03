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
import com.kit.video.domain.UserVideoReportType;
import com.kit.video.service.manager.IUserVideoReportTypeService;
import com.kit.common.utils.poi.ExcelUtil;
import com.kit.common.core.page.TableDataInfo;

/**
 * 举报类型信息Controller
 *
 * @author xjh
 * @date 2024-10-20
 */
@RestController
@RequestMapping("/video/type")
public class UserVideoReportTypeController extends BaseController {
    @Autowired
    private IUserVideoReportTypeService userVideoReportTypeService;

/**
 * 查询举报类型信息列表
 */
@PreAuthorize("@ss.hasPermi('video:type:list')")
@GetMapping("/list")
    public TableDataInfo list(UserVideoReportType userVideoReportType) {
        startPage();
        List<UserVideoReportType> list = userVideoReportTypeService.selectUserVideoReportTypeList(userVideoReportType);
        return getDataTable(list);
    }

    /**
     * 导出举报类型信息列表
     */
    @PreAuthorize("@ss.hasPermi('video:type:export')")
    @Log(title = "举报类型信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, UserVideoReportType userVideoReportType) {
        List<UserVideoReportType> list = userVideoReportTypeService.selectUserVideoReportTypeList(userVideoReportType);
        ExcelUtil<UserVideoReportType> util = new ExcelUtil<UserVideoReportType>(UserVideoReportType. class);
        util.exportExcel(response, list, "举报类型信息数据");
    }

    /**
     * 获取举报类型信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('video:type:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id) {
        return success(userVideoReportTypeService.selectUserVideoReportTypeById(id));
    }

    /**
     * 新增举报类型信息
     */
    @PreAuthorize("@ss.hasPermi('video:type:add')")
    @Log(title = "举报类型信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody UserVideoReportType userVideoReportType) {
        return toAjax(userVideoReportTypeService.insertUserVideoReportType(userVideoReportType));
    }

    /**
     * 修改举报类型信息
     */
    @PreAuthorize("@ss.hasPermi('video:type:edit')")
    @Log(title = "举报类型信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody UserVideoReportType userVideoReportType) {
        return toAjax(userVideoReportTypeService.updateUserVideoReportType(userVideoReportType));
    }

    /**
     * 删除举报类型信息
     */
    @PreAuthorize("@ss.hasPermi('video:type:remove')")
    @Log(title = "举报类型信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids) {
        return toAjax(userVideoReportTypeService.deleteUserVideoReportTypeByIds(ids));
    }
}
