package com.zy.yaoproject.observer;

import com.zy.yaoproject.base.fragment.BaseUIFragment;
import com.zy.yaoproject.entity.Result;
import com.zy.yaoproject.utils.StringUtils;
import com.zy.yaoproject.utils.ToastUtils;

import org.json.JSONObject;

/**
 * Created by muzi on 2018/1/30.
 * 727784430@qq.com
 */

public abstract class JsonFragmentObserver extends BaseJsonObserver {

    private boolean isShow = true;
    private BaseUIFragment context;


    public JsonFragmentObserver(BaseUIFragment context) {
        super(context.getActivity());
        this.context = context;
    }

    public JsonFragmentObserver(BaseUIFragment context, boolean isShow) {
        this(context);
        this.isShow = isShow;
    }

    @Override
    public void onStart() {
        if (isShow) {
            context.refreshStart();
        }
    }

    @Override
    public void onComplete() {
        super.onComplete();
        if (isShow) {
            context.refreshFinish();
        }
    }

    @Override
    public void onError(Result baseEntity, JSONObject jsonObject) {
        super.onError(baseEntity, jsonObject);
        if (!StringUtils.isEmpty(baseEntity.getShowapi_res_error())) {
            ToastUtils.showToast(baseEntity.getShowapi_res_error());
        }
    }

}
