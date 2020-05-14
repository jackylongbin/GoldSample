package com.tutk.IOTC;

public interface IVideoDecoder {

    void startDecode();
    boolean onFrame(byte[] buf, int offset, int length);
    void stopDecode();
}
