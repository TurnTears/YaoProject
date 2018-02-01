package com.zy.yaoproject.observer;

import com.zy.yaoproject.base.activity.BaseUIActivity;
import com.zy.yaoproject.entity.Result;
import com.zy.yaoproject.utils.StringUtils;
import com.zy.yaoproject.utils.ToastUtils;


/**
 * Created by muzi on 2018/1/30.
 * 727784430@qq.com
 */

public abstract class EntityActivityObserver<T> extends BaseEntityObserver<T> {

    private BaseUIActivity context;
    private boolean isShow = true;

    public EntityActivityObserver(BaseUIActivity context) {
        super(context);
        this.context = context;
    }

    public EntityActivityObserver(BaseUIActivity context, boolean isShow) {
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
