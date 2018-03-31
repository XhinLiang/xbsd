package com.xhinliang.xbsd.services;

import static com.google.common.base.Preconditions.checkNotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.xhinliang.xbsd.db.model.User;
import com.xhinliang.xbsd.db.repository.UserRepository;

/**
 * @author xhinliang
 */
@Service
@Lazy
public class XUserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username);
        checkNotNull(user, "user not found!");
        return user;
    }
}
