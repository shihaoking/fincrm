/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.simon.fincrm.service;

import com.simon.fincrm.service.entities.LoginUserInfo;
import org.apache.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * @author jinshihao
 * @version $Id: UserSecurityUtils.java, v 0.1 2016-09-19 14:22 jinshihao Exp $$
 */
@Service
public class UserSecurityUtils {
    @SuppressWarnings("unchecked")
    public static <T extends LoginUserInfo> T getCurrentUser() {
        Authentication authentication = getAuthentication();

        if (authentication == null) {
            return null;
        }

        Object principal = authentication.getPrincipal();
        if (!(principal instanceof LoginUserInfo)) {
            return null;
        }

        return (T) principal;
    }

    /**
     * 取得当前用户的登录名, 如果当前用户未登录则返回空字符串.
     */
    public static String getCurrentUserName() {
        Authentication authentication = getAuthentication();

        if (authentication == null || authentication.getPrincipal() == null) {
            return "";
        }

        return authentication.getName();
    }

    public static int getCurrentUserId() {
       return  getCurrentUser().getUserId();
    }


    /**
     * 取得当前用户登录IP, 如果当前用户未登录则返回空字符串.
     */
    public static String getCurrentUserIp() {
        Authentication authentication = getAuthentication();

        if (authentication == null) {
            return "";
        }

        Object details = authentication.getDetails();
        if (!(details instanceof WebAuthenticationDetails)) {
            return "";
        }

        WebAuthenticationDetails webDetails = (WebAuthenticationDetails) details;
        return webDetails.getRemoteAddress();
    }

    /**
     * 判断用户是否拥有角色, 如果用户拥有参数中的任意一个角色则返回true.
     */
    public static boolean hasAnyRole(String... roles) {
        Authentication authentication = getAuthentication();

        if (authentication == null) {
            return false;
        }

        Collection<? extends GrantedAuthority> grantedAuthorityList = authentication
                .getAuthorities();

        for (String role : roles) {
            for (GrantedAuthority authority : grantedAuthorityList) {
                if (role.equals(authority.getAuthority())) {
                    return true;
                }
            }
        }

        return false;
    }


    /**
     * 取得Authentication, 如当前SecurityContext为空时返回null.
     */
    private static Authentication getAuthentication() {
        SecurityContext context = SecurityContextHolder.getContext();

        if (context == null) {
            return null;
        }

        return context.getAuthentication();
    }
}
