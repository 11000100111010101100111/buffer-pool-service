package com.kit.web.controller.ai.img;

import com.kit.common.annotation.RateLimiter;
import com.kit.common.config.SystemConfig;
import com.kit.common.core.controller.BaseController;
import com.kit.common.core.domain.AjaxResult;
import com.kit.common.core.domain.R;
import com.kit.common.core.domain.model.LoginUser;
import com.kit.common.utils.file.FileUploadUtils;
import com.kit.common.utils.file.FileUtils;
import com.kit.common.utils.ip.IpUtils;
import com.kit.framework.config.ServerConfig;
import com.kit.system.domain.ai.img.entity.ProcessInfoEntity;
import com.kit.system.domain.ai.img.entity.ProcessStepInfo;
import com.kit.system.domain.ai.img.param.GeneratorParam;
import com.kit.system.domain.ai.img.vo.GeneratorVo;
import com.kit.system.service.ai.img.AiImgGeneratorService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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

    @PostMapping("/open-api/generator")
    //@RateLimiter(key = "#ip", count = 3, time = 24*60*60)
    public R<GeneratorVo> generator(HttpServletRequest request, @RequestBody GeneratorParam text) {
        return R.ok(aiImgGeneratorService.generator(text, IpUtils.getIpAddr(request), null));
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

        return R.ok(aiImgGeneratorService.findProcessInfo(processId, IpUtils.getIpAddr(request)));
    }


    @GetMapping("/process-info")
    public R<ProcessInfoEntity> getProcessInfo(@RequestParam(name = "processId") String processId) {
        return R.ok(aiImgGeneratorService.findProcessInfo(processId, getUsername()));
    }

    @GetMapping("/open-api/process-step-info")
    public R<List<ProcessStepInfo>> getProcessStepInfo(HttpServletRequest request, @RequestParam(name = "processId") String processId) {
        return R.ok(aiImgGeneratorService.findStepInfoByProcessId(processId, IpUtils.getIpAddr(request)));
    }


    @GetMapping("/process-step-info")
    public R<List<ProcessStepInfo>> getProcessStepInfo(@RequestParam(name = "processId") String processId) {
        return R.ok(aiImgGeneratorService.findStepInfoByProcessId(processId, getUsername()));
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
            aiImgGeneratorService.updateUrl(processId, url);
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
