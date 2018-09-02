package com.rodak.party_shaker;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainMenuActivity extends AppCompatActivity {

    @BindView(R.id.backgroundImage)
    ImageView backgroundImage;
    @BindView(R.id.backgroundColor)
    View backgroundColor;
    @BindView(R.id.logoImage)
    ImageView logoImage;
    @BindView(R.id.firstOption)
    TextView firstOption;
    @BindView(R.id.secondOption)
    TextView secondOption;
    @BindView(R.id.thirdOption)
    TextView thirdOption;
    @BindView(R.id.fourthOption)
    TextView fourthOption;
    @BindView(R.id.fifthOption)
    TextView fifthOption;
    @BindView(R.id.linearContainer)
    LinearLayout linearContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.firstOption, R.id.secondOption, R.id.thirdOption, R.id.fourthOption, R.id.fifthOption})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.firstOption:
                startActivity(new Intent(MainMenuActivity.this, SearchActivity.class));
                break;
            case R.id.secondOption:
                break;
            case R.id.thirdOption:
                break;
            case R.id.fourthOption:
                break;
            case R.id.fifthOption:
                break;
        }
    }
}
