package com.redbee.goldsample.view;

import android.os.Bundle;
import android.view.Surface;
import android.view.TextureView;
import android.view.WindowManager;
import android.widget.Button;

import com.redbee.goldsample.R;
import com.redbee.goldsample.model.ICameraModel;
import com.redbee.goldsample.model.impl.CameraModelImpl;
import com.redbee.goldsample.presenter.CameraPresenter;
import com.redbee.goldsample.view.interfaceview.ICameraView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CameraActivity extends BaseActivity<ICameraModel, ICameraView, CameraPresenter> implements ICameraView {

    @BindView(R.id.bt_connect)
    public Button btConnect;

    @BindView(R.id.tv_camera_view)
    public TextureView tvCameraView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        ButterKnife.bind(this);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON,
                WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);//保持屏幕唤醒
    }

    @OnClick(R.id.bt_connect)
    public void onCameraConnect()
    {
        if(presenter != null)
        {
            presenter.startConnectCamera(new Surface(tvCameraView.getSurfaceTexture()), 740, 360, "SKNBHT25ZS92G1BZ111A");
        }
    }



    @Override
    public void showToast(String info) {

    }

    @Override
    public void showProgress() {

    }

    @Override
    public ICameraModel createModel() {
        return new CameraModelImpl();
    }

    @Override
    public ICameraView createView() {
        return this;
    }

    @Override
    public CameraPresenter createPresenter() {
        return new CameraPresenter();
    }
}
