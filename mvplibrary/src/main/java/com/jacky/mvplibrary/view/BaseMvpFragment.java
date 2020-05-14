package com.jacky.mvplibrary.view;

import android.content.Context;

import com.jacky.mvplibrary.IBaseMvp;
import com.jacky.mvplibrary.model.IModel;
import com.jacky.mvplibrary.presenter.BasePresenter;

import androidx.fragment.app.Fragment;

public abstract class BaseMvpFragment<M extends IModel, V extends IView, P extends BasePresenter> extends Fragment implements IBaseMvp<M, V, P> {
    protected P presenter;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        presenter = createPresenter();
        if(presenter != null){
            presenter.registerModel(createModel());
            presenter.registerView(createView());
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        if(presenter != null){
            presenter.destroy();
        }
    }
}
