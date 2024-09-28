package com.kit.web.controller.ai.img;

import com.kit.common.annotation.RateLimiter;
import com.kit.common.config.SystemConfig;
import com.kit.common.core.controller.BaseController;
import com.kit.common.core.domain.AjaxResult;
import com.kit.common.core.domain.R;
import com.kit.common.core.domain.model.LoginUser;
import com.kit.common.core.page.TableDataInfo;
import com.kit.common.utils.file.FileUploadUtils;
import com.kit.common.utils.file.FileUtils;
import com.kit.common.utils.ip.IpUtils;
import com.kit.framework.config.ServerConfig;
import com.kit.system.domain.SysConfig;
import com.kit.system.domain.ai.img.entity.ProcessInfoEntity;
import com.kit.system.domain.ai.img.entity.ProcessStepInfo;
import com.kit.system.domain.ai.img.param.GeneratorParam;
import com.kit.system.domain.ai.img.vo.GeneratorVo;
import com.kit.system.service.ai.img.AiImgGeneratorService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Api("AI生成图片")
@RestController
@RequestMapping("/ai/img")
public class AiImgGeneratorController extends BaseController {
    @Autowired
    private AiImgGeneratorService aiImgGeneratorService;
    @Autowired
    private ServerConfig serverConfig;

    /**
     * 获取参数配置列表
     */
    @GetMapping("/open-api/list")
    public TableDataInfo list(HttpServletRequest request) {
        startPage();
        return getDataTable(aiImgGeneratorService.page(getUserIdOrIp(request)));
    }

    @DeleteMapping("/open-api/delete/{processId}")
    public R delete(HttpServletRequest request, @PathVariable("processId") String processId) {
        aiImgGeneratorService.deleteOne(processId);
       return R.ok();
    }

    @PostMapping("/open-api/generator")
    //@RateLimiter(key = "#ip", count = 1, time = 60)
    public R<GeneratorVo> generator(HttpServletRequest request, @RequestBody GeneratorParam text) {
        LoginUser loginUser = null;
        try {
            loginUser = getLoginUser();
        } catch (Exception e) {
            //do nothing
        }
        return R.ok(aiImgGeneratorService.generator(text, getUserIdOrIp(request), loginUser));
    }

    @GetMapping("/open-api/remaining-usage-times")
    @RateLimiter(key = "#ip", count = 20)
    public R<Object> generatorTimes(HttpServletRequest request) {
        return R.ok(aiImgGeneratorService.remainingUsageTimes(IpUtils.getIpAddr(request)));
    }

    @PostMapping("/avatar")
    @RateLimiter(key = "#ip", count = 3, time = 24*60*60)
    public R<GeneratorVo> generatorAvatar(@RequestParam(name = "text") String text) {
        LoginUser loginUser = getLoginUser();
        return R.ok(aiImgGeneratorService.generator(
                new GeneratorParam()
                    .withText(text)
                    .withHeight(512)
                    .withWidth(512),
                String.valueOf(loginUser.getUserId()), loginUser));
    }

    @GetMapping("/open-api/process-info")
    public R<ProcessInfoEntity> getProcessInfo(HttpServletRequest request, @RequestParam(name = "processId") String processId) {

        return R.ok(aiImgGeneratorService.findProcessInfo(processId, getUserIdOrIp(request)));
    }

    @GetMapping("/open-api/process-step-info")
    public R<List<ProcessStepInfo>> getProcessStepInfo(HttpServletRequest request, @RequestParam(name = "processId") String processId) {
        return R.ok(aiImgGeneratorService.findStepInfoByProcessId(processId, getUserIdOrIp(request)));
    }


    @PostMapping("/open-api/deploy-step-info")
   //@todo @IpWhitelist
    public R deployStepInfo(@RequestBody ProcessStepInfo info) {
        aiImgGeneratorService.deployProcessStepInfo(info);
        return R.ok();
    }


    @PostMapping("/open-api/upload-img")
    //@todo @IpWhitelist
    public AjaxResult uploadFile(MultipartFile file,
                                 @RequestParam(name = "processId") String processId) {
        try {
            String filePath = SystemConfig.getAiImgUploadPath();
            String fileName = FileUploadUtils.upload(filePath, file);
            String url = serverConfig.getUrl() + fileName;
            aiImgGeneratorService.updateUrl(processId, fileName);
            AjaxResult ajax = AjaxResult.success();
            ajax.put("url", url);
            ajax.put("fileName", fileName);
            ajax.put("newFileName", FileUtils.getName(fileName));
            ajax.put("originalFilename", file.getOriginalFilename());
            return ajax;
        } catch (Exception e) {
            return AjaxResult.error(e.getMessage());
        }
    }

    //@todo 查询ip白名单列表
    //@todo 新增ip白名单，移除

}
