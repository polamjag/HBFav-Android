package com.hbfav.android.core;

import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.preference.PreferenceManager;

import com.hbfav.android.Constants;
import com.hbfav.android.model.ImageCache;
import com.hbfav.android.ui.MainActivity;
import com.loopj.android.http.BinaryHttpResponseHandler;

public class UserInfoManager {

    public static void setUserName(final String userName) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(MainActivity.getContextOfApplication());
        sp.edit().putString(Constants.PREF_USER_NAME, userName).apply();
    }

    public static String getUserName() {
        return PreferenceManager.getDefaultSharedPreferences(MainActivity.getContextOfApplication()).getString(Constants.PREF_USER_NAME, "");
    }

    public static Drawable getUserThumb() {
        Drawable thumb = ImageCache.getImage(thumbUrl());
        if (thumb == null) {
            HBFavFetcher.getImage(thumbUrl(), new BinaryHttpResponseHandler(HBFavFetcher.ALLOWED_IMAGE_CONTENT_TYPE) {
                @Override
                public void onSuccess(byte[] fileData) {
                    Drawable image = new BitmapDrawable(BitmapFactory.decodeByteArray(fileData, 0, fileData.length));
                    ImageCache.setImage(thumbUrl(), image);
                }
            });
        }
        return thumb;
    }

    private static String thumbUrl() {
        return Constants.BASE_THUMBNAIL_URL + getUserName() + "/profile.gif";
    }
}
