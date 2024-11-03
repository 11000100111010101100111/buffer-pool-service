package com.kit.system.service.message.phone;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.kit.common.exception.base.BaseException;
import com.kit.common.utils.DateUtils;
import com.kit.system.config.SmsConfig;
import com.kit.system.service.message.AbstractCodeSend;
import com.zhenzi.sms.ZhenziSmsClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Random;

@Service
@Slf4j
public class PhoneServiceImpl extends AbstractCodeSend {
    @Autowired
    SmsConfig smsConfig;

    @Autowired
    ZhenziSmsClient client;

    @Value("${sms.cacheKey:phone_code_key_}")
    private String cacheSuffix;

    @Resource(name = "redisTemplate")
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public String genericCode() {
        Random rnd = new Random();
        int randNum = rnd.nextInt(8999) + 1000;
        return String.valueOf(randNum);
    }

    @Override
    protected void send(String target, String code) throws Exception {
        Map<String, Object> params = new HashMap<>();
        params.put("number", target);
        params.put("templateId", smsConfig.getTemplateId());
        String[] templateParams = new String[4];
        templateParams[0] = "用户";
        templateParams[1] = code;
        templateParams[2] = String.format("%d分钟", smsConfig.getTimeout());
        templateParams[3] = DateUtils.parseDateToStr("hh:mm", new Date());
        params.put("templateParams", templateParams);
        final String send = client.send(params);
        String codeRes = "";
        String msg = "";
        try {
            final JSONObject jsonObject = JSON.parseObject(send);
            codeRes= jsonObject.getString("code");
            msg = jsonObject.getString("data");
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        if (!Objects.equals(codeRes, "200")) {
            throw new BaseException("发送失败：" + msg);
        }
    }

    @Override
    protected String getCacheSuffix() {
        return cacheSuffix;
    }

    @Override
    protected int getCacheTimeout() {
        return smsConfig.getTimeout();
    }
}
