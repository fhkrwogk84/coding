package com.test.coding.business.place.domain.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.coding.business.place.domain.model.PlaceSearchHistory;
import com.test.coding.business.place.domain.repository.PlaceSearchHistoryRepository;
import com.test.coding.business.place.domain.service.dto.PlaceDTO;
import com.test.coding.business.place.domain.service.dto.PlaceDocumentsDTO;
import com.test.coding.business.place.domain.service.dto.PlaceSearchHistoryTop10DTO;
import com.test.coding.business.user.domain.model.User;
import com.test.coding.core.util.SessionUtil;
import com.test.coding.core.vo.PageDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class PlaceSearchHistoryServiceImpl implements PlaceSearchHistoryService {

    final String PLACE_SEARCH_URL = "https://dapi.kakao.com/v2/local/search/keyword.json";
    final String AUTHORIZATION_KEY_VALUE = "KakaoAK f7b9da8ba9aa79a2cc46e8ca913f80e5";

    @Autowired
    PlaceSearchHistoryRepository placeSearchHistoryRepository;

    @Autowired
    ObjectMapper objectMapper;

    @Override
    public PlaceSearchHistory save(String searchKeyword) {

        User loginUser = SessionUtil.getLoginUser();
        PlaceSearchHistory placeSearchHistory = new PlaceSearchHistory();
        placeSearchHistory.setUserId(loginUser.getUserId());
        placeSearchHistory.setSearchKeyword(searchKeyword);
        placeSearchHistory.setCreatedDate(new Date());

        return placeSearchHistoryRepository.save(placeSearchHistory);
    }

    @Override
    public List<PlaceSearchHistory> findByUserId() {
        User loginUser = SessionUtil.getLoginUser();
        return placeSearchHistoryRepository.findByUserIdOrderByCreatedDateDesc(loginUser.getUserId());
    }

    @Override
    @Cacheable(value = "hotSearchKeywordList")
    public List<PlaceSearchHistoryTop10DTO> countQueryByGroupBySearchKeyword() {
        return placeSearchHistoryRepository.countQueryByGroupBySearchKeyword();
    }

    @Override
    @CacheEvict(value = "hotSearchKeywordList")
    public void  removeCache() {}

    @Override
    public PageDTO<PlaceDocumentsDTO> getPlacePages(String searchKeyword, Pageable pageable){

        // 검색어가 없을 경우, 조회하지 앟는다.
        if(StringUtils.isEmpty(searchKeyword)){
            return null;
        }

        // 검색어 조회 로직
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(0, new StringHttpMessageConverter(Charset.forName("UTF-8")));

        final HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", AUTHORIZATION_KEY_VALUE);

        final HttpEntity<String> entity = new HttpEntity<String>(headers);
        String url = PLACE_SEARCH_URL+"?page="+pageable.getPageNumber()+"&size="+pageable.getPageSize()+"&query="+searchKeyword;

        ResponseEntity<Map> responseMap = restTemplate.exchange(url, HttpMethod.GET, entity, Map.class);

        PlaceDTO placeSearhResult = objectMapper.convertValue(responseMap.getBody(), PlaceDTO.class);

        PageDTO<PlaceDocumentsDTO> result = new PageDTO<>();
        result.setList(placeSearhResult.getDocuments());
        result.setPage(pageable);
        result.setTotalCount(placeSearhResult.getMeta().getTotalCount());

        return result;
    }


}
