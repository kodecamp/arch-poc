package com.example.demo.person;

import lombok.Value;

import javax.validation.constraints.NotNull;

@Value
public class Name {
    @NotNull(message="Name can not be null")
    String value;
}
