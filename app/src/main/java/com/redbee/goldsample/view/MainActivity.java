package com.redbee.goldsample.view;

import android.os.Bundle;
import android.widget.TextView;

import com.redbee.goldsample.R;
import com.redbee.goldsample.model.IMainModel;
import com.redbee.goldsample.model.impl.MainModelImpl;
import com.redbee.goldsample.presenter.MainPresenter;
import com.redbee.goldsample.view.interfaceview.IMainView;

import androidx.annotation.Nullable;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity<IMainModel, IMainView, MainPresenter> implements IMainView {

    @BindView(R.id.textView)
    public TextView textView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    @Override
    public IMainModel createModel() {
        return new MainModelImpl();
    }

    @Override
    public IMainView createView() {
        return this;
    }

    @Override
    public MainPresenter createPresenter() {
        return new MainPresenter();
    }

    @Override
    public void showToast(String info) {

    }

    @Override
    public void showProgress() {

    }
}