package com.redbee.mvplibrary;

import android.content.Context;

import com.redbee.mvplibrary.model.IModel;
import com.redbee.mvplibrary.presenter.BasePresenter;
import com.redbee.mvplibrary.view.IView;

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
