package com.kit.framework.security.handle;

import java.io.IOException;
import java.io.Serializable;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import com.alibaba.fastjson2.JSON;
import com.kit.common.constant.HttpStatus;
import com.kit.common.core.domain.AjaxResult;
import com.kit.common.utils.ServletUtils;
import com.kit.common.utils.StringUtils;

/**
 * 认证失败处理类 返回未授权
 * 
 * @author xiao
 */
@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint, Serializable {
    private static final long serialVersionUID = -8970718410437077606L;

    @Value("${request.route.public}")
    String publicRouteRegex;

    Pattern pattern;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e)
            throws IOException {
        if (Objects.nonNull(publicRouteRegex)) {
            String url = request.getRequestURI();
            if (Objects.isNull(pattern)) {
                pattern = Pattern.compile(publicRouteRegex);
            }
            Matcher matcher = pattern.matcher(url);
            if (matcher.find()) {
                ServletUtils.renderString(response, "Not need auth page");
                return;
            }
        }

        int code = HttpStatus.UNAUTHORIZED;
        String msg = StringUtils.format("请求访问：{}，认证失败，无法访问系统资源", request.getRequestURI());
        ServletUtils.renderString(response, JSON.toJSONString(AjaxResult.error(code, msg)));
    }
}
