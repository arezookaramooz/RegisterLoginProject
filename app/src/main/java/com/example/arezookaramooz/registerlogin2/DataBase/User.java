package com.example.arezookaramooz.registerlogin2.DataBase;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.Realm;
import io.realm.RealmObject;

public class User extends RealmObject {

    @SerializedName("phoneNumber")
    @Expose
    String phoneNumber;
    @SerializedName("password")
    @Expose
    String password;
    @SerializedName("email")
    @Expose
    String email;
    @SerializedName("province")
    @Expose
    String province;

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

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }
}
