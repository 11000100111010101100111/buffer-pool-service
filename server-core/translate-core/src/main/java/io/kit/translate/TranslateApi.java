package io.kit.translate;

import cn.hutool.json.JSONUtil;
import io.kit.limitting.LimitLock;
import io.kit.translate.param.TranslateParam;
import io.kit.translate.vo.TranslateResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@Scope("singleton")
public class TranslateApi {
    @Value("${translate.baidu.appId}")
    private String appId;

    @Value("${translate.baidu.appKey}")
    private String securityKey;

    private TranslateApi() {
    }

    private static final String TRANS_API_HOST = "https://fanyi-api.baidu.com/api/trans/vip/translate";

    private static final LimitLock lock = new LimitLock(10);

    public TranslateResult translate(TranslateParam param) {
        try {
            lock.lock();
            String transResult = getTransResult(param.getP(), param.getFrom(), param.getTo());
            return JSONUtil.toBean(transResult, TranslateResult.class);
        } finally {
            lock.unlock();
        }
    }


    public String getTransResult(String query, String from, String to) {
        Map<String, String> params = buildParams(query, from, to);
        return HttpGet.get(TRANS_API_HOST, params);
    }

    private Map<String, String> buildParams(String query, String from, String to) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("q", query);
        params.put("from", from);
        params.put("to", to);

        params.put("appid", appId);

        // 随机数
        String salt = String.valueOf(System.currentTimeMillis());
        params.put("salt", salt);

        // 签名
        String src = appId + query + salt + securityKey; // 加密前的原文
        params.put("sign", MD5.md5(src));

        return params;
    }

}
