package com.example.standard.bakingappseggio.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.standard.bakingappseggio.R;
import com.example.standard.bakingappseggio.data.Recipe;

import java.util.ArrayList;
import java.util.List;

public class RecipeActivity extends AppCompatActivity {

    private Recipe recipe;
    private List<Recipe> recipeItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);

        recipeItems = new ArrayList<>();

        /*
        * Receive Data from RecipeStartActivity
        */
        Intent intent = getIntent();
        Parcelable extras = intent.getParcelableExtra("data");
        if (extras != null){
            recipe = getIntent().getParcelableExtra("data");
            Log.d("Test", "Daten übertragen");
            Log.d("Test", "Description = " + recipe.getmDescription());
            Log.d("Test", "Description = " + recipe.getmDescription());
        } else {
            Log.d("Test", "Daten nicht übertragen");
        }

        /*
        *  Checken ob Phone oder Tablet vorliegt
        * */

        if (findViewById(R.id.fragment_tablet_layout) != null) {
            // Tabletmode
            // Todo: Aufrufen des StepDetailFragments und rüberschicken der RezeptDaten
            // und ersetzen des jeweiligen Containers durch ein neues Fragment
            Log.d("Test", "Ich bin ein Tablet");
        }
        // Todo: Liste an die RecipeDetail Activity schicken, wo dann das DetailFragment aufgerufen wird
    }
}
