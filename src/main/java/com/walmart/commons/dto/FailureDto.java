package com.walmart.commons.dto;

import com.walmart.commons.config.AppConfiguration;
import lombok.Getter;

import java.util.Arrays;
import java.util.Locale;

@Getter
public class FailureDto implements BaseDto {
    private final String errorCode;
    private final String message;


    private FailureDto(String errorCode, String msg) {
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
    static public FailureDto from(String errorCode) {
        // this can be fetched from properties file
//        String shortMessage = "Message from properties file";
        String shortMessage = AppConfiguration
                .messageSource()
                .getMessage(errorCode, null, Locale.ENGLISH);
        return new FailureDto(errorCode, shortMessage);
    }

    static public FailureDto from(String errorCode, String ...params) {
        System.out.println("errorCode = " + errorCode + ", params = " + Arrays.deepToString(params));
        // this can be fetched from properties file
//        String shortMessage = "Message from properties file";
        String shortMessage = AppConfiguration
                .messageSource()
                .getMessage(errorCode, params, Locale.ENGLISH);
        return new FailureDto(errorCode, shortMessage);
    }

    static private String loadFromProps(String errorCode) {
       return "" ;
    }

    static public FailureDto empty() {
        return new FailureDto("","");
    }
}
