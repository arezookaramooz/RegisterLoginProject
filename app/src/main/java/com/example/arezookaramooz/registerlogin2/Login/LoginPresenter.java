package com.example.arezookaramooz.registerlogin2.Login;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.widget.Toast;

import com.example.arezookaramooz.registerlogin2.Register.RegisterModel;

public class LoginPresenter {

    private LoginView loginView;
    private RegisterModel registerModel;

    LoginPresenter(LoginView loginView, RegisterModel registerModel) {
        this.loginView = loginView;
        this.registerModel = registerModel;
    }

    public void onLoginButtonClicked(String phoneNumber, String password) {
        validate(phoneNumber, password);
    }

    private void validate(String phoneNumber, String password) {
        if (registerModel.getPassword(phoneNumber) == null){
            loginView.setNotRegisteredError();
        }else if (!registerModel.getPassword(phoneNumber).equals(password)){
            loginView.setWrongPasswordError();
        }else {
            loginView.navigateToProfileActivity();
        }
    }

    public void onRegisterNowButtonClicked(){
        loginView.navigateToRegisterActivity();
    }

}