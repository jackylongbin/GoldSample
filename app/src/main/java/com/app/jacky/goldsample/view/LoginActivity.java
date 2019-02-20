package com.app.jacky.goldsample.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.app.jacky.goldsample.R;
import com.app.jacky.goldsample.view.interfaceview.ILoginView;

public class LoginActivity extends AppCompatActivity implements ILoginView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    @Override
    public void showLoginProcess() {

    }

    @Override
    public void hideLoginProcess() {

    }

    @Override
    public void loginSuccess() {

    }

    @Override
    public void loginFailed() {

    }
}
