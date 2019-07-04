package com.test.coding.core.exception;

import com.test.coding.core.util.MessageByLocale;
import com.test.coding.core.vo.RestResponse;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.HttpURLConnection;

/**
 * Controller에 대한 Exception 처리하는 Advice
 */
@Slf4j
@ControllerAdvice
public class ExceptionControllerAdvice {

    private static final String API = "/api/";

    @Autowired
    MessageByLocale MessageByLocale;

    /**
     * Not Found (NoHandlerFoundException)에 대한 핸들러
     */
    @ExceptionHandler(NoHandlerFoundException.class)
    public Object notFoundNoHandlerHandle(NoHandlerFoundException ex, HttpServletRequest request,HttpServletResponse response) {

        ExceptionInfo info = new ExceptionInfo(ex);
        info.setUserMessage(MessageByLocale.getMessage("common.exception.notfound"));
        info.setSystemMessage(ex.getMessage());
        info.setHttpStatus(HttpStatus.NOT_FOUND);
        info.setTemplateName(String.valueOf(java.net.HttpURLConnection.HTTP_NOT_FOUND));

        return makeResponse(info, request, response);
    }


    /**
     * Exception / RuntimeException 타입에 대한 핸들러.
     */
    @ExceptionHandler({ Exception.class, RuntimeException.class })
    public Object exceptionHandle(Exception ex, HttpServletRequest request, HttpServletResponse response) {

        ExceptionInfo info = new ExceptionInfo(ex);
        info.setUserMessage(MessageByLocale.getMessage("common.exception.server"));
        info.setSystemMessage(ex.getMessage());
        info.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        info.setTemplateName(String.valueOf(HttpURLConnection.HTTP_INTERNAL_ERROR));

        return makeResponse(info, request, response);
    }

    /**
     * response를 위한 객체 생성.
     */
    private Object makeResponse(ExceptionInfo info, HttpServletRequest request, HttpServletResponse response) {

        info.e.printStackTrace();

        response.setStatus(info.getHttpStatus().value());

        MediaType requestMediaType = null;
        if (request.getContentType() != null) {
            requestMediaType = MediaType.valueOf(request.getContentType());
        }

        String apiPath = request.getContextPath() + API;
        // /api 로 시작하거나, content-type이 application/json 이면. rest-api
        if (request.getServletPath().startsWith(apiPath) || MediaType.APPLICATION_JSON.isCompatibleWith(requestMediaType)) {
            RestResponse restResponse = new RestResponse().userMessage(info.userMessage).systemMessage(info.getSystemMessage()).code(info.getCode());
            return new ResponseEntity<RestResponse>(restResponse, info.getHttpStatus());
        }
        // 아니면 view
        else {
            ModelAndView mav = new ModelAndView();
            mav.addObject("userMessage", info.getUserMessage());
            mav.addObject("systemMessage", info.getSystemMessage());
            mav.setViewName("view/error/error-" + info.getTemplateName());
            return mav;
        }
    }

    @Getter
    @Setter
    private class ExceptionInfo {
        private Throwable e;
        private Throwable cause;
        private String userMessage;
        private String systemMessage;
        private String code;
        private HttpStatus httpStatus;
        private String templateName;

        public ExceptionInfo(Throwable e) {
            this.e = e;
            this.cause = e.getCause();
        }
    }

}
