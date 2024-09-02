package io.kit.hook.impl;

import io.kit.hook.server.WebHookHistoryService;
import io.kit.hook.server.WebHookService;
import io.kit.hook.work.sender.WebHookPostSender;
import io.kit.hook.vo.WebHookHistoryInfoVo;
import io.kit.hook.vo.WebHookInfoVo;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@Primary
public class WebHookHistoryServiceImpl  implements WebHookHistoryService<WebHookHistoryInfoVo> {
    @Autowired
    WebHookPostSender webHookPostSender;
    @Autowired
    WebHookService<WebHookInfoVo> webHookService;

    @Setter
    @Getter
    @Value("${webhook.history.maxLength:200}")
    private int maxHistoryLength;

    public WebHookHistoryServiceImpl() {
        super();
    }

//    @Override
//    public Page<WebHookHistoryInfoVo> list(HistoryPageParam pageParam, UserDetail userDetail, Locale locale) {
//        if (null == pageParam) {
//            return new Page<>(0L, Lists.newArrayList());
//        }
//        Criteria criteria = Criteria.where(ConstVariable.HOOK_ID).is(new ObjectId(pageParam.getHookId()));
//        AggregationPipeline pipeline = new AggregationPipeline()
//                .add(new MatchOperation(criteria))
//                .add(new UnwindOperation(Fields.fields("$hookEvent").asList().get(0), true))
//                .add(new SortOperation(Sort.by(Sort.Order.desc("$hookEvent.createAt"))))
//                .add(new GroupOperation(Fields.fields(ConstVariable.HOOK_ID))
//                        .push("$hookEvent").as(ConstVariable.HOOK_EVENT))
//                .add(new ProjectionOperation()
//                        .andInclude(ConstVariable.HOOK_ID)
//                        .and("_id").as(ConstVariable.HOOK_ID)
//                        .and(ConstVariable.HOOK_EVENT).slice(pageParam.getPageSize(), pageParam.getPageFrom()).as(ConstVariable.HOOK_EVENT)
//                        .and(ConstVariable.HOOK_EVENT).size().as("eventCount")
//                );
//        Aggregation aggregation = Aggregation.newAggregation(pipeline.getOperations());
//        AggregationResults<WebHookHistoryDto> aggregate = webHookHistoryRepository.aggregate(aggregation, WebHookHistoryDto.class);
//        List<WebHookHistoryDto> mappedResults = aggregate.getMappedResults();
//        if (CollUtil.isEmpty(mappedResults)) {
//            return new Page<>(0L, Lists.newArrayList());
//        }
//        List<WebHookHistoryDto> collect = mappedResults.stream()
//                .filter(Objects::nonNull)
//                .filter(h -> pageParam.getHookId().equals(h.getHookId()))
//                .collect(Collectors.toList());
//        if (CollUtil.isEmpty(collect)) {
//            return new Page<>(0L, Lists.newArrayList());
//        }
//        WebHookHistoryDto dto = collect.get(0);
//        long eventCount = dto.getEventCount();
//        List<HookOneHistory> hookEvents = dto.getHookEvent();
//        if (CollUtil.isEmpty(hookEvents)) {
//            return new Page<>(0L, Lists.newArrayList());
//        }
//        return new Page<>(
//                eventCount,
//                hookEvents.stream()
//                        .map(e -> {
//                            WebHookHistoryInfoVo infoVo = new WebHookHistoryInfoVo();
//                            copy(e, infoVo);
//                            infoVo.setHookId(dto.getHookId());
//                            infoVo.setId(e.getId().toHexString());
//                            infoVo.setCreateAt(e.getCreateAt().getTime());
//                            return infoVo;
//                        })
//                        .collect(Collectors.toList())
//        );
//    }
//
//    protected void copy(Object from, Object to) {
//        BeanUtils.copyProperties(from, to);
//    }
//
//    @Override
//    protected void beforeSave(WebHookHistoryDto dto, UserDetail userDetail) {
//        //do nothing
//    }
//
//    @Override
//    public WebHookHistoryInfoVo reSend(HookOneHistoryDto history, UserDetail user) {
//        String hookId = history.getHookId();
//        ObjectId historyId = history.getId();
//        if (null == historyId) {
//            throw new BizException("webhook.history.reSend");
//        }
//        WebHookInfoVo webHookInfo = webHookService.findWebHookByHookId(hookId, user);
//        if (null == webHookInfo) {
//            throw new BizException("webhook.info.existsById", hookId);
//        }
//        webHookService.checkUrl(history.getUrl());
//        Criteria criteria = Criteria.where(ConstVariable.HOOK_ID).is(MongoUtils.toObjectId(hookId));
//
//        AggregationPipeline pipeline = new AggregationPipeline()
//                .add(new MatchOperation(criteria))
//                .add(new ProjectionOperation()
//                        .andInclude(ConstVariable.HOOK_ID, "_id", ConstVariable.HOOK_EVENT)
//                        .and(ConstVariable.HOOK_EVENT)
//                        .filter(ConstVariable.HOOK_EVENT,
//                                ConditionalOperators.Cond.when(Criteria.where("$$hookEvent._id").is(historyId)).then(true).otherwise(false)
//                        ).as(ConstVariable.HOOK_EVENT)
//                );
//        Aggregation aggregation = Aggregation.newAggregation(pipeline.getOperations());
//        AggregationResults<WebHookHistory> aggregate = webHookHistoryRepository.aggregate(aggregation, WebHookHistory.class);
//        List<WebHookHistory> results = aggregate.getMappedResults();
//        HookOneHistory toBeSend = new HookOneHistory();
//
//        boolean onlyUpdate = false;
//        if (CollUtil.isEmpty(results)) {
//            copy(history, toBeSend);
//        } else {
//            WebHookHistory webHookHistory = results.stream().filter(Objects::nonNull).collect(Collectors.toList()).get(0);
//            List<HookOneHistory> hookEvent = webHookHistory.getHookEvent();
//            if (CollUtil.isEmpty(hookEvent)) {
//                copy(history, toBeSend);
//            } else {
//                Optional<HookOneHistory> first = hookEvent.stream()
//                        .filter(h -> Objects.nonNull(h) && Objects.nonNull(h.getId()) && historyId.toHexString().equals(h.getId().toHexString()))
//                        .findFirst();
//                if (!first.isPresent()) {
//                    copy(history, toBeSend);
//                } else {
//                    toBeSend = first.get();
//                    onlyUpdate = true;
//                }
//            }
//        }
//        HookOneHistory post = webHookHttpUtil.post(toBeSend);
//        WebHookHistoryInfoVo vo = new WebHookHistoryInfoVo();
//        copy(post, vo);
//        vo.setHookId(hookId);
//
//        //save history
//        if (!onlyUpdate) {
//            pushHistory(hookId, Lists.newArrayList(post));
//        } else {
//            updateHistory(hookId, post, user);
//        }
//        WebHookInfoDto updatePingResult = new WebHookInfoDto();
//        updatePingResult.setId(MongoUtils.toObjectId(hookId));
//        updatePingResult.setPingResult(PingResult.valueOf(post.getStatus()));
//        webHookService.updatePingResult(updatePingResult);
//        vo.setCreateAt(post.getCreateAt().getTime());
//        return vo;
//    }
//
//    protected void updateHistory(String hookId, HookOneHistory history, UserDetail user) {
//        Query query = new Query(Criteria.where(ConstVariable.HOOK_ID).is(MongoUtils.toObjectId(hookId))
//                .and(ConstVariable.HOOK_EVENT).elemMatch(Criteria.where("_id").is(history.getId())));
//        Update update = new Update().set("hookEvent.$", history);
//        update(query, update, user);
//    }
//
//    @Override
//    public long pushHistory(String hookId, List<HookOneHistory> history) {
//        if (CollUtil.isEmpty(history)) {
//            return 0L;
//        }
//        Pair<Query, Update> pair = buildOneHistory(hookId, history);
//        UpdateResult result = repository.upsert(pair.getFirst(), pair.getSecond());
//        return result.getMatchedCount();
//    }
//
//    protected Pair<Query, Update> buildOneHistory(String hookId, List<HookOneHistory> history) {
//        List<HookOneHistory> collect = history.stream()
//                .filter(Objects::nonNull)
//                .map(h -> {
//                    h.setCreateAt(new Date());
//                    if (null == h.getId()) {
//                        h.setId(new ObjectId());
//                    }
//                    return h;
//                }).collect(Collectors.toList());
//        Query query = Query.query(Criteria.where(ConstVariable.HOOK_ID).is(MongoUtils.toObjectId(hookId)));
//        Update update = new Update().push(ConstVariable.HOOK_EVENT)
//                .slice(-maxHistoryLength)
//                .each(collect);
//        return Pair.of(query, update);
//    }
//
//    @Override
//    public long pushManyHistory(List<Pair<String, List<HookOneHistory>>> historyInfos) {
//        if (CollUtil.isEmpty(historyInfos)) {
//            return 0L;
//        }
//        List<Pair<Query, Update>> queryParams = new ArrayList<>();
//        for (Pair<String, List<HookOneHistory>> historyInfo : historyInfos) {
//            String hookId = historyInfo.getFirst();
//            List<HookOneHistory> history = historyInfo.getSecond();
//            if (StringUtils.isBlank(hookId) || CollUtil.isEmpty(history)) {
//                continue;
//            }
//            queryParams.add(buildOneHistory(hookId, history));
//        }
//        if (CollUtil.isEmpty(queryParams)) {
//            return 0L;
//        }
//        BulkWriteResult execute = repository.bulkOperations(BulkOperations.BulkMode.ORDERED)
//                .upsert(queryParams)
//                .execute();
//        return execute.getMatchedCount();
//    }
//
//    @Override
//    public void deleteHookHistory(String hookId, UserDetail user) {
//        Query query = Query.query(Criteria.where(ConstVariable.HOOK_ID).is(MongoUtils.toObjectId(hookId)));
//        deleteAll(query, user);
//    }
}
