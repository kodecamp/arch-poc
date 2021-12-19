package com.walmart.commons.service;

import lombok.Builder;
import lombok.Getter;

@Getter
public class FailureDto implements BaseDto {
    private final String errorCode;
    private final String shortMessage;
    private final String details;

    private FailureDto(String errorCode, String shortMessage, String details) {
        this.errorCode = errorCode;
        this.shortMessage = shortMessage;
        this.details = details;
    }

    static public FailureDto from(String errorCode, String shortMessage, String details) {
        return new FailureDto(errorCode, shortMessage, details);
    }

    /**
     * <p>
     *     This method takes the <tt>ErrorCode</tt> and implements the
     *     Logic to fetch the message from other sources(Properties Files/db)
     * </p>
     *
     * @param errorCode
     * @return
     */
    static public FailureDto from(String errorCode) {
        String shortMessage = null;
        String details = null;
        return new FailureDto(errorCode, shortMessage, details);
    }

    static public FailureDto empty() {
        return new FailureDto("","","");
    }
}
