package com.example.arezookaramooz.registerlogin2.Register;

import android.view.View;
import android.widget.AdapterView;

public interface RegisterView {
    void setEmptyFieldError();
    void setWrongPhoneNumberFormatError();
    void setWrongEmailFormatError();
    void setWrongPasswordFormatError();
    void setWrongPasswordConfirmationError();
    void navigateToProfileActivity();
}
