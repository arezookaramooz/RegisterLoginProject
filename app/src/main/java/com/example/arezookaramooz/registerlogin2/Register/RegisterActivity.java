package com.example.arezookaramooz.registerlogin2.Register;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.arezookaramooz.registerlogin2.Login.LoginPresenter;
import com.example.arezookaramooz.registerlogin2.Profile.ProfileActivity;
import com.example.arezookaramooz.registerlogin2.R;

public class RegisterActivity extends AppCompatActivity implements RegisterView{

    EditText phoneNumberBox;
    EditText passwordBox;
    EditText confirmPasswordBox;
    EditText emailBox;
    Button registerButton;
    private RegisterPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        initialize();
        presenter = new RegisterPresenter(this, RegisterModel.getInstance(this));
    }

    private void initialize() {
        findViews();
        listeners();
    }

    private void findViews() {
        phoneNumberBox = findViewById(R.id.phone_number_box2);
        passwordBox = findViewById(R.id.password_box2);
        confirmPasswordBox = findViewById(R.id.confirm_Password_box);
        emailBox = findViewById(R.id.email_box);
        registerButton = findViewById(R.id.register_button);
    }

    private void listeners() {
        RegisterButtonListener();
    }

    private void RegisterButtonListener() {
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onRegisterButtonClicked(getPhoneNumber(), getPassword(), getConfirmPassword(), getEmail());
            }
        });
    }

    private String getPhoneNumber() {
        return phoneNumberBox.getText().toString();
    }

    private String getPassword() {
        return passwordBox.getText().toString();
    }

    private String getConfirmPassword(){
        return confirmPasswordBox.getText().toString();
    }

    private String getEmail(){
        return emailBox.getText().toString();
    }

    @Override
    public void setEmptyFieldError() {
        Toast.makeText(this, "all fields should be filled", Toast.LENGTH_LONG).show();
    }

    @Override
    public void setWrongPhoneNumberFormatError() {
        phoneNumberBox.setError(getString(R.string.wrong_phone_number_format_error));
    }

    @Override
    public void setWrongEmailFormatError() {
        emailBox.setError(getString(R.string.wrong_email_format_error));
    }

    @Override
    public void setWrongPasswordFormatError() {
        passwordBox.setError(getString(R.string.wrong_password_format_error));
    }

    @Override
    public void setWrongPasswordConfirmationError(){
        confirmPasswordBox.setError(getString(R.string.wrong_password_confirmation));
    }

    @Override
    public void navigateToProfileActivity(){
        startActivity(new Intent(this, ProfileActivity.class));
    }
}
