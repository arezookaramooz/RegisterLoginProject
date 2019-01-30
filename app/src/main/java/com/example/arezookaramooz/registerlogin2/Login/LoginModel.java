package com.example.arezookaramooz.registerlogin2.Login;

import com.example.arezookaramooz.registerlogin2.DataBase.User;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmResults;

public class LoginModel {

    private Realm realm;

    public LoginModel() {
        realm = Realm.getDefaultInstance();
    }

    public String getPassword(String phoneNumber) {

        RealmResults<User> results = realm.where(User.class).equalTo("phoneNumber", phoneNumber).findAll();
        if (results.size() < 1)
            return null;
        return results.get(0).getPassword();
    }

}
