package com.redbee.goldsample.model.impl;

import android.view.Surface;

import com.redbee.goldsample.model.ICameraModel;
import com.redbee.p2plibrary.HardwareH264Decode;
import com.tutk.IOTC.IRegisterIOTCListener;
import com.tutk.IOTC.TUTKCamera;

public class CameraModelImpl implements ICameraModel {

    private TUTKCamera tutkCamera;

    @Override
    public void startConnectCamera(Surface surface, int width, int height, String uid, IRegisterIOTCListener iotcListener) {
        TUTKCamera camera = new TUTKCamera();
        HardwareH264Decode hardwareH264Decode = new HardwareH264Decode( surface,width,height);
        camera.setVideoDecoder(hardwareH264Decode);
        camera.init();
        camera.registerIOTCListener(iotcListener);
        camera.connect(uid);
    }

    @Override
    public void startCamera(int avChannel, String username, String password) {
        tutkCamera.start(avChannel, username, password);
    }

    @Override
    public void startShow(int avChannel, boolean avNoClearBuf) {
        tutkCamera.startShow(avChannel, avNoClearBuf);
    }

    @Override
    public void stopCamera(int avChannel) {
        tutkCamera.stop(avChannel);
        tutkCamera = null;
    }


}
