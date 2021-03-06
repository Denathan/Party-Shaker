/*
 * Copyright (c) 2018.
 * Application made by Denathan.
 */

package com.rodak.party_shaker;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

public class HelperActivity extends Activity {

    SharedPreferences prefs = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        prefs = getSharedPreferences("com.rodak.party_shaker", MODE_PRIVATE);
    }

    @Override
    protected void onResume() {
        super.onResume();

        //Main menu
//        if (BuildConfig.DEBUG) {
//            prefs.edit().putBoolean("firstrun", false).apply();
//        }

        if (prefs.getBoolean("firstrun", true)) {
            prefs.edit().putBoolean("firstrun", false).apply();
            startActivity(new Intent(HelperActivity.this, FirstLaunchActivity.class));
            finish();
        } else {
            startActivity(new Intent(HelperActivity.this, MainMenuActivity.class));
            finish();
            //SearchActivity
            /*if (BuildConfig.DEBUG) {
                startActivity(new Intent(HelperActivity.this, SearchActivity.class));
                finish();
            }*/
        }
    }
}
