/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.simon.fincrm.service.impl;

import com.simon.fincrm.dal.model.UserInfoDo;
import com.simon.fincrm.dal.model.UserLevelDo;
import com.simon.fincrm.service.entities.LoginUserInfo;
import com.simon.fincrm.service.facade.IUserInfo;
import com.simon.fincrm.service.facade.IUserLevel;
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
    @Autowired
    private IUserInfo userInfo;

    @Autowired
    private IUserLevel userLevel;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserInfoDo user = userInfo.selectByName(username);

        if (user == null) {
            throw new UsernameNotFoundException("用户" + username + "不存在!");
        }

        Set<GrantedAuthority> grantedAuths = obtainGrantedAuthorities(user);

        boolean accountNonExpired = true;
        boolean credentialsNonExpired = true;
        boolean accountNonLocked = true;

        UserDetails userDetails = new LoginUserInfo( user.getId(),
                user.getUserName(), user.getUserPwd(), user.getStatus(), accountNonExpired,accountNonLocked,
                credentialsNonExpired,  grantedAuths);

        return userDetails;
    }

    /**
     * 获得用户所有角色的权限集合.
     */
    private Set<GrantedAuthority> obtainGrantedAuthorities(UserInfoDo user) {
        Set<GrantedAuthority> authSet = new HashSet<GrantedAuthority>();
        UserLevelDo userLevelDo = userLevel.selectByUserId(user.getId());

        if (userLevelDo != null) {
            authSet.add(new SimpleGrantedAuthority(userLevelDo.getLevelName()));
        }

        return authSet;
    }
}
