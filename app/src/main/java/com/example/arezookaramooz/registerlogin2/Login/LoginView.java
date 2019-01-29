package com.example.arezookaramooz.registerlogin2.Login;

public interface LoginView {

    void setNotRegisteredError();

    void setWrongPasswordError();

    void navigateToRegisterActivity();

    void navigateToProfileActivity();
}
