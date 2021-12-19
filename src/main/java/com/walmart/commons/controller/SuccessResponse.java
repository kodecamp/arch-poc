package com.walmart.commons.controller;

import java.util.Collection;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Builder;
import lombok.Getter;


/**
* SuccessResponse
*/
@Builder
@Getter
@JsonInclude(Include.NON_NULL)
public final class SuccessResponse<T> implements ResponseObj  {
	private final T responseValue;
	private final Collection<T> responseValues;

}


