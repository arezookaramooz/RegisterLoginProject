package com.example.arezookaramooz.registerlogin2.Login;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.arezookaramooz.registerlogin2.Profile.ProfileActivity;
import com.example.arezookaramooz.registerlogin2.R;
import com.example.arezookaramooz.registerlogin2.Register.RegisterActivity;
import com.example.arezookaramooz.registerlogin2.Register.RegisterModel;

public class LoginActivity extends AppCompatActivity implements LoginView {

    private EditText phoneNumberBox;
    private EditText passwordBox;
    private Button loginButton;
    private Button registerNowButton;
    private LoginPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initialize();
        presenter = new LoginPresenter(this, RegisterModel.getInstance(this));
    }

    @Override
    public void setNotRegisteredError() {
        phoneNumberBox.setError(getString(R.string.not_registered_error));
    }

    @Override
    public void setWrongPasswordError() {
        passwordBox.setError(getString(R.string.wrong_password_error));
    }

    @Override
    public void navigateToRegisterActivity() {
        startActivity(new Intent(this, RegisterActivity.class));
        finish();
    }

    @Override
    public void navigateToProfileActivity() {
        startActivity(new Intent(this, ProfileActivity.class));
    }

    private void initialize() {
        findViews();
        listeners();
    }

    private void findViews() {
        phoneNumberBox = findViewById(R.id.phone_number_box);
        passwordBox = findViewById(R.id.password_box);
        loginButton = findViewById(R.id.login_button);
        registerNowButton = findViewById(R.id.register_now);
    }

    private void listeners() {
        loginButtonListener();
        registerNowButtonegister();
    }

    private void loginButtonListener() {
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onLoginButtonClicked(getPhoneNumber(), getPassword());
            }
        });
    }

    private String getPhoneNumber() {
        return phoneNumberBox.getText().toString();
    }

    private String getPassword() {
        return passwordBox.getText().toString();
    }

    private void registerNowButtonegister() {
        registerNowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onRegisterNowButtonClicked();
            }
        });
    }
}
