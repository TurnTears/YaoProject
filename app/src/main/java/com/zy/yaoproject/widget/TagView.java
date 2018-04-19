package com.zy.yaoproject.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;

import com.zy.yaoproject.R;

/**
 * Created by muzi on 2018/4/19.
 * 727784430@qq.com
 */
public class TagView extends View {

    private Path path;
    private Paint paint;

    public TagView(Context context) {
        this(context, null);
    }

    public TagView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        path = new Path();
        paint = new Paint();
        paint.setColor(ContextCompat.getColor(getContext(), R.color.colorAccent));
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);


        path.lineTo(0, 40);
        path.lineTo(10, 63);
        path.lineTo(20, 40);
        path.lineTo(20, 0);
        path.lineTo(0, 0);
        path.close();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(20, 63);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawPath(path, paint);
    }

    public void setColor(int color){
        paint.setColor(color);
        postInvalidate();
    }
}
