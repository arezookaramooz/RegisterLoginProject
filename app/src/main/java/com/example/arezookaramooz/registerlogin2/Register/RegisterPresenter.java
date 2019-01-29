package com.example.arezookaramooz.registerlogin2.Register;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.widget.Toast;

import com.example.arezookaramooz.registerlogin2.Login.LoginView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterPresenter {

    private RegisterView registerView;
    private RegisterModel registerModel;

    RegisterPresenter(RegisterView registerView, RegisterModel registerModel) {
        this.registerView = registerView;
        this.registerModel = registerModel;
    }

    public void onRegisterButtonClicked(String phoneNumber, String password, String confirmPassword, String email) {
        validate(phoneNumber, password, confirmPassword, email);
    }

    private void validate(String phoneNumber, String password, String confirmPassword, String email) {

        checkEmptyFields(phoneNumber, password, confirmPassword, email);

        checkFormats(phoneNumber, password, confirmPassword, email);
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

    private boolean checkEmptyFields(String phoneNumber, String password, String confirmPassword, String email) {
        if (phoneNumber.equals("") | password.equals("") | confirmPassword.equals("") | email.equals("")) {
            registerView.setEmptyFieldError();
            return true;
        }
        return false;
    }

    private void checkFormats(String phoneNumber, String password, String confirmPassword, String email) {
        if (!checkEmptyFields(phoneNumber,password,confirmPassword, email)) {

            if (!isPhoneValid(phoneNumber)) {

                registerView.setWrongPhoneNumberFormatError();

            } else if (!isEmailValid(email)) {

                registerView.setWrongEmailFormatError();

            } else if (!isPasswordValid(password)) {

                registerView.setWrongPasswordFormatError();

            } else if (!password.equals(confirmPassword)) {

                registerView.setWrongPasswordConfirmationError();

            } else {
                registerModel.insertRowToUsers(phoneNumber, password, email);
                registerView.navigateToProfileActivity();

            }
        }
    }
}