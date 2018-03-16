package com.example.standard.bakingappseggio.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.standard.bakingappseggio.R;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Log.d("Test", "DetailActivity: onCreate");
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
