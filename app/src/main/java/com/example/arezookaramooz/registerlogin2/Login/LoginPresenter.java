package com.example.arezookaramooz.registerlogin2.Login;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.widget.Toast;

import com.example.arezookaramooz.registerlogin2.Register.RegisterModel;

public class LoginPresenter {

    private LoginView loginView;
    private LoginModel loginModel;

    LoginPresenter(LoginView loginView, LoginModel loginModel) {
        this.loginView = loginView;
        this.loginModel = loginModel;
    }

    public void onLoginButtonClicked(String phoneNumber, String password) {
        validate(phoneNumber, password);
    }

    private void validate(String phoneNumber, String password) {
        if (loginModel.getPassword(phoneNumber) == null){
            loginView.setNotRegisteredError();
        }else if (!loginModel.getPassword(phoneNumber).equals(password)){
            loginView.setWrongPasswordError();
        }else {
            loginView.navigateToProfileActivity();
        }
    }

    public void onRegisterNowButtonClicked(){
        loginView.navigateToRegisterActivity();
    }

}