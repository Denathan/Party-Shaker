package com.rodak.party_shaker;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
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
                new View.OnClickListener()
                {
                    public void onClick(View view)
                    {
                        cocktailsLists.clear();
                        loadUrlData(searchByIngredient.getText().toString().trim());
                    }
                });
    }

    private void loadUrlData(String ingredient) {

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                URL_DATA + ingredient, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                progressDialog.dismiss();

                try {

                    JSONObject jsonObject = new JSONObject(response);

                    JSONArray array = jsonObject.getJSONArray("drinks");

                    for (int i = 0; i < array.length(); i++) {

                        JSONObject jo = array.getJSONObject(i);

                        CocktailsList cocktails = new CocktailsList(jo.getString("strDrink"),
                                jo.getString("strDrinkThumb"));
                        cocktailsLists.add(cocktails);

                    }

                    adapter = new CocktailAdapter(cocktailsLists, getApplicationContext());
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

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
