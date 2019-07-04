package com.test.coding.core.util;

import com.test.coding.business.user.domain.model.User;
import com.test.coding.core.security.UserDetailsImpl;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

public class SessionUtil {

    private SessionUtil() {
        throw new IllegalStateException("Utility Class");
    }

    /*-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
    | PUBLIC
    |-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=*/

    /**
     * 로그인 사용자 정보 조회
     */
    public static User getLoginUser() {

        Authentication authentication = getSessionAuthentication();

        if (authentication != null) {
            Object principal = authentication.getPrincipal();
            return (principal instanceof UserDetailsImpl) ? ((UserDetailsImpl) principal).getLoginUser() : null;
        }

        return null;
    }

    /*-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
    | PRIVATE
    |-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=*/

    /**
     * 인증 정보 조회
     */
    private static Authentication getSessionAuthentication() {

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        SecurityContext sc = (SecurityContext) request.getSession(true).getAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY);

        if (sc != null) {
            return sc.getAuthentication();
        }

        return null;
    }

}
