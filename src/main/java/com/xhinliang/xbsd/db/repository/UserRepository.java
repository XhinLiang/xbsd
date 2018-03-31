package com.xhinliang.xbsd.db.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.xhinliang.xbsd.db.model.User;

/**
 * @author xhinliang
 */
public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);

    List<User> findByStatus(Integer status);
}
