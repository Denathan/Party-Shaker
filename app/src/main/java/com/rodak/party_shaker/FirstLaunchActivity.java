package com.rodak.party_shaker;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class FirstLaunchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_launch);
    }

    public void startButton(View v) {
        startActivity(new Intent(FirstLaunchActivity.this, MainMenuActivity.class));
        finish();
    }
}
