package com.app.jacky.goldsample.model;

import android.view.Surface;

import com.jacky.mvplibrary.model.IModel;
import com.tutk.IOTC.IRegisterIOTCListener;

public interface ICameraModel extends IModel {

    void startConnectCamera(Surface surface, int width, int height, String uid, IRegisterIOTCListener iotcListener);

    void startCamera(int avChannel, String viewAccount, String viewPassword);

    void startShow(int avChannel, boolean avNoClearBuf);

    void stopCamera(int avChannel);
}
