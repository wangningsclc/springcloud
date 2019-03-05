package com.itmuch.cloud.user.service;

import com.itmuch.cloud.user.entity.SecurityUser;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * @Auth wn
 * @Date 2019/2/26
 */
@Component
public class CustomUserDetailsService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if ("admin".equals(username)) {
            return new SecurityUser("admin","admin","admin-role");
        } else if ("root".equals(username)) {
            return new SecurityUser("root","root","root-role");
        } else {
            return null;
        }
    }
}
