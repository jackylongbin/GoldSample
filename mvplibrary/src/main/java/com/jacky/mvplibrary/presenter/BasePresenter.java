package com.jacky.mvplibrary.presenter;


import com.jacky.mvplibrary.model.IModel;
import com.jacky.mvplibrary.view.IView;

import java.lang.ref.WeakReference;

public abstract class BasePresenter<M extends IModel, V extends IView> implements IPresenter<M, V>{

    /**
     * Use weak reference to define OOM
     */
    private WeakReference<V> weakReference;
    protected M model;

    @Override
    public void registerModel(M model) {
        this.model = model;
    }

    @Override
    public void registerView(V view) {
        weakReference = new WeakReference<>(view);
    }

    @Override
    public V getView() {
        return weakReference == null ? null : weakReference.get();
    }

    /**
     * When unregister activity or fragment, invoke void finish function
     */
    @Override
    public void destroy() {
        if(weakReference != null)
        {
            weakReference.clear();
            weakReference = null;
        }
        onViewDestroy();
    }

    protected abstract void onViewDestroy();
}
