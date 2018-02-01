package com.zy.yaoproject.ui;

import android.widget.ImageView;

import com.zy.yaoproject.R;
import com.zy.yaoproject.base.activity.BaseActivity;
import com.zy.yaoproject.task.TypeFaceTask;
import com.zy.yaoproject.ui.main.MainActivity;
import com.zy.yaoproject.utils.ImagLoadUtils;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;

public class WelcomeActivity extends BaseActivity {

    @BindView(R.id.welcome_img)
    ImageView welcomeImg;

    @Override
    protected int bindLayout() {
        return R.layout.activity_welcome;
    }

    @Override
    protected void initView() {
        ImagLoadUtils.loadImg(this, R.drawable.welcome_img, welcomeImg);
        initIconFont();
    }

    private void initIconFont() {
        TypeFaceTask typeFaceTask = new TypeFaceTask(this);
        typeFaceTask.execute();
        typeFaceTask.setComplete(() ->
                Observable.timer(500, TimeUnit.MILLISECONDS)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(aLong -> startActivity(MainActivity.class))
        );
    }
}
