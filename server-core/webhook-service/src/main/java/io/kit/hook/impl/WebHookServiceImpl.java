package io.kit.hook.impl;

import io.kit.hook.server.WebHookAdapterService;
import io.kit.hook.server.WebHookService;
import io.kit.hook.vo.WebHookInfoVo;
import io.kit.hook.work.sender.WebHookAbstractSender;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@Primary
public class WebHookServiceImpl implements WebHookService<WebHookInfoVo> {
    public static final String URL_INVALID_CODE = "webhook.url.invalid";
    @Autowired
    WebHookAbstractSender webHookHttpUtil;
    @Autowired
    WebHookAdapterService webHookAdapter;
    @Autowired
    ApplicationContext applicationContext;
    @Setter
    @Getter
    @Value("${webhook.info.maxHttpHeadersLength:1024}")
    private int maxHttpHeadersLength;
    @Setter
    @Getter
    @Value("${webhook.info.maxCustomTemplateLength:4096}")
    private int maxCustomTemplateLength;
    @Setter
    @Getter
    @Value("${webhook.info.maxMarkLength:512}")
    private int maxMarkLength;
    @Setter
    @Getter
    @Value("${webhook.info.maxURLLength:2083}")
    private int maxURLLength;

    public WebHookServiceImpl() {

    }

//    @Override
//    protected void beforeSave(WebHookInfoDto dto, UserDetail userDetail) {
//        ObjectId id = dto.getId();
//        if (null == id) {
//            dto.setId(new ObjectId());
//        }
//        dto.setUserId(userDetail.getUserId());
//    }
//
//    @Override
//    public Page<WebHookInfoVo> list(Filter filter, UserDetail userDetail, Locale locale) {
//        Page<WebHookInfoDto> webHookInfoDtoPage = find(filter, userDetail);
//        List<WebHookInfoDto> all = webHookInfoDtoPage.getItems();
//        if (CollUtil.isEmpty(all)) {
//            return new Page<>(webHookInfoDtoPage.getTotal(), Lists.newArrayList());
//        }
//        return new Page<>(
//                webHookInfoDtoPage.getTotal(),
//                all.stream().filter(Objects::nonNull)
//                        .map(d -> dtoToVo(d, new WebHookInfoVo()))
//                        .filter(Objects::nonNull)
//                        .collect(Collectors.toList())
//        );
//    }
//
//    @Override
//    public WebHookInfoVo findWebHookByHookId(String hookId, UserDetail user) {
//        Query query = Query.query(Criteria.where("_id").is(MongoUtils.toObjectId(hookId)));
//        WebHookInfoDto one = findOne(query, user);
//        return dtoToVo(one, new WebHookInfoVo());
//    }
//
//    @Override
//    public WebHookInfoVo create(WebHookInfoDto dto, UserDetail user) {
//        checkWebHookInfo(dto);
//        String hookName = dto.getHookName();
//        if (StringUtils.isBlank(hookName)) {
//            dto.setHookName(MessageUtil.getMessage("webhook.create.hook.defaultName"));
//        }
//        if (null == dto.getOpen()) {
//            dto.setOpen(true);
//        }
//        WebHookInfo insert = webHookRepository.insert(convertToEntity(WebHookInfo.class, dto), user);
//        return copy(insert);
//    }
//
//    protected WebHookInfoVo copy(WebHookInfo info){
//        WebHookInfoVo webHookInfoVo = new WebHookInfoVo();
//        BeanUtils.copyProperties(info, webHookInfoVo);
//        return webHookInfoVo;
//    }
//
//    @Override
//    public WebHookInfoVo update(WebHookInfoDto dto, UserDetail user) {
//        ObjectId id = dto.getId();
//        Query query = Query.query(Criteria.where("_id").is(dto.getId()));
//        String checkUrl = dto.getUrl();
//        if (null == id) {
//            dto.setId(new ObjectId());
//        }
//        WebHookInfoDto one = findOne(query, user);
//        if (null != one && null == checkUrl) {
//            checkUrl = one.getUrl();
//        }
//        dto.setUrl(checkUrl);
//        checkWebHookInfo(dto);
//        if (null == one) {
//            return create(dto, user);
//        }
//        WebHookInfo info = new WebHookInfo();
//        BeanUtils.copyProperties(dto, info);
//        info.setId(null);
//        repository.updateByWhere(query, info, user);
//        return dtoToVo(findOne(query, user), new WebHookInfoVo());
//    }
//
//    @Override
//    public WebHookInfoVo updatePingResult(WebHookInfoDto dto) {
//        Query query = Query.query(Criteria.where("_id").is(dto.getId()));
//        update(query, Update.update("pingResult", dto.getPingResult()));
//        return dtoToVo(findOne(query), new WebHookInfoVo());
//    }

//    @Override
//    public List<WebHookInfoVo> close(String[] ids, UserDetail userDetail) {
//        return updateParamByIds(ids, "open", false, userDetail);
//    }
//
//    @Override
//    public void delete(String[] ids, UserDetail userDetail) {
//        List<ObjectId> idList = new ArrayList<>();
//        for (String id : ids) {
//            try {
//                idList.add(MongoUtils.toObjectId(id));
//            } catch (Exception e) {
//                log.warn("Can not close WebHook by an invalid WebHook id: {}", id);
//            }
//        }
//        if (!idList.isEmpty()) {
//            Query query = Query.query(Criteria.where("_id").in(idList));
//            deleteAll(query, userDetail);
//        }
//    }
//
//    @Override
//    public List<WebHookInfoDto> findMyOpenHookInfoList(String hookType, String metric, List<String> userId) {
//        List<String> match = Lists.newArrayList();
//        if (StringUtils.isNotBlank(hookType)) {
//            match.add(hookType);
//        }
//        if (StringUtils.isNotBlank(metric)) {
//            match.add(metric);
//        }
//        Criteria criteria = Criteria.where("userId").in(userId)
//                .and("open").is(true)
//                .and("hookTypes").in(match);
//        Query query = Query.query(criteria);
//        return findAll(query);
//    }
//
//    protected WebHookInfoVo dtoToVo(WebHookInfoDto dto, WebHookInfoVo vo) {
//        if (null == dto) return null;
//        if (null == vo) {
//            vo = new WebHookInfoVo();
//        }
//        BeanUtils.copyProperties(dto, vo);
//        return vo;
//    }
//
//    @Override
//    public HookOneHistoryDto ping(WebHookInfoDto webHookEvent, UserDetail userDetail) {
//        WebHookEvent event = WebHookEvent.of()
//                .withEvent(PingWebHookConverter.PING_TEMPLATE)
//                .withUserId(Lists.newArrayList(userDetail.getUserId()))
//                .withMetric(HookType.PING.getHookName())
//                .withType(HookType.PING.getHookName());
//        String url = webHookEvent.getUrl();
//        if (!webHookHttpUtil.checkURL(url)) {
//            throw new BizException(URL_INVALID_CODE, url);
//        }
//        HookOneHistory send;
//        ObjectId webHookId = webHookEvent.getId();
//        if (null == webHookId) {
//            webHookEvent.setId(new ObjectId());
//            send = webHookAdapter.send(event, webHookEvent);
//        } else {
//            send = webHookAdapter.sendAndSave(event, webHookEvent);
//            WebHookInfoDto updatePingResult = new WebHookInfoDto();
//            updatePingResult.setId(webHookId);
//            updatePingResult.setPingResult(PingResult.valueOf(send.getStatus()));
//            updatePingResult(updatePingResult);
//        }
//        if (null == send || !PingResult.SUCCEED.name().equals(send.getStatus())) {
//            throw new BizException("webhook.ping.failed", null == send ? "" : Optional.ofNullable(send.getResponseStatus()).orElse(""));
//        }
//        return copy(send);
//    }
//
//    protected HookOneHistoryDto copy(HookOneHistory send) {
//        HookOneHistoryDto dto = new HookOneHistoryDto();
//        BeanUtils.copyProperties(send, dto);
//        return dto;
//    }
//
//    @Override
//    public List<WebHookInfoVo> reOpen(String[] ids, UserDetail userDetail) {
//        return updateParamByIds(ids, "open", true, userDetail);
//    }
//
//    protected List<WebHookInfoVo> updateParamByIds(String[] ids, String key, Object value, UserDetail userDetail) {
//        Map<String, Object> newParams = new HashMap<>();
//        newParams.put(key, value);
//        return updateParamByIds(ids, newParams, userDetail);
//    }
//
//    protected List<WebHookInfoVo> updateParamByIds(String[] ids, Map<String, Object> updateInfo, UserDetail userDetail) {
//        List<ObjectId> idList = new ArrayList<>();
//        for (String id : ids) {
//            try {
//                idList.add(MongoUtils.toObjectId(id));
//            } catch (Exception e) {
//                log.warn("Can not set new value {} by an invalid WebHook id: {}", updateInfo, id);
//            }
//        }
//        if (!idList.isEmpty()) {
//            Query query = Query.query(Criteria.where("_id").in(idList));
//            Update update = new Update();
//            updateInfo.forEach(update::set);
//            update(query, update, userDetail);
//            List<WebHookInfoDto> all = findAll(query);
//            if (CollUtil.isEmpty(all)) {
//                return Lists.newArrayList();
//            }
//            return all.stream().filter(Objects::nonNull)
//                    .map(d -> dtoToVo(d, new WebHookInfoVo()))
//                    .filter(Objects::nonNull)
//                    .collect(Collectors.toList());
//        }
//        return Lists.newArrayList();
//    }
//
//    @Override
//    public void checkUrl(String url) {
//        if(StringUtils.isBlank(url)) {
//            throw new BizException(URL_INVALID_CODE, "");
//        }
//        if (url.length() > maxURLLength) {
//            throw new BizException("webhook.url.too.long", maxURLLength, url.length());
//        }
//        if (!webHookHttpUtil.checkURL(url)) {
//            throw new BizException(URL_INVALID_CODE, url);
//        }
//
//        try {
//            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
//            URL requestUrl = new URL(request.getRequestURL().toString());
//            URL u = new URL(url);
//            if (requestUrl.getHost().equals(u.getHost()) && requestUrl.getPort() == u.getPort()) {
//                throw new BizException("webhook.url.host.invalid", String.format("%s:%d", u.getHost() , u.getPort()));
//            }
//        } catch (Exception e) {
//            if (e instanceof BizException) {
//                throw (BizException)e;
//            }
//            log.warn(e.getMessage());
//        }
//    }
//
//    protected void checkWebHookInfo(WebHookInfoDto dto) {
//        checkUrl(dto.getUrl());
//        checkCustomTemplate(dto.getCustomTemplate());
//        checkCustomHttpHeaders(dto.getCustomHttpHeaders());
//        checkMark(dto.getMark());
//    }
//
//    protected void checkMark(String mark) {
//        if (StringUtils.isNotBlank(mark) && mark.length() > maxMarkLength) {
//            throw new BizException("webhook.info.mark.too.long", maxMarkLength, mark.length());
//        }
//    }
//
//    protected void checkCustomTemplate(String customTemplate) {
//        if (StringUtils.isNotBlank(customTemplate)) {
//            if (customTemplate.length() > maxCustomTemplateLength) {
//                throw new BizException("webhook.info.custom.template.too.long", maxCustomTemplateLength, customTemplate.length());
//            }
//            try {
//                TapSimplify.fromJsonObject(customTemplate);
//            } catch (Exception e) {
//                throw new BizException("webhook.info.custom.template.invalid");
//            }
//        }
//    }
//
//    protected void checkCustomHttpHeaders(String customHttpHeaders) {
//        if (StringUtils.isNotBlank(customHttpHeaders)) {
//            if (customHttpHeaders.length() > maxHttpHeadersLength) {
//                throw new BizException("webhook.info.custom.http.headers.too.long", maxHttpHeadersLength, customHttpHeaders.length());
//            }
//            String[] headers = customHttpHeaders.split("\n");
//            for (String header : headers) {
//                checkHeard(header);
//            }
//        }
//    }
//
//    protected void checkHeard(String header) {
//        if (!header.contains(":")) {
//            throw new BizException("webhook.info.custom.http.headers.invalid", header);
//        }
//        String[] split = header.split(":");
//        String key = "";
//        if (split.length > 0) {
//            key = String.valueOf(split[0]).trim();
//        }
//        if (split.length < 1 || key.contains(" ")) {
//            throw new BizException("webhook.info.custom.http.headers.invalid", header);
//        }
//    }
}
