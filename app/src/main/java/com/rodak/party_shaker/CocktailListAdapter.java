/*
 * Copyright (c) 2018.
 * Application made by Denathan.
 */

package com.rodak.party_shaker;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class CocktailListAdapter extends RecyclerView.Adapter<CocktailListAdapter.ViewHolder> {

    public static final String KEY_ID = "id";

    private List<CocktailsList> cocktailsLists;
    private Context context;

    public CocktailListAdapter(List<CocktailsList> cocktailsLists, Context context) {
        this.cocktailsLists = cocktailsLists;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cocktails_list, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        final CocktailsList cocktailsList = cocktailsLists.get(position);
        holder.cocktailName.setText(cocktailsList.getCocktailName());

        Picasso.get()
                .load(cocktailsList.getCocktailImageUrl())
                .into(holder.cocktailUrl);

        holder.cocktailContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CocktailsList cocktailsList1 = cocktailsLists.get(position);
                Intent skipIntent = new Intent(v.getContext(), CocktailActivity.class);
                skipIntent.putExtra(KEY_ID, cocktailsList1.getIdDrink());
                v.getContext().startActivity(skipIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return cocktailsLists.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView cocktailName;
        public ImageView cocktailUrl;
        public LinearLayout cocktailContainer;

        public ViewHolder(View itemView) {
            super(itemView);
            cocktailName = (TextView) itemView.findViewById(R.id.cocktailName);
            cocktailUrl = (ImageView) itemView.findViewById(R.id.cocktailImage);
            cocktailContainer = (LinearLayout) itemView.findViewById(R.id.cocktailContainer);
        }
    }

}
