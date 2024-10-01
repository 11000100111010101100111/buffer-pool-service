package com.kit.framework.util;

import com.kit.common.core.domain.AjaxResult;
import com.kit.common.utils.file.FileUploadUtils;
import com.kit.common.utils.file.FileUtils;
import com.kit.framework.config.ServerConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class CommonUploadUtil {

    @Autowired
    private ServerConfig serverConfig;

    public AjaxResult upload(MultipartFile file, String filePath) {
        try {
            String fileName = FileUploadUtils.upload(filePath, file);
            String url = serverConfig.getUrl() + fileName;
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
}
