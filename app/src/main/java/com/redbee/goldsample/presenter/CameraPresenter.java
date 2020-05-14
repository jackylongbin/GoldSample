package com.redbee.goldsample.presenter;

import android.graphics.Bitmap;
import android.view.Surface;

import com.redbee.goldsample.logger.LoggerUtil;
import com.redbee.goldsample.model.ICameraModel;
import com.redbee.goldsample.view.interfaceview.ICameraView;
import com.redbee.mvplibrary.presenter.BasePresenter;
import com.tutk.IOTC.IRegisterIOTCListener;
import com.tutk.IOTC.TUTKCamera;

public class CameraPresenter extends BasePresenter<ICameraModel, ICameraView> implements IRegisterIOTCListener {

    public void startConnectCamera(Surface surface, int width, int height, String uid) {
        if(model != null)
        {
            model.startConnectCamera(surface, width, height, uid,this);
        }
    }

    @Override
    public void receiveFrameData(TUTKCamera camera, int avChannel, Bitmap bmp) {

    }

    @Override
    public void receiveFrameInfo(TUTKCamera camera, int avChannel, long bitRate, int frameRate, int onlineNm, int frameCount, int incompleteFrameCount) {

    }

    @Override
    public void receiveSessionInfo(TUTKCamera camera, int resultCode) {
        LoggerUtil.d("Receive session info:" + resultCode);

        switch (resultCode)
        {
            case TUTKCamera.CONNECTION_STATE_CONNECT_FAILED:
                break;
            case TUTKCamera.CONNECTION_STATE_CONNECTED:
                if(model != null){
                    model.startCamera(0,"IPC0999999","IPC0999999");
                }
                break;
        }
    }

    @Override
    public void receiveChannelInfo(TUTKCamera camera, int avChannel, int resultCode) {
        LoggerUtil.d("Receive channel info:" + resultCode);
        switch (resultCode)
        {
            case TUTKCamera.CONNECTION_STATE_CONNECTED:
                if(model != null)
                {
                    model.startShow(avChannel, true);
                }
                break;
        }
    }

    @Override
    public void receiveIOCtrlData(TUTKCamera camera, int avChannel, int avIOCtrlMsgType, byte[] data) {

    }

    @Override
    protected void onViewDestroy() {
        if(model != null)
        {
            model.stopCamera(0);
        }
    }
}
