package com.zy.yaoproject.ui;

import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

import com.jakewharton.rxbinding2.widget.RxTextView;
import com.zy.yaoproject.R;
import com.zy.yaoproject.base.activity.BaseActivity;

import butterknife.BindView;

public class TestActivity extends BaseActivity {

    @BindView(R.id.edit)
    EditText edit;

    @Override
    protected int bindLayout() {
        return R.layout.activity_test;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        RxTextView.afterTextChangeEvents(edit).subscribe(textViewAfterTextChangeEvent -> {
            Log.d("TestActivity", "afterTextChangeEvents");
        });
        RxTextView.textChanges(edit).subscribe(sequence -> {
            Log.d("TestActivity", "textChanges");
        });
        RxTextView.textChangeEvents(edit).subscribe(textViewTextChangeEvent -> {
            Log.d("TestActivity", "textChangeEvents");
        });

    }

}
