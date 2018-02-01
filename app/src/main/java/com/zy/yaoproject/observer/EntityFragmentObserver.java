package com.zy.yaoproject.observer;

import com.zy.yaoproject.base.fragment.BaseUIFragment;
import com.zy.yaoproject.entity.Result;
import com.zy.yaoproject.utils.StringUtils;
import com.zy.yaoproject.utils.ToastUtils;


/**
 * Created by muzi on 2018/1/30.
 * 727784430@qq.com
 */

public abstract class EntityFragmentObserver<T> extends BaseEntityObserver<T> {

    private BaseUIFragment context;
    private boolean isShow = true;

    public EntityFragmentObserver(BaseUIFragment context) {
        super(context.getActivity());
        this.context = context;
    }

    public EntityFragmentObserver(BaseUIFragment context, boolean isShow) {
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
    public void onError(Result baseEntity) {
        super.onError(baseEntity);
        if (!StringUtils.isEmpty(baseEntity.getShowapi_res_error())) {
            ToastUtils.showToast(baseEntity.getShowapi_res_error());
        }
    }

}
