package com.example.arezookaramooz.registerlogin2.DataBase;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(getApplicationContext());
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder()
                .name("my db")
                .build();
        Realm.getInstance(realmConfiguration);
    }
}
