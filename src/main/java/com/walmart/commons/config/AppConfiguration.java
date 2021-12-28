package com.walmart.commons.config;

import org.springframework.context.MessageSource;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;


public class AppConfiguration {

    private static final String MESSAGES_FILE_PATH = "classpath:/app_messages";

    static public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource
                = new ReloadableResourceBundleMessageSource();
        messageSource.setBasenames(
                MESSAGES_FILE_PATH
        );
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }
}
