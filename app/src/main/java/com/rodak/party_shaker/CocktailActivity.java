/*
 * Copyright (c) 2018.
 * Application made by Denathan.
 */

package com.rodak.party_shaker;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CocktailActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.search_progress)
    ProgressBar searchProgress;
    @BindView(R.id.cocktailImage)
    ImageView cocktailImage;
    @BindView(R.id.headerFirst)
    TextView headerFirst;
    @BindView(R.id.firstIngredient)
    TextView firstIngredient;
    @BindView(R.id.secondIngredient)
    TextView secondIngredient;
    @BindView(R.id.thirdIngredient)
    TextView thirdIngredient;
    @BindView(R.id.fourthIngredient)
    TextView fourthIngredient;
    @BindView(R.id.fifthIngredient)
    TextView fifthIngredient;
    @BindView(R.id.sixthIngredient)
    TextView sixthIngredient;
    @BindView(R.id.seventhIngredient)
    TextView seventhIngredient;
    @BindView(R.id.eighthIngredient)
    TextView eighthIngredient;
    @BindView(R.id.ninthIngredient)
    TextView ninthIngredient;
    @BindView(R.id.tenthIngredient)
    TextView tenthIngredient;
    @BindView(R.id.eleventhIngredient)
    TextView eleventhIngredient;
    @BindView(R.id.twelfthIngredient)
    TextView twelfthIngredient;
    @BindView(R.id.thirteenthIngredient)
    TextView thirteenthIngredient;
    @BindView(R.id.fourteenthIngredient)
    TextView fourteenthIngredient;
    @BindView(R.id.fifteenthIngredient)
    TextView fifteenthIngredient;
    @BindView(R.id.headerSecond)
    TextView headerSecond;
    @BindView(R.id.glass)
    TextView glass;
    @BindView(R.id.headerThird)
    TextView headerThird;
    @BindView(R.id.instructions)
    TextView instructions;

    private static final String URL_DATA = "https://www.thecocktaildb.com/api/json/v1/1/lookup.php?i=";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cocktail);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        final String id = intent.getStringExtra(CocktailListAdapter.KEY_ID);
        loadUrlData(id);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    private void loadUrlData(String id) {

        showProgress(true);

        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                URL_DATA + id, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                showProgress(false);

                try {

                    JSONObject jsonObject = new JSONObject(response);

                    JSONArray array = jsonObject.getJSONArray("drinks");


                    JSONObject jo = array.getJSONObject(0);

                    CocktailsList cocktails = new CocktailsList(jo.getString("strDrink"),
                            jo.getString("strDrinkThumb"), jo.getString("strIngredient1"),
                            jo.getString("strIngredient2"), jo.getString("strIngredient3"),
                            jo.getString("strIngredient4"), jo.getString("strIngredient5"),
                            jo.getString("strIngredient6"), jo.getString("strIngredient7"),
                            jo.getString("strIngredient8"), jo.getString("strIngredient9"),
                            jo.getString("strIngredient10"), jo.getString("strIngredient11"),
                            jo.getString("strIngredient12"), jo.getString("strIngredient13"),
                            jo.getString("strIngredient14"), jo.getString("strIngredient15"),
                            jo.getString("strMeasure1"), jo.getString("strMeasure2"),
                            jo.getString("strMeasure3"), jo.getString("strMeasure4"),
                            jo.getString("strMeasure5"), jo.getString("strMeasure6"),
                            jo.getString("strMeasure7"), jo.getString("strMeasure8"),
                            jo.getString("strMeasure9"), jo.getString("strMeasure10"),
                            jo.getString("strMeasure11"), jo.getString("strMeasure12"),
                            jo.getString("strMeasure13"), jo.getString("strMeasure14"),
                            jo.getString("strMeasure15"), jo.getString("strGlass"),
                            jo.getString("strInstructions"));


                    toolbar.setTitle(cocktails.getCocktailName());
                    Picasso.get()
                            .load(cocktails.getCocktailImageUrl())
                            .into(cocktailImage);

                    if(!"".equals(cocktails.getStrIngredient1()) && !"null".equals(cocktails.getStrIngredient1())) {
                        firstIngredient.setText(cocktails.getStrIngredient1());
                        firstIngredient.setVisibility(View.VISIBLE);
                    }
                    if(!"".equals(cocktails.getStrIngredient2()) && !"null".equals(cocktails.getStrIngredient2())) {
                        secondIngredient.setText(cocktails.getStrIngredient2());
                        secondIngredient.setVisibility(View.VISIBLE);
                    }
                    if(!"".equals(cocktails.getStrIngredient3()) && !"null".equals(cocktails.getStrIngredient3())) {
                        thirdIngredient.setText(cocktails.getStrIngredient3());
                        thirdIngredient.setVisibility(View.VISIBLE);
                    }
                    if(!"".equals(cocktails.getStrIngredient4()) && !"null".equals(cocktails.getStrIngredient4())) {
                        fourthIngredient.setText(cocktails.getStrIngredient4());
                        fourthIngredient.setVisibility(View.VISIBLE);
                    }
                    if(!"".equals(cocktails.getStrIngredient5()) && !"null".equals(cocktails.getStrIngredient5())) {
                        fifthIngredient.setText(cocktails.getStrIngredient5());
                        fifthIngredient.setVisibility(View.VISIBLE);
                    }
                    if(!"".equals(cocktails.getStrIngredient6()) && !"null".equals(cocktails.getStrIngredient6())) {
                        sixthIngredient.setText(cocktails.getStrIngredient6());
                        sixthIngredient.setVisibility(View.VISIBLE);
                    }
                    if(!"".equals(cocktails.getStrIngredient7()) && !"null".equals(cocktails.getStrIngredient7())) {
                        seventhIngredient.setText(cocktails.getStrIngredient7());
                        seventhIngredient.setVisibility(View.VISIBLE);
                    }
                    if(!"".equals(cocktails.getStrIngredient8()) && !"null".equals(cocktails.getStrIngredient8())) {
                        eighthIngredient.setText(cocktails.getStrIngredient8());
                        eighthIngredient.setVisibility(View.VISIBLE);
                    }
                    if(!"".equals(cocktails.getStrIngredient9()) && !"null".equals(cocktails.getStrIngredient9())) {
                        ninthIngredient.setText(cocktails.getStrIngredient9());
                        ninthIngredient.setVisibility(View.VISIBLE);
                    }
                    if(!"".equals(cocktails.getStrIngredient10()) && !"null".equals(cocktails.getStrIngredient10())) {
                        tenthIngredient.setText(cocktails.getStrIngredient10());
                        tenthIngredient.setVisibility(View.VISIBLE);
                    }
                    if(!"".equals(cocktails.getStrIngredient11()) && !"null".equals(cocktails.getStrIngredient11())) {
                        eleventhIngredient.setText(cocktails.getStrIngredient11());
                        eleventhIngredient.setVisibility(View.VISIBLE);
                    }
                    if(!"".equals(cocktails.getStrIngredient12()) && !"null".equals(cocktails.getStrIngredient12())) {
                        twelfthIngredient.setText(cocktails.getStrIngredient12());
                        twelfthIngredient.setVisibility(View.VISIBLE);
                    }
                    if(!"".equals(cocktails.getStrIngredient13()) && !"null".equals(cocktails.getStrIngredient13())) {
                        thirteenthIngredient.setText(cocktails.getStrIngredient13());
                        thirteenthIngredient.setVisibility(View.VISIBLE);
                    }
                    if(!"".equals(cocktails.getStrIngredient14()) && !"null".equals(cocktails.getStrIngredient14())) {
                        fourteenthIngredient.setText(cocktails.getStrIngredient14());
                        fourteenthIngredient.setVisibility(View.VISIBLE);
                    }
                    if(!"".equals(cocktails.getStrIngredient15()) && !"null".equals(cocktails.getStrIngredient15())) {
                        fifteenthIngredient.setText(cocktails.getStrIngredient15());
                        fifteenthIngredient.setVisibility(View.VISIBLE);
                    }
                    glass.setText(cocktails.getStrGlass());
                    glass.setVisibility(View.VISIBLE);
                    instructions.setText(cocktails.getStrInstructions());
                    instructions.setVisibility(View.VISIBLE);

                } catch (JSONException e) {

                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(CocktailActivity.this, "Error" + error.toString(), Toast.LENGTH_SHORT).show();

            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

        searchProgress.setVisibility(show ? View.VISIBLE : View.GONE);
        searchProgress.animate().setDuration(shortAnimTime).alpha(
                show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                searchProgress.setVisibility(show ? View.VISIBLE : View.GONE);
            }
        });

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        finish();
        return true;
    }
}
