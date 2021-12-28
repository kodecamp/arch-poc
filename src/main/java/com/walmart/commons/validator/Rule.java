package com.walmart.commons.validator;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.function.Supplier;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class Rule<T> {

    private final Supplier<Boolean> condition;
    private final Supplier<T> code;
    private final Supplier<T> codeOtherwise;

    public Supplier<Boolean> condition() {
        return this.condition;
    }

    public Supplier<T> code() {
        return this.code;
    }

    public Supplier<T> codeOtherwise() {
        return this.codeOtherwise;
    }

    static public <T> Rule<T> is(Supplier<Boolean> cond, Supplier<T> code, Supplier<T> codeOtherwise) {
       return new Rule<>(cond, code, codeOtherwise);
    }

    static public <T> Rule<T> is(Supplier<Boolean> cond, Supplier<T> code) {
        return new Rule<>(cond, code, null);
    }



    static public Context ctx() {
        return new Context<>();
    }

    public T run() {
        return null;
    }

    public static class Context<T> {
        private Supplier<Boolean> cond;
        private Supplier<T> block;
        private Supplier<T> alternateBlock;

        public Context(Supplier<Boolean> cond, Supplier<T> block, Supplier<T> alternateBlock) {
            this.cond = cond;
            this.block = block;
            this.alternateBlock = alternateBlock;
        }

        public Context() {
            this(null, null, null);
        }

        public Context when(Supplier<Boolean> cond) {
            this.cond = cond;
            return this;
        }

        public Context then(Supplier<T> block) {
            this.block = block;
            return this;
        }

        public Context otherwise(Supplier<T> alternateBlock) {
            this.alternateBlock = alternateBlock;
            return this;
        }

        public T run() {
            return this.cond.get() ? this.block.get() : this.alternateBlock.get();
        }


    }





}
