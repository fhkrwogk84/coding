package com.test.coding.business.place.domain.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;


@Getter
@Setter
@Entity(name = "placeSearchHistory")
public class PlaceSearchHistory implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 아이디
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long placeSearchHistoryId;

    /**
     * 사용자 아이디
     */
    private Long userId;

    /**
     * 검색 키워드
     */
    private String searchKeyword;

    /**
     * 생성일시 (검색일시)
     */
    private Date createdDate;
}
