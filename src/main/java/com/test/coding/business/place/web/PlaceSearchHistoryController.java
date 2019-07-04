package com.test.coding.business.place.web;

import com.test.coding.business.place.domain.service.PlaceSearchHistoryService;
import com.test.coding.business.place.domain.service.dto.PlaceDocumentsDTO;
import com.test.coding.core.vo.PageDTO;
import com.test.coding.core.vo.RestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class PlaceSearchHistoryController {

    @Autowired
    private PlaceSearchHistoryService placeSearchHistoryService;

    /*-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
    | VIEW
    |-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=*/

    /**
     * 장소 검색
     *
     * @param searchKeyword 검색어
     * @param pageable  페이징
     * @return
     */
    @GetMapping("/view/place-search")
    public ModelAndView placeSearchView(String searchKeyword, @PageableDefault(size = 10, page = 1) Pageable pageable) {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("view/place-search");
        modelAndView.addObject("result", new PageDTO<PlaceDocumentsDTO>());
        modelAndView.addObject("pageNumber", pageable.getPageNumber());
        modelAndView.addObject("title", pageable.getPageNumber());

        // 검색 키워드가 없는 경우, 검색을 하지 않는다.
        if(!StringUtils.isEmpty(searchKeyword)){

            // 페이징일 경우 검색어가 저장되지 않도록 한다.
            if(pageable.getPageNumber() == 1) {
                placeSearchHistoryService.save(searchKeyword);
            }

            PageDTO<PlaceDocumentsDTO> searchedPlace = placeSearchHistoryService.getPlacePages(searchKeyword, pageable);
            modelAndView.addObject("result", searchedPlace);
            modelAndView.addObject("searchKeyword", searchKeyword);
        }

        return modelAndView;
    }

    /**
     * 장소 검색 - 상세
     *
     * @return
     */
    @GetMapping("/view/place-search/detail")
    public ModelAndView placeSearchDetailView() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("view/place-search-detail");
        return modelAndView;
    }

    /**
     * 내 검색 히스토리
     *
     * @return
     */
    @GetMapping("/view/my-search-history")
    public ModelAndView mySearchHistoryView() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("view/my-search-history");
        modelAndView.addObject("result", placeSearchHistoryService.findByUserId());
        return modelAndView;
    }

    /**
     * 인기 키워드 목록
     * @return
     */
    @GetMapping("/view/hot-search-keyword")
    public ModelAndView hotSearchKeywordView() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("view/hot-search-keyword");
        modelAndView.addObject("result", placeSearchHistoryService.countQueryByGroupBySearchKeyword());
        return modelAndView;
    }

    /*-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
    | REST API
    |-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=*/

    /**
     * 캐시 삭제
     *
     * @return
     */
    @GetMapping("/api/my-search-history/command/remove-cache")
    public RestResponse removeCache() {
        placeSearchHistoryService.removeCache();
        return new RestResponse(null);
    }

}
