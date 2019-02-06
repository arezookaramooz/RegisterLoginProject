package com.example.arezookaramooz.registerlogin2.Register;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.example.arezookaramooz.registerlogin2.ConnectToServer.RetrofitInterface;
import com.example.arezookaramooz.registerlogin2.DataBase.User;
import com.example.arezookaramooz.registerlogin2.Login.LoginView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class RegisterPresenter {

    private RegisterView registerView;
    private RegisterModel registerModel;

    RegisterPresenter(RegisterView registerView, RegisterModel registerModel) {
        this.registerView = registerView;
        this.registerModel = registerModel;
    }

    public void onRegisterButtonClicked(String phoneNumber, String password, String confirmPassword, String email, String spinnerItem) {
        validate(phoneNumber, password, confirmPassword, email, spinnerItem);
    }

    private void validate(String phoneNumber, String password, String confirmPassword, String email, String spinnerItem) {

        checkEmptyFields(phoneNumber, password, confirmPassword, email, spinnerItem);

        checkFormats(phoneNumber, password, confirmPassword, email, spinnerItem);
    }

    public static boolean isEmailValid(String email) {
        String expression = "^\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*(\\.\\w{1,3})$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }


    public static boolean isPhoneValid(String email) {
        String expression = "(\\+98|0)?9\\d{9}";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public static boolean isPasswordValid(String email) {
        String expression = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{4,}$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    private boolean checkEmptyFields(String phoneNumber, String password, String confirmPassword, String email, String spinnerItem) {
        if (phoneNumber.equals("") | password.equals("") | confirmPassword.equals("") | email.equals("") | spinnerItem.equals("")) {
            registerView.setEmptyFieldError();
            return true;
        }
        return false;
    }

    private void checkFormats(String phoneNumber, String password, String confirmPassword, String email, String spinnerItem) {
        if (!checkEmptyFields(phoneNumber, password, confirmPassword, email, spinnerItem)) {

            if (!isPhoneValid(phoneNumber)) {

                registerView.setWrongPhoneNumberFormatError();

            } else if (!isEmailValid(email)) {

                registerView.setWrongEmailFormatError();

            } else if (!isPasswordValid(password)) {

                registerView.setWrongPasswordFormatError();

            } else if (!password.equals(confirmPassword)) {

                registerView.setWrongPasswordConfirmationError();

            } else {
                User user = new User();
                user.setPhoneNumber(phoneNumber);
                user.setPassword(password);
                user.setEmail(email);
                user.setProvince(spinnerItem);
                registerModel.addUser(user);
                registerView.navigateToProfileActivity();

                SendUserInfoToServer(user);
            }
        }
    }

    private void SendUserInfoToServer(User user) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://arezookaramooz.gigfa.com//httpdocs/login.php/")
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitInterface service = retrofit.create(RetrofitInterface.class);

        Call<User> call = service.getStringScalar(user);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                Log.d("RegisterPresenter", "onResponse: successful");
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.d("RegisterPresenter", "onFailure: unsuccessful");
            }
        });
    }
}