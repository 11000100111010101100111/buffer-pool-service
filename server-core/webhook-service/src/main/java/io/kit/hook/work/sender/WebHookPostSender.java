package io.kit.hook.work.sender;

import io.kit.hook.entity.HookOneHistory;
import io.kit.hook.enums.PingResult;
import io.kit.hook.enums.WebHookHistoryStatus;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.Header;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Map;

@Slf4j
@Service
public class WebHookPostSender extends WebHookAbstractSender {

    @Override
    public HookOneHistory send(String url,
                               Map<String, Object> head,
                               Map<String, Object> urlParam,
                               Object body) {
        return httpControl(getHookOneHistoryByParams(url, head, urlParam, body), httpRetryTimes, HttpSenderUtil.toHeads(head));
    }

    public HookOneHistory getHookOneHistoryByParams(String url,
                                                    Map<String, Object> head,
                                                    Map<String, Object> urlParam,
                                                    Object body) {
        HookOneHistory event = new HookOneHistory();
        event.setId(HttpSenderUtil.genericHookId());
        event.setUrl(url);
        String urlParameters = HttpSenderUtil.getUrlParam(urlParam);
        event.setRequestParams(urlParameters);
        String requestBody = HttpSenderUtil.getRequestBody(body);
        event.setRequestBody(requestBody);
        event.setRequestHeaders(HttpSenderUtil.getAllHead(HttpSenderUtil.toHeads(head)));
        return event;
    }

    @Override
    public HookOneHistory send(HookOneHistory history) {
        return httpControl(history, httpRetryTimes, HttpSenderUtil.toHeads(history.getRequestHeaders()));
    }

    @Override
    protected HookOneHistory send(HookOneHistory history, Header[] headers) {
        history.setHistoryStatus(WebHookHistoryStatus.ING.name());
        String url = history.getUrl();
        url = HttpSenderUtil.addUrlParamToUrl(url, history.getRequestParams());
        String requestBody = history.getRequestBody();
        if (log.isDebugEnabled()) {
            log.debug("Post request will be send, url：{}, body：{}", url, requestBody);
        }
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpPost httpPost = new HttpPost(url);
            HttpSenderUtil.addHeard(httpPost, new BasicHeader(HttpSenderUtil.REQUEST_METHOD, "POST"));
            HttpSenderUtil.addHeard(httpPost, new BasicHeader("Accept", "application/json,text/plain,application/*+json,*/*"));
            HttpSenderUtil.addHeard(httpPost, new BasicHeader("Accept-Language", "en-US,en;q=0.5"));
            HttpSenderUtil.addHeard(httpPost, new BasicHeader("Upgrade", "HTTP/2.0,SHTTP/1.3,IRC/6.9,RTA/x11"));
            HttpSenderUtil.addHeardParams(httpPost, headers);
            HttpSenderUtil.addHeard(httpPost, new BasicHeader("X-Event", history.getType()));
            HttpSenderUtil.addHeard(httpPost, new BasicHeader("X-Web-Hook-Event", history.getEventType()));
            HttpSenderUtil.addHeard(httpPost, new BasicHeader("X-Web-Hook-ID", history.getHookId()));
            HttpSenderUtil.addHeard(httpPost, new BasicHeader("X-Web-Hook-Action", "WEBHOOK_HTTP_POST"));
            HttpSenderUtil.addHeard(httpPost, new BasicHeader("User-Agent", "Service-Hook"));
            HttpSenderUtil.addHeard(httpPost, new BasicHeader("X-Web-Hook-History-ID", history.getId()));
            StringEntity entity = new StringEntity(requestBody, ContentType.create(httpPost.getFirstHeader(HttpSenderUtil.CONTENT_TYPE).getValue(), StandardCharsets.UTF_8));
            httpPost.setEntity(entity);
            history.setRequestHeaders(HttpSenderUtil.getAllHead(httpPost.getAllHeaders()));
            history.setRequestAt(System.currentTimeMillis());
            RequestConfig requestConfig = RequestConfig.custom()
                    .setConnectTimeout(connectTimout)
                    .setConnectionRequestTimeout(connectionRequestTimeout)
                    .build();
            httpPost.setConfig(requestConfig);
            try (CloseableHttpResponse response = httpClient.execute(httpPost)) {
                StatusLine statusLine = response.getStatusLine();
                int statusCode = statusLine.getStatusCode();
                history.setResponseAt(System.currentTimeMillis());
                history.setResponseCode(statusCode);
                history.setResponseStatus(statusLine.toString());
                history.setResponseResult(EntityUtils.toString(response.getEntity(), HttpSenderUtil.UTF_8));
                history.setResponseHeaders(HttpSenderUtil.getAllHead(response.getAllHeaders()));
                if (statusCode >= HttpStatus.SC_OK && statusCode < 300) {
                    history.setStatus(PingResult.SUCCEED.name());
                    history.setHistoryStatus(WebHookHistoryStatus.SUCCEED.name());
                    history.setHistoryMessage("Send over");
                } else {
                    history.setStatus(PingResult.FAILED.name());
                    String msg = String.format("Http POST request failed, url: %s, body: %s, http code: %s", url, requestBody, statusCode);
                    history.setHistoryStatus(WebHookHistoryStatus.FAILED.name());
                    history.setHistoryMessage(msg);
                    log.error(msg);
                }
            }
        } catch (Exception e) {
            history.setStatus(PingResult.FAILED.name());
            history.setHistoryStatus(WebHookHistoryStatus.FAILED.name());
            history.setHistoryMessage("Send failed, message" + e.getMessage());
            log.error("Http POST request failed, path: {}, body: {}, message: {}", url, requestBody, e);
        }
        return history;
    }
}
