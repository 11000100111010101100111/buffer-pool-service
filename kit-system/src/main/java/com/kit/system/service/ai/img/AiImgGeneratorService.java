package com.kit.system.service.ai.img;

import cn.hutool.core.map.MapUtil;
import com.alibaba.fastjson2.JSONObject;
import com.kit.common.core.domain.model.LoginUser;
import com.kit.common.core.redis.RedisCache;
import com.kit.common.core.text.Convert;
import com.kit.common.utils.sign.Md5Utils;
import com.kit.common.utils.uuid.UUID;
import com.kit.system.domain.ai.img.entity.ProcessInfoEntity;
import com.kit.system.domain.ai.img.entity.ProcessStepInfo;
import com.kit.system.domain.ai.img.param.GeneratorParam;
import com.kit.system.domain.ai.img.vo.GeneratorVo;
import com.kit.system.mapper.AIImageGeneratorMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.TimeUnit;


@Service
@Slf4j
public class AiImgGeneratorService {
    @Autowired
    private RedisCache redisCache;

    @Value("${mq.exchange}")
    String exchange;

    @Value("${mq.routing}")
    String routing;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private AIImageGeneratorMapper imageGeneratorMapper;

    public GeneratorVo generator(GeneratorParam param, String userIdOrIp, LoginUser loginUser) {
        String hash = Md5Utils.hash(param.getText());
        String redisKey = key(hash, userIdOrIp);
        boolean hasKey = Boolean.TRUE.equals(redisCache.hasKey(redisKey));

        if (hasKey && !redisCache.expire(redisKey, 1, TimeUnit.DAYS)) {
            //今天已经查询过相同的文本
            return new GeneratorVo()
                    .withText(param.getText())
                    .withProcessId(Convert.toStr(redisCache.getCacheObject(redisKey)));
        }

        String processId = UUID.randomUUID().toString();
        imageGeneratorMapper.saveProcessInfo(ProcessInfoEntity.builder()
                .processId(processId)
                .result("0")
                .resultMessage("等待初始化中，请稍后")
                .ipOrUserId(userIdOrIp)
                .text(param.getText())
                .width(param.getWidth())
                .height(param.getHeight())
                .createBy(Optional.ofNullable(loginUser)
                        .map(LoginUser::getUsername)
                        .orElse(""))
                .build());
        redisCache.setCacheObject(redisKey, processId, 1, TimeUnit.DAYS);
        Map<String, String> message = MapUtil.builder("processId", processId)
                .put("text", param.getText())
                .put("width", String.valueOf(param.getWidth()))
                .put("height", String.valueOf(param.getHeight()))
                .build();
        rabbitTemplate.convertAndSend(exchange, routing, JSONObject.toJSONString(message));
        return new GeneratorVo()
                .withText(param.getText())
                .withProcessId(processId);
    }

    public void updateUrl(String processId, String url){
        imageGeneratorMapper.updateUrl(processId, url, "2", "生成成功");
    }

    public ProcessInfoEntity findProcessInfo(String processId, String userIdOrIp) {
        return imageGeneratorMapper.findByProcessId(processId, userIdOrIp);
    }

    public void deployProcessStepInfo(ProcessStepInfo stepInfo) {
        imageGeneratorMapper.deployProcessStepInfo(stepInfo);
    }

    public List<ProcessStepInfo> findStepInfoByProcessId(String processId, String userIdOrIp) {
        return imageGeneratorMapper.findStepInfoByProcessId(processId, userIdOrIp);
    }

    protected String key(String hash, String userId) {
        return String.format("%s:%s:%s", "ai_img", hash, userId);
    }
}
