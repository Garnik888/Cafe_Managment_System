package com.example.cafemanagementsystem.security;


import com.example.cafemanagementsystem.domain.entity.User;
import org.springframework.security.core.authority.AuthorityUtils;

public class CurrentUser extends org.springframework.security.core.userdetails.User {

    private final User user;

    public CurrentUser(User user) {
        super(user.getUsername(),user.getPassword(),
                AuthorityUtils.createAuthorityList(String.valueOf(user.getRoleType())));
        this.user = user;
    }

    public User getUser(){
        return user;
    }
}
