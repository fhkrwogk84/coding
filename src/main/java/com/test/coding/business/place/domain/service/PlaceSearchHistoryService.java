package com.test.coding.business.place.domain.service;

import com.test.coding.business.place.domain.model.PlaceSearchHistory;
import com.test.coding.business.place.domain.service.dto.PlaceDocumentsDTO;
import com.test.coding.business.place.domain.service.dto.PlaceSearchHistoryTop10DTO;
import com.test.coding.core.vo.PageDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PlaceSearchHistoryService {

    /**
     * 사용자 검색 기록 저장
     *
     * @param searchKeyword 검색어
     * @return
     */
    PlaceSearchHistory save(String searchKeyword);

    /**
     * 사용자의 검색 기록 조회 (최신순)
     *
     * @return
     */
    List<PlaceSearchHistory> findByUserId();

    /**
     * 인기 검색어 (TOP10) 조회 (검색어-카운트)
     *
     * @return
     */
    List<PlaceSearchHistoryTop10DTO> countQueryByGroupBySearchKeyword();

    /**
     * 인기 검색어 캐시 삭제
     */
    void removeCache();

    /**
     * 카카오 API를 통한 장소 검색
     *
     * @param searchKeyword 검색어
     * @param pageable 페이징
     *
     * @return
     */
    PageDTO<PlaceDocumentsDTO> getPlacePages(String searchKeyword, Pageable pageable);
}
