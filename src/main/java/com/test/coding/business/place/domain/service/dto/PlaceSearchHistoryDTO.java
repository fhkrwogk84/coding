package com.test.coding.business.place.domain.service.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
public class PlaceSearchHistoryDTO implements Serializable {

    private static final long serialVersionUID = 1008365157420151778L;

    private String searchKeyword;

    private Date createdDate;

}
