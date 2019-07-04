package com.test.coding.business.place.domain.repository;

import com.test.coding.business.place.domain.model.PlaceSearchHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PlaceSearchHistoryRepository extends JpaRepository<PlaceSearchHistory, Long>, PlaceSearchHistoryRepositoryExtend {

    /**
     * 사용자 검색 기록 저장
     *
     * @param placeSearchHistory 장소검색 히스토리
     * @return
     */
    PlaceSearchHistory save(PlaceSearchHistory placeSearchHistory);

    /**
     * 사용자의 검색 기록 조회 (최신순)
     *
     * @param userId 사용자 아이디
     * @return
     */
    List<PlaceSearchHistory> findByUserIdOrderByCreatedDateDesc(long userId);

}
