package com.example.standard.bakingappseggio.loaders;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;

import com.example.standard.bakingappseggio.data.Step;
import com.example.standard.bakingappseggio.data.StepsUtils;

import java.util.List;

/**
 * Created by vince on 09.01.2018.
 */

public class StepsLoader extends AsyncTaskLoader<List<Step>> {

    private String mUrl;
    private int mRecipeId;

    public StepsLoader(Context context, String mUrl, int mRecipeId) {
        super(context);
        this.mUrl = mUrl;
        this.mRecipeId = mRecipeId;
        Log.d("Test", "StepsLoader: Constructor");
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        Log.d("Test", "StepsLoader: onStartLoading");
        forceLoad();
    }

    @Override
    public List<Step> loadInBackground() {
        if (mUrl == null) {
            return null;
        }
        Log.d("Test", "StepsLoader: loadInBackground");
        List<Step> steps = StepsUtils.fetchStepData(getContext(), mUrl, mRecipeId);
        return steps;
    }
}
