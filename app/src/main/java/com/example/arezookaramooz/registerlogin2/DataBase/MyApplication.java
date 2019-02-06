package com.example.arezookaramooz.registerlogin2.DataBase;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.exceptions.RealmMigrationNeededException;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

//        Realm realm = null;
//        Realm.init(getApplicationContext());
//
//        try {
//            realm = Realm.getInstance(new RealmConfiguration.Builder().name(Realm.DEFAULT_REALM_NAME).schemaVersion(4).build());
//        } catch (RealmMigrationNeededException r) {
//            Realm.deleteRealm(Realm.getDefaultConfiguration());
//            realm = Realm.getInstance(new RealmConfiguration.Builder().name(Realm.DEFAULT_REALM_NAME).schemaVersion(4).build());
//        }

//        Realm.init(getApplicationContext());
//        RealmConfiguration config = new RealmConfiguration.Builder()
//                .deleteRealmIfMigrationNeeded()
//                .build();
//        Realm.getInstance(config);


        Realm.init(getApplicationContext());
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder()
                .name("my db")
                .schemaVersion(3)
                .build();

            Realm.getInstance(realmConfiguration);

    }
}
