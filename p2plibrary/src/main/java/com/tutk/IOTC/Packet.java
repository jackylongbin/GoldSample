/******************************************************************************
 *                                                                            *
 * Copyright (c) 2011 by TUTK Co.LTD. All Rights Reserved.                    *
 *                                                                            *
 *                                                                            *
 * Class: Packet.java                                                         *
 *                                                                            *
 * Author: joshua ju                                                          *
 *                                                                            *
 * Date: 2011-05-14                                                           *
 *                                                                            *
 ******************************************************************************/

package com.tutk.IOTC;


public class Packet {

	//
	public static final short byteArrayToShort_Little(byte byt[], int nBeginPos) {
		return (short) ((0xff & byt[nBeginPos]) | ((0xff & byt[nBeginPos + 1]) << 8));
	}

	public static final int byteArrayToInt_Little(byte byt[], int nBeginPos) {
		return (0xff & byt[nBeginPos]) | (0xff & byt[nBeginPos + 1]) << 8 | (0xff & byt[nBeginPos + 2]) << 16 | (0xff & byt[nBeginPos + 3]) << 24;
	}

	public static final int byteArrayToInt_Little(byte byt[]) {
		if (byt.length == 1)
			return 0xff & byt[0];
		else if (byt.length == 2)
			return (0xff & byt[0]) | ((0xff & byt[1]) << 8);
		else if (byt.length == 4)
			return (0xff & byt[0]) | (0xff & byt[1]) << 8 | (0xff & byt[2]) << 16 | (0xff & byt[3]) << 24;
		else
			return 0;
	}

	public static final long byteArrayToLong_Little(byte byt[], int nBeginPos) {

		return (0xff & byt[nBeginPos]) | (0xff & byt[nBeginPos + 1]) << 8 | (0xff & byt[nBeginPos + 2]) << 16 | (0xff & byt[nBeginPos + 3]) << 24
				| (0xff & byt[nBeginPos + 1]) << 32 | (0xff & byt[nBeginPos + 1]) << 40 | (0xff & byt[nBeginPos + 1]) << 48 | (0xff & byt[nBeginPos + 1]) << 56;
	}

	public static final int byteArrayToInt_Big(byte byt[]) {
		if (byt.length == 1)
			return 0xff & byt[0];
		else if (byt.length == 2)
			return (0xff & byt[0]) << 8 | 0xff & byt[1];
		else if (byt.length == 4)
			return (0xff & byt[0]) << 24 | (0xff & byt[1]) << 16 | (0xff & byt[2]) << 8 | 0xff & byt[3];
		else
			return 0;
	}

	public static final byte[] longToByteArray_Little(long value) {
		return new byte[] { (byte) value, (byte) (value >>> 8), (byte) (value >>> 16), (byte) (value >>> 24), (byte) (value >>> 32), (byte) (value >>> 40),
				(byte) (value >>> 48), (byte) (value >>> 56) };
	}

	public static final byte[] intToByteArray_Little(int value) {
		return new byte[] { (byte) value, (byte) (value >>> 8), (byte) (value >>> 16), (byte) (value >>> 24) };
	}
	
	public static final byte[] intToByteArray_Big(int value) {
		return new byte[] { (byte) (value >>> 24), (byte) (value >>> 16), (byte) (value >>> 8), (byte) value };
	}

	public static final byte[] shortToByteArray_Little(short value) {
		return new byte[] { (byte) value, (byte) (value >>> 8) };

	}

	public static final byte[] shortToByteArray_Big(short value) {
		return new byte[] { (byte) (value >>> 8), (byte) value };
	}

	public static final int StringIpToInt(String StrIp){
	    int ipadress = 0;
	    String[] spilts = StrIp.split("\\.");
	    
	    ipadress = Integer.parseInt(spilts[0])& 0xff;
	    ipadress = ipadress|((Integer.parseInt(spilts[1])&0xff)<< 8);
	    ipadress = ipadress|((Integer.parseInt(spilts[2])&0xff)<< 16);
	    ipadress = ipadress|((Integer.parseInt(spilts[3])&0xff)<< 24);
	    
	    return ipadress;
	}
	
	
	 public static byte[] longToByte(long res,int length) {  
	        byte[] targets = new byte[5];	        
	        targets[0] = (byte) (res & 0xff);// 最低位  
	        targets[1] = (byte) ((res >> 8) & 0xff);// 次低位  
	        targets[2] = (byte) ((res >> 16) & 0xff);// 次高位  
	        targets[3] = (byte) (res >>> 24);  
	        if(length > 5)
	            targets[4] = (byte) (res >>>32);  
	        if(length > 5)
	        	targets[6] = (byte) (res >>>40);
	        return targets;  
	    }  
	 public static byte[] intToByte(long res,int length) {  
	        byte[] targets = new byte[5];
	        if(length > 0){
	        	 targets[0] = (byte) (res & 0xff);// 最低位  
	        }
	        if(length > 1){
	        	targets[1] = (byte) ((res >> 8) & 0xff);// 次低位  
	        }
	        if(length > 2){
	        	 targets[2] = (byte) ((res >> 16) & 0xff);
	        }
	        if(length > 3){
	        	 targets[3] = (byte) (res >>> 24);  
	        }
	       
	    
	        return targets;  
	    }  
	/**
	 * 智能家居校验方法
	 * @param result需要校验的字节数组(result.length = 12 (固定))
	 * @return 返回校验码
	 */
	public static byte xORVerifySum(byte[] result){
		byte verifySum = -1;
		int len = result.length - 1;
		for(int i = 0; i < len;i++){
			if(i == 0){
				verifySum  =(byte)(result[i]^result[i+1]); 	
			}else {
				verifySum = (byte)(verifySum ^ result[i+1]);
			}
		}
		return verifySum;
	}

	public static final byte[] ScheduleTimeToByteArray_Little(int min,int hour) {
		int time = (int)(min&0x3f) + (int)((int)(hour&0x1f)<<6);
		return new byte[] { (byte) time, (byte) (time >>> 8), (byte) (time >>> 16), (byte) (time >>> 24) };
	}
}
