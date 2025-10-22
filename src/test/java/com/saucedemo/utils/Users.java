package com.saucedemo.utils;

public enum Users {
    user1("standard_user"),
    user2("locked_out_user"),
    user3("performance_glitch_user");

    private String userName;

    Users(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }
}
