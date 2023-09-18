package org.xueliang.learndrools;

import org.kie.api.event.rule.AfterMatchFiredEvent;
import org.kie.api.event.rule.DefaultAgendaEventListener;

import java.util.*;

public class MyAgendaEventListener extends DefaultAgendaEventListener {

    private Map<String, Integer> rulesFired = new HashMap<>();

    private List<String> rulesFiredOrder = new ArrayList<>();

    @Override
    public void afterMatchFired(final AfterMatchFiredEvent event) {
        String rule = event.getMatch().getRule().getName();
        if (isRuleFired(rule)) {
            rulesFired.put(rule, rulesFired.get(rule) + 1);
        } else {
            rulesFired.put(rule, 1);
        }
        rulesFiredOrder.add(rule);
    }

    /**
     * Return true if the rule was fired at least once
     * @param rule - name of the rule
     * @return true if the rule was fired
     */
    public boolean isRuleFired(final String rule) {
        return rulesFired.containsKey(rule);
    }

    public Set<String> getFiredRules() {
        return rulesFired.keySet();
    }
}
