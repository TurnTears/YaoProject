package com.zy.yaoproject.task;

import android.content.Context;
import android.graphics.Typeface;
import android.os.AsyncTask;

import com.zy.yaoproject.GlobalData;
import com.zy.yaoproject.entity.IconFontEntity;


/**
 * Created by muzi on 2017/7/20.
 * 727784430@qq.com
 */

public class TypeFaceTask extends AsyncTask<Context, Integer, IconFontEntity> {

    private Context context;
    private onComplete complete;

    public void setComplete(onComplete complete) {
        this.complete = complete;
    }

    public TypeFaceTask(Context context) {
        this.context = context;
        GlobalData.iconFontEntity.setPrepare(true);
    }

    @Override
    protected IconFontEntity doInBackground(Context... params) {
        GlobalData.iconFontEntity.setTypeface(Typeface.createFromAsset(context.getAssets(), "iconfont.ttf"));
        return GlobalData.iconFontEntity;
    }

    @Override
    protected void onPostExecute(IconFontEntity iconFontEntity) {
        super.onPostExecute(iconFontEntity);
        if (complete != null) {
            complete.onComplete();
        }
    }

    public interface onComplete {
        void onComplete();
    }
}
