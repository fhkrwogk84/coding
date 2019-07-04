package com.test.coding.business.place.domain.repository;

import com.test.coding.business.place.domain.service.dto.PlaceSearchHistoryTop10DTO;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

public class PlaceSearchHistoryRepositoryImpl implements PlaceSearchHistoryRepositoryExtend {

    @PersistenceContext
    EntityManager em;

    @Override
    public List<PlaceSearchHistoryTop10DTO> countQueryByGroupBySearchKeyword() {

        // Query 생성
        String sql = "SELECT new com.test.coding.business.place.domain.service.dto.PlaceSearchHistoryTop10DTO(count(psh) as cnt, searchKeyword) FROM placeSearchHistory psh GROUP BY searchKeyword ORDER BY cnt DESC";

        TypedQuery<PlaceSearchHistoryTop10DTO> query= em.createQuery(sql, PlaceSearchHistoryTop10DTO.class).setMaxResults(10);
        List result = query.getResultList();

        return result;
    }

}
