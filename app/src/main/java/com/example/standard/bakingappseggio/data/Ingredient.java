package com.example.standard.bakingappseggio.data;

import android.util.Log;

/**
 * Created by vince on 09.01.2018.
 */

public class Ingredient {

    String mIngredient, mQuantity, mMeasure;

    public Ingredient(String mIngredient, String mQuantity, String mMeasure) {
        this.mIngredient = mIngredient;
        this.mQuantity = mQuantity;
        this.mMeasure = mMeasure;

        Log.d("Test", "Ingredient: Constructor");
    }

    public String getmIngredient() {
        return mIngredient;
    }

    public String getmQuantity() {
        return mQuantity;
    }

    public String getmMeasure() {
        return mMeasure;
    }
}
