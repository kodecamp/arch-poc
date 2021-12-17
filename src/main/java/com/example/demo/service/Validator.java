package com.example.demo.service;


@FunctionalInterface
public interface Validator<T> {
    public ValidationResult validate(T obj);
}
