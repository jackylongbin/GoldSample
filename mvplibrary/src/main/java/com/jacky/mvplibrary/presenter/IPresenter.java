package com.jacky.mvplibrary.presenter;


import com.jacky.mvplibrary.model.IModel;
import com.jacky.mvplibrary.view.IView;

public interface IPresenter<M extends IModel, V extends IView> {

    /**
     * Register model
     */
    void registerModel(M model);

    /**
     * Register view
     */
    void registerView(V view);

    /**
     * Get view
     */
    V getView();

    /**
     * Destroy(Unregister Activity Or Fragment)
     */
    void destroy();
}
