package com.zy.yaoproject.widget;

import android.content.Context;
import android.util.AttributeSet;

import com.zy.yaoproject.GlobalData;
import com.zy.yaoproject.task.TypeFaceTask;


/**
 * Created by muzi on 2018/1/24.
 * 727784430@qq.com
 */

public class IconFont extends android.support.v7.widget.AppCompatTextView {

    private TypeFaceTask typeFaceTask;

    public IconFont(Context context) {
        this(context, null);
    }

    public IconFont(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        if (!GlobalData.iconFontEntity.isPrepare()) {
            typeFaceTask = new TypeFaceTask(context);
            typeFaceTask.execute();
            return;
        }

        try {
            setTypeface(GlobalData.iconFontEntity.getTypeface());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
