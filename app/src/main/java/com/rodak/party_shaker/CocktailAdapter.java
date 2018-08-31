/*
 * Copyright (c) 2018.
 * Application made by Denathan.
 */

package com.rodak.party_shaker;

import android.content.Context;
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

public class CocktailAdapter extends RecyclerView.Adapter<CocktailAdapter.ViewHolder> {

    public static final String KEY_NAME = "name";
    public static final String KEY_IMAGE = "image";

    private List<CocktailsList> cocktailsLists;
    private Context context;

    public CocktailAdapter(List<CocktailsList> cocktailsLists, Context context) {
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

        /*TODO: Uncomment after drinks activity will be created*/
//        holder.cocktailContainer.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                CocktailsList cocktailsList1 = cocktailsLists.get(position);
//                Intent skipIntent = new Intent(v.getContext(), SearchActivity.class);
//                skipIntent.putExtra(KEY_NAME, developersList1.getLogin());
//                skipIntent.putExtra(KEY_URL, developersList1.getHtml_url());
//                skipIntent.putExtra(KEY_IMAGE, developersList1.getAvatar_url());
//                v.getContext().startActivity(skipIntent);
//            }
//        });
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
