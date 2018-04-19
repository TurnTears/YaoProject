package com.zy.yaoproject.widget.dialogfragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.zy.yaoproject.R;
import com.zy.yaoproject.base.dialogfragment.BaseDialogFragment;
import com.zy.yaoproject.bean.AddCallBackBean;
import com.zy.yaoproject.bean.BaseBean;
import com.zy.yaoproject.bean.BusTypeFund;
import com.zy.yaoproject.bean.NeeadBean;
import com.zy.yaoproject.network.RxRetrofit;
import com.zy.yaoproject.observer.EntityObserver;
import com.zy.yaoproject.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

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

    private String name;
    private String unit;
    private String typeId;
    private NeeadBean neeadBean;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        typeId = getArguments().getString("typeId");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
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
                name = editName.getText().toString().trim();
                unit = editUnit.getText().toString().trim();
                if (StringUtils.isEmpty(name) ||
                        StringUtils.isEmpty(unit)) {
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
        fund.setName(name);
        fund.setUnit(unit);
        fund.setTypeId(typeId);
        busTypeFunds.add(fund);
        RxRetrofit.getApi()
                .addNeed(busTypeFunds)
                .compose(applySchedulers())
                .subscribe(new EntityObserver<AddCallBackBean>(this) {
                    @Override
                    protected void onSuccess(AddCallBackBean addCallBackBean) {
                        super.onSuccess(addCallBackBean);
                        String ids = addCallBackBean.getIds().get(0);
                        neeadBean = new NeeadBean();
                        neeadBean.setId(ids);
                        neeadBean.setName(name);
                        neeadBean.setUnit(unit);
                        if (addCallBack != null) {
                            addCallBack.onSuccess(neeadBean);
                        }
                        dismissDialog();
                    }

                    @Override
                    protected void onError(BaseBean t) {
                        super.onError(t);
                        if (StringUtils.isEmpty(t.getMsg())) {
                            showToast("添加失败请重试");
                        }
                    }
                });
    }

    private void dismissDialog() {
        editUnit.setText(null);
        editName.setText(null);
        dismissAllowingStateLoss();
    }

    private AddCallBack addCallBack;

    public void setAddCallBack(AddCallBack addCallBack) {
        this.addCallBack = addCallBack;
    }

    public interface AddCallBack {
        void onSuccess(NeeadBean neeadBean);
    }
}
