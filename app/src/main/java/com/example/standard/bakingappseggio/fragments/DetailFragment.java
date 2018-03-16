package com.example.standard.bakingappseggio.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.standard.bakingappseggio.R;

/**
 * Created by vince on 21.12.2017.
 */

public class DetailFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        //Log.d("Test", "MasterListFragment");
        Log.d("Test", "DetailFragment: onCreateView");

        /*
        * Hier wird die Sache mit dem ExoPlayer und die Darstellung des Zubereitungs-
        * schritts gehandled
        * */

        final View rootView = inflater.inflate(R.layout.detail_fragment, container, false);

        return rootView;
    }
}
