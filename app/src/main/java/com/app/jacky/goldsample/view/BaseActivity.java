package com.app.jacky.goldsample.view;

import com.jacky.mvplibrary.BaseMvpActivity;
import com.jacky.mvplibrary.model.IModel;
import com.jacky.mvplibrary.presenter.BasePresenter;
import com.jacky.mvplibrary.view.IView;

public abstract class BaseActivity<M extends IModel, V extends IView, P extends BasePresenter> extends BaseMvpActivity<M, V, P> {

}
