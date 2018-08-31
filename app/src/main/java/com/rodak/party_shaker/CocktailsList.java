/*
 * Copyright (c) 2018.
 * Application made by Denathan.
 */

package com.rodak.party_shaker;

public class CocktailsList {

    private String cocktailName;
    private String cocktailImageUrl;

    public CocktailsList(String cocktailName, String cocktailImageUrl) {
        this.cocktailName = cocktailName;
        this.cocktailImageUrl = cocktailImageUrl;
    }

    public String getCocktailName() {
        return cocktailName;
    }

    public String getCocktailImageUrl() {
        return cocktailImageUrl;
    }
}
