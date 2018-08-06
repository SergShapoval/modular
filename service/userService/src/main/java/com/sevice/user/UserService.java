package com.sevice.user;

import com.dao.user.UserDao;

public class UserService {
    private UserDao userDao;

    public UserService(){
        userDao = new UserDao();
    }

    public String getUser(){
        return userDao.getUser();
    }
}
