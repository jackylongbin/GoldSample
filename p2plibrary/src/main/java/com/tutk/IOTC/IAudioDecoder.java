package com.tutk.IOTC;

public interface IAudioDecoder {

    void startDecode();
    boolean onFrame(byte[] buf, int offset, int length);
    void stopDecode();
}
