package com.tutk.IOTC;

import android.graphics.Bitmap;

public interface IRegisterIOTCListener {

	public void receiveFrameData(final TUTKCamera camera, final int avChannel, final Bitmap bmp);

	public void receiveFrameInfo(final TUTKCamera camera, final int avChannel, final long bitRate, final int frameRate, final int onlineNm, final int frameCount,
                                 final int incompleteFrameCount);

	public void receiveSessionInfo(final TUTKCamera camera, final int resultCode);

	public void receiveChannelInfo(final TUTKCamera camera, final int avChannel, final int resultCode);

	public void receiveIOCtrlData(final TUTKCamera camera, final int avChannel, final int avIOCtrlMsgType, final byte[] data);
}