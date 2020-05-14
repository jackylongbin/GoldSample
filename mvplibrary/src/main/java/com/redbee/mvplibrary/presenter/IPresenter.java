package com.redbee.mvplibrary.presenter;


import com.redbee.mvplibrary.model.IModel;
import com.redbee.mvplibrary.view.IView;

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
