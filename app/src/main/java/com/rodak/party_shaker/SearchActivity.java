package com.rodak.party_shaker;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchActivity extends AppCompatActivity {

    @BindView(R.id.foundCocktailsList)
    RecyclerView foundCocktailsList;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.searchByIngredient)
    EditText searchByIngredient;
    @BindView(R.id.search_progress)
    ProgressBar searchProgress;

    private RecyclerView.Adapter adapter;
    private List<CocktailsList> cocktailsLists;
    private static final String URL_DATA = "https://www.thecocktaildb.com/api/json/v1/1/filter.php?i=";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        toolbar.setTitle("SEARCH");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        foundCocktailsList.setHasFixedSize(true);
        foundCocktailsList.setLayoutManager(new LinearLayoutManager(this));
        cocktailsLists = new ArrayList<>();

        searchByIngredient.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View view) {
                        cocktailsLists.clear();
                        loadUrlData(searchByIngredient.getText().toString().trim().replaceAll(" ", "_"));
                    }
                });
    }

    private void loadUrlData(String ingredient) {

        showProgress(true);

        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                URL_DATA + ingredient, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                showProgress(false);

                try {

                    JSONObject jsonObject = new JSONObject(response);

                    JSONArray array = jsonObject.getJSONArray("drinks");

                    for (int i = 0; i < array.length(); i++) {

                        JSONObject jo = array.getJSONObject(i);

                        CocktailsList cocktails = new CocktailsList(jo.getString("strDrink"),
                                jo.getString("strDrinkThumb"), jo.getString("idDrink"));
                        cocktailsLists.add(cocktails);

                    }

                    adapter = new CocktailListAdapter(cocktailsLists, getApplicationContext());
                    foundCocktailsList.setAdapter(adapter);

                } catch (JSONException e) {

                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(SearchActivity.this, "Error" + error.toString(), Toast.LENGTH_SHORT).show();

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

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        View view = getCurrentFocus();
        boolean ret = super.dispatchTouchEvent(event);

        if (view instanceof EditText) {
            View w = getCurrentFocus();
            int scrcoords[] = new int[2];
            w.getLocationOnScreen(scrcoords);
            float x = event.getRawX() + w.getLeft() - scrcoords[0];
            float y = event.getRawY() + w.getTop() - scrcoords[1];

            if (event.getAction() == MotionEvent.ACTION_UP
                    && (x < w.getLeft() || x >= w.getRight()
                    || y < w.getTop() || y > w.getBottom())) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(getWindow().getCurrentFocus().getWindowToken(), 0);
            }
        }
        return ret;
    }
}
