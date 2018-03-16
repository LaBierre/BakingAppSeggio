package com.example.standard.bakingappseggio.data;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

/**
 * Created by vince on 09.01.2018.
 */

public class Step implements Parcelable {

    private String mShortDescription, mDescription, mVideoURL;

    public Step(String mShortDescription, String mDescription, String mVideoURL) {
        this.mShortDescription = mShortDescription;
        this.mDescription = mDescription;
        this.mVideoURL = mVideoURL;

        Log.d("Test", "Step: Constructor");
    }

    public Step(Parcel parcel) {
        this.mShortDescription = parcel.readString();
        this.mDescription = parcel.readString();
        this.mVideoURL = parcel.readString();
    }

    public String getmShortDescription() {
        return mShortDescription;
    }

    public String getmDescription() {
        return mDescription;
    }

    public String getmVideoURL() {
        return mVideoURL;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(mShortDescription);
        parcel.writeString(mDescription);
        parcel.writeString(mVideoURL);
    }

    public static final Creator<Step> CREATOR = new Creator<Step>() {
        @Override
        public Step createFromParcel(Parcel source) {
            return new Step(source);
        }

        @Override
        public Step[] newArray(int size) {
            return new Step[size];
        }
    };
}
