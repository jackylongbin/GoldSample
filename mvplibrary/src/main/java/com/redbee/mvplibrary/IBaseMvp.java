package com.redbee.mvplibrary;

import com.redbee.mvplibrary.model.IModel;
import com.redbee.mvplibrary.presenter.BasePresenter;
import com.redbee.mvplibrary.view.IView;

public interface IBaseMvp<M extends IModel, V extends IView, P extends BasePresenter> {

    M createModel();

    V createView();

    P createPresenter();
}
