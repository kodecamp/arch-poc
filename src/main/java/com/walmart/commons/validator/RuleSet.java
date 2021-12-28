package com.walmart.commons.validator;

import java.util.*;
import java.util.function.Supplier;

import static java.util.stream.Collectors.*;

public class RuleSet<T> {

    private List<Rule<T>> ruleList ;

    private RuleSet() {
    }

    @SafeVarargs
    static public <T> RuleSet<T> of(Rule<T> ...rules) {
        RuleSet<T> ruleSet = new RuleSet<>();
        ruleSet.ruleList = Arrays.stream(rules).collect(toList());
        return ruleSet;
    }

    public T run() {
       return this.ruleList.stream()
               .filter(rule -> rule.condition().get())
               .map(rule -> rule.code().get()).findFirst().orElse(null);

    }

    public T orElse(T defaultObj) {
        return this.ruleList.stream()
                .filter(rule -> rule.condition().get())
                .map(rule -> rule.code().get())
                .findFirst()
                .orElse(defaultObj);

    }

    public T orElse(Supplier<T> defaultCond) {
        return this.ruleList.stream()
                .filter(rule -> rule.condition().get())
                .map(rule -> rule.code().get())
                .findFirst()
                .orElse(defaultCond.get());

    }

    public static void main(String[] args) {
       test(11);
    }

    static public void test(int value) {
        int result = 0;

        if(value < 10) {
            result = value + 10;
        }
        else if (value > 10 ) {
            result = value - 10;
        }
        else {
            result = value * 100;
        }

        System.out.println("result = " + result);


        Integer rs  = RuleSet
                .of(Rule.is(() -> value < 10,
                            () -> value + 10),

                    Rule.is(() -> value > 10,
                            () -> value - 10))

                .orElse(() -> value * 100);

        System.out.println("rs = " + rs);
        

    }




}
