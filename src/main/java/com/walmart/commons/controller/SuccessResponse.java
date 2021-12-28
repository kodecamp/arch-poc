package com.walmart.commons.controller;

import java.util.LinkedHashMap;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Getter;


/**
* SuccessResponse
*/
@Getter
@JsonInclude(Include.NON_NULL)
public final class SuccessResponse extends LinkedHashMap<String,Object> {

	private SuccessResponse() {
	}

	static public SuccessResponse instanceOf() {
		return new SuccessResponse();
	}

	public LinkedHashMap add(String key, Object obj) {
	   this.put(key, obj);
	   return this;
	}

}


