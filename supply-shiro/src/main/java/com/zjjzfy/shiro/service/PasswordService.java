package com.zjjzfy.shiro.service;

public interface PasswordService {
    String doGetPassword(String password, String mixSalt);
}