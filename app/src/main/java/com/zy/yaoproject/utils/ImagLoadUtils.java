package com.zy.yaoproject.utils;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;

/**
 * Created by muzi on 2017/12/28.
 * 727784430@qq.com
 */

public class ImagLoadUtils {

    public static void loadImg(@NonNull Context context, @NonNull Object path, @NonNull ImageView imageView) {
        Glide.with(context)
                .load(path)
                .into(imageView);
    }

    public static void loadImgFade(@NonNull Context context, @NonNull Object path, @NonNull ImageView imageView) {
        Glide.with(context)
                .load(path)
                .transition(withCrossFade())
                .into(imageView);
    }

    public static void loadImgPlace(@NonNull Context context, @NonNull Object path, @NonNull ImageView imageView, @DrawableRes int resId) {
        Glide.with(context)
                .load(path)
                .apply(new RequestOptions().error(resId).placeholder(resId))
                .into(imageView);
    }

    public static void loadImgPlaceFade(@NonNull Context context, @NonNull Object path, @NonNull ImageView imageView, @DrawableRes int resId) {
        Glide.with(context)
                .load(path)
                .apply(new RequestOptions().error(resId).placeholder(resId))
                .transition(withCrossFade())
                .into(imageView);
    }

    public static void loadImgOptions(@NonNull Context context, @NonNull Object path, @NonNull ImageView imageView, @NonNull RequestOptions requestOptions) {
        Glide.with(context)
                .load(path)
                .apply(requestOptions)
                .into(imageView);
    }
    public static void loadImgFadeOptions(@NonNull Context context, @NonNull Object path, @NonNull ImageView imageView, @NonNull RequestOptions requestOptions) {
        Glide.with(context)
                .load(path)
                .apply(requestOptions)
                .transition(withCrossFade())
                .into(imageView);
    }
}
