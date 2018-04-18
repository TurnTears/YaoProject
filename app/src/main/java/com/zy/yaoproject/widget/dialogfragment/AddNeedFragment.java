package com.zy.yaoproject.widget.dialogfragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.zy.yaoproject.R;
import com.zy.yaoproject.base.dialogfragment.BaseDialogFragment;
import com.zy.yaoproject.bean.BusTypeFund;
import com.zy.yaoproject.network.RxRetrofit;
import com.zy.yaoproject.observer.BaseObserver;
import com.zy.yaoproject.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import okhttp3.ResponseBody;

/**
 * Created by muzi on 2018/4/17.
 * 727784430@qq.com
 */

public class AddNeedFragment extends BaseDialogFragment {

    @BindView(R.id.text_name)
    TextView textName;
    @BindView(R.id.edit_name)
    EditText editName;
    @BindView(R.id.text_unit)
    TextView textUnit;
    @BindView(R.id.edit_unit)
    EditText editUnit;
    Unbinder unbinder;
    @BindView(R.id.btn_cancel)
    Button btnCancel;
    @BindView(R.id.btn_complete)
    Button btnComplete;

    private String typeId;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        typeId = getArguments().getString("typeId");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_fragment_add_need, container, true);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    public static AddNeedFragment getInstance(String typeId) {
        AddNeedFragment fragment = new AddNeedFragment();
        Bundle bundle = new Bundle();
        bundle.putString("typeId", typeId);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.btn_cancel, R.id.btn_complete})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_cancel:
                dismissDialog();
                break;
            case R.id.btn_complete:
                if (StringUtils.isEmpty(editName.getText().toString().trim()) ||
                        StringUtils.isEmpty(editUnit.getText().toString().trim())) {
                    showToast("请补全信息！");
                    return;
                }
                addNeed();
                break;
        }
    }

    /**
     * 添加
     */
    private void addNeed() {
        List<BusTypeFund> busTypeFunds = new ArrayList<>();
        BusTypeFund fund = new BusTypeFund();
        fund.setName(editName.getText().toString().trim());
        fund.setUnit(editUnit.getText().toString().trim());
        fund.setTypeId(typeId);
        busTypeFunds.add(fund);
        RxRetrofit.getApi()
                .addNeed(busTypeFunds)
                .compose(applySchedulers())
                .subscribe(new BaseObserver<ResponseBody>(this) {
                    @Override
                    protected void onSuccess(ResponseBody responseBody) {
                        super.onSuccess(responseBody);
                        dismissDialog();
                    }
                });
    }

    private void dismissDialog() {
        editUnit.setText(null);
        editName.setText(null);
        dismissAllowingStateLoss();
    }
}
