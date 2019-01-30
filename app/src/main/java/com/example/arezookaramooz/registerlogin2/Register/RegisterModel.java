package com.example.arezookaramooz.registerlogin2.Register;

import com.example.arezookaramooz.registerlogin2.DataBase.User;

import io.realm.Realm;
import io.realm.RealmResults;

public class RegisterModel {

    private Realm realm;

    public RegisterModel() {
        realm = Realm.getDefaultInstance();
    }

    public void addUser(User user) {
        realm.beginTransaction();
        realm.copyToRealm(user);
        realm.commitTransaction();
    }
}