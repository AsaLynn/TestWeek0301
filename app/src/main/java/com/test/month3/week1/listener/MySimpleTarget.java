package com.test.month3.week1.listener;

import android.graphics.Bitmap;

import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.example.demonstrate.DemonstrateUtil;

/**
 * Created by think on 2018/3/16.
 */

public class MySimpleTarget extends SimpleTarget<Bitmap> {

    @Override
    public void onResourceReady(Bitmap resource, Transition<? super Bitmap> transition) {
        int height = resource.getHeight();
        DemonstrateUtil.showLogResult("height".concat(String.valueOf(height)));
    }
}
