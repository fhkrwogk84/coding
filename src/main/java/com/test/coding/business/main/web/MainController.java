package com.test.coding.business.main.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 메인 컨트롤러
 */
@Controller
public class MainController {
    
    /**
     * 루트/메인페이지 페이지
     */
    @GetMapping("/")
    public String root() {
        return "redirect:/view/place-search";
    }

}
