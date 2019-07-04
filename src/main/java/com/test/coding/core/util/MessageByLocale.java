package com.test.coding.core.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.servlet.LocaleResolver;

@Configuration
public class MessageByLocale {

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private LocaleResolver localeResolver;

    public String getMessage(String code) {
        return messageSource.getMessage(code, null, null, LocaleContextHolder.getLocale());
    }

    public String getMessage(String code, Object[] args) {
        return messageSource.getMessage(code, args, null, LocaleContextHolder.getLocale());
    }

    public String getMessage(String code, Object[] args, String defaultMessage) {
        return messageSource.getMessage(code, args, defaultMessage, LocaleContextHolder.getLocale());
    }
}
