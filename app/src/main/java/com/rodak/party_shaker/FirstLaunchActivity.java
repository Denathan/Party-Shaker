package com.rodak.party_shaker;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FirstLaunchActivity extends AppCompatActivity {

    @BindView(R.id.backgroundImage)
    ImageView backgroundImage;
    @BindView(R.id.backgroundColor)
    View backgroundColor;
    @BindView(R.id.logoImage)
    ImageView logoImage;
    @BindView(R.id.largeText)
    TextView largeText;
    @BindView(R.id.smallTextFirst)
    TextView smallTextFirst;
    @BindView(R.id.smallTextSecond)
    TextView smallTextSecond;
    @BindView(R.id.linearContainer)
    LinearLayout linearContainer;
    @BindView(R.id.startButton)
    Button startButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_launch);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.startButton)
    public void onViewClicked() {
        startActivity(new Intent(FirstLaunchActivity.this, MainMenuActivity.class));
        finish();
    }
}
