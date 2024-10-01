package com.kit.web.controller.server;

import com.kit.common.core.controller.BaseController;
import com.kit.common.core.domain.AjaxResult;
import com.kit.common.core.domain.entity.SysUser;
import com.kit.common.core.page.TableDataInfo;
import com.kit.system.domain.entity.LibraryInfo;
import com.kit.system.domain.vo.LibraryInfoVo;
import com.kit.system.service.QuestionBankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 *
 * @author xjh
 * @since 2024/8/3
 * Time: 19:46
 **/
@RestController
@RequestMapping("/api/bank")
public class QuestionManagementController extends BaseController {
    @Autowired
    QuestionBankService questionBankService;

    /**
     * 系统资源有限，管理员才能上传题库，个人上传方式暂不开启
     * 上传题库-->解析--->新建表-->插入题
     */
    @PostMapping("upload")
    @PreAuthorize("@ss.hasPermi('*:*:*')")
    public AjaxResult uploadQuestions(@RequestParam("file") MultipartFile file) {
        if (null == file) {
            return AjaxResult.error("请上传文件对象");
        }
        return AjaxResult.success(questionBankService.importLibrary(file, getLoginUser()));
    }

    @PostMapping("upload/{tableName}")
    @PreAuthorize("@ss.hasPermi('*:*:*')")
    public AjaxResult uploadSystemInfo(@RequestParam("file") MultipartFile file,
                                       @PathVariable(name = "tableName", required = true) String tableName) {
        if (null == file) {
            return AjaxResult.error("请上传文件对象");
        }
        return AjaxResult.success(questionBankService.importSystemInfo(file, tableName, getLoginUser()));
    }

    /**
     * 下载题库，支持输出为excel、json、csv
     */
    @PostMapping("download/{bankId}")
    public void downloadQuestions(@PathVariable(name = "bankId") String bankId,
                                  @RequestParam(name = "type", required = false, defaultValue = "excel") String type) {

    }

    /**
     * 分页查询题库列表
     */
    @GetMapping("find/page")
    public TableDataInfo findQuestionPage(LibraryInfo info) {
        startPage();
        List<LibraryInfoVo> list = questionBankService.selectLibraryInfoList(info);
        return getDataTable(list);
    }

    /**
     * 根据ID查询题库信息
     */
    @GetMapping("find/{bankId}")
    public void findQuestionInfoByID(@PathVariable("bankId") String bankId) {

    }

    /**
     * 收藏题库
     */
    @GetMapping("collect/{bankId}")
    public void collectQuestionBank(@PathVariable("bankId") String bankId) {

    }

    /**
     * 取消收藏题库
     */
    @GetMapping("remove-collect/{bankId}")
    public void removeCollectQuestionBank(@PathVariable("bankId") String bankId) {

    }


    /**
     * 启用题库
     */
    @GetMapping("enable/{bankId}")
    public void enableQuestionBank(@PathVariable("bankId") String bankId) {

    }

    /**
     * 停用题库
     */
    @GetMapping("remove-enable/{bankId}")
    public void unEnableQuestionBank(@PathVariable("bankId") String bankId) {

    }
}
