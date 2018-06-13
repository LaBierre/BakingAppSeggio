package com.example.standard.bakingappseggio.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.standard.bakingappseggio.R;
import com.example.standard.bakingappseggio.data.Step;
import com.example.standard.bakingappseggio.fragments.DetailFragment;

import java.util.ArrayList;
import java.util.List;

public class DetailActivity extends AppCompatActivity {

    private List<Step> stepItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Log.d("Test", "DetailActivity: onCreate");

        Bundle extras = getIntent().getExtras();

        stepItems = new ArrayList<>();
        stepItems = extras.getParcelableArrayList("stepItems");

        DetailFragment detailFragment = new DetailFragment();

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.detail_container, detailFragment).commit();


        /*
        * Wird aufgerufen, wenn auf einem Phone auf ein Zubereitungsschritt (step)
        * geklickt wird.
        *
        * Hier wird dann eine Instanz des Detailfragments erzeugt
        *
        * Es müssen die Videourl und der Description-Text übergeben werden
        *
        * Bei einem Tablet wird die Instanz des Detailfragments von der RecipeActivity
        * erzeugt
        * */
    }
}
