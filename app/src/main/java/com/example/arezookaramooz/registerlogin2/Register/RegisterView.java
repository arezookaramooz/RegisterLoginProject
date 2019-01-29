package com.example.arezookaramooz.registerlogin2.Register;

public interface RegisterView {
    void setEmptyFieldError();
    void setWrongPhoneNumberFormatError();
    void setWrongEmailFormatError();
    void setWrongPasswordFormatError();
    void setWrongPasswordConfirmationError();
    void navigateToProfileActivity();
}
