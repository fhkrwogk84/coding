package com.test.coding.business.place.domain.repository;

import com.test.coding.business.place.domain.service.dto.PlaceSearchHistoryTop10DTO;

import java.util.List;

public interface PlaceSearchHistoryRepositoryExtend {

    /**
     * 인기 검색어 (TOP10) 조회 (검색어-카운트)
     *
     * @return
     */
    List<PlaceSearchHistoryTop10DTO> countQueryByGroupBySearchKeyword();
}
