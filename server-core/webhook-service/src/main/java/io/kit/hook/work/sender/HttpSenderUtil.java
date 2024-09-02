package io.kit.hook.work.sender;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.json.JSONUtil;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.Header;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicHeader;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;
import java.util.UUID;

public class HttpSenderUtil {
    protected static final String REQUEST_URL = "Request-URL";
    protected static final String REQUEST_METHOD = "Request-Method";
    protected static final String UTF_8 = "utf-8";
    protected static final String CONTENT_TYPE_JSON = "application/json";
    protected static final String CONTENT_TYPE = "Content-Type";

    private HttpSenderUtil(){}

    public static String genericHookId() {
        return UUID.randomUUID().toString();
    }

    public static boolean checkURL(String url) {
        if (StringUtils.isBlank(url)) {
            return false;
        }
        try {
            new URL(url);
            return true;
        } catch (MalformedURLException e) {
            //do nothing
        }
        return false;
    }


    public static String getAllHead(Header[] headers) {
        if (null == headers || headers.length == 0) {
            return "";
        }
        StringJoiner joiner = new StringJoiner("\n");
        for (Header header : headers) {
            joiner.add(header.toString());
        }
        return joiner.toString();
    }

    public static Header[] toHeads(String heads) {
        if (StringUtils.isBlank(heads)) return new Header[0];
        String[] split = heads.split("\n");
        List<Header> allHeader = new ArrayList<>();
        for (String head : split) {
            int splitIndex = head.indexOf(":");
            if (splitIndex > 0) {
                Header header = new BasicHeader(
                        head.substring(0, splitIndex).trim(),
                        splitIndex < head.length() - 1 ? head.substring(splitIndex + 1).trim() : ""
                );
                allHeader.add(header);
            }
        }
        Header[] hs = new Header[allHeader.size()];
        for (int index = 0; index < allHeader.size(); index++) {
            hs[index] = allHeader.get(index);
        }
        return hs;
    }

    public static Header[] toHeads(Map<String, Object> head) {
        if (null == head) return addContentType(new Header[0], new HashMap<>());
        Header[] hs = new Header[head.size()];
        List<String> hKey = new ArrayList<>(head.keySet());
        for (int index = 0; index < hKey.size(); index++) {
            String key = hKey.get(index).trim();
            String value = String.valueOf(head.get(key)).trim();
            hs[index] = new BasicHeader(key, value);
        }

        return addContentType(hs, head);
    }

    public static Header[] addContentType(Header[] headers, Map<String, Object> head) {
        if (CollUtil.isNotEmpty(head) && head.containsKey(CONTENT_TYPE)) {
            return headers;
        }
        return ArrayUtils.add(headers, new BasicHeader(CONTENT_TYPE, CONTENT_TYPE_JSON));
    }

    public static void addHeardParams(HttpPost post, Header[] head) {
        if (null != head && head.length > 0) {
            for (Header header : head) {
                if (REQUEST_METHOD.equals(header.getName())) {
                    continue;
                }
                addHeard(post, header);
            }
        }
    }

    protected static void addHeard(HttpPost post, Header header) {
        Header[] headers = post.getHeaders(header.getName());
        if (headers.length <= 0) {
            post.addHeader(header);
        }
    }

    public static String addUrlParamToUrl(String url, String urlParams) {
        url = url.trim();
        if (StringUtils.isNotBlank(urlParams)) {
            url = url + (!url.endsWith("?") ? "?" : "") + urlParams;
        }
        return url;
    }

    public static String getUrlParam(Map<String, Object> urlParam) {
        StringJoiner joiner = new StringJoiner("&");
        if (null != urlParam && !urlParam.isEmpty()) {
            urlParam.forEach((key, value) -> joiner.add(key + "=" + (null == value ? "" : String.valueOf(value))));
        }
        return joiner.toString();
    }

    public static String getRequestBody(Object requestBody) {
        if (null == requestBody) return null;
        return JSONUtil.toJsonStr(requestBody);
    }
}
