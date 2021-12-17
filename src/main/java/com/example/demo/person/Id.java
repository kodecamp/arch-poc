package com.example.demo.person;

import lombok.Value;

import javax.validation.constraints.NotNull;

@Value
public class Id {
    @NotNull(message = "Id can not be null")
    String value;
}
