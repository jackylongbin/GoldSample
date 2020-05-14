package com.tutk.IOTC;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.AudioFormat;
import android.media.AudioRecord;
import android.media.MediaRecorder;
import android.util.Log;
import com.tutk.IOTC.AVIOCTRLDEFs.SMsgAVIoctrlAVStream;
import com.tutk.IOTC.AVIOCTRLDEFs.SMsgAVIoctrlReceiveFirstIFrame;


import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedList;
import java.util.Vector;


public class TUTKCamera {
	private static final String TAG = "TUTKCamera";

	/* camera info */
	private String mDevUID;
	private String mDevAccount;
	private String mDevPwd;

	//Camera counts
	private static volatile int mCameraCount = 0;
	//AVChannel counts limit
	private static int mDefaultMaxCameraLimit = 4;

	private final Object mWaitObjectForConnected = new Object();
	//IOTC session connecting thread
	private ThreadConnectDev mThreadConnectDev = null;
	//IOTC session connecting check thread
	private ThreadCheckDevStatus mThreadChkDevStatus = null;
	//TUTK IOTC get session id
	private volatile int nGet_SID = -1;
	//TUTK IOTC connected return sid
	private volatile int mSID = -1;
	//Send audio thread
	private ThreadSendAudio mThreadSendAudio = null;
	//Record session mode
	private volatile int mSessionMode = -1;


	private boolean mReocrding = false;
	private boolean mInitReocrd = false;
	private String mFileName = null;

	private int mKeepTime = 0;
	Date RecordTime = null;
	private Date t;
	public static final int DEFAULT_AV_CHANNEL = 0;

	public static final int CONNECTION_STATE_NONE = 0;
	public static final int CONNECTION_STATE_CONNECTING = 1;
	public static final int CONNECTION_STATE_CONNECTED = 2;
	public static final int CONNECTION_STATE_DISCONNECTED = 3;
	public static final int CONNECTION_STATE_UNKNOWN_DEVICE = 4;
	public static final int CONNECTION_STATE_WRONG_PASSWORD = 5;
	public static final int CONNECTION_STATE_TIMEOUT = 6;
	public static final int CONNECTION_STATE_UNSUPPORTED = 7;
	public static final int CONNECTION_STATE_CONNECT_FAILED = 8;
	public static final int SEARCH_SUCCESS =9;
	public static final int SEARCH_ERROR =10;
	public static final int CONNECTION_STATE_WRONG_UID =11;
	public static final int SPEAK_STOP=13;
	public static final int SPEAK_ERROR = 14;

	public static  final int RECORD_ERR = 0x2000;
	public static final int RECORD_OK = 0x2001;
	public static final int RECORD_TOO_SHORT = 0x2002;
	public static final int RECORDING = 0x2003;

	public static final int MAX_VIDEO_BUFFER = 512000;
	public static final int STATUS_BEGIN_RECORD = 0;
	public static final int STATUS_END_RECORD = 1;
	public static final int STATUS_PREPARE_RECORD = 2;
	private int _recordStatus = STATUS_END_RECORD;
	private volatile int[] bResend = new int[1];
	private volatile int nRecvFrmPreSec;
	private volatile int nDispFrmPreSec;
	private volatile int tempAvIndex = -1;

	private int mCamIndex = 0;

	public static int nFlow_total_FPS_count = 0;
	public static int nFlow_total_FPS_count_noClear = 0;

	private Vector<IRegisterIOTCListener> mIOTCListeners = new Vector<IRegisterIOTCListener>();
	protected static Vector<AVChannel> mAVChannels = new Vector<AVChannel>();


	private IVideoDecoder mVideoDecoder = null;
	private IAudioDecoder mAudioDecoder = null;


	public TUTKCamera() {
		mDevUID = "";
		mDevPwd = "";
	}

	public void setVideoDecoder(IVideoDecoder videoDecoder)
	{
		this.mVideoDecoder = videoDecoder;
	}

	public void setmAudioDecoder(IAudioDecoder audioDecoder)
	{
		this.mAudioDecoder = audioDecoder;
	}

	public void record(){

		//当视频线程异常退出时，请置_recordStatus为STATUS_END_RECORD
		//当开启时要判断是否音视频流还活着，活着才可以置_recordStatus为STATUS_PREPARE_RECORD
	}

	/**
	 * Search cameras in LAN
	 * @return
	 */
	public synchronized static st_LanSearchInfo2[] SearchLAN() {

		int num[] = new int[1];
		st_LanSearchInfo2[] result = null;
//		result = IOTCAPIs.IOTC_Lan_Search(num,3000);
		result = IOTCAPIs.IOTC_Lan_Search2(num,3000);
		return result;
	}
	/**
	 * Set camera limit
	 * @param limit
	 */
	public static void setMaxCameraLimit(int limit) {
		mDefaultMaxCameraLimit = limit;
	}

	/**
	 * Initial TUTKCamera
	 * @return
	 */
	public synchronized static int init() {
		int nRet = 0;
		if (mCameraCount == 0) {
			//Random an UDP port to init IOTC
			int port = (int) (10000 + (System.currentTimeMillis() % 10000));
			//Init TUTK IOTC Module
			nRet = IOTCAPIs.IOTC_Initialize2(port);
			//唤醒
			IOTCAPIs.IOTC_WakeUp_Setup_Auto_WakeUp(1);

			Log.i(TAG, "Initial IOTC APIS: " + nRet);
			if (nRet < 0) {
				return nRet;
			}
			//Init TUTK AVAPI Module with Max channel counts
			nRet = AVAPIs.avInitialize(mDefaultMaxCameraLimit * 16);
			Log.i(TAG, "Initial AV APIS: " + nRet);
			if (nRet < 0) {
				return nRet;
			}
		}
		mCameraCount++;
		return nRet;
	}

	/**
	 * Uninitial TUTKCamera
	 * @return
	 */
	public synchronized static int uninit() {

		int nRet = 0;

		if (mCameraCount > 0) {
			mCameraCount--;
			if (mCameraCount == 0) {
				nRet = AVAPIs.avDeInitialize();
				Log.i(TAG, "AV API DeInitialize: " + nRet);
				nRet = IOTCAPIs.IOTC_DeInitialize();
				Log.i(TAG, "IOTC API DeInitialize: " + nRet);
				//Encoder Decoder uinitial
			}
		}

		return nRet;
	}

	/**
	 * Register listener
	 * @param listener
	 * @return
	 */
	public boolean registerIOTCListener(IRegisterIOTCListener listener) {
		boolean result = false;

		// synchronized (mIOTCListeners) {
		if (!mIOTCListeners.contains(listener)) {
			Log.i(TAG, "register IOTC listener");
			mIOTCListeners.add(listener);
			result = true;
		}
		// }

		return result;
	}

	/**
	 * Remove listener
	 * @param listener
	 * @return
	 */
	public boolean unregisterIOTCListener(IRegisterIOTCListener listener) {
		boolean result = false;

		// synchronized (mIOTCListeners) {
		if (mIOTCListeners.contains(listener)) {
			Log.i(TAG, "unregister IOTC listener");
			mIOTCListeners.remove(listener);
			result = true;
		}
		// }

		return result;
	}

//	public void unRegistAllListener(){
//		mIOTCListeners.removeAllElements();
//	}

	/**
	 * IOTC Session connect with device UID
	 * @param uid
	 */
	public void connect(String uid) {

		mDevUID = uid;
		if (mThreadConnectDev == null) {
			mThreadConnectDev = new ThreadConnectDev();
			mThreadConnectDev.start();
		}

		if (mThreadChkDevStatus == null) {
			mThreadChkDevStatus = new ThreadCheckDevStatus();
			mThreadChkDevStatus.start();
		}
	}
	/**
	 * IOTC Session connect with device UID and password
	 * @param uid
	 */
	public void connect(String uid, String pwd) {

		mDevUID = uid;
		mDevPwd = pwd;

		if (mThreadConnectDev == null) {
			mThreadConnectDev = new ThreadConnectDev();
			mThreadConnectDev.start();
		}

		if (mThreadChkDevStatus == null) {
			mThreadChkDevStatus = new ThreadCheckDevStatus();
			mThreadChkDevStatus.start();
		}
	}

	/**
	 * TUTK IOTC Session connecting thread
	 */
	private class ThreadConnectDev extends Thread {

		private boolean mIsRunning = false;
		private Object m_waitForStopConnectThread = new Object();
		public void stopThread() {

			mIsRunning = false;

			if (nGet_SID == -1)
				IOTCAPIs.IOTC_Connect_Stop_BySID(nGet_SID);

			synchronized (m_waitForStopConnectThread) {
				m_waitForStopConnectThread.notify();
			}
		}

		public void run() {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			int nRetryForIOTC_Conn = 0;

			mIsRunning = true;

			while (mIsRunning && mSID < 0) {
				for (int i = 0; i < mIOTCListeners.size(); i++) {
					IRegisterIOTCListener listener = mIOTCListeners.get(i);
					listener.receiveSessionInfo(TUTKCamera.this, CONNECTION_STATE_CONNECTING);
				}
				nGet_SID = IOTCAPIs.IOTC_Get_SessionID();
				if(nGet_SID >= 0)
				{


					mSID = IOTCAPIs.IOTC_Connect_ByUID_Parallel(mDevUID,nGet_SID);
					Log.e(TAG,"IOTC Session connect success!"+mSID + " get id:" + nGet_SID);
					for (int i = 0; i < mIOTCListeners.size(); i++) {
						IRegisterIOTCListener listener = mIOTCListeners.get(i);
						listener.receiveSessionInfo(TUTKCamera.this, CONNECTION_STATE_WRONG_UID);
					}
					nGet_SID = -1;
				}

				if (mSID >= 0) {
					Log.d(TAG,"IOTC Session connect success!");
					//Session connected notify listeners
					for (int i = 0; i < mIOTCListeners.size(); i++) {
						IRegisterIOTCListener listener = mIOTCListeners.get(i);
						listener.receiveSessionInfo(TUTKCamera.this, CONNECTION_STATE_CONNECTED);
					}
					synchronized (mWaitObjectForConnected) {
						mWaitObjectForConnected.notify();
					}


					//if is in connecting
				} else if (mSID == IOTCAPIs.IOTC_ER_CONNECT_IS_CALLING) {
					Log.d(TAG,"IOTC Session is calling!");
					try {
						synchronized (m_waitForStopConnectThread) {
							m_waitForStopConnectThread.wait(1000);
						}
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					//if can not found device or device offline
				} else if (mSID == IOTCAPIs.IOTC_ER_UNKNOWN_DEVICE || mSID == IOTCAPIs.IOTC_ER_UNLICENSE || mSID == IOTCAPIs.IOTC_ER_CAN_NOT_FIND_DEVICE || mSID == IOTCAPIs.IOTC_ER_TIMEOUT) {

					Log.d(TAG,"IOTC Session can not found device!");
					if (mSID != IOTCAPIs.IOTC_ER_TIMEOUT) {
						for (int i = 0; i < mIOTCListeners.size(); i++) {

							IRegisterIOTCListener listener = mIOTCListeners.get(i);
//							listener.receiveSessionInfo(TUTKCamera.this, CONNECTION_STATE_UNKNOWN_DEVICE);
							listener.receiveSessionInfo(TUTKCamera.this, SEARCH_ERROR);
						}
					}
					nRetryForIOTC_Conn++;
					try {
						long sleepTime = nRetryForIOTC_Conn > 60 ? 60000 : nRetryForIOTC_Conn * 1000;
						synchronized (m_waitForStopConnectThread) {
							m_waitForStopConnectThread.wait(sleepTime);
						}
					} catch (InterruptedException e) {
						e.printStackTrace();
					}

				} else if (mSID == IOTCAPIs.IOTC_ER_DEVICE_NOT_SECURE_MODE ||
						mSID == IOTCAPIs.IOTC_ER_DEVICE_SECURE_MODE) {
					for (int i = 0; i < mIOTCListeners.size(); i++) {
						IRegisterIOTCListener listener = mIOTCListeners.get(i);
						listener.receiveSessionInfo(TUTKCamera.this, CONNECTION_STATE_UNSUPPORTED);
					}
					break;
				} else {
					for (int i = 0; i < mIOTCListeners.size(); i++) {

						IRegisterIOTCListener listener = mIOTCListeners.get(i);
						listener.receiveSessionInfo(TUTKCamera.this, CONNECTION_STATE_CONNECT_FAILED);
					}
					break;
				}
			}

			Log.i(TAG, "===ThreadConnectDev exit===");
		}
	}

	/**
	 * Session status check thread
	 */
	private class ThreadCheckDevStatus extends Thread {

		private boolean m_bIsRunning = false;
		private Object m_waitObjForCheckDevStatus = new Object();

		public void stopThread() {

			m_bIsRunning = false;

			synchronized (m_waitObjForCheckDevStatus) {
				m_waitObjForCheckDevStatus.notify();
			}
		}

		@Override
		public void run() {
			super.run();

			this.setName("Thread-Check-Status");
			m_bIsRunning = true;
			St_SInfo stSInfo = new St_SInfo();
			int ret = 0;

			while (m_bIsRunning && mSID < 0) {

				try {

					synchronized (mWaitObjectForConnected) {
						mWaitObjectForConnected.wait(1000);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			while (m_bIsRunning) {

				if (mSID >= 0) {

					ret = IOTCAPIs.IOTC_Session_Check(mSID, stSInfo);

					if (ret >= 0) {

						if (mSessionMode != stSInfo.Mode) {
							mSessionMode = stSInfo.Mode;
						}
					} else if (ret == IOTCAPIs.IOTC_ER_REMOTE_TIMEOUT_DISCONNECT || ret == IOTCAPIs.IOTC_ER_TIMEOUT) {

						Log.i(TAG, "IOTC_Session_Check(" + mSID + ") timeout");
						for (int i = 0; i < mIOTCListeners.size(); i++) {
							IRegisterIOTCListener listener = mIOTCListeners.get(i);
							listener.receiveSessionInfo(TUTKCamera.this, CONNECTION_STATE_TIMEOUT);
						}
					} else {

						Log.i(TAG, "IOTC_Session_Check(" + mSID + ") Failed return " + ret);
						for (int i = 0; i < mIOTCListeners.size(); i++) {
							IRegisterIOTCListener listener = mIOTCListeners.get(i);
							listener.receiveSessionInfo(TUTKCamera.this, CONNECTION_STATE_CONNECT_FAILED);
						}
					}
				}

				synchronized (m_waitObjForCheckDevStatus) {
					try {
						m_waitObjForCheckDevStatus.wait(5000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}

			Log.i(TAG, "===ThreadCheckDevStatus exit===");
		}
	}


	/**
	 * disconnect session and AV channel connection
	 */
	public void disconnect() {

		synchronized (mAVChannels) {

			for (AVChannel ch : mAVChannels) {

				stopSpeaking(ch.getChannel());

				if (ch.threadStartDev != null)
					ch.threadStartDev.stopThread();

				if (ch.threadDecVideo != null)
					ch.threadDecVideo.stopThread();

				if (ch.threadRecvAudio != null)
					ch.threadRecvAudio.stopThread();

				if (ch.threadRecvVideo != null)
					ch.threadRecvVideo.stopThread();

				if (ch.threadRecvIOCtrl != null)
					ch.threadRecvIOCtrl.stopThread();

				if (ch.threadSendIOCtrl != null)
					ch.threadSendIOCtrl.stopThread();

				if (ch.threadRecvVideo != null) {
					try {
						ch.threadRecvVideo.interrupt();
						ch.threadRecvVideo.join();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					ch.threadRecvVideo = null;
				}

				if (ch.threadRecvAudio != null) {
					try {
						ch.threadRecvAudio.interrupt();
						ch.threadRecvAudio.join();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					ch.threadRecvAudio = null;
				}

				if (ch.threadDecVideo != null) {
					try {
						ch.threadDecVideo.interrupt();
						ch.threadDecVideo.join();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					ch.threadDecVideo = null;
				}

				if (ch.threadRecvIOCtrl != null) {
					try {
						ch.threadRecvIOCtrl.interrupt();
						ch.threadRecvIOCtrl.join();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					ch.threadRecvIOCtrl = null;
				}

				if (ch.threadSendIOCtrl != null) {
					try {
						ch.threadSendIOCtrl.interrupt();
						ch.threadSendIOCtrl.join();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					ch.threadSendIOCtrl = null;
				}

				if (ch.threadStartDev != null && ch.threadStartDev.isAlive()) {
					try {
						ch.threadStartDev.interrupt();
						ch.threadStartDev.join();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				ch.threadStartDev = null;


				ch.AudioFrameQueue.removeAll();
				ch.AudioFrameQueue = null;

				ch.VideoFrameQueue.removeAll();
				ch.VideoFrameQueue = null;

				ch.IOCtrlQueue.removeAll();
				ch.IOCtrlQueue = null;

				if (ch.getAVIndex() >= 0) {

					AVAPIs.avClientStop(ch.getAVIndex());
					for (int j = 0; j < mIOTCListeners.size(); j++){
						IRegisterIOTCListener listener = mIOTCListeners.get(j);
						listener.receiveChannelInfo(TUTKCamera.this,ch.getChannel(),CONNECTION_STATE_DISCONNECTED);
					}
					Log.i(TAG, "avClientStop(avIndex = " + ch.getAVIndex() + ")");
				}
			}
		}
		mAVChannels.clear();

		synchronized (mWaitObjectForConnected) {
			mWaitObjectForConnected.notify();
		}

		if (mThreadChkDevStatus != null)
			mThreadChkDevStatus.stopThread();

		if (mThreadConnectDev != null)
			mThreadConnectDev.stopThread();

		if (mThreadChkDevStatus != null) {
			try {
				mThreadChkDevStatus.interrupt();
				mThreadChkDevStatus.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			mThreadChkDevStatus = null;
		}

		if (mThreadConnectDev != null && mThreadConnectDev.isAlive()) {
			try {
				mThreadConnectDev.interrupt();
				mThreadConnectDev.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		mThreadConnectDev = null;

		if (mSID >= 0) {

			IOTCAPIs.IOTC_Session_Close(mSID);
			Log.i(TAG, "IOTC_Session_Close(nSID = " + mSID + ")");
			mSID = -1;
		}
		for (int i = 0; i < mIOTCListeners.size(); i++){
			IRegisterIOTCListener listener = mIOTCListeners.get(i);
			listener.receiveSessionInfo(TUTKCamera.this,CONNECTION_STATE_DISCONNECTED);
		}

		mSessionMode = -1;
	}




	/**
	 * Start AV connection
	 * @param avChannel
	 * @param viewAccount
	 * @param viewPasswd
	 */
	public void start(int avChannel, String viewAccount, String viewPasswd) {
		mDevAccount = viewAccount;
		mDevPwd = viewPasswd;
		AVChannel session = null;

		synchronized (mAVChannels) {
			for (AVChannel ch : mAVChannels) {
				if (ch.getChannel() == avChannel) {
					session = ch;
					break;
				}
			}
		}

		if (session == null) {

			AVChannel ch = new AVChannel(avChannel, viewAccount, viewPasswd);
			mAVChannels.add(ch);
			//Start AV connection thread
			ch.threadStartDev = new ThreadStartDev(ch);
			ch.threadStartDev.mIsRunning = true;
			ch.threadStartDev.start();

			//Start IO ctrl receive thread
			ch.threadRecvIOCtrl = new ThreadRecvIOCtrl(ch);
			ch.threadRecvIOCtrl.bIsRunning =true;
			ch.threadRecvIOCtrl.start();
			//Start IO ctrl send thread
			ch.threadSendIOCtrl = new ThreadSendIOCtrl(ch);
			ch.threadSendIOCtrl.bIsRunning = true;
			ch.threadSendIOCtrl.start();

		} else {

			if (session.threadStartDev == null) {
				session.threadStartDev = new ThreadStartDev(session);
				session.threadStartDev.mIsRunning = true;
				session.threadStartDev.start();
			}

			if (session.threadRecvIOCtrl == null) {
				session.threadRecvIOCtrl = new ThreadRecvIOCtrl(session);
				session.threadRecvIOCtrl.bIsRunning = true;
				session.threadRecvIOCtrl.start();
			}

			if (session.threadSendIOCtrl == null) {
				session.threadSendIOCtrl = new ThreadSendIOCtrl(session);
				session.threadSendIOCtrl.bIsRunning = true;
				session.threadSendIOCtrl.start();
			}
		}

//		sendIOCtrl(avChannel,AVIOCTRLDEFs.IOTYPE_USER_IPCAM_VIDEOSTART, Packet.intToByteArray_Little(mCamIndex));//9.19 修改
	}

	/**
	 * AV connection thread
	 */
	private class ThreadStartDev extends Thread {

		private boolean mIsRunning = false;
		private AVChannel mAVChannel;
		private Object mWaitObject = new Object();

		public ThreadStartDev(AVChannel channel) {
			mAVChannel = channel;
		}

		public void stopThread() {

			mIsRunning = false;

			if (mSID >= 0) {
				Log.i(TAG, "avClientExit(" + mSID + ", " + mAVChannel.getChannel() + ")");
				AVAPIs.avClientExit(mSID, mAVChannel.getChannel());
			}

			synchronized (mWaitObject) {
				mWaitObject.notify();
			}
		}

		@Override
		public void run() {

//			mIsRunning = true;
			int avIndex = -1;
			while (mIsRunning) {

				if (mSID < 0) {

					try {
						synchronized (mWaitObjectForConnected) {
							mWaitObjectForConnected.wait(100);
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
					continue;
				}

				for (int i = 0; i < mIOTCListeners.size(); i++) {

					IRegisterIOTCListener listener = mIOTCListeners.get(i);
					listener.receiveChannelInfo(TUTKCamera.this, mAVChannel.getChannel(), CONNECTION_STATE_CONNECTING);
				}

				int[] nServType = new int[1];
				nServType[0] = -1;
				avIndex = AVAPIs.avClientStart2(mSID, mAVChannel.getViewAcc(), mAVChannel.getViewPwd(), 30, nServType, mAVChannel.getChannel(),bResend);
				tempAvIndex = avIndex;
				Log.e(TAG, "avClientStart2(" + mAVChannel.getChannel() + ", " + mAVChannel.getViewAcc() + ", " + mAVChannel.getViewPwd() +  ") in Session(" + mSID + ") returns " + avIndex + " bResend = " + bResend[0]);
				long servType = nServType[0];

				if (avIndex >= 0) {

					mAVChannel.setAVIndex(avIndex);
					mAVChannel.setServiceType(servType);

					for (int i = 0; i < mIOTCListeners.size(); i++) {

						IRegisterIOTCListener listener = mIOTCListeners.get(i);
						listener.receiveChannelInfo(TUTKCamera.this, mAVChannel.getChannel(), CONNECTION_STATE_CONNECTED);
					}
					break;

				} else if (avIndex == AVAPIs.AV_ER_REMOTE_TIMEOUT_DISCONNECT || avIndex == AVAPIs.AV_ER_TIMEOUT) {

					for (int i = 0; i < mIOTCListeners.size(); i++) {

						IRegisterIOTCListener listener = mIOTCListeners.get(i);
						listener.receiveChannelInfo(TUTKCamera.this, mAVChannel.getChannel(), CONNECTION_STATE_TIMEOUT);
//						AVAPIs.avClientStop(mAVChannel.getAVIndex());
					}

				}else if (avIndex == AVAPIs.AV_ER_WRONG_VIEWACCorPWD) {

					for (int i = 0; i < mIOTCListeners.size(); i++) {

						IRegisterIOTCListener listener = mIOTCListeners.get(i);
						listener.receiveChannelInfo(TUTKCamera.this, mAVChannel.getChannel(), CONNECTION_STATE_WRONG_PASSWORD);
					}

					break;

				} else {

					try {
						synchronized (mWaitObject) {
							mWaitObject.wait(1000);
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
			Log.i(TAG, "===ThreadStartDev exit===");
		}
	}

	/**
	 * AV control receive thread
	 */
	private class ThreadRecvIOCtrl extends Thread {

		private final int TIME_OUT = 3000;
		private boolean bIsRunning = false;

		private AVChannel mAVChannel;

		public ThreadRecvIOCtrl(AVChannel channel) {
			mAVChannel = channel;
		}

		public void stopThread() {
			bIsRunning = false;
		}

		@Override
		public void run() {

//			bIsRunning = true;

			while (bIsRunning && (mSID < 0 || mAVChannel.getAVIndex() < 0)) {
				try {
					synchronized (mWaitObjectForConnected) {
						mWaitObjectForConnected.wait(1000);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			int idx = 0;

			while (bIsRunning) {

				if (mSID >= 0 && mAVChannel.getAVIndex() >= 0) {

					int[] ioCtrlType = new int[1];
					byte[] ioCtrlBuf = new byte[1024];

					int	nRet = AVAPIs.avRecvIOCtrl(mAVChannel.getAVIndex(), ioCtrlType, ioCtrlBuf, ioCtrlBuf.length, TIME_OUT);
//					Log.i("ThreadRecvIOCtrl", "nRet(" + nRet);
					if (nRet >= 0) {

//						Log.i("ThreadRecvIOCtrl", "avRecvIOCtrl(" + mAVChannel.getAVIndex() + ", 0x" + Integer.toHexString(ioCtrlType[0]) + ", " + getHex(ioCtrlBuf, nRet) + ")");

						byte[] data = new byte[nRet];
						System.arraycopy(ioCtrlBuf, 0, data, 0, nRet);

						if (ioCtrlType[0] == AVIOCTRLDEFs.IOTYPE_USER_IPCAM_GETAUDIOOUTFORMAT_RESP) {

							int channel = Packet.byteArrayToInt_Little(data, 0);
							int format = Packet.byteArrayToInt_Little(data, 4);

							for (AVChannel ch : mAVChannels) {
								if (ch.getChannel() == channel) {
									ch.setAudioCodec(format);
									break;
								}
							}
						}
						if (ioCtrlType[0] == AVIOCTRLDEFs.IOTYPE_USER_IPCAM_EVENT_REPORT) {

							Log.i("ThreadRecvIOCtrl", "========================报警信息=======================");
						}


						if (ioCtrlType[0] == AVIOCTRLDEFs.IOTYPE_USER_IPCAM_GET_FLOWINFO_REQ) {


							int channel = Packet.byteArrayToInt_Little(data, 0);
							int collect_interval = Packet.byteArrayToInt_Little(data, 4);

							for (AVChannel ch : mAVChannels) {
								if (ch.getChannel() == channel) {
									ch.flowInfoInterval = collect_interval;
									sendIOCtrl(mAVChannel.mChannel, AVIOCTRLDEFs.IOTYPE_USER_IPCAM_GET_FLOWINFO_RESP, AVIOCTRLDEFs.SMsgAVIoctrlGetFlowInfoResp.parseContent(channel,ch.flowInfoInterval));
									break;
								}
							}
							Log.i("ThreadRecvIOCtrl", "AVIOCTRLDEFs.IOTYPE_USER_IPCAM_GET_FLOWINFO_REQ ++" );
						}
						for (int i = 0; i < mIOTCListeners.size(); i++) {
							IRegisterIOTCListener listener = mIOTCListeners.get(i);
							Log.i("ThreadRecvIOCtrl", "ioCtrlType[0]:"+ioCtrlType[0] );
							listener.receiveIOCtrlData(TUTKCamera.this, mAVChannel.getChannel(),ioCtrlType[0], data);
						}
					} else {

						try {
							Thread.sleep(100);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			}

			Log.i(TAG, "===ThreadRecvIOCtrl exit===");
		}
	}

	/**
	 * AV control send thread
	 */
	private class ThreadSendIOCtrl extends Thread {

		private boolean bIsRunning = false;
		private AVChannel mAVChannel;

		public ThreadSendIOCtrl(AVChannel channel) {
			mAVChannel = channel;
		}

		public void stopThread() {

			bIsRunning = false;

			if (mAVChannel.getAVIndex() >= 0) {
				Log.i(TAG, "avSendIOCtrlExit(" + mAVChannel.getAVIndex() + ")");
				AVAPIs.avSendIOCtrlExit(mAVChannel.getAVIndex());
			}
		}

		@Override
		public void run() {

//			bIsRunning = true;

			while (bIsRunning && (mSID < 0 || mAVChannel.getAVIndex() < 0)) {
				try {
					synchronized (mWaitObjectForConnected) {
						mWaitObjectForConnected.wait(1000);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			if (bIsRunning && mSID >= 0 && mAVChannel.getAVIndex() >= 0) {
				int nDelayTime_ms = 0;
				AVAPIs.avSendIOCtrl(mAVChannel.getAVIndex(), AVAPIs.IOTYPE_INNER_SND_DATA_DELAY, Packet.intToByteArray_Little(nDelayTime_ms), 4);
				Log.i(TAG, "发送avSendIOCtrl(" + mAVChannel.getAVIndex() + ", 0x" + Integer.toHexString(AVAPIs.IOTYPE_INNER_SND_DATA_DELAY) + ", " + getHex(Packet.intToByteArray_Little(nDelayTime_ms), 4) + ")");

			}

			while (bIsRunning) {

				if (mSID >= 0 && mAVChannel.getAVIndex() >= 0 && !mAVChannel.IOCtrlQueue.isEmpty()) {

					IOCtrlQueue.IOCtrlSet data = mAVChannel.IOCtrlQueue.Dequeue();
					Log.i(TAG, "data数据avSendIOCtrl: " + data.toString());
					if (bIsRunning && data != null) {

						int ret = AVAPIs.avSendIOCtrl(mAVChannel.getAVIndex(), data.IOCtrlType, data.IOCtrlBuf, data.IOCtrlBuf.length);
						if (ret >= 0) {

							for (int i = 0; i < mIOTCListeners.size(); i++) {
								IRegisterIOTCListener listener = mIOTCListeners.get(i);
								listener.receiveIOCtrlData(TUTKCamera.this, mAVChannel.getChannel(),Integer.valueOf(data.IOCtrlType), data.IOCtrlBuf);
//								Log.i(TAG, "ret内容avSendIOCtrl(" + mAVChannel.getAVIndex() + ", 0x" + Integer.toHexString(data.IOCtrlType) + ", " + data.IOCtrlBuf + ")");
							}
						} else {
							Log.i(TAG, "ret内容avSendIOCtrl failed : " + ret);
						}
					}
				} else {
					try {
						Thread.sleep(50);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}

			Log.i(TAG, "===ThreadSendIOCtrl exit===");
		}
	}

	/**
	 * Start receive AV frame
	 * @param avChannel
	 * @param avNoClearBuf
	 */
	public void startShow(int avChannel,boolean avNoClearBuf) {

		if(mVideoDecoder != null)
			mVideoDecoder.startDecode();
		synchronized (mAVChannels) {

			for (int i = 0; i < mAVChannels.size(); i++) {

				AVChannel ch = mAVChannels.get(i);

				if (ch.getChannel() == avChannel) {

					ch.VideoFrameQueue.removeAll();

					if (ch.threadRecvVideo == null) {
						ch.threadRecvVideo = new ThreadRecvVideo(ch,avNoClearBuf);
						ch.threadRecvVideo.bIsRunning = true;
						ch.threadRecvVideo.start();
					}

					if (ch.threadDecVideo == null) {
						ch.threadDecVideo = new ThreadDecodeVideo(ch);
						ch.threadDecVideo.m_bIsRunning = true;
						ch.threadDecVideo.start();
					}
					break;
				}
			}
		}
	}

	/**
	 * Stop receive AV frame
	 * @param avChannel
	 */
	public void stopShow(int avChannel) {

		if(mVideoDecoder != null)
			mVideoDecoder.stopDecode();

		synchronized (mAVChannels) {

			for (int i = 0; i < mAVChannels.size(); i++) {

				AVChannel ch = mAVChannels.get(i);

				if (ch.getChannel() == avChannel) {

					if (ch.threadRecvVideo != null) {
						ch.threadRecvVideo.stopThread();
						try {
							ch.threadRecvVideo.interrupt();
							ch.threadRecvVideo.join();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						ch.threadRecvVideo = null;
					}

					if (ch.threadDecVideo != null) {
						ch.threadDecVideo.stopThread();
						try {
							ch.threadDecVideo.interrupt();
							ch.threadDecVideo.join();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						ch.threadDecVideo = null;
					}

					ch.VideoFrameQueue.removeAll();

					break;
				}
			}
		}
	}

	/**
	 * Start receive audio
	 * @param avChannel
	 */
	public void startListening(int avChannel) {

		if(mAudioDecoder != null)
			mAudioDecoder.startDecode();
		synchronized (mAVChannels) {
			for (int i = 0; i < mAVChannels.size(); i++) {

				AVChannel ch = mAVChannels.get(i);

				if (avChannel == ch.getChannel()) {

					ch.AudioFrameQueue.removeAll();

					if (ch.threadRecvAudio == null) {
						ch.threadRecvAudio = new ThreadRecvAudio(ch);
						ch.threadRecvAudio.bIsRunning = true;
						ch.threadRecvAudio.start();
					}
					break;
				}
			}
		}
	}

	/**
	 * Stop receive audio
	 * @param avChannel
	 */
	public void stopListening(int avChannel) {

		if(mAudioDecoder != null)
			mAudioDecoder.stopDecode();
		synchronized (mAVChannels) {

			for (int i = 0; i < mAVChannels.size(); i++) {

				AVChannel ch = mAVChannels.get(i);

				if (avChannel == ch.getChannel()) {

					if (ch.threadRecvAudio != null) {
						ch.threadRecvAudio.stopThread();
						try {
							ch.threadRecvAudio.interrupt();
							ch.threadRecvAudio.join();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						ch.threadRecvAudio = null;
					}

					ch.AudioFrameQueue.removeAll();

					break;
				}
			}
		}
	}

	public void stop(int avChannel) {

		synchronized (mAVChannels) {

			int idx = -1;

			for (int i = 0; i < mAVChannels.size(); i++) {

				AVChannel ch = mAVChannels.get(i);

				if (ch.getChannel() == avChannel) {

					idx = i;

					stopSpeaking(ch.getChannel());

					if (ch.threadStartDev != null)
						ch.threadStartDev.stopThread();

					if (ch.threadDecVideo != null)
						ch.threadDecVideo.stopThread();

					if (ch.threadRecvAudio != null)
						ch.threadRecvAudio.stopThread();

					if (ch.threadRecvVideo != null)
						ch.threadRecvVideo.stopThread();

					if (ch.threadRecvIOCtrl != null)
						ch.threadRecvIOCtrl.stopThread();

					if (ch.threadSendIOCtrl != null)
						ch.threadSendIOCtrl.stopThread();

					if (ch.threadRecvVideo != null) {
						try {
							ch.threadRecvVideo.interrupt();
							ch.threadRecvVideo.join();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						ch.threadRecvVideo = null;
					}

					if (ch.threadRecvAudio != null) {
						try {
							ch.threadRecvAudio.interrupt();
							ch.threadRecvAudio.join();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						ch.threadRecvAudio = null;
					}

					if (ch.threadDecVideo != null) {
						try {
							ch.threadDecVideo.interrupt();
							ch.threadDecVideo.join();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						ch.threadDecVideo = null;
					}

					if (ch.threadRecvIOCtrl != null) {
						try {
							ch.threadRecvIOCtrl.interrupt();
							ch.threadRecvIOCtrl.join();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						ch.threadRecvIOCtrl = null;
					}

					if (ch.threadSendIOCtrl != null) {
						try {
							ch.threadSendIOCtrl.interrupt();
							ch.threadSendIOCtrl.join();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						ch.threadSendIOCtrl = null;
					}

					if (ch.threadStartDev != null && ch.threadStartDev.isAlive()) {
						try {
							ch.threadStartDev.interrupt();
							ch.threadStartDev.join();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					ch.threadStartDev = null;


					ch.AudioFrameQueue.removeAll();
					ch.AudioFrameQueue = null;

					ch.VideoFrameQueue.removeAll();
					ch.VideoFrameQueue = null;

					ch.IOCtrlQueue.removeAll();
					ch.IOCtrlQueue = null;

					if (ch.getAVIndex() >= 0) {

						AVAPIs.avClientStop(ch.getAVIndex());
						for (int j = 0; j < mIOTCListeners.size(); j++){
							IRegisterIOTCListener listener = mIOTCListeners.get(j);
							listener.receiveChannelInfo(TUTKCamera.this,ch.getChannel(),CONNECTION_STATE_DISCONNECTED);
						}
						Log.i(TAG, "avClientStop(avIndex = " + ch.getAVIndex() + ")");
					}

					break;
				}
			}

			if (idx >= 0) {
				mAVChannels.remove(idx);
			}
		}

//		sendIOCtrl(avChannel,AVIOCTRLDEFs.IOTYPE_USER_IPCAM_STOP, Packet.intToByteArray_Little(mCamIndex));//9.19 修改
	}


	/**
	 * Get session mode
	 * @return
	 */
	public int getSessionMode() {
		return mSessionMode;
	}

	/**
	 * Get session id
	 * @return
	 */
	public int getMSID() {
		return mSID;
	}

	/**
	 * Get current avIndex
	 * @return
	 */
	public int gettempAvIndex() {
		return tempAvIndex;
	}

	/**
	 *get resend status if open or not
	 * @return
	 */
	public int getbResend(){
		return bResend[0];
	}

	/**
	 *Get frame receive frequency frames per second
	 * @return
	 */
	public int getRecvFrmPreSec(){
		return nRecvFrmPreSec;
	}

	/**
	 * Get frame display frequency
	 * @return
	 */
	public int getDispFrmPreSec(){
		return nDispFrmPreSec;
	}

	/**
	 * Get av channel service type
	 * @param avChannel
	 * @return
	 */
	public long getChannelServiceType(int avChannel) {
		long ret = 0;
		synchronized (mAVChannels) {
			for (AVChannel ch : mAVChannels) {
				if (ch.getChannel() == avChannel) {
					ret = ch.getServiceType();
					break;
				}
			}
		}
		return ret;
	}

	/**
	 * Check if session is connected
	 * @return
	 */
	public boolean isSessionConnected() {
		return mSID >= 0;
	}

	/**
	 * Check is av channel is connected
	 * @param avChannel
	 * @return
	 */
	public boolean isChannelConnected(int avChannel) {

		boolean result = false;

		synchronized (mAVChannels) {
			for (AVChannel ch : mAVChannels) {
				if (avChannel == ch.getChannel()) {
					result = mSID >= 0 && ch.getAVIndex() >= 0;
					break;
				}
			}
		}

		return result;
	}

	/**
	 * Send AV IO Ctrl data by queue
	 * @param avChannel
	 * @param type
	 * @param data
	 */
	public void sendIOCtrl(int avChannel, int type, byte[] data) {

		synchronized (mAVChannels) {
			for (AVChannel ch : mAVChannels) {
				Log.i("sendIOCtrl------","avChannel:"+avChannel);
				Log.i("sendIOCtrl------","ch.getChannel():"+ch.getChannel());
				if (avChannel == ch.getChannel()) {
					Log.i("sendIOCtrl------","avChannel:("+avChannel+"), type("+type+"), data"+data);
					ch.IOCtrlQueue.Enqueue(type, data);
				}
			}
		}
	}

	/**
	 * Capture an image
	 * @param
	 * @return
	 */
	public boolean snapshot(){
		File file = new File("/sdcard/Redbee IPC/");
		if (!file.exists()){
			file.mkdirs();
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String path = "/sdcard/Redbee IPC/"+sdf.format(new Date())+".jpg";
		return false;
	}



	public void startSpeaking(int avChannel) {

		synchronized (mAVChannels) {
			for (int i = 0; i < mAVChannels.size(); i++) {

				AVChannel ch = mAVChannels.get(i);

				if (ch.getChannel() == avChannel) {

					ch.AudioFrameQueue.removeAll();

					if (mThreadSendAudio == null) {
						mThreadSendAudio = new ThreadSendAudio(ch);
						mThreadSendAudio.start();
						Log.e("mThreadSendAudio---","start");
					}

					break;
				}
			}
		}
	}
	public void stopSpeaking(int avChannel) {
		synchronized (mAVChannels) {
			if (mThreadSendAudio != null) {
				mThreadSendAudio.stopThread();

				try {
					mThreadSendAudio.interrupt();
					mThreadSendAudio.join();
					mThreadSendAudio = null;
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

			}
		}
	}


	/**
	 *  9月19号加
	 *
	 * */
	public void startStream(int avChannel){
		synchronized (mAVChannels) {
			for (int i = 0; i < mAVChannels.size(); i++) {
				AVChannel ch = mAVChannels.get(i);
				if (ch.getChannel() == avChannel) {
					if (ch.threadDecVideo != null) {
						ch.threadDecVideo.startVideoDecodingThread();
					}
					if (ch.threadRecvAudio != null){
						ch.threadRecvAudio.startRecvAudioingThread();
					}
					break;
				}
			}
		}
		if (mThreadSendAudio != null){
			mThreadSendAudio.startSendAudioingThread();
		}


	}

	public void stopStream(int avChannel){
		synchronized (mAVChannels) {
			for (int i = 0; i < mAVChannels.size(); i++) {
				AVChannel ch = mAVChannels.get(i);
				if (ch.getChannel() == avChannel) {
					if (ch.threadDecVideo != null) {
						ch.threadDecVideo.stopVideoDecodingThread();
					}
					if (ch.threadRecvAudio != null){
						ch.threadRecvAudio.stopRecvAudioingThread();
					}
					break;
				}
			}
		}
		if (mThreadSendAudio != null) {
			mThreadSendAudio.stopSendAudioingThread();
		}
	}




	//视频接收
	private class ThreadRecvVideo extends Thread {
		/**
		 *  Change...
		 */
//		private static final int MAX_BUF_SIZE = 1280 * 720 * 3;
        private static final int MAX_BUF_SIZE = 1920 * 1080 * 3;
		private boolean bIsRunning = false;
		private AVChannel mAVChannel;
		private boolean avNoClearBuf = false;
		private boolean getFirstIFrame = true;


		public ThreadRecvVideo(AVChannel channel,boolean noClearBuf) {
			mAVChannel = channel;
			avNoClearBuf = noClearBuf;
		}

		public void stopThread() {
			bIsRunning = false;
		}



		@Override
		public void run() {

			System.gc();

			this.setName("Thread-Receive-Video");
//			bIsRunning = true;

			while (bIsRunning && (mSID < 0 || mAVChannel.getAVIndex() < 0)) {
				try {
					synchronized (mWaitObjectForConnected) {
						mWaitObjectForConnected.wait(100);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			mAVChannel.VideoBPS = 0;

			byte[] buf = new byte[MAX_BUF_SIZE];
			byte[] pFrmInfoBuf = new byte[AVFrame.FRAMEINFO_SIZE];

			int[] pFrmNo = new int[1];
			int nCodecId = 0;
			int nReadSize = 0;
			int nFrmCount = 0;
			int nIncompleteFrmCount = 0;
			int nOnlineNumber = 0;
			long nPrevFrmNo = 0x0FFFFFFF;
			long nLastTimeStamp = System.currentTimeMillis();

			int nFlow_total_frame_count = 0;
			int nFlow_lost_incomplete_frame_count = 0;
			int nFlow_total_expected_frame_size = 0;
			int nFlow_total_actual_frame_size = 0;
			long nFlow_timestamp = System.currentTimeMillis();
			nRecvFrmPreSec = 0;

			int[] outBufSize = new int[1];
			int[] outFrmSize = new int[1];
			int[] outFrmInfoBufSize = new int[1];

			if(avNoClearBuf){

				if (mSID >= 0 && mAVChannel.getAVIndex() >= 0)
					AVAPIs.avClientCleanVideoBuf(mAVChannel.getAVIndex());

			}
			mAVChannel.VideoFrameQueue.removeAll();


			if (bIsRunning && mSID >= 0 && mAVChannel.getAVIndex() >= 0) {
				mAVChannel.IOCtrlQueue.Enqueue(1, AVIOCTRLDEFs.IOTYPE_USER_IPCAM_VIDEOSTART, Packet.intToByteArray_Little(mCamIndex));
			}


			while (bIsRunning) {

				if (mSID >= 0 && mAVChannel.getAVIndex() >= 0) {

					if (System.currentTimeMillis() - nLastTimeStamp > 1000) {

						nLastTimeStamp = System.currentTimeMillis();

						for (int i = 0; i < mIOTCListeners.size(); i++) {

							IRegisterIOTCListener listener = mIOTCListeners.get(i);
							listener.receiveFrameInfo(TUTKCamera.this, mAVChannel.getChannel(), (mAVChannel.AudioBPS + mAVChannel.VideoBPS) * 8 / 1024, mAVChannel.VideoFPS, nOnlineNumber, nFrmCount, nIncompleteFrmCount);
						}

						mAVChannel.VideoFPS = mAVChannel.VideoBPS = mAVChannel.AudioBPS = 0;
					}

					if (mAVChannel.flowInfoInterval > 0 && (System.currentTimeMillis() - nFlow_timestamp) > (mAVChannel.flowInfoInterval * 1000)) {

						int elapsedTimeMillis = (int)(System.currentTimeMillis());
						sendIOCtrl(mAVChannel.mChannel, AVIOCTRLDEFs.IOTYPE_USER_IPCAM_CURRENT_FLOWINFO, AVIOCTRLDEFs.SMsgAVIoctrlCurrentFlowInfo.parseContent(mAVChannel.getChannel(),nFlow_total_frame_count,nFlow_total_frame_count-nFlow_total_FPS_count,nFlow_total_expected_frame_size,nFlow_total_actual_frame_size,elapsedTimeMillis));
						nFlow_total_frame_count = 0;
						nFlow_lost_incomplete_frame_count = 0;
						nFlow_total_expected_frame_size = 0;
						nFlow_total_actual_frame_size = 0;
						nFlow_total_FPS_count = 0;
						nFlow_timestamp = System.currentTimeMillis();
					}

					nReadSize = AVAPIs.avRecvFrameData2(mAVChannel.getAVIndex(), buf,
							buf.length, outBufSize, outFrmSize, pFrmInfoBuf,
							pFrmInfoBuf.length, outFrmInfoBufSize, pFrmNo);
					Log.i(TAG,"nReadSize:  "+nReadSize);
					if (nReadSize >= 0) {


						mAVChannel.VideoBPS += outBufSize[0];
						nFrmCount++;

						byte[] frameData = new byte[nReadSize];
						System.arraycopy(buf, 0, frameData, 0, nReadSize);
						AVFrame frame = new AVFrame(pFrmNo[0], AVFrame.FRM_STATE_COMPLETE, pFrmInfoBuf, frameData, nReadSize);
						nCodecId = (int) frame.getCodecId();
						nOnlineNumber = (int) frame.getOnlineNum();

						Log.i(TAG, "recv video len:"+outBufSize[0]+" info len:"+outFrmInfoBufSize[0] + " codec id:" + nCodecId);

						int[] rDataSize = new int[1];
						rDataSize[0] = MAX_VIDEO_BUFFER;

						//==============注意：此信息解析头得出===============
						//_vParam.width =  704;
						//_vParam.height = 560;
						//_vParam.frame_rate = 25; // 按照板端的实际帧率填写，否则会快放或者慢放,目前手上板子是这个值才正常速度播放
						//_vParam.time_scale = 90000;
						//===============================================

						try {
							Thread.sleep(1);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}

						/*--------------------------------------------------*/

						if(mInitReocrd == false)
						{
							//if record not initial then initial record
							if(mFileName != null){
								String fileName = null;
								if(nCodecId == AVFrame.MEDIA_CODEC_VIDEO_H264){
									fileName = mFileName +"RD_"+ getNowDate()+".flv";
									//NativeAgent.NativeStartStreamingMedia(fileName);
								}else if(nCodecId == AVFrame.MEDIA_CODEC_VIDEO_MJPEG){
									fileName = mFileName +"RD_"+ getNowDate()+".avi";
									//NativeAgent.NativeStartStreamingMediaMJ(fileName);
								}
								mInitReocrd = true;
							}
						}

						if (nCodecId == AVFrame.MEDIA_CODEC_VIDEO_H264) {

							if (frame.isIFrame() || pFrmNo[0] == (nPrevFrmNo + 1)) {
								if(mReocrding == true){
									t = new Date();
									if(RecordTime == null) {
										RecordTime = t;
									}
									//if open record function then record the frame
									//NativeAgent.NativeRecordMedia(frame.frmData, frame.getFrmSize(), frame.getVideoWidth(), frame.getVideoHeight(), 25, (int)(t.getTime()-RecordTime.getTime()),0);
								}
								nPrevFrmNo = pFrmNo[0];
								nRecvFrmPreSec++;
								mAVChannel.VideoFrameQueue.addLast(frame);
							} else {
								Log.i(TAG, "Incorrect frame no(" + pFrmNo[0] + "), prev:" + nPrevFrmNo + " -> drop frame");
							}

						} else if (nCodecId == AVFrame.MEDIA_CODEC_VIDEO_MPEG4) {

							//20130910 by Chun
							if(frame.isIFrame()&& getFirstIFrame)
							{
								sendIOCtrl(mAVChannel.mChannel, AVIOCTRLDEFs.IOTYPE_USER_IPCAM_RECEIVE_FIRST_IFRAME, SMsgAVIoctrlReceiveFirstIFrame.parseContent(mAVChannel.mChannel,0));
								getFirstIFrame = false;
							}
							//20130910 by Chun
							if (frame.isIFrame() || pFrmNo[0] == (nPrevFrmNo + 1)) {
								nPrevFrmNo = pFrmNo[0];
								nRecvFrmPreSec++;
								mAVChannel.VideoFrameQueue.addLast(frame);
							}

						} else if (nCodecId == AVFrame.MEDIA_CODEC_VIDEO_MJPEG) {
							nRecvFrmPreSec++;
							if(mReocrding == true){
								t = new Date();
								if(RecordTime == null) {
									RecordTime = t;
								}
								//NativeAgent.NativeRecordMediaMJ(buf, nReadSize, frame.getVideoWidth(), frame.getVideoHeight(), 25, (int)(t.getTime()-RecordTime.getTime()),0);
								//RecordTime = t;
							}
							Bitmap bmp = BitmapFactory.decodeByteArray(frameData, 0, nReadSize);

							if (bmp != null) {

								mAVChannel.VideoFPS++;
								nFlow_total_FPS_count++;
								nFlow_total_FPS_count_noClear++;
								nDispFrmPreSec++;

								for (int i = 0; i < mIOTCListeners.size(); i++) {
									IRegisterIOTCListener listener = mIOTCListeners.get(i);
									listener.receiveFrameData(TUTKCamera.this, mAVChannel.getChannel(), bmp);
								}
								mAVChannel.LastFrame = bmp;
							}
							try {
								Thread.sleep(32);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}


						nFlow_total_actual_frame_size += outBufSize[0];
						nFlow_total_expected_frame_size += outFrmSize[0];
						nFlow_total_frame_count++;

					} else if (nReadSize == AVAPIs.AV_ER_SESSION_CLOSE_BY_REMOTE) {
						if (mSID >0) {
							IOTCAPIs.IOTC_Session_Close(mSID);
							mSID = -1;
						}
						for (int i = 0; i < mIOTCListeners.size(); i++){
							IRegisterIOTCListener listener = mIOTCListeners.get(i);
							listener.receiveSessionInfo(TUTKCamera.this,CONNECTION_STATE_DISCONNECTED);
						}
						Log.i(TAG, "AV_ER_SESSION_CLOSE_BY_REMOTE");
						continue;
					} else if (nReadSize == AVAPIs.AV_ER_REMOTE_TIMEOUT_DISCONNECT) {
						for (int i = 0; i < mIOTCListeners.size(); i++){
							IRegisterIOTCListener listener = mIOTCListeners.get(i);
							listener.receiveSessionInfo(TUTKCamera.this,CONNECTION_STATE_DISCONNECTED);
						}
						Log.i(TAG, "AV_ER_REMOTE_TIMEOUT_DISCONNECT");
						continue;
					} else if (nReadSize == AVAPIs.AV_ER_DATA_NOREADY) {
						try {
							Thread.sleep(32);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						//Log.i(TAG, "AV_ER_DATA_NOREADY");
						continue;

					} else if (nReadSize == AVAPIs.AV_ER_BUFPARA_MAXSIZE_INSUFF) {

						continue;

					} else if (nReadSize == AVAPIs.AV_ER_MEM_INSUFF) {

						nFrmCount++;
						nIncompleteFrmCount++;
						nFlow_lost_incomplete_frame_count++;
						nFlow_total_frame_count++;
						Log.i(TAG, "AV_ER_MEM_INSUFF");

					} else if (nReadSize == AVAPIs.AV_ER_LOSED_THIS_FRAME) {

						Log.i(TAG, "AV_ER_LOSED_THIS_FRAME");

						nFrmCount++;
						nIncompleteFrmCount++;
						nFlow_lost_incomplete_frame_count++;
						nFlow_total_frame_count++;

					} else if (nReadSize == AVAPIs.AV_ER_INCOMPLETE_FRAME) {

						nFlow_total_actual_frame_size += outBufSize[0];
						nFlow_total_expected_frame_size += outFrmSize[0];
						nFrmCount++;
						nFlow_total_frame_count++;
						mAVChannel.VideoBPS += outBufSize[0];


						if (outFrmInfoBufSize[0] == 0 || (outFrmSize[0]) != outBufSize[0] || (int) pFrmInfoBuf[2]  == AVFrame.IPC_FRAME_FLAG_PBFRAME) {
							nIncompleteFrmCount++;
							nFlow_lost_incomplete_frame_count++;
							Log.i(TAG, ((int) pFrmInfoBuf[2]  == AVFrame.IPC_FRAME_FLAG_PBFRAME ? "P" : "I") + " frame, outFrmSize(" + outFrmSize[0]+ ") = " + ((outFrmSize[0])) + " > outBufSize("+outBufSize[0] + ")");
							continue;
						}

						byte[] frameData = new byte[outFrmSize[0]];
						System.arraycopy(buf, 0, frameData, 0, outFrmSize[0]);
						nCodecId = Packet.byteArrayToShort_Little(pFrmInfoBuf, 0);

						if (nCodecId == AVFrame.MEDIA_CODEC_VIDEO_MJPEG || nCodecId == AVFrame.MEDIA_CODEC_VIDEO_MPEG4 ) {

							nIncompleteFrmCount++;
							nFlow_lost_incomplete_frame_count++;
							continue;
						} else if (nCodecId == AVFrame.MEDIA_CODEC_VIDEO_H264) {

							if (outFrmInfoBufSize[0] == 0 || (outFrmSize[0]) != outBufSize[0] || (int) pFrmInfoBuf[2]  == AVFrame.IPC_FRAME_FLAG_PBFRAME) {
								nIncompleteFrmCount++;
								Log.i(TAG, ((int) pFrmInfoBuf[2]  == AVFrame.IPC_FRAME_FLAG_PBFRAME ? "P" : "I") + " frame, outFrmSize(" + outFrmSize[0]+ ") = " + ((outFrmSize[0])) + " > outBufSize("+outBufSize[0] + ")");
								continue;
							}

							AVFrame frame = new AVFrame(pFrmNo[0], AVFrame.FRM_STATE_COMPLETE, pFrmInfoBuf, frameData, outFrmSize[0]);

							if (frame.isIFrame() || pFrmNo[0] == (nPrevFrmNo + 1)) {
								nPrevFrmNo = pFrmNo[0];
								nRecvFrmPreSec++;
								mAVChannel.VideoFrameQueue.addLast(frame);
								nFlow_total_actual_frame_size += outBufSize[0];
								nFlow_total_expected_frame_size += outFrmSize[0];

								Log.i(TAG, "AV_ER_INCOMPLETE_FRAME - H264 or MPEG4");
							} else {
								nIncompleteFrmCount++;
								nFlow_lost_incomplete_frame_count++;
								Log.i(TAG, "AV_ER_INCOMPLETE_FRAME - H264 or MPEG4 - LOST");
							}

						} else {
							nIncompleteFrmCount++;
							nFlow_lost_incomplete_frame_count++;
						}
					}
				}

			}// while--end
			if (mSID >= 0 && mAVChannel.getAVIndex() >= 0) {
				Log.i(TAG, "video - stop :mSID:"+mSID+",  mAVChannel :"+mAVChannel.getAVIndex());
				mAVChannel.IOCtrlQueue.Enqueue( AVIOCTRLDEFs.IOTYPE_USER_IPCAM_VIDEOSTOP, Packet.intToByteArray_Little(mCamIndex));
//				sendIOCtrl(0,AVIOCTRLDEFs.IOTYPE_USER_IPCAM_STOP, Packet.intToByteArray_Little(mCamIndex));
//				mAVChannel.IOCtrlQueue.Enqueue(1, AVIOCTRLDEFs.IOTYPE_USER_IPCAM_STOP, Packet.intToByteArray_Little(mCamIndex));// 9.19
				//AVAPIs.avClientCleanBuf(mAVChannel.getAVIndex());
			}

			if(avNoClearBuf){
				avNoClearBuf = false;
			}
			mAVChannel.VideoFrameQueue.removeAll();


			buf = null;

			Log.i(TAG, "===ThreadRecvVideo exit===");
		}
	}
	//视频转码
	private class ThreadDecodeVideo extends Thread {
		private boolean m_bIsRunning = false;
		private boolean isVideoDecoding = false;
		private AVChannel mAVChannel;

		public ThreadDecodeVideo(AVChannel channel) {
			mAVChannel = channel;
		}

		public void stopThread() {
			m_bIsRunning = false;
		}

		public void startVideoDecodingThread(){

			isVideoDecoding = true;
		}

		public void stopVideoDecodingThread(){
			isVideoDecoding = false;
		}

		@Override
		public void run() {

			this.setName("Thread-Decode-Video");
			System.gc();
			int avFrameSize = 0;
			AVFrame avFrame = null;

			nDispFrmPreSec = 0;
			boolean bInitH264 = false;
//			m_bIsRunning = true;

			System.gc();

			while (m_bIsRunning) {

				avFrame = null;

				if (mAVChannel.VideoFrameQueue.getCount() > 0) {
					avFrame = mAVChannel.VideoFrameQueue.removeHead();
					if (avFrame == null)
						continue;
					avFrameSize = avFrame.getFrmSize();
				} else {
					try {
						Thread.sleep(4);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					continue;
				}
				if (avFrameSize > 0) {
					if(mVideoDecoder != null)
					{
						if (avFrame.getCodecId() == AVFrame.MEDIA_CODEC_VIDEO_H264) {
							mVideoDecoder.onFrame(avFrame.frmData, 0, avFrameSize);
						}
						if (avFrame != null) {
							avFrame.frmData = null;
						}
					}

				}
			}//run
			System.gc();
			Log.i(TAG, "===ThreadDecodeVideo exit===");
		}

	}
	//音频接收
	private class ThreadRecvAudio extends Thread {

		private final int MAX_BUF_SIZE = 1280;
		private int nReadSize = 0;
		private boolean bIsRunning = false;
		private boolean isRecvAudioing = false;

		private AVChannel mAVChannel;

		public ThreadRecvAudio(AVChannel channel) {
			mAVChannel = channel;
		}

		public void stopThread() {
			bIsRunning = false;
		}

		public void startRecvAudioingThread(){
			isRecvAudioing = true;
		}

		public void stopRecvAudioingThread(){
			isRecvAudioing = false;
		}
		@Override
		public void run() {

//			bIsRunning = true;
			Log.i(TAG,"threadrecvaudio run!");
			while (bIsRunning && (mSID < 0 || mAVChannel.getAVIndex() < 0)) {

				try {
					synchronized (mWaitObjectForConnected) {
						mWaitObjectForConnected.wait(100);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			mAVChannel.AudioBPS = 0;
			byte[] recvBuf = new byte[MAX_BUF_SIZE];
			byte[] bytAVFrame = new byte[AVFrame.FRAMEINFO_SIZE];
			int[] pFrmNo = new int[1];


			byte[] G711OutBuf = new byte[2048];
			long[] G711OutBufLen = new long[1];

			byte[] AACOutBuf = new byte[2048];
			long[] AACOutBufLen = new long[1];

			boolean bFirst = true;

			int nSamplerate = 8000;//44100;
			int nDatabits = 1;
			int nChannel = 1;
			int nCodecId = 0;
			int nFPS = 0;

			if (mSID >= 0 && mAVChannel.getAVIndex() >= 0)
				AVAPIs.avClientCleanAudioBuf(mAVChannel.getAVIndex());
				mAVChannel.AudioFrameQueue.removeAll();

			if (bIsRunning && mSID >= 0 && mAVChannel.getAVIndex() >= 0)
				mAVChannel.IOCtrlQueue.Enqueue(mAVChannel.getAVIndex(), AVIOCTRLDEFs.IOTYPE_USER_IPCAM_AUDIOSTART, Packet.intToByteArray_Little(mCamIndex));

			while (bIsRunning) {

				if (mSID >= 0 && mAVChannel.getAVIndex() >= 0) {

					nReadSize = AVAPIs.avRecvAudioData(mAVChannel.getAVIndex(), recvBuf, recvBuf.length, bytAVFrame, AVFrame.FRAMEINFO_SIZE, pFrmNo);
					if (nReadSize > 0  )
					{
						if(mAudioDecoder != null)
						{
							mAudioDecoder.onFrame(recvBuf, 0, nReadSize);
						}
//						if (isRecvAudioing && m_mediaDecoder!= 0){
//							m_mediaDecoderParam.sample = 8000;
//							m_mediaDecoderParam.channel = 1;
//							m_mediaDecoderParam.bitsPerSample = 16;
//							m_mediaDecoderParam.decoderType = HxCxDefine.TYPE_AV_DECODER.TYPE_G711_DECODER;
//
//							HxCxPlayer.HxCx_SetDecoderParam(m_mediaDecoder,m_mediaDecoderParam);
//							HxCxPlayer.HxCx_PushAudio(m_mediaDecoder,recvBuf,nReadSize);
//
//							//===========注意：解析头信息得出==============
//							_aParam.sample = 16000;//音频采样率
//							_aParam.channel = mAVChannel.getAVIndex();
//							_aParam.duration = 1024;
//							_aParam.lever = HxCxAudioParam.LEVER.LOW;
//						}

						//==========================================
//
//						mAVChannel.AudioBPS += nReadSize;
//
//						byte[] frameData = new byte[nReadSize];
//						System.arraycopy(recvBuf, 0, frameData, 0, nReadSize);
//
//						AVFrame frame = new AVFrame(pFrmNo[0], AVFrame.FRM_STATE_COMPLETE, bytAVFrame, frameData, nReadSize);
//
//						nCodecId = (int) frame.getCodecId();
//						Log.i(TAG,"nCodecId: "+nCodecId);
//						if (bFirst) {
//
//							if ((nCodecId == AVFrame.MEDIA_CODEC_AUDIO_AAC || nCodecId == AVFrame.MEDIA_CODEC_AUDIO_G711)) {
//								bFirst = false;
//								nSamplerate = AVFrame.getSamplerate(frame.getFlags());
//								nDatabits = (int) (frame.getFlags() & 0x02);
//								nDatabits = (nDatabits == 0x02) ? 1 : 0;
//								nChannel = (int) (frame.getFlags() & 0x01);
//
//								if (nCodecId == AVFrame.MEDIA_CODEC_AUDIO_G711) {
//									nFPS = ((nSamplerate * (nChannel == AVFrame.AUDIO_CHANNEL_MONO ? 1 : 2) * (nDatabits == AVFrame.AUDIO_DATABITS_8 ? 8 : 16)) / 8) / 640;
//								}
//								else if (nCodecId == AVFrame.MEDIA_CODEC_AUDIO_AAC) {
//									nFPS = ((nSamplerate * (nChannel == AVFrame.AUDIO_CHANNEL_MONO ? 1 : 2) * (nDatabits == AVFrame.AUDIO_DATABITS_8 ? 8 : 16)) / 8) / 640;
//								}
//							}
//						}
//						//Analysis audio params from frame but not use now,so give the constant value
//						Log.d(TAG,"Samplerate: " + nSamplerate + " Channel:" + nChannel + " nDatabits:" + nDatabits);
//						m_mediaDecoderParam.sample = 8000;
//						m_mediaDecoderParam.channel = 1;
//						m_mediaDecoderParam.bitsPerSample = 16;
//
//						nCodecId = AVFrame.MEDIA_CODEC_AUDIO_G711;
//
//						if (nCodecId == AVFrame.MEDIA_CODEC_AUDIO_G711) {
//							Log.i(TAG,"G711:  before");
//							m_mediaDecoderParam.decoderType = HxCxDefine.TYPE_AV_DECODER.TYPE_G711_DECODER;
//							HxCxPlayer.HxCx_SetDecoderParam(m_mediaDecoder,m_mediaDecoderParam);
//							HxCxPlayer.HxCx_PushAudio(m_mediaDecoder,frameData,(int)frameData.length);
//
//
//							nFPS = ((nSamplerate * (nChannel == AVFrame.AUDIO_CHANNEL_MONO ? 1 : 2) * (nDatabits == AVFrame.AUDIO_DATABITS_8 ? 8 : 16)) / 8) / 640;
//							Log.i(TAG,"Push G711 audio frame!");
//							Log.i(TAG,"G711:   later");
//						}else if(nCodecId == AVFrame.MEDIA_CODEC_AUDIO_AAC)
//						{
//							m_mediaDecoderParam.decoderType = HxCxDefine.TYPE_AV_DECODER.TYPE_AAC_DECODER;
//							HxCxPlayer.HxCx_SetDecoderParam(m_mediaDecoder,m_mediaDecoderParam);
//							HxCxPlayer.HxCx_PushAudio(m_mediaDecoder,frameData,(int)frameData.length);
//							nFPS = ((nSamplerate * (nChannel == AVFrame.AUDIO_CHANNEL_MONO ? 1 : 2) * (nDatabits == AVFrame.AUDIO_DATABITS_8 ? 8 : 16)) / 8) / 640;
//							Log.i(TAG,"Push AAC audio frame!");
//						}
//




					} else if (nReadSize == AVAPIs.AV_ER_DATA_NOREADY) {
//						try {
//							Thread.sleep(500);
//						} catch (InterruptedException e) {
//							e.printStackTrace();
//						}

					} else if (nReadSize == AVAPIs.AV_ER_LOSED_THIS_FRAME) {
						Log.i(TAG, "avRecvAudioData returns AV_ER_LOSED_THIS_FRAME");
					} else {
						try {
							Thread.sleep(nFPS == 0 ? 33 : (1000 / nFPS));
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						Log.i(TAG, "avRecvAudioData returns " + nReadSize);
					}
				}
			} // while(true);



			mAVChannel.IOCtrlQueue.Enqueue(mAVChannel.getAVIndex(), AVIOCTRLDEFs.IOTYPE_USER_IPCAM_AUDIOSTOP, Packet.intToByteArray_Little(mCamIndex));

			Log.i(TAG, "===ThreadRecvAudio exit===");
		}
	}
	//音频发送
	private class ThreadSendAudio extends Thread {

		private boolean m_bIsRunning = false;
		private static final int SAMPLE_RATE_IN_HZ = 8000;
		private int avIndexForSendAudio = -1;
		private int chIndexForSendAudio = -1;
		private AVChannel mAVChannel = null;
		private boolean isSendAudioing = false;

		public ThreadSendAudio(AVChannel ch) {
			mAVChannel = ch;
		}

		public void stopThread() {
			Log.e("stopThread","---");
			if (mSID >= 0 && chIndexForSendAudio >= 0) {
				AVAPIs.avServExit(mSID, chIndexForSendAudio);
				sendIOCtrl(mAVChannel.mChannel, AVIOCTRLDEFs.IOTYPE_USER_IPCAM_SET_SPEAKER_STOP_REQ, SMsgAVIoctrlAVStream.parseContent(chIndexForSendAudio));
			}

			m_bIsRunning = false;
		}

		public void startSendAudioingThread(){
			isSendAudioing = true;
		}

		public void stopSendAudioingThread(){
			isSendAudioing = false;
		}

		@Override
		public void run() {
			super.run();
			if (mSID < 0) {
				Log.e(TAG, "=== ThreadSendAudio exit because SID < 0 ===");
				return;
			}

			m_bIsRunning = true;

			int nMinBufSize = 0;
			int nReadBytes = 0;

			/* wait for connection */
			chIndexForSendAudio = IOTCAPIs.IOTC_Session_Get_Free_Channel(mSID);
			Log.e(TAG, "------chIndexForSendAudio-------" + chIndexForSendAudio+"----- sid---"+mSID+"-------mChannel---"+mAVChannel.mChannel);
			if (chIndexForSendAudio < 0) {
				Log.e(TAG, "=== ThreadSendAudio exit becuase no more channel for connection ===");
				return;
			}

//			sendIOCtrl(mAVChannel.mChannel,AVIOCTRLDEFs.IOTYPE_USER_IPCAM_AUDIOSTOP,Packet.intToByteArray_Little(mAVChannel.mChannel));
			//Send msg tell device it will send audio
			sendIOCtrl(0, AVIOCTRLDEFs.IOTYPE_USER_IPCAM_SET_SPEAKER_START_REQ, SMsgAVIoctrlAVStream.parseContent(chIndexForSendAudio));
			Log.e(TAG, "start avServerStart(" + mSID + ", " + chIndexForSendAudio + ")");
			int[] reSend = new int[1];
			//ipc or doorbell
//			if(MainActivity.device_type == MainActivity.DEVICE_IPC){
//				avIndexForSendAudio = AVAPIs.avServStart3(mSID, mDevAccount, mDevPwd, 30, 0, chIndexForSendAudio,reSend);
//			}else if (MainActivity.device_type == MainActivity.DEVICE_DOORBELL){
//				avIndexForSendAudio = AVAPIs.avServStart3(mSID, "", "", 30, 0, chIndexForSendAudio,reSend);
//			}

			while (m_bIsRunning && avIndexForSendAudio < 0) {
				Log.e(TAG, "avServerStart failed(" + mSID + ", " + chIndexForSendAudio + ") : " + avIndexForSendAudio);
			}
			Log.e(TAG, "avServerStart success(" + mSID + ", " + chIndexForSendAudio + ") : " + avIndexForSendAudio);
			Log.e(TAG, "------m_bIsRunning-----===" + m_bIsRunning);
			/* init mic of phone */
			AudioRecord recorder = null;
			if (m_bIsRunning) {
//				int audioFormat = AudioFormat.ENCODING_PCM_16BIT;
//
//				if(m_audioEncodeParam.sampleFormat == HxCxDefine.AUDIO_SAMPLE_FORMAT.FMT_S16){
//					audioFormat = AudioFormat.ENCODING_PCM_16BIT;
//				}else if(m_audioEncodeParam.sampleFormat == HxCxDefine.AUDIO_SAMPLE_FORMAT.FMT_U8){
//					audioFormat = AudioFormat.ENCODING_PCM_8BIT;
//				}
//				int channel = AudioFormat.CHANNEL_IN_MONO;
//				if(m_audioEncodeParam.channels == 1){
//					channel = AudioFormat.CHANNEL_IN_MONO;
//				}else if(m_audioEncodeParam.channels == 2){
//					channel = AudioFormat.CHANNEL_IN_STEREO;
//				}

//				// 采集pcm数据
//				int mBufferSizeInBytes = AudioRecord.getMinBufferSize(
//						m_audioEncodeParam.sampleRate,
//						channel,
//						audioFormat);
//				if (m_bIsRunning && mAVChannel.getAudioCodec() == AVFrame.MEDIA_CODEC_AUDIO_G711) {
//					nMinBufSize = AudioRecord.getMinBufferSize(SAMPLE_RATE_IN_HZ, AudioFormat.CHANNEL_IN_MONO, AudioFormat.ENCODING_PCM_16BIT);
//				}
//				else if(m_bIsRunning && mAVChannel.getAudioCodec() == AVFrame.MEDIA_CODEC_AUDIO_AAC) {
//					nMinBufSize = AudioRecord.getMinBufferSize(SAMPLE_RATE_IN_HZ, AudioFormat.CHANNEL_IN_MONO, AudioFormat.ENCODING_PCM_16BIT);
//				}
				int mBufferSizeInBytes = 640;
				try {
//					Log.e(TAG, "------sampleRate-----===" + m_audioEncodeParam.sampleRate);
//					recorder = new AudioRecord(MediaRecorder.AudioSource.MIC,
//                            m_audioEncodeParam.sampleRate,
//                            channel,
//                            audioFormat,
//                            mBufferSizeInBytes);

				} catch (Exception e) {
					e.printStackTrace();
				}

				// 2018.9.30 update by yoyo
				if (recorder != null){
					recorder.startRecording();

					byte[] inG711Buf = new byte[640];
					byte[] inAACBuf = new byte[640];

					byte[] outG711Buf = new byte[640];
					long[] outG711BufLen = new long[1];
					byte[] outAACBuf = new byte[640];
					long[] outAACBufLen = new long[1];

			/* send audio data continuously */
					while (m_bIsRunning) {
						// read speaker data
						Log.e(TAG, "mAVChannel:"+mAVChannel.getAudioCodec());
						Log.e(TAG, "AVFrame:"+AVFrame.MEDIA_CODEC_AUDIO_G711);
						Log.e(TAG, "AVFrame:"+AVFrame.MEDIA_CODEC_AUDIO_AAC);
						mAVChannel.setAudioCodec(AVFrame.MEDIA_CODEC_AUDIO_G711);
						if (mAVChannel.getAudioCodec() == AVFrame.MEDIA_CODEC_AUDIO_G711) {
							nReadBytes = recorder.read(inG711Buf, 0, 640);
							if (nReadBytes > 0) {

//						if (isSendAudioing && m_audioEncoder!=0){
								Log.e(TAG, "speaker read audio len:"+nReadBytes);
//								int encodeLen = HxCxPlayer.HxCx_EncodeAudio(m_audioEncoder,
//										inG711Buf, nReadBytes, outG711Buf);
								//Log.e(TAG, "speaker encoder after len:"+encodeLen);
								Log.e(TAG, ""+outG711Buf[0]+" "+outG711Buf[1]+" "+outG711Buf[2]+" "+outG711Buf[3]+" "+outG711Buf[4]);
								byte flag = (AVFrame.AUDIO_SAMPLE_8K << 2) | (AVFrame.AUDIO_DATABITS_16 << 1) | AVFrame.AUDIO_CHANNEL_STERO;

								Log.e(TAG, "----------time = " + (int) System.currentTimeMillis());
								byte[] frameInfo = AVIOCTRLDEFs.SFrameInfo.parseContent((short) AVFrame.MEDIA_CODEC_AUDIO_G711, flag, (byte) 0, (byte) 0, (int) System.currentTimeMillis());
								Log.e(TAG, "G711 ================================outG711BufLen:"+outG711BufLen+"========66666666666");

								//int avSendAudioData = AVAPIs.avSendAudioData(avIndexForSendAudio, outG711Buf, encodeLen, frameInfo, 16);
								//Log.e(TAG, "G711 =========================================avSendAudioData : "+avSendAudioData);
//						}

							}
						}else if(mAVChannel.getAudioCodec() == AVFrame.MEDIA_CODEC_AUDIO_AAC)
						{

						}
					}
				}

			}

			/* uninit speaker of phone */
			if (recorder != null) {
				recorder.stop();
				recorder.release();
				recorder = null;
			}
			/* close connection */
			if (avIndexForSendAudio >= 0) {
				AVAPIs.avServStop(avIndexForSendAudio);
			}

			if (chIndexForSendAudio >= 0) {
				IOTCAPIs.IOTC_Session_Channel_OFF(mSID, chIndexForSendAudio);
			}

			avIndexForSendAudio = -1;
			chIndexForSendAudio = -1;

			Log.e(TAG, "===ThreadSendAudio exit===");
		}
	}

	private class AVChannel {

		private volatile int mChannel = -1;
		private volatile int mAVIndex = -1;
		private long mServiceType = 0xFFFFFFFF;
		private String mViewAcc;
		private String mViewPwd;
		private int mAudioCodec;

		public IOCtrlQueue IOCtrlQueue;
		public AVFrameQueue VideoFrameQueue;
		public AVFrameQueue AudioFrameQueue;

		public Bitmap LastFrame;

		public int VideoFPS;
		public int VideoBPS;
		public int AudioBPS;

		public int flowInfoInterval;
		public ThreadStartDev threadStartDev = null;
		public ThreadRecvIOCtrl threadRecvIOCtrl = null;
		public ThreadSendIOCtrl threadSendIOCtrl = null;
		public ThreadRecvVideo threadRecvVideo = null;
		public ThreadRecvAudio threadRecvAudio = null;
		public ThreadDecodeVideo threadDecVideo = null;

		public AVChannel(int channel, String view_acc, String view_pwd) {
			mChannel = channel;
			mViewAcc = view_acc;
			mViewPwd = view_pwd;
			mServiceType = 0xFFFFFFFF;

			VideoFPS = VideoBPS = AudioBPS = flowInfoInterval = 0;

			LastFrame = null;

			IOCtrlQueue = new IOCtrlQueue();
			VideoFrameQueue = new AVFrameQueue();
			AudioFrameQueue = new AVFrameQueue();
		}

		public int getChannel() {
			return mChannel;
		}

		public synchronized int getAVIndex() {
			return mAVIndex;
		}

		public synchronized void setAVIndex(int idx) {
			mAVIndex = idx;
		}

		public synchronized long getServiceType() {
			return mServiceType;
		}

		public synchronized int getAudioCodec() {
			return mAudioCodec;
		}

		public synchronized void setAudioCodec(int codec) {
			mAudioCodec = codec;
		}

		public synchronized void setServiceType(long serviceType) {
			mServiceType = serviceType;
			mAudioCodec = (serviceType & 4096) == 0 ? AVFrame.MEDIA_CODEC_AUDIO_SPEEX : AVFrame.MEDIA_CODEC_AUDIO_ADPCM;
		}

		public String getViewAcc() {
			return mViewAcc;
		}

		public String getViewPwd() {
			return mViewPwd;
		}


	}

	private class IOCtrlQueue {

		public class IOCtrlSet {

			public int IOCtrlType;
			public byte[] IOCtrlBuf;

			@Override
			public String toString() {
				return "IOCtrlSet{" +
						"IOCtrlType=" + IOCtrlType +
						", IOCtrlBuf=" + Arrays.toString(IOCtrlBuf) +
						'}';
			}

			public IOCtrlSet(int avIndex, int type, byte[] buf) {
				IOCtrlType = type;
				IOCtrlBuf = buf;
			}

			public IOCtrlSet(int type, byte[] buf) {
				IOCtrlType = type;
				IOCtrlBuf = buf;
			}
		}

		LinkedList<IOCtrlSet> listData = new LinkedList<IOCtrlSet>();

		public synchronized boolean isEmpty() {
			return listData.isEmpty();
		}

		public synchronized void Enqueue(int type, byte[] data) {
			listData.addLast(new IOCtrlSet(type, data));
		}

		public synchronized void Enqueue(int avIndex, int type, byte[] data) {
			listData.addLast(new IOCtrlSet(avIndex, type, data));
		}

		public synchronized IOCtrlSet Dequeue() {

			return listData.isEmpty() ? null : listData.removeFirst();
		}

		public synchronized void removeAll() {
			if (!listData.isEmpty())
				listData.clear();
		}
	}

	private static final String HEXES = "0123456789ABCDEF";

	static String getHex(byte[] raw, int size) {

		if (raw == null) {
			return null;
		}

		final StringBuilder hex = new StringBuilder(2 * raw.length);

		int len = 0;

		for (final byte b : raw) {
			hex.append(HEXES.charAt((b & 0xF0) >> 4)).append(HEXES.charAt((b & 0x0F))).append(" ");

			if (++len >= size)
				break;
		}

		return hex.toString();
	}



	public boolean startRecord(String file,boolean isRecord){
		return true;
	}
	public boolean stopRecord(){
		return true;
	}
	public boolean isRecord(){
		return mReocrding;
	}
	public String getNowDate(){
		String temp_str="";
		Date dt = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		temp_str=sdf.format(dt);
		return temp_str;
	}
}
