package com.kit.web.controller.website;

import com.kit.common.annotation.RateLimiter;
import com.kit.common.core.controller.BaseController;
import com.kit.common.core.domain.R;
import com.kit.common.utils.ip.IpUtils;
import com.kit.system.service.website.WebSiteServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/website")
public class WebSiteController extends BaseController {
    @Autowired
    private WebSiteServer webSiteServer;

    @PostMapping("/open-api/love-me")
    @RateLimiter(key = "#ip", count = 20)
    public R<Boolean> loveMe(HttpServletRequest request) {
        String userIdOrIp;
        boolean isUser = false;
        try {
            userIdOrIp = String.valueOf(getLoginUser().getUserId());
            isUser = true;
        } catch (Exception e) {
            userIdOrIp = IpUtils.getIpAddr(request);
        }
        boolean loveOnce = webSiteServer.loveOnce(userIdOrIp, isUser);
        return loveOnce ? R.ok() : R.fail("今天已经喜欢过一次了");

    }
    @GetMapping("/open-api/has-loved-me")
    @RateLimiter(key = "#ip", count = 20)
    public R<Map<String, Object>> hasLovedMe(HttpServletRequest request) {
        String userIdOrIp = getUserIdOrIp(request);
        boolean hasLoved = webSiteServer.hasLoved(userIdOrIp);
        Map<String, Object> lovedInfo = new HashMap<>();
        lovedInfo.put("hasLoved", hasLoved);
        if (hasLoved) {
            lovedInfo.put("lovedCount", webSiteServer.loveCount());
        }
        return R.ok(lovedInfo);
    }

    @GetMapping("/open-api/loved-count")
    @RateLimiter(key = "#ip", count = 20)
    public R<Integer> loveCount() {
        return R.ok(webSiteServer.loveCount());
    }
}
