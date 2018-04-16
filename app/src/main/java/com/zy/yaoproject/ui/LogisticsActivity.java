package com.zy.yaoproject.ui;

import android.os.Bundle;
import android.widget.Button;

import com.zy.yaoproject.R;
import com.zy.yaoproject.base.activity.BaseActivity;
import com.zy.yaoproject.bean.BusTypeFund;
import com.zy.yaoproject.network.RxRetrofit;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 后勤
 */
public class LogisticsActivity extends BaseActivity {

    @BindView(R.id.button)
    Button button;

    @Override
    protected int bindLayout() {
        return R.layout.activity_logistics;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

    }


    @OnClick(R.id.button)
    public void onViewClicked() {
        test();
    }


    private void test() {
        List<BusTypeFund> busTypeFunds = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            BusTypeFund fund = new BusTypeFund();
            fund.setName("小典手套" + i);
            fund.setUnit("条");
            fund.setTypeId("1");
            busTypeFunds.add(fund);
        }
        Map<String, Object> map = new HashMap<>();
        map.put("map", busTypeFunds);

        RxRetrofit.getApi()
                .addNeed(map)
                .compose(applySchedulers())
                .subscribe();
    }
}
