package com.andrew.testroid_share;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.Spinner;

import java.util.List;

public class LoginActivity extends AppCompatActivity {

    private Button shared;
    private Button page;
    private Spinner city;
    private Spinner level;
    private List targetedShareIntents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

    }
}
