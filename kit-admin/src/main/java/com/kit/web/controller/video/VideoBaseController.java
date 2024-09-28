package com.kit.web.controller.video;

import com.kit.common.config.SystemConfig;
import com.kit.common.core.domain.AjaxResult;
import com.kit.common.utils.file.FileUploadUtils;
import com.kit.web.controller.utli.CommonUploadUtil;
import io.kit.file.FileCompressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/video")
public class VideoBaseController {
    @Autowired
    private CommonUploadUtil commonUploadUtil;

    @Autowired
    private FileCompressService compressService;

    @PostMapping("/upload")
    public AjaxResult upload(MultipartFile file) {
        String filePath = SystemConfig.getVideoPath();
        String fileName = FileUploadUtils.extractFilename(file);
        AjaxResult upload = commonUploadUtil.upload(file, filePath);

        //创建异步任务，压缩视频
        try {
            String absolutePath = FileUploadUtils.getAbsoluteFile(filePath, fileName)
                    .getAbsolutePath();

            //@todo
        } catch (Exception e) {
            //do nothing
        }
        return upload;
    }
}
