package com.example.standard.bakingappseggio.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.standard.bakingappseggio.R;
import com.example.standard.bakingappseggio.data.Recipe;
import com.example.standard.bakingappseggio.data.RecipeAdapter;
import com.example.standard.bakingappseggio.data.RecipeLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vince on 21.12.2017.
 */

public class IngredientsFragment extends Fragment implements
        LoaderManager.LoaderCallbacks<List<Recipe>>,
        RecipeAdapter.RecipeAdapterOnClickHandler {

    private RecyclerView rvIngredients;
    private RecipeAdapter mAdapter;
    //private LinearLayoutManager mLayoutManager;
    private List<Recipe> recipeItems;

    private LoaderManager loader;

    private String mUrl;

    private boolean mDetailedLayout;

    public IngredientsFragment() {
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d("Test", "MasterFragment onActivityCreated");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d("Test", "MasterFragment onCreateView");
        View rootView = inflater.inflate(R.layout.ingredients_fragment, container, false);

        mUrl = getString(R.string.url);

        recipeItems = new ArrayList<>();

        rvIngredients = (RecyclerView) rootView.findViewById(R.id.rv_ingredients);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        rvIngredients.setHasFixedSize(true);
        rvIngredients.setLayoutManager(linearLayoutManager);
        mAdapter = new RecipeAdapter(getActivity(), this, recipeItems);

        rvIngredients.setAdapter(mAdapter);

        loader = getLoaderManager();
        loader.initLoader(1, null, this);

        return rootView;
    }

    @Override
    public Loader<List<Recipe>> onCreateLoader(int id, Bundle args) {
        mDetailedLayout = false;
        return new RecipeLoader(getContext(), mUrl);
    }

    @Override
    public void onLoadFinished(Loader<List<Recipe>> loader, List<Recipe> data) {
        //progressBar.setVisibility(View.GONE);

        /*
        * This if request checks if the home button in the detail activity is clicked and avoids
        * that the lastly loaded page with movies will be added again in the list
        */
        if (!mDetailedLayout){
            if (recipeItems != null && !recipeItems.isEmpty()) {
                mAdapter.add(recipeItems);
                mAdapter.notifyDataSetChanged();

            } else {
                rvIngredients.setVisibility(View.GONE);
                //Toast.makeText(this, getString(R.string.toast_message), Toast.LENGTH_LONG).show();
            }
        }
        mDetailedLayout = true;
    }

    @Override
    public void onLoaderReset(Loader<List<Recipe>> loader) {
        mAdapter.clear();
    }

    @Override
    public void onClick(Recipe data) {

    }
}
