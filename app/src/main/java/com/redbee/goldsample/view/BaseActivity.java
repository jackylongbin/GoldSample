package com.redbee.goldsample.view;

import com.redbee.mvplibrary.BaseMvpActivity;
import com.redbee.mvplibrary.model.IModel;
import com.redbee.mvplibrary.presenter.BasePresenter;
import com.redbee.mvplibrary.view.IView;

public abstract class BaseActivity<M extends IModel, V extends IView, P extends BasePresenter> extends BaseMvpActivity<M, V, P> {

}
