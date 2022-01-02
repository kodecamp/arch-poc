package com.walmart.commons.dto;

import com.walmart.commons.config.AppConfiguration;
import lombok.Getter;

import java.util.Arrays;
import java.util.Locale;

@Getter
public class ErrorDto {
    private final String errorCode;
    private final String message;


    private ErrorDto(String errorCode, String msg) {
        this.errorCode = errorCode;
        this.message = msg;
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
    static public ErrorDto from(String errorCode) {
        // this can be fetched from properties file
//        String shortMessage = "Message from properties file";
        String shortMessage = AppConfiguration
                .messageSource()
                .getMessage(errorCode, null, Locale.ENGLISH);
        return new ErrorDto(errorCode, shortMessage);
    }

    static public ErrorDto from(String errorCode, String ...params) {
        System.out.println("errorCode = " + errorCode + ", params = " + Arrays.deepToString(params));
        // this can be fetched from properties file
//        String shortMessage = "Message from properties file";
        String shortMessage = AppConfiguration
                .messageSource()
                .getMessage(errorCode, params, Locale.ENGLISH);
        return new ErrorDto(errorCode, shortMessage);
    }

    static public ErrorDto empty() {
        return new ErrorDto("","");
    }
}
