package com.zy.yaoproject.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.jakewharton.rxbinding2.widget.RxTextView;
import com.zy.yaoproject.R;

/**
 * Created by muzi on 2018/4/17.
 * 727784430@qq.com
 */

public class AddSubView extends LinearLayout implements View.OnClickListener {

    private ImageButton btnAdd, btnSub;
    private EditText editText;
    private boolean isShowing = false;
    private int number;

    public AddSubView(Context context) {
        this(context, null);
    }

    public AddSubView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.widget_add_sub, this, true);
        btnAdd = findViewById(R.id.btn_add);
        btnSub = findViewById(R.id.btn_sub);
        editText = findViewById(R.id.edit);
        btnAdd.setOnClickListener(this);
        btnSub.setOnClickListener(this);
        editText.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_NULL) {
                accepte();
                return true;
            }
            return false;
        });

        RxTextView.afterTextChangeEvents(editText).subscribe(textViewAfterTextChangeEvent -> {
            accepte();
        });
    }

    private void accepte() {
        try {
            int inputNumber = Integer.parseInt(editText.getText().toString().trim());
            if (inputNumber > number) {
                number = inputNumber;
                if (listener != null) {
                    listener.onChange(true, number);
                }
            } else {
                number = inputNumber;
                if (listener != null) {
                    listener.onChange(false, number);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_add:
                number++;
                if (!isShowing) {
                    isShowing = true;
                    btnSub.setVisibility(VISIBLE);
                    editText.setVisibility(VISIBLE);
                }
                editText.setText(String.valueOf(number));
                break;
            case R.id.btn_sub:
                if (number > 1) {
                    number--;
                } else {
                    number = 0;
                    isShowing = false;
                    btnSub.setVisibility(INVISIBLE);
                    editText.setVisibility(INVISIBLE);
                }
                editText.setText(String.valueOf(number));
                break;
        }
    }

    public int getNumber() {
        return number;
    }

    private OnChangeListener listener;

    public void setListener(OnChangeListener listener) {
        this.listener = listener;
    }

    public interface OnChangeListener {
        void onChange(boolean isAdd, int number);
    }
}
