package com.jacky.mvplibrary;

import android.os.Bundle;

import com.jacky.mvplibrary.IBaseMvp;
import com.jacky.mvplibrary.model.IModel;
import com.jacky.mvplibrary.presenter.BasePresenter;
import com.jacky.mvplibrary.view.IView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public abstract class BaseMvpActivity<M extends IModel, V extends IView, P extends BasePresenter> extends AppCompatActivity implements IBaseMvp<M, V, P> {

    protected P presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Create presenter
        presenter = createPresenter();
        if(presenter != null)
        {
            //Register model to presenter
            presenter.registerModel(createModel());
            //Register view to presenter
            presenter.registerView(createView());
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(presenter != null)
        {
            presenter.destroy();
        }
    }

}
