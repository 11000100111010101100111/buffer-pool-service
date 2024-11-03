package com.kit.web.controller.common;

import com.google.code.kaptcha.Producer;
import com.kit.common.config.SystemConfig;
import com.kit.common.constant.CacheConstants;
import com.kit.common.constant.Constants;
import com.kit.common.core.domain.AjaxResult;
import com.kit.common.core.redis.RedisCache;
import com.kit.common.utils.sign.Base64;
import com.kit.common.utils.uuid.IdUtils;
import com.kit.system.service.ISysConfigService;
import com.kit.system.service.message.email.EmailServiceImpl;
import com.kit.system.service.message.phone.PhoneServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FastByteArrayOutputStream;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * 验证码操作处理
 *
 * @author xiao
 */
@RestController
public class CaptchaController {
    @Resource(name = "captchaProducer")
    private Producer captchaProducer;

    @Resource(name = "captchaProducerMath")
    private Producer captchaProducerMath;

    @Autowired
    private RedisCache redisCache;

    @Autowired
    private ISysConfigService configService;

    @Autowired
    private EmailServiceImpl emailService;

    @Autowired
    private PhoneServiceImpl phoneService;

    /**
     * 生成验证码
     */
    @GetMapping("/captchaImage")
    public AjaxResult getCode(HttpServletResponse response) throws IOException {
        AjaxResult ajax = AjaxResult.success();
        boolean captchaEnabled = configService.selectCaptchaEnabled();
        ajax.put("captchaEnabled", captchaEnabled);
        if (!captchaEnabled) {
            return ajax;
        }

        // 保存验证码信息
        String uuid = IdUtils.simpleUUID();
        String verifyKey = CacheConstants.CAPTCHA_CODE_KEY + uuid;

        String capStr = null, code = null;
        BufferedImage image = null;

        // 生成验证码
        String captchaType = SystemConfig.getCaptchaType();
        if ("math".equals(captchaType)) {
            String capText = captchaProducerMath.createText();
            capStr = capText.substring(0, capText.lastIndexOf("@"));
            code = capText.substring(capText.lastIndexOf("@") + 1);
            image = captchaProducerMath.createImage(capStr);
        } else if ("char".equals(captchaType)) {
            capStr = code = captchaProducer.createText();
            image = captchaProducer.createImage(capStr);
        }

        redisCache.setCacheObject(verifyKey, code, Constants.CAPTCHA_EXPIRATION, TimeUnit.MINUTES);
        // 转换流信息写出
        FastByteArrayOutputStream os = new FastByteArrayOutputStream();
        try {
            ImageIO.write(image, "jpg", os);
        } catch (IOException e) {
            return AjaxResult.error(e.getMessage());
        }

        ajax.put("uuid", uuid);
        ajax.put("img", Base64.encode(os.toByteArray()));
        return ajax;
    }


    /**
     * 根据邮箱发送邮箱验证码
     */
    @GetMapping("/valid-code/emailCode")
    public AjaxResult getEmailCode(@RequestParam(name = "email") String email) {
        emailService.sendCode(email);
        return AjaxResult.success();
    }

    /**
     * 根据手机号发送邮箱验证码
     */
    @GetMapping("/valid-code/phone-code")
    public AjaxResult getPhoneCode(@RequestParam(name = "phone") String phone) {
        phoneService.sendCode(phone);
        return AjaxResult.success();
    }
}
