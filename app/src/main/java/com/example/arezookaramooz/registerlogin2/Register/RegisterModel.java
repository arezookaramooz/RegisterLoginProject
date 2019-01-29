package com.example.arezookaramooz.registerlogin2.Register;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class RegisterModel extends SQLiteOpenHelper {
    public static final String CREATE = "CREATE ";
    public static final String TABLE = "TABLE ";
    public static final String INSERT = "INSERT ";
    public static final String INTO = "INTO ";
    public static final String SELECT = "SELECT ";
    public static final String FROM = "FROM ";
    public static final String STAR = "* ";
    public static final String WHERE = "WHERE ";
    public static final String EQUAL = "= ";
    public static final String DELETE = "DELETE ";
    public static final String VALUES = "VALUES ";
    public static final String AUTO_INCREMENT = "AUTOINCREMENT ";
    public static final String PRIMARY_KEY = "PRIMARY KEY ";

    public static final String USERS_TABLE = "users ";
    public static final String COLUMN_ID = "id ";
    public static final String COLUMN_PHONE_NUMBER = "phoneNumber ";
    public static final String COLUMN_PASSWORD = "password ";
    public static final String COLUMN_EMAIL = "email ";

    public static final String DATA_TYPE_INTEGER = "INTEGER ";
    public static final String DATA_TYPE_STRING = "VARCHAR(1000) ";

    public static final String COMMA = ", ";
    public static final String PARENTHESIS_OPEN = "( ";
    public static final String PARENTHESIS_CLOSE = ") ";
    public static final String SEMI_COLON = "; ";
    private static final String TAG = "DatabaseHelper ";


    public RegisterModel(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public static RegisterModel getInstance(Context context) {
        RegisterModel myDatabase = new RegisterModel(context, "myDatabase", null, 2);
        return myDatabase;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String q = CREATE + TABLE + USERS_TABLE + PARENTHESIS_OPEN
                + COLUMN_ID + DATA_TYPE_INTEGER + PRIMARY_KEY + AUTO_INCREMENT + COMMA
                + COLUMN_PHONE_NUMBER + DATA_TYPE_STRING + COMMA
                + COLUMN_PASSWORD + DATA_TYPE_STRING + COMMA
                + COLUMN_EMAIL + DATA_TYPE_STRING
                + PARENTHESIS_CLOSE + SEMI_COLON;
        Log.d(TAG, "create report table query=" + q);
        db.execSQL(q);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + USERS_TABLE);
        onCreate(db);
    }

    public void insertRowToUsers(String phoneNumber, String password, String email) {
        String i = INSERT + INTO + USERS_TABLE + PARENTHESIS_OPEN
                + COLUMN_PHONE_NUMBER + COMMA
                + COLUMN_PASSWORD + COMMA
                + COLUMN_EMAIL
                + PARENTHESIS_CLOSE + VALUES + PARENTHESIS_OPEN
                + "\"" + phoneNumber + "\"" + COMMA
                + "\"" + password + "\"" + COMMA
                + "\"" + email + "\""
                + PARENTHESIS_CLOSE + SEMI_COLON;
        getWritableDatabase().execSQL(i);
    }

    public String getPassword(String phoneNumber) {
        String q = SELECT + COLUMN_PASSWORD + FROM + USERS_TABLE + WHERE + COLUMN_PHONE_NUMBER + EQUAL + "\"" + phoneNumber + "\"" + SEMI_COLON;
        Cursor c = getReadableDatabase().rawQuery(q, null);
        if (c.getCount() > 0) {
            c.moveToFirst();
            return c.getString(0);
        }
        return null;
    }
}
