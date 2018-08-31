package com.rodak.party_shaker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public class SearchActivity extends AppCompatActivity {

    private RecyclerView foundCocktailsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        foundCocktailsList = (RecyclerView) findViewById(R.id.foundCocktailsList);
        foundCocktailsList.setHasFixedSize(true);
        foundCocktailsList.setLayoutManager(new LinearLayoutManager(this));
    }
}
