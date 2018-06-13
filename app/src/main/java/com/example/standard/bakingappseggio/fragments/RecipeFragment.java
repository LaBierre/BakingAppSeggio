package com.example.standard.bakingappseggio.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
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
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.standard.bakingappseggio.OnRecipeClickListener;
import com.example.standard.bakingappseggio.R;
import com.example.standard.bakingappseggio.activities.DetailActivity;
import com.example.standard.bakingappseggio.adapters.IngredientsAdapter;
import com.example.standard.bakingappseggio.adapters.StepsAdapter;
import com.example.standard.bakingappseggio.data.Ingredient;
import com.example.standard.bakingappseggio.data.Recipe;
import com.example.standard.bakingappseggio.data.Step;
import com.example.standard.bakingappseggio.loaders.IngredientsLoader;
import com.example.standard.bakingappseggio.loaders.StepsLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vince on 07.03.2018.
 */

public class RecipeFragment extends Fragment implements
        StepsAdapter.StepsAdapterOnClickHandler {
    // Todo: implement 2 LoaderCallbacks
    // One Callback is for ingredients and the other for the steps

    private static final String LOG_TAG = RecipeFragment.class.getName();

    private String mUrl;

    private Recipe recipe;

    // Todo: need 2 Adapters and two recyclerviews, done

    //Todo: perhaps implement a progressbar for each recyclerview?

    private static final int LOADER_ID_INGREDIENTS = 0;
    private static final int LOADER_ID_STEPS = 1;

    private OnRecipeClickListener mCallback;

    private IngredientsAdapter mIngredientsAdapter;
    private StepsAdapter mStepsAdapter;

    private RecyclerView mRvIngredients, mRvSteps;
    private ProgressBar progressBar;

    private List<Ingredient> mIngredientItems;
    private List<Step> mStepItems;

    public int recipeId;

    LoaderManager loaderIngredient, loaderStep;

    public void setRecipeId(int recipeId) {
        this.recipeId = recipeId;
    }

    // Override onAttach to make sure that the container activity has implemented the callback
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        // This makes sure that the host activity has implemented the callback interface
        // If not, it throws an exception
        try {
            mCallback = (OnRecipeClickListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement OnImageClickListener");
        }
    }

    public LoaderManager.LoaderCallbacks<List<Ingredient>> ingredientLoader =
            new LoaderManager.LoaderCallbacks<List<Ingredient>>() {
        @Override
        public Loader<List<Ingredient>> onCreateLoader(int id, Bundle args) {
            // Todo: Hier und im step loader muss die Id mit rein, done
            Log.d(LOG_TAG, "Recipe Id = " + recipeId);
            return new IngredientsLoader(getContext(), mUrl, recipeId);
        }

        @Override
        public void onLoadFinished(Loader<List<Ingredient>> loader, List<Ingredient> ingredients) {
            progressBar.setVisibility(View.GONE);

            if (ingredients != null && !ingredients.isEmpty()) {
                mIngredientsAdapter.add(ingredients);
                mIngredientsAdapter.notifyDataSetChanged();
            } else {
                mRvIngredients.setVisibility(View.GONE);
                Toast.makeText(getContext(), getString(R.string.toast_message), Toast.LENGTH_LONG).show();
            }
        }

        @Override
        public void onLoaderReset(Loader<List<Ingredient>> loader) {
            mIngredientsAdapter.clear();
        }
    };

    public LoaderManager.LoaderCallbacks<List<Step>> stepLoader =
            new LoaderManager.LoaderCallbacks<List<Step>>() {
        @Override
        public Loader<List<Step>> onCreateLoader(int id, Bundle args) {
            return new StepsLoader(getContext(), mUrl, recipeId);
        }

        @Override
        public void onLoadFinished(Loader<List<Step>> loader, List<Step> steps) {
            progressBar.setVisibility(View.GONE);

            if (steps != null && !steps.isEmpty()) {
                mStepsAdapter.add(steps);
                mStepsAdapter.notifyDataSetChanged();
            } else {
                mRvSteps.setVisibility(View.GONE);
                Toast.makeText(getContext(), getString(R.string.toast_message), Toast.LENGTH_LONG).show();
            }
        }

        @Override
        public void onLoaderReset(Loader<List<Step>> loader) {
            mStepsAdapter.clear();
        }
    };

    // Constructor
    public RecipeFragment (){
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        recipe = new Recipe();

        Bundle bundle = getActivity().getIntent().getExtras();
        recipe = bundle.getParcelable("data");
        recipeId = recipe.getmId();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.recipe_fragment,container,false);

        //Bundle bundle = getActivity().getIntent().getExtras().getParcelable("data");
        //recipeId = bundle.getI;


        //String text = getArguments().getString("test");

        //Log.d(LOG_TAG, "onCreateView Test = " + text);

        progressBar = (ProgressBar) rootView.findViewById(R.id.load_indicator_recipe);

        mRvIngredients = (RecyclerView) rootView.findViewById(R.id.rv_ingredients);
        mRvSteps = (RecyclerView) rootView.findViewById(R.id.rv_steps);

        mIngredientItems = new ArrayList<>();
        mStepItems = new ArrayList<>();

        mUrl = getString(R.string.url);

        ingredients();
        steps();

        return rootView;
    }

    public void ingredients(){

        Log.d(LOG_TAG, "ingredients");

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        mRvIngredients.setHasFixedSize(true);
        mRvIngredients.setLayoutManager(layoutManager);

        mIngredientsAdapter = new IngredientsAdapter(getContext(), mIngredientItems);

        mRvIngredients.setAdapter(mIngredientsAdapter);

         loaderIngredient = getLoaderManager();
        loaderIngredient.initLoader(LOADER_ID_INGREDIENTS, null, ingredientLoader);
    }

    public void steps(){

        Log.d(LOG_TAG, "steps");

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        mRvSteps.setHasFixedSize(true);
        mRvSteps.setLayoutManager(layoutManager);

        mStepsAdapter = new StepsAdapter(getContext(), this, mStepItems);

        mRvSteps.setAdapter(mStepsAdapter);

        loaderStep = getLoaderManager();
        loaderStep.initLoader(LOADER_ID_STEPS, null, stepLoader);
    }

    @Override
    public void onClick(Step data) {
        Toast.makeText(getContext(), "Angeklickt", Toast.LENGTH_LONG).show();
        // Todo: Call DetailActivity

        Intent intent = new Intent(getContext(), DetailActivity.class);
        intent.putExtra("stepData", data);
        intent.putParcelableArrayListExtra("stepItems", (ArrayList<? extends Parcelable>) mStepItems);
        startActivity(intent);
    }


}
