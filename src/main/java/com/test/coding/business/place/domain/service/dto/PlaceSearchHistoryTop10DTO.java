package com.test.coding.business.place.domain.service.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;


@Getter
@Setter
public class PlaceSearchHistoryTop10DTO implements Serializable {

    private static final long serialVersionUID = 614276909639579141L;

    private Long cnt;
    private String searchKeyword;

    public PlaceSearchHistoryTop10DTO(){}

    public PlaceSearchHistoryTop10DTO(Long cnt, String searchKeyword){
        this.cnt = cnt;
        this.searchKeyword = searchKeyword;
    }




}
