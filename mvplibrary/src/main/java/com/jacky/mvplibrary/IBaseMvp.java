package com.jacky.mvplibrary;

import com.jacky.mvplibrary.model.IModel;
import com.jacky.mvplibrary.presenter.BasePresenter;
import com.jacky.mvplibrary.view.IView;

public interface IBaseMvp<M extends IModel, V extends IView, P extends BasePresenter> {

    M createModel();

    V createView();

    P createPresenter();
}
