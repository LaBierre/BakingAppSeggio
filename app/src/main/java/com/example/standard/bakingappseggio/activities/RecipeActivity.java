package com.example.standard.bakingappseggio.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.example.standard.bakingappseggio.OnRecipeClickListener;
import com.example.standard.bakingappseggio.R;
import com.example.standard.bakingappseggio.data.Recipe;
import com.example.standard.bakingappseggio.fragments.RecipeFragment;

import java.util.ArrayList;
import java.util.List;

public class RecipeActivity extends AppCompatActivity implements OnRecipeClickListener {

    private Recipe recipeData, id;
    private List<Recipe> recipeItems;

    private String recipeName;
    private int recipeId;
    private RecipeFragment recipeFragment;

    private static final String LOG_TAG = RecipeActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Receive data from Start Activity


        Intent intent = getIntent();
        Parcelable extras = intent.getParcelableExtra("data");

        if (extras != null){
            recipeData = getIntent().getParcelableExtra("data");
        } else {
            Toast.makeText(this, "No data transfered", Toast.LENGTH_SHORT).show();
        }
        recipeName = recipeData.getmName();
        recipeId = recipeData.getmId();

        setContentView(R.layout.activity_recipe);

        // send the recipeId to RecipeFragment
//        Bundle bundle = new Bundle();
//        bundle.putInt("recipeFragment", recipeId);
//        RecipeFragment fragment = new RecipeFragment();
//        fragment.setArguments(bundle);
//        getSupportFragmentManager().beginTransaction()
//                .replace(R.id.container, fragment)
//                .commit();

        Log.d("Test", "RecipeActivity: onCreate");

        recipeItems = new ArrayList<>();

        /*
        *  Checken ob Phone oder Tablet vorliegt
        * */

        if (findViewById(R.id.fragment_tablet_layout) != null) {
            /*
            * In diesem Zweig der if Bedingung läuft die App auf einem Tablet. Es muss
            * eine Instanz des DetailFragments bei Klicken eines Step erzeugt werden.
            *
            * */
            Log.d(LOG_TAG, "RecipeActivity: Ich bin ein Tablet");
        }
        Log.d(LOG_TAG, "RecipeActivity: Ich bin kein Tablet");
    }

    @Override
    public void onRecipeSelected(int recipeId) {

    }
}
