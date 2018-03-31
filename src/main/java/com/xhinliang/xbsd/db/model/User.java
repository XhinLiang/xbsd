package com.xhinliang.xbsd.db.model;

import java.nio.charset.Charset;
import java.time.Instant;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.google.common.hash.Hashing;

/**
 * @author xhinliang
 */
@Entity

@Table(name = "user", indexes = { @Index(name = "idx_email", columnList = "email", unique = true) })
public class User implements UserDetails {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String email;

    @Column
    private String nickname;

    @Column
    private String rawPassword;

    @Column
    private Integer status;

    @Column
    private Long birthTime;

    @Column
    private Integer birthdayType;

    @Column
    private String passwordSalt;

    @Column
    @CreationTimestamp
    private Instant createdTime;

    @Column
    @UpdateTimestamp
    private Instant updatedTime;

    public User(String email, String nickname, String password, Long birthTime,
            Integer birthdayType) {
        this.email = email;
        this.nickname = nickname;
        this.rawPassword = password;
        this.birthTime = birthTime;
        this.birthdayType = birthdayType;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    public String getPassword() {
        String composePassword = rawPassword + passwordSalt;
        return Hashing.md5().hashString(composePassword, Charset.defaultCharset()).toString();
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        // TODO
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        // TODO
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // TODO
        return false;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public Long getId() {
        return id;
    }

    public Instant getCreatedTime() {
        return createdTime;
    }

    public Instant getUpdatedTime() {
        return updatedTime;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCreatedTime(Instant createdTime) {
        this.createdTime = createdTime;
    }

    public void setUpdatedTime(Instant updatedTime) {
        this.updatedTime = updatedTime;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getRawPassword() {
        return rawPassword;
    }

    public void setRawPassword(String rawPassword) {
        this.rawPassword = rawPassword;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getBirthTime() {
        return birthTime;
    }

    public void setBirthTime(Long birthTime) {
        this.birthTime = birthTime;
    }

    public Integer getBirthdayType() {
        return birthdayType;
    }

    public void setBirthdayType(Integer birthdayType) {
        this.birthdayType = birthdayType;
    }

    public String getPasswordSalt() {
        return passwordSalt;
    }

    public void setPasswordSalt(String passwordSalt) {
        this.passwordSalt = passwordSalt;
    }
}
