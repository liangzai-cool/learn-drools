package org.xueliang.learndrools;

import org.apache.commons.lang3.StringUtils;
import org.kie.api.KieBase;
import org.kie.api.io.ResourceType;
import org.kie.api.runtime.KieSession;
import org.kie.internal.utils.KieHelper;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import static java.util.stream.Collectors.groupingBy;

public class DroolsProcessor {

    private final static ConcurrentMap<String, KieBase> KIE_BASE_CACHE = new ConcurrentHashMap<>();

    private final static ConcurrentMap<String, Rule> RULE_CACHE = new ConcurrentHashMap<>();

    private final static ConcurrentMap<String, List<Rule>> RULE_GROUP_CACHE = new ConcurrentHashMap<>();


    public void reload(List<Rule> ruleList) {
        RULE_GROUP_CACHE.clear();
        RULE_GROUP_CACHE.putAll(group(ruleList));
        RULE_GROUP_CACHE.forEach(this::reload);
    }

    public void reload(String groupId, List<Rule> ruleList) {
        KieHelper helper = new KieHelper();
        int[] count = new int[]{ 0 };
        ruleList.forEach(rule -> {
            RULE_CACHE.put(rule.getId(), rule);
            if (StringUtils.isNotEmpty(rule.getCode())) {
                ++count[0];
                helper.addContent(rule.getCode(), ResourceType.DRL);
            }
        });
        if (count[0] > 0) {
            KIE_BASE_CACHE.put(groupId, helper.build());
        }
    }

    private Map<String, List<Rule>> group(List<Rule> ruleList) {
        return ruleList.stream().collect(groupingBy(Rule::getGroupId));
    }

    public KieSession getStatelessKieSession(String groupId) {
        if (KIE_BASE_CACHE.containsKey(groupId)) {
            return KIE_BASE_CACHE.get(groupId).newKieSession();
        }
        return null;
    }

    public List<Rule> matchRule(String groupId, Object object) {
        KieSession kieSession = getStatelessKieSession(groupId);
        if (kieSession == null) {
            System.out.println("group id not exist, groupId: " + groupId);
            return Collections.emptyList();
        }
        MyAgendaEventListener myAgendaEventListener = new MyAgendaEventListener();
        kieSession.addEventListener(myAgendaEventListener);
        kieSession.insert(object);
        kieSession.fireAllRules();
        kieSession.dispose();
        List<Rule> matchedRuleList = new ArrayList<>();
        Set<String> matchedRuleIdSet = myAgendaEventListener.getFiredRules();
        matchedRuleIdSet.forEach(matchedRuleId -> {
            matchedRuleList.add(RULE_CACHE.get(matchedRuleId));
        });
        return matchedRuleList;
    }
}
