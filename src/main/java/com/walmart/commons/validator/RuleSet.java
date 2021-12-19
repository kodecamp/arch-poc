package com.walmart.commons.validator;

import java.util.*;
import static java.util.stream.Collectors.*;

public class RuleSet<T> {

    private List<Rule<T>> ruleList ;

    private RuleSet() {
    }

    @SafeVarargs
    static public <T> RuleSet<T> create(Rule<T> ...rules) {
        RuleSet<T> ruleSet = new RuleSet<>();
        ruleSet.ruleList = Arrays.stream(rules).collect(toList());
        return ruleSet;
    }

    public T run() {
       return this.ruleList.stream()
               .filter(rule -> rule.condition().get())
               .map(rule -> rule.code().get()).findFirst().orElse(null);

    }

    public T runWithDefault(T defaultObj) {
        return this.ruleList.stream()
                .filter(rule -> rule.condition().get())
                .map(rule -> rule.code().get())
                .findFirst()
                .orElse(defaultObj);

    }




}
