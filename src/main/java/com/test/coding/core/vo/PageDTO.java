package com.test.coding.core.vo;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class PageDTO<T> implements Serializable {
    private static final long serialVersionUID = -6618206850210257861L;

    private List<T> list;
    private Pageable page;
    private int totalCount;     // 게시판 전체 데이터 개수

    // 페이지 네비게이션을 위한 설정
    private int displayPageNum = 5;   // 게시판 화면에서 한번에 보여질 페이지 번호의 개수 ( 1,2,3,4,5,6,7,9,10)
    private int startPage;      // 현재 화면에서 보이는 startPage 번호
    private int endPage;        // 현재 화면에 보이는 endPage 번호
    private boolean prev;       // 페이징 이전 버튼 활성화 여부
    private boolean next;       // 페이징 다음 버튼 활서화 여부

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
        calcData();
    }

    private void calcData() {
        endPage = (int) (Math.ceil(page.getPageNumber() / (double) displayPageNum) * displayPageNum);

        startPage = (endPage - displayPageNum) + 1;

        int tempEndPage = (int) (Math.ceil(totalCount / (double) page.getPageSize()));

        if(endPage > tempEndPage) {
            endPage = tempEndPage;
        }

        prev = startPage == 1 ? false : true;

        next = endPage * page.getPageSize() >= totalCount ? false : true;
    }

}
