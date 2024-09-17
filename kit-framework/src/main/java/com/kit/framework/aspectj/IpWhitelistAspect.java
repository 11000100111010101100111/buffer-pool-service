package com.kit.framework.aspectj;

import com.kit.system.service.WhitelistService;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
public class IpWhitelistAspect {

    @Autowired
    private WhitelistService whitelistService;

    @Autowired
    private HttpServletRequest request;

    @Before("@annotation(com.kit.common.annotation.IpWhitelist)")
    public void checkIpWhitelist() throws Exception {
        String clientIp = getClientIp(request);
        if (!whitelistService.checkWhitelistIp(clientIp)) {
            throw new SecurityException("The IP does not allow access, please contact the administrator");
        }
    }

    // 获取请求客户端 IP
    private String getClientIp(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
}
