package com.zy.yaoproject.observer;


import com.zy.yaoproject.base.inter.IBaseView;
import com.zy.yaoproject.bean.BaseEntity;
import com.zy.yaoproject.network.ErrorHandle;
import com.zy.yaoproject.utils.StringUtils;

/**
 * Created by muzi on 2018/3/2.
 * 727784430@qq.com
 */

public abstract class EntityObserver<T extends BaseEntity> extends BaseObserver<T> {

    private IBaseView iBaseView;

    public EntityObserver(IBaseView iBaseView) {
        super(iBaseView);
        this.iBaseView = iBaseView;
    }

    public EntityObserver(IBaseView iBaseView, boolean showLoadView) {
        super(iBaseView, showLoadView);
        this.iBaseView = iBaseView;
    }


    @Override
    public final void onNext(T t) {
        super.onNext(t);
        switch (t.getCode()) {
            case ErrorHandle.SUCCESS:
                onSuccess(t);
                break;
            default:
                onOther(t);
                break;
        }
    }

    @Override
    protected void onError(BaseEntity t) {
        super.onError(t);
        if (!StringUtils.isEmpty(t.getMsg()) && isShowToast()) {
            iBaseView.showToast(t.getMsg());
        }
    }

}
