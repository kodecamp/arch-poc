package com.walmart.commons.controller;

public enum ErrorCodes {
	SERVER_ERROR("Something went wrong."),
	NOT_FOUND("Content not found.");

	private final String desc;

	private ErrorCodes(String desc) {
		this.desc = desc;
	}

	public String value() {
		return desc;
	}

}
