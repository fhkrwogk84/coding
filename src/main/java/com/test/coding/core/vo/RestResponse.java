package com.test.coding.core.vo;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;


public class RestResponse implements Serializable {

    /*-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
    | Private Variables
    |-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=*/
    private static final long serialVersionUID = -7857710275656843711L;

    // Data
    @Getter
    @Setter
    private Object data;

    @Getter
    @Setter
    private RestResponseMeta meta = new RestResponseMeta();

    /*-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
    | Constructor
    |-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=*/

    /**
     *
     */
    public RestResponse() {
    }

    /**
     * 
     */
    @SuppressWarnings("rawtypes")
    public RestResponse(Object data) {
        this.data = data;

        if (data instanceof PageDTO) {
            // PageDTO는 list가 data 자체가 되게함.
            this.data = ((PageDTO) data).getList();
            this.meta.setPage(((PageDTO) data).getPage());
            this.meta.setTotalCount(((PageDTO) data).getTotalCount());
        }
    }

    /*-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
    | Getter & Setter Method
    |-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=*/
    /**
     * useMessage
     */
    public void setUserMessage(String message) {
        this.meta.setUserMessage(message);
    }

    /**
     * systemMessage
     */
    public void setSystemMessage(String systemMessage) {
        this.meta.setSystemMessage(systemMessage);
    }

    /**
     * code
     */
    public void setCode(String code) {
        this.meta.setCode(code);
    }

    /*-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
    | Public Method for chaining
    |-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=*/
    public RestResponse userMessage(String userMessage) {
        this.setUserMessage(userMessage);
        return this;
    }

    public RestResponse systemMessage(String systemMessage) {
        this.setSystemMessage(systemMessage);
        return this;
    }

    public RestResponse code(String code) {
        this.setCode(code);
        return this;
    }

    /*-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
    | Inner Class
    |-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=*/
    @Getter
    @Setter
    private class RestResponseMeta implements Serializable {
        private static final long serialVersionUID = 144476231638185217L;
        
        private String userMessage = "";
        private String systemMessage = "";
        private String code = "";
        private Pageable page;
        private int totalCount;
    }
}
