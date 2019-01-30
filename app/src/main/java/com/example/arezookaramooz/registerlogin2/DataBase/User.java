package com.example.arezookaramooz.registerlogin2.DataBase;

import io.realm.Realm;
import io.realm.RealmObject;

public class User extends RealmObject {

    String phoneNumber, password, email;

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
