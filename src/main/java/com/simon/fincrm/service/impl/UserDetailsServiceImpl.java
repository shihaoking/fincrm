/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.simon.fincrm.service.impl;


import com.simon.fincrm.service.entities.LoginUserInfo;
import com.simon.fincrm.service.facade.IUserInfo;
import com.simon.fincrm.service.facade.IUserLevel;
import com.simon.fincrmprod.service.facade.model.UserInfoModel;
import com.simon.fincrmprod.service.facade.model.UserLevelModel;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

/**
 * @author jinshihao
 * @version $Id: UserDetailsServiceImpl.java, v 0.1 2016-09-19 13:27 jinshihao Exp $$
 */
@Transactional(readOnly = true)
@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {
    Logger logger = Logger.getLogger(UserDetailsServiceImpl.class);

    @Autowired
    private IUserInfo userInfo;

    @Autowired
    private IUserLevel userLevel;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.info("valid name:" + username);
        try {
            UserInfoModel user = userInfo.selectByName(username);
            logger.info("user info: id:" + user.getId() + "_name:" + user.getUserName());
            if (user == null) {
                throw new UsernameNotFoundException("用户" + username + "不存在!");
            }

            Set<GrantedAuthority> grantedAuths = obtainGrantedAuthorities(user);

            boolean accountNonExpired = true;
            boolean credentialsNonExpired = true;
            boolean accountNonLocked = true;

            UserDetails userDetails = new LoginUserInfo(user.getId(),
                    user.getUserName(), user.getUserPwd(), user.getStatus(), accountNonExpired, accountNonLocked,
                    credentialsNonExpired, grantedAuths);

            return userDetails;
        } catch (Exception ex) {
            logger.error("loadUserByUsername", ex);
        }

        return null;
    }

    /**
     * 获得用户所有角色的权限集合.
     */
    private Set<GrantedAuthority> obtainGrantedAuthorities(UserInfoModel user) {
        Set<GrantedAuthority> authSet = new HashSet<GrantedAuthority>();
        UserLevelModel userLevelDo = userLevel.selectByUserId(user.getId());

        if (userLevelDo != null) {
            authSet.add(new SimpleGrantedAuthority(userLevelDo.getLevelName()));
        }

        return authSet;
    }
}
