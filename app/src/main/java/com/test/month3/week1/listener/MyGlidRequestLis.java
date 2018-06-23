package com.test.month3.week1.listener;

import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;

import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.demonstrate.DemonstrateUtil;

/**
 * Created by think on 2018/3/16.
 */

public class MyGlidRequestLis implements RequestListener<Drawable> {

    protected void onResourceReadyBitmap(int height) {
        DemonstrateUtil.showLogResult("height:".concat(String.valueOf(height)));
    }

    @Override
    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
        return false;
    }

    @Override
    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
        int height = resource.getBounds().height();
        onResourceReadyBitmap(height);
        return true;
    }
}
