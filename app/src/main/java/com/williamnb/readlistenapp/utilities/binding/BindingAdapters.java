package com.williamnb.readlistenapp.utilities.binding;

import android.util.Log;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

/**
 * Author: William Giang Nguyen | 3/2022
 */
public class BindingAdapters {
    @BindingAdapter("android:imageURL")
    public static void setImageURL(ImageView imageView, String URL) {
        try {
            imageView.setAlpha(0f);
            Picasso.get()
                    .load(URL)
                    .noFade()
                    .into(imageView, new Callback() {
                        @Override
                        public void onSuccess() {
                            imageView.animate().setDuration(300).alpha(1f).start();
                        }

                        @Override
                        public void onError(Exception e) {
                            Log.d("BindingAdapters", e.getMessage());
                        }
                    });
        } catch (Exception ignored) {
            Log.d("BindingAdapters", ignored.getMessage());
        }
    }
}