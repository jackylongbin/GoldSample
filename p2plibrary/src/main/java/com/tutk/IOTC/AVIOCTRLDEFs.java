package com.tutk.IOTC;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class AVIOCTRLDEFs {

	// 2018.11.  otto制定的新版协议, yoyo修改
	//  ---------start-----------
	public static final int IOTYPE_USER_IPCAM_VIDEOSTART = 0x1ff;
	public static final int IOTYPE_USER_IPCAM_VIDEOSTOP = 0x102;

	public static final int IOTYPE_USER_IPCAM_AUDIOSTART = 0x104;
	public static final int IOTYPE_USER_IPCAM_AUDIOSTOP = 0x106;

	public static final int IOTYPE_USER_IPCAM_SET_SPEAKER_START_REQ = 0x108;
	public static final int IOTYPE_USER_IPCAM_SET_SPEAKER_START_RESP = 0x109;
	public static final int IOTYPE_USER_IPCAM_SET_SPEAKER_STOP_REQ = 0x10A;
	public static final int IOTYPE_USER_IPCAM_SET_SPEAKER_STOP_RESP = 0x10B;

	public static final int IOTYPE_USER_IPCAM_SET_MUTE_AUDIO_REQ = 0x10C;
	public static final int IOTYPE_USER_IPCAM_SET_MUTE_AUDIO_RESP = 0x10D;
	public static final int IOTYPE_USER_IPCAM_GET_MUTE_AUDIO_REQ = 0x10E;
	public static final int IOTYPE_USER_IPCAM_GET_MUTE_AUDIO_RESP = 0x10F;

	public static final int IOTYPE_USER_IPCAM_SET_WIFI_REQ = 0x1FC;
	public static final int IOTYPE_USER_IPCAM_SET_WIFI_RESP = 0x1FD;
	public static final int IOTYPE_USER_IPCAM_GET_WIFI_REQ = 0x1FE;
	public static final int IOTYPE_USER_IPCAM_GET_WIFI_RESP = 0x1FF;

	public static final int IOTYPE_USER_IPCAM_SET_ROTATE_IMAGE_REQ = 0x200;
	public static final int IOTYPE_USER_IPCAM_SET_ROTATE_IMAGE_RESP = 0x201;
	public static final int IOTYPE_USER_IPCAM_GET_ROTATE_IMAGE_REQ = 0x202;
	public static final int IOTYPE_USER_IPCAM_GET_ROTATE_IMAGE_RESP = 0x203;

	public static final int IOTYPE_USER_IPCAM_SET_VIDEO_QUALITY_REQ = 0x204;
	public static final int IOTYPE_USER_IPCAM_SET_VIDEO_QUALITY_RESP = 0x205;
	public static final int IOTYPE_USER_IPCAM_GET_VIDEO_QUALITY_REQ = 0x206;
	public static final int IOTYPE_USER_IPCAM_GET_VIDEO_QUALITY_RESP = 0x207;

	public static final int IOTYPE_USER_IPCAM_SET_VIDEO_RESOLUTION_REQ = 0x208;
	public static final int IOTYPE_USER_IPCAM_SET_VIDEO_RESOLUTION_RESP = 0x209;
	public static final int IOTYPE_USER_IPCAM_GET_VIDEO_RESOLUTION_REQ = 0x20A;
	public static final int IOTYPE_USER_IPCAM_GET_VIDEO_RESOLUTION_RESP = 0x20B;

	public static final int IOTYPE_USER_IPCAM_SET_VIDEO_FRAMERATE_REQ = 0x20C;
	public static final int IOTYPE_USER_IPCAM_SET_VIDEO_FRAMERATE_RESP = 0x20D;
	public static final int IOTYPE_USER_IPCAM_GET_VIDEO_FRAMERATE_REQ = 0x20E;
	public static final int IOTYPE_USER_IPCAM_GET_VIDEO_FRAMERATE_RESP = 0x20F;

	public static final int IOTYPE_USER_IPCAM_SET_VIDEO_GOP_REQ = 0x210;
	public static final int IOTYPE_USER_IPCAM_SET_VIDEO_GOP_RESP = 0x211;
	public static final int IOTYPE_USER_IPCAM_GET_VIDEO_GOP_REQ = 0x212;
	public static final int IOTYPE_USER_IPCAM_GET_VIDEO_GOP_RESP = 0x213;

	public static final int IOTYPE_USER_IPCAM_SET_VIDEO_AWB_REQ = 0x214;
	public static final int IOTYPE_USER_IPCAM_SET_VIDEO_AWB_RESP = 0x215;
	public static final int IOTYPE_USER_IPCAM_GET_VIDEO_AWB_REQ = 0x216;
	public static final int IOTYPE_USER_IPCAM_GET_VIDEO_AWB_RESP = 0x217;

	public static final int IOTYPE_USER_IPCAM_SET_VIDEO_CONFIG_REQ = 0x2FC;
	public static final int IOTYPE_USER_IPCAM_SET_VIDEO_CONFIG_RESP = 0x2FD;
	public static final int IOTYPE_USER_IPCAM_GET_VIDEO_CONFIG_REQ = 0x2FE;
	public static final int IOTYPE_USER_IPCAM_GET_VIDEO_CONFIG_RESP = 0x2FF;

	public static final int IOTYPE_USER_IPCAM_SET_CHIME_TONE_REQ = 0x300;
	public static final int IOTYPE_USER_IPCAM_SET_CHIME_TONE_RESP = 0x301;
	public static final int IOTYPE_USER_IPCAM_GET_CHIME_TONE_REQ = 0x302;
	public static final int IOTYPE_USER_IPCAM_GET_CHIME_TONE_RESP = 0x303;

	public static final int IOTYPE_USER_IPCAM_SET_AUDIO_VOLUME_REQ = 0x304;
	public static final int IOTYPE_USER_IPCAM_SET_AUDIO_VOLUME_RESP = 0x305;
	public static final int IOTYPE_USER_IPCAM_GET_AUDIO_VOLUME_REQ = 0x306;
	public static final int IOTYPE_USER_IPCAM_GET_AUDIO_VOLUME_RESP = 0x307;

	public static final int IOTYPE_USER_IPCAM_SET_AUDIO_CODEC_REQ = 0x308;
	public static final int IOTYPE_USER_IPCAM_SET_AUDIO_CODEC_RESP = 0x309;
	public static final int IOTYPE_USER_IPCAM_GET_AUDIO_CODEC_REQ = 0x30A;
	public static final int IOTYPE_USER_IPCAM_GET_AUDIO_CODEC_RESP = 0x30B;

	public static final int IOTYPE_USER_IPCAM_SET_AUDIO_RATE_REQ = 0x30C;
	public static final int IOTYPE_USER_IPCAM_SET_AUDIO_RATE_RESP = 0x30D;
	public static final int IOTYPE_USER_IPCAM_GET_AUDIO_RATE_REQ = 0x30E;
	public static final int IOTYPE_USER_IPCAM_GET_AUDIO_RATE_RESP = 0x30F;

	public static final int IOTYPE_USER_IPCAM_SET_AUDIO_CHANNEL_REQ = 0x310;
	public static final int IOTYPE_USER_IPCAM_SET_AUDIO_CHANNEL_RESP = 0x311;
	public static final int IOTYPE_USER_IPCAM_GET_AUDIO_CHANNEL_REQ = 0x312;
	public static final int IOTYPE_USER_IPCAM_GET_AUDIO_CHANNEL_RESP = 0x313;

	public static final int IOTYPE_USER_IPCAM_SET_AUDIO_CONFIG_REQ = 0x3FC;
	public static final int IOTYPE_USER_IPCAM_SET_AUDIO_CONFIG_RESP = 0x3FD;
	public static final int IOTYPE_USER_IPCAM_GET_AUDIO_CONFIG_REQ = 0x3FE;
	public static final int IOTYPE_USER_IPCAM_GET_AUDIO_CONFIG_RESP = 0x3FF;

	public static final int IOTYPE_USER_IPCAM_SET_LIGHT_BRIGHT_REQ = 0x400;
	public static final int IOTYPE_USER_IPCAM_SET_LIGHT_BRIGHT_RESP = 0x401;
	public static final int IOTYPE_USER_IPCAM_GET_LIGHT_BRIGHT_REQ = 0x402;
	public static final int IOTYPE_USER_IPCAM_GET_LIGHT_BRIGHT_RESP = 0x403;

	public static final int IOTYPE_USER_IPCAM_SET_MOTION_REQ = 0x404;
	public static final int IOTYPE_USER_IPCAM_SET_MOTION_RESP = 0x405;
	public static final int IOTYPE_USER_IPCAM_GET_MOTION_REQ = 0x406;
	public static final int IOTYPE_USER_IPCAM_GET_MOTION_RESP = 0x407;

	public static final int IOTYPE_USER_IPCAM_SET_MOTION_NOTIFICATION_REQ = 0x408;
	public static final int IOTYPE_USER_IPCAM_SET_MOTION_NOTIFICATION_RESP = 0x409;
	public static final int IOTYPE_USER_IPCAM_GET_MOTION_NOTIFICATION_REQ = 0x40A;
	public static final int IOTYPE_USER_IPCAM_GET_MOTION_NOTIFICATION_RESP = 0x40B;

	public static final int IOTYPE_USER_IPCAM_SET_NIGHT_VISION_REQ = 0x40C;
	public static final int IOTYPE_USER_IPCAM_SET_NIGHT_VISION_RESP = 0x40D;
	public static final int IOTYPE_USER_IPCAM_GET_NIGHT_VISION_REQ = 0x40E;
	public static final int IOTYPE_USER_IPCAM_GET_NIGHT_VISION_RESP = 0x40F;

	public static final int IOTYPE_USER_IPCAM_GET_FWINFO_REQ = 0xF00;
	public static final int IOTYPE_USER_IPCAM_GET_FWINFO_RESP = 0xF01;

	public static final int IOTYPE_USER_IPCAM_SET_SYNC_DATETIME_REQ = 0xF02;
	public static final int IOTYPE_USER_IPCAM_SET_SYNC_DATETIME_RESP = 0xF03;
	public static final int IOTYPE_USER_IPCAM_GET_SYNC_DATETIME_REQ = 0xF04;
	public static final int IOTYPE_USER_IPCAM_GET_SYNC_DATETIME_RESP = 0xF05;

	public static final int IOTYPE_USER_IPCAM_GET_SYSTEMREBOOT_REQ = 0xF06;
	public static final int IOTYPE_USER_IPCAM_GET_SYSTEMREBOOT_RESP = 0xF07;

	// ------------------------- END ------------------------

	public static final int IOTYPE_USER_IPCAM_SET_STREAMCTRL_REQ = 0x320;
	public static final int IOTYPE_USER_IPCAM_SET_STREAMCTRL_RESP = 0x321;
	public static final int IOTYPE_USER_IPCAM_GET_STREAMCTRL_REQ = 0x322;
	public static final int IOTYPE_USER_IPCAM_GET_STREAMCTRL_RESP = 0x323;










	public static final int IOTYPE_USER_IPCAM_SET_USERNAMEPWD_REQ = 0x338;
	public static final int IOTYPE_USER_IPCAM_SET_USERNAMEPWD_RESP = 0x339;
	public static final int IOTYPE_USER_IPCAM_GET_USERNAMEPWD_REQ = 0x33A;
	public static final int IOTYPE_USER_IPCAM_GET_USERNAMEPWD_RESP = 0x33B;






	//  --------- end -----------

	/* AVAPIs IOCTRL Message Type */
//	public static final int IOTYPE_USER_IPCAM_START = 0x01FF;
//	public static final int IOTYPE_USER_IPCAM_STOP = 0x02FF;

//	public static final int IOTYPE_USER_IPCAM_AUDIOSTART = 0x0300;
//	public static final int IOTYPE_USER_IPCAM_AUDIOSTOP = 0x0301;

	// record setTime  Repeat
	public static final int IOTYPE_USER_IPCAM_SET_RECORDSCHEULE_REQ = 0x0302;
	public static final int IOTYPE_USER_IPCAM_SET_RECORDSCHEULE_RESP = 0x0303;
	public static final int IOTYPE_USER_IPCAM_GET_RECORDSCHEULE_REQ = 0x0304;
	public static final int IOTYPE_USER_IPCAM_GET_RECORDSCHEULE_RESP = 0x0305;


//	public static final int IOTYPE_USER_IPCAM_SPEAKERSTART = 0x0350;
//	public static final int IOTYPE_USER_IPCAM_SPEAKERSTOP = 0x0351;




	public static final int IOTYPE_USER_IPCAM_GETSUPPORTSTREAM_REQ = 0x0328;
	public static final int IOTYPE_USER_IPCAM_GETSUPPORTSTREAM_RESP = 0x0329;

	public static final int IOTYPE_USER_IPCAM_GETAUDIOOUTFORMAT_REQ = 0x032A;
	public static final int IOTYPE_USER_IPCAM_GETAUDIOOUTFORMAT_RESP = 0x032B;

	public static final int IOTYPE_USER_IPCAM_DEVINFO_REQ = 0x0330;
	public static final int IOTYPE_USER_IPCAM_DEVINFO_RESP = 0x0331;

	public static final int IOTYPE_USER_IPCAM_SETPASSWORD_REQ = 0x0332;
	public static final int IOTYPE_USER_IPCAM_SETPASSWORD_RESP = 0x0333;

	// SDCard And Record status
	public static final int IOTYPE_GET_SD_RECORD_STATUS_REQ  = 0x0335;
	public static final int IOTYPE_GET_SD_RECORD_STATUS_RESP = 0x0336;

	public static final int IOTYPE_USER_IPCAM_LISTWIFIAP_REQ = 0x0340;
	public static final int IOTYPE_USER_IPCAM_LISTWIFIAP_RESP = 0x0341;

//	public static final int IOTYPE_USER_IPCAM_SETWIFI_REQ_2 = 0x0346;
	public static final int IOTYPE_USER_IPCAM_CLOUD_DATA_REQ = 0x0346;//H_F_VERSION
	public static final int IOTYPE_USER_IPCAM_CLOUD_DATA_ACK = 0x0347;//Camera First time ACK, ignore
	public static final int IOTYPE_USER_IPCAM_CLOUD_DATA_RESP = 0x0328;//Actually value ack

	public static final int IOTYPE_USER_IPCAM_SETRECORD_REQ = 0x0310;
	public static final int IOTYPE_USER_IPCAM_SETRECORD_RESP = 0x0311;
	public static final int IOTYPE_USER_IPCAM_GETRECORD_REQ = 0x0312;
	public static final int IOTYPE_USER_IPCAM_GETRECORD_RESP = 0x0313;

	public static final int IOTYPE_USER_IPCAM_SETRCD_DURATION_REQ = 0x0314;
	public static final int IOTYPE_USER_IPCAM_SETRCD_DURATION_RESP = 0x0315;
	public static final int IOTYPE_USER_IPCAM_GETRCD_DURATION_REQ = 0x0316;
	public static final int IOTYPE_USER_IPCAM_GETRCD_DURATION_RESP = 0x0317;

	public static final int IOTYPE_USER_IPCAM_LISTEVENT_REQ = 0x0318;
	public static final int IOTYPE_USER_IPCAM_LISTEVENT_RESP = 0x0319;

	public static final int IOTYPE_USER_IPCAM_RECORD_PLAYCONTROL = 0x031A;
	public static final int IOTYPE_USER_IPCAM_RECORD_PLAYCONTROL_RESP = 0x031B;

	public static final int IOTYPE_USER_IPCAM_GET_EVENTCONFIG_REQ = 0x0400;
	public static final int IOTYPE_USER_IPCAM_GET_EVENTCONFIG_RESP = 0x0401;
	public static final int IOTYPE_USER_IPCAM_SET_EVENTCONFIG_REQ = 0x0402;
	public static final int IOTYPE_USER_IPCAM_SET_EVENTCONFIG_RESP = 0x0403;

	public static final int IOTYPE_USER_IPCAM_SET_ENVIRONMENT_REQ = 0x0360;
	public static final int IOTYPE_USER_IPCAM_SET_ENVIRONMENT_RESP = 0x0361;
	public static final int IOTYPE_USER_IPCAM_GET_ENVIRONMENT_REQ = 0x0362;
	public static final int IOTYPE_USER_IPCAM_GET_ENVIRONMENT_RESP = 0x0363;


	public static final int IOTYPE_USER_IPCAM_SET_VIDEOMODE_REQ = 0x0370;
	public static final int IOTYPE_USER_IPCAM_SET_VIDEOMODE_RESP = 0x0371;
	public static final int IOTYPE_USER_IPCAM_GET_VIDEOMODE_REQ = 0x0372;
	public static final int IOTYPE_USER_IPCAM_GET_VIDEOMODE_RESP = 0x0373;

	public static final int IOTYPE_USER_IPCAM_FORMATEXTSTORAGE_REQ = 0x380;
	public static final int IOTYPE_USER_IPCAM_FORMATEXTSTORAGE_RESP = 0x381;

	public static final int IOTYPE_USER_IPCAM_PTZ_COMMAND = 0x1001;

	public static final int IOTYPE_USER_IPCAM_EVENT_REPORT = 0x1FFF;

	public static final int IOTYPE_USER_IPCAM_RECEIVE_FIRST_IFRAME = 0x1002;	// Send from client, used to talk to device that

	public static final int	IOTYPE_USER_IPCAM_GET_FLOWINFO_REQ	= 0x0390;
	public static final int	IOTYPE_USER_IPCAM_GET_FLOWINFO_RESP	= 0x0391;
	public static final int	IOTYPE_USER_IPCAM_CURRENT_FLOWINFO	= 0x0392;

	public static final int IOTYPE_USER_IPCAM_GET_TIMEZONE_REQ	= 0x3A0;
	public static final int IOTYPE_USER_IPCAM_GET_TIMEZONE_RESP	= 0x3A1;
	public static final int IOTYPE_USER_IPCAM_SET_TIMEZONE_REQ	= 0x3B0;
	public static final int	IOTYPE_USER_IPCAM_SET_TIMEZONE_RESP	= 0x3B1;

	/* AVAPIs IOCTRL Event Type */
	public static final int AVIOCTRL_EVENT_ALL = 0x00;
	public static final int AVIOCTRL_EVENT_MOTIONDECT = 0x01;
	public static final int AVIOCTRL_EVENT_VIDEOLOST = 0x02;
	public static final int AVIOCTRL_EVENT_IOALARM = 0x03;
	public static final int AVIOCTRL_EVENT_MOTIONPASS = 0x04;
	public static final int AVIOCTRL_EVENT_VIDEORESUME = 0x05;
	public static final int AVIOCTRL_EVENT_IOALARMPASS = 0x06;
	public static final int AVIOCTRL_EVENT_EXPT_REBOOT = 0x10;
	public static final int AVIOCTRL_EVENT_SDFAULT = 0x11;

	/* AVAPIs IOCTRL Play Record Command */
	public static final int AVIOCTRL_RECORD_PLAY_PAUSE = 0x00;
	public static final int AVIOCTRL_RECORD_PLAY_STOP = 0x01;
	public static final int AVIOCTRL_RECORD_PLAY_STEPFORWARD = 0x02;
	public static final int AVIOCTRL_RECORD_PLAY_STEPBACKWARD = 0x03;
	public static final int AVIOCTRL_RECORD_PLAY_FORWARD = 0x04;
	public static final int AVIOCTRL_RECORD_PLAY_BACKWARD = 0x05;
	public static final int AVIOCTRL_RECORD_PLAY_SEEKTIME = 0x06;
	public static final int AVIOCTRL_RECORD_PLAY_END = 0x07;
	public static final int AVIOCTRL_RECORD_PLAY_START = 0x10;

	// AVIOCTRL PTZ Command Value
	public static final int AVIOCTRL_PTZ_STOP = 0;
	public static final int AVIOCTRL_PTZ_UP = 1;
	public static final int AVIOCTRL_PTZ_DOWN = 2;
	public static final int AVIOCTRL_PTZ_LEFT = 3;
	public static final int AVIOCTRL_PTZ_LEFT_UP = 4;
	public static final int AVIOCTRL_PTZ_LEFT_DOWN = 5;
	public static final int AVIOCTRL_PTZ_RIGHT = 6;
	public static final int AVIOCTRL_PTZ_RIGHT_UP = 7;
	public static final int AVIOCTRL_PTZ_RIGHT_DOWN = 8;
	public static final int AVIOCTRL_PTZ_AUTO = 9;
	public static final int AVIOCTRL_PTZ_SET_POINT = 10;
	public static final int AVIOCTRL_PTZ_CLEAR_POINT = 11;
	public static final int AVIOCTRL_PTZ_GOTO_POINT = 12;
	public static final int AVIOCTRL_PTZ_SET_MODE_START = 13;
	public static final int AVIOCTRL_PTZ_SET_MODE_STOP = 14;
	public static final int AVIOCTRL_PTZ_MODE_RUN = 15;
	public static final int AVIOCTRL_PTZ_MENU_OPEN = 16;
	public static final int AVIOCTRL_PTZ_MENU_EXIT = 17;
	public static final int AVIOCTRL_PTZ_MENU_ENTER = 18;
	public static final int AVIOCTRL_PTZ_FLIP = 19;
	public static final int AVIOCTRL_PTZ_START = 20;

	public static final int AVIOCTRL_LENS_APERTURE_OPEN = 21;
	public static final int AVIOCTRL_LENS_APERTURE_CLOSE = 22;
	public static final int AVIOCTRL_LENS_ZOOM_IN = 23;
	public static final int AVIOCTRL_LENS_ZOOM_OUT = 24;
	public static final int AVIOCTRL_LENS_FOCAL_NEAR = 25;
	public static final int AVIOCTRL_LENS_FOCAL_FAR = 26;

	public static final int AVIOCTRL_AUTO_PAN_SPEED = 27;
	public static final int AVIOCTRL_AUTO_PAN_LIMIT = 28;
	public static final int AVIOCTRL_AUTO_PAN_START = 29;

	public static final int AVIOCTRL_PATTERN_START = 30;
	public static final int AVIOCTRL_PATTERN_STOP = 31;
	public static final int AVIOCTRL_PATTERN_RUN = 32;

	public static final int AVIOCTRL_SET_AUX = 33;
	public static final int AVIOCTRL_CLEAR_AUX = 34;
	public static final int AVIOCTRL_MOTOR_RESET_POSITION = 35;


	public static final int IOTYPE_USER_GET_IPC_TIME_REQ = 0x5000;
	public static final int IOTYPE_USER_SET_IPC_TIME_REQ = 0x5001;

	public static final int IOTYPE_USER_SET_IPC_RESET_REQ= 0x6000;
	public static final int IOTYPE_USER_SET_IPC_RESET_RESP= 0x6001;
	/* AVAPIs IOCTRL Quality Type */
	public static final int AVIOCTRL_QUALITY_UNKNOWN = 0x00;
	public static final int AVIOCTRL_QUALITY_MAX = 0x01;
	public static final int AVIOCTRL_QUALITY_HIGH = 0x02;
	public static final int AVIOCTRL_QUALITY_MIDDLE = 0x03;
	public static final int AVIOCTRL_QUALITY_LOW = 0x04;
	public static final int AVIOCTRL_QUALITY_MIN = 0x05;

	/* AVAPIs IOCTRL WiFi Mode */
	public static final int AVIOTC_WIFIAPMODE_ADHOC = 0x00;
	public static final int AVIOTC_WIFIAPMODE_MANAGED = 0x01;

	/* AVAPIs IOCTRL WiFi Enc Type */
	public static final int AVIOTC_WIFIAPENC_INVALID = 0x00;
	public static final int AVIOTC_WIFIAPENC_NONE = 0x01;
	public static final int AVIOTC_WIFIAPENC_WEP = 0x02;
	public static final int AVIOTC_WIFIAPENC_WPA_TKIP = 0x03;
	public static final int AVIOTC_WIFIAPENC_WPA_AES = 0x04;
	public static final int AVIOTC_WIFIAPENC_WPA2_TKIP = 0x05;
	public static final int AVIOTC_WIFIAPENC_WPA2_AES = 0x06;
	public static final int AVIOTC_WIFIAPENC_WPA_PSK_TKIP  = 0x07;
	public static final int	AVIOTC_WIFIAPENC_WPA_PSK_AES   = 0x08;
	public static final int	AVIOTC_WIFIAPENC_WPA2_PSK_TKIP = 0x09;
	public static final int	AVIOTC_WIFIAPENC_WPA2_PSK_AES  = 0x0A;

	/* AVAPIs IOCTRL Recording Type */
	public static final int AVIOTC_RECORDTYPE_OFF = 0x00;
	public static final int AVIOTC_RECORDTYPE_FULLTIME = 0x01;
	public static final int AVIOTC_RECORDTYPE_ALAM = 0x02;
	public static final int AVIOTC_RECORDTYPE_MANUAL = 0x03;

	public static final int AVIOCTRL_ENVIRONMENT_INDOOR_50HZ = 0x00;
	public static final int AVIOCTRL_ENVIRONMENT_INDOOR_60HZ = 0x01;
	public static final int AVIOCTRL_ENVIRONMENT_OUTDOOR = 0x02;
	public static final int AVIOCTRL_ENVIRONMENT_NIGHT = 0x03;

	/* AVIOCTRL VIDEO MODE */
	public static final int AVIOCTRL_VIDEOMODE_NORMAL = 0x00;
	public static final int AVIOCTRL_VIDEOMODE_FLIP = 0x01;
	public static final int AVIOCTRL_VIDEOMODE_MIRROR = 0x02;
	public static final int AVIOCTRL_VIDEOMODE_FLIP_MIRROR = 0x03;

	/* 向設備取得是否支援此功能及連結的 Dropbox 帳號為何時使用 */
	public static final int IOTYPE_USER_IPCAM_GET_SAVE_DROPBOX_REQ=0x500;
	public static final int IOTYPE_USER_IPCAM_GET_SAVE_DROPBOX_RESP=0x501;
	public static final int IOTYPE_USER_IPCAM_SET_SAVE_DROPBOX_REQ=0x502;
	public static final int IOTYPE_USER_IPCAM_SET_SAVE_DROPBOX_RESP=0x503;

	// app main use next cmd
	public static final int  CMD_GET_CAMERA_ONOFF_STATUS = 0x2A;
	public static final int CMD_SET_CAMERA_ONOFF_STATUS  = 0x2B;
	public static final int CMD_SEND_MOTION_NOTIFICATION = 0x41;
	public static final int CMD_SET_24H_RECORD = 0x42;
	public static final int CMD_GET_24H_RECORD = 0x43;
	public static final int CMD_SET_PRIVACY_MODE_DURING_DAY = 0x44;
	public static final int CMD_GET_PRIVACY_MODE_DURING_DAY = 0x45;
	public static final int CMD_SET_MOTION_NOTIFICATION = 0x46;
	public static final int CMD_GET_MOTION_NOTIFICATION = 0x47;
	public static final int CMD_SET_STAY_MODE_NOTIFICATION = 0x48;
	public static final int CMD_GET_STAY_MODE_NOTIFICATION = 0x49;
	public static final int CMD_GET_ALL_SWITCH_STATUS = 0x4A;// example: 10010: 0: camera on/off; 1: 24/7 recording;  0: privacy mode during day; 0: motion notification 1: stay mode notification low->high


	public static class SFrameInfo {

		short codec_id;
		byte flags;
		byte cam_index;
		byte onlineNum;
		byte[] reserved = new byte[3];
		int reserved2;
		int timestamp;

		public static byte[] parseContent(short codec_id, byte flags, byte cam_index, byte online_num, int timestamp) {

			byte[] result = new byte[16];

			byte[] codec = Packet.shortToByteArray_Little(codec_id);
			System.arraycopy(codec, 0, result, 0, 2);

			result[2] = flags;
			result[3] = cam_index;
			result[4] = online_num;

			byte[] time = Packet.intToByteArray_Little(timestamp);
			System.arraycopy(time, 0, result, 12, 4);

			return result;
		}
	}

	public static class SMsgAVIoctrlAVStream {
		int channel = 0; // camera index
		byte[] reserved = new byte[4];

		public static byte[] parseContent(int channel) {
			byte[] result = new byte[8];
			byte[] ch = Packet.intToByteArray_Little(channel);
			System.arraycopy(ch, 0, result, 0, 4);

			return result;
		}
	}
	public static class SMsgAVIoctrlSetMuAuEablet {
		int channel;
		int muAuEable;

		public static byte[] parseContent(int channel, int muAuEable) {
			byte[] result = new byte[8];
			byte[] ch = Packet.intToByteArray_Little(channel);
			byte[] mu = Packet.intToByteArray_Little(muAuEable);
			System.arraycopy(ch, 0, result, 0, 4);
			System.arraycopy(mu, 0, result, 4, 4);
			return result;
		}
	}

	public class SMsgAVIoctrlEventConfig {
		long channel; // Camera Index
		byte mail; // enable send email
		byte ftp; // enable ftp upload photo
		byte localIO; // enable local io output
		byte p2pPushMsg; // enable p2p push msg
	}

	public static class SMsgAVIoctrlPtzCmd {
		byte control; // ptz control command
		byte speed; // ptz control speed
		byte point;
		byte limit;
		byte aux;
		byte channel; // camera index
		byte[] reserved = new byte[2];

		public static byte[] parseContent(byte control, byte speed, byte point, byte limit, byte aux, byte channel) {
			byte[] result = new byte[8];

			result[0] = control;
			result[1] = speed;
			result[2] = point;
			result[3] = limit;
			result[4] = aux;
			result[5] = channel;

			return result;
		}
	}



	public class SMsgAVIoctrlGetStreamCtrlResp {
		int channel; // Camera Index
		byte quality; // AVIOCTRL_QUALITY_XXXX
		byte[] reserved = new byte[3];
	}

	public class SMsgAVIoctrlSetStreamCtrlResp {
		int result;
		byte[] reserved = new byte[4];
	}

	public static class SMsgAVIoctrlGetStreamCtrlReq {
		int channel; // Camera Index
		byte[] reserved = new byte[4];

		public static byte[] parseContent(int channel) {

			byte[] result = new byte[8];
			byte[] ch = Packet.intToByteArray_Little(channel);
			System.arraycopy(ch, 0, result, 0, 4);

			return result;
		}
	}

	// 2018.11.13  Motion旧版协议
	public static class SMsgAVIoctrlSetMotionDetectReq {
		int channel; // Camera Index
		int sensitivity; /* 0(disbale) ~ 100(MAX) */

		public static byte[] parseContent(int channel, int sensitivity, int recordTime) {

			byte[] result = new byte[12];
			byte[] ch = Packet.intToByteArray_Little(channel);
			byte[] sen = Packet.intToByteArray_Little(sensitivity);
			byte[] rt = Packet.intToByteArray_Little(recordTime);

			System.arraycopy(ch, 0, result, 0, 4);
			System.arraycopy(sen, 0, result, 4, 4);
			System.arraycopy(rt, 0, result, 8, 4);

			return result;
		}
	}

	// 2018.11.13  otto. yoyo修改  Motion新版协议
	public static class SMsgAVIoctrlSetMotionReq {
		int channel; // Camera Index
		int sensitivity; /* 0(disbale) ~ 100(MAX) */

		public static byte[] parseContent(int channel,int enable, int sensitivity) {

			byte[] result = new byte[8];
			byte[] ch = Packet.intToByteArray_Little(channel);
			byte[] en = Packet.intToByteArray_Little(enable);
			byte[] sen = Packet.intToByteArray_Little(sensitivity);
			System.arraycopy(ch, 0, result, 0, 4);
			System.arraycopy(en, 0, result, 4, 1);
			System.arraycopy(sen, 0, result, 5, 1);

			return result;
		}
	}

	public static class SMsgAVIoctrlSetVideoQualityReq {
		int channel; // Camera Index
		byte quality; // AVIOCTRL_QUALITY_XXXX
		byte[] reserved = new byte[3];

		public static byte[] parseContent(int channel, byte quality) {

			byte[] result = new byte[8];
			byte[] ch = Packet.intToByteArray_Little(channel);
			System.arraycopy(ch, 0, result, 0, 4);
			result[4] = quality;

			return result;
		}
	}
	// 2018.11.13  otto. yoyo新增  MotionEvent
	public static class SMsgAVIoctrlSetMotionNotificationReq {
		int channel; // Camera Index
		byte[] reserved = new byte[4];

		public static byte[] parseContent(int enable,String url) {

			byte[] result = new byte[136];
			byte[] intEnable = Packet.intToByteArray_Little(enable);
			byte[] u =url.getBytes();
			System.arraycopy(intEnable, 0, result, 0, 4);
			System.arraycopy(u, 0, result, 4, url.length());

			return result;
		}
	}



	// 新版 设置画面反转
	public static class SMsgAVIoctrlSetRotateImageReq {
		int channel; // Camera Index
		byte mode; // Video mode
		byte[] reserved = new byte[3];

		public static byte[] parseContent(int rotate) {
			byte[] result = new byte[8];

			byte[] ro= Packet.intToByteArray_Little(rotate);
			System.arraycopy(ro, 0, result, 0, 4);

			return result;
		}
	}
	// 新版 设置设备静音
	public static class SMsgAVIoctrlSetMuteAudioReq {
		int channel; // Camera Index
		byte mode; // Video mode
		byte[] reserved = new byte[3];

		public static byte[] parseContent(int enable) {
			byte[] result = new byte[8];

			byte[] en= Packet.intToByteArray_Little(enable);
			System.arraycopy(en, 0, result, 0, 4);

			return result;
		}
	}

	// 新版 设置用户名密码
	public static class SMsgAVIoctrlSetUserNamePwdReq {
		int channel; // Camera Index
		byte mode; // Video mode
		byte[] reserved = new byte[3];

		public static byte[] parseContent(String name,String pwd) {
			byte[] result = new byte[72];

			byte[] na= name.getBytes();
			byte[] p= name.getBytes();
			System.arraycopy(na, 0, result, 0, name.length());
			System.arraycopy(p, 0, result, 36, pwd.length());

			return result;
		}
	}

	// 2018.11月  新版协议
	public static class SMsgAVIoctrlSetNightVisionReq {
		int channel; // Camera Index
		byte mode; // Environment mode
		byte[] reserved = new byte[3];

		public static byte[] parseContent(int mode,int lightH,int lightL) {

			byte[] result = new byte[8];

			byte[] bytemode = Packet.intToByteArray_Little(mode);
			byte[] bytelightH = Packet.intToByteArray_Little(lightH);
			byte[] bytelightL = Packet.intToByteArray_Little(lightL);
			System.arraycopy(bytemode, 0, result, 0, 1);
			System.arraycopy(bytelightH, 0, result, 1, 1);
			System.arraycopy(bytelightL, 0, result, 2, 1);


			return result;
		}
	}

	// 新版 设置设备声音
	public static class SMsgAVIoctrlSetChimeToneReq {
		int channel; // Camera Index
		byte mode; // Video mode
		byte[] reserved = new byte[3];

		public static byte[] parseContent(int tone) {
			byte[] result = new byte[8];

			byte[] to= Packet.intToByteArray_Little(tone);
			System.arraycopy(to, 0, result, 0, 4);

			return result;
		}
	}
	// 新版 获取设备声音
	public static class SMsgAVIoctrlGetReq {
		int channel; // Camera Index
		byte[] reserved = new byte[4];

		public static byte[] parseContent(int channel) {

			byte[] result = new byte[8];
			byte[] ch = Packet.intToByteArray_Little(channel);
			System.arraycopy(ch, 0, result, 0, 4);

			return result;
		}
	}

	// 新版 设置设备音量大小
	public static class SMsgAVIoctrlSetAudioVolumeReq {
		int channel; // Camera Index
		byte mode; // Video mode
		byte[] reserved = new byte[3];

		public static byte[] parseContent(int type,byte volume) {
			byte[] result = new byte[8];

			byte[] ty= Packet.intToByteArray_Little(type);
			System.arraycopy(ty, 0, result, 0, 4);
			result[4] = volume;
			return result;
		}
	}
	// 新版 设置设备音频编码格式
	public static class SMsgAVIoctrlSetAudioCodecReq {
		int channel; // Camera Index
		byte mode; // Video mode
		byte[] reserved = new byte[3];

		public static byte[] parseContent(int spk_mic,byte codec) {
			byte[] result = new byte[8];

			byte[] ty= Packet.intToByteArray_Little(spk_mic);
			System.arraycopy(ty, 0, result, 0, 4);
			result[4] = codec;
			return result;
		}
	}
	// 新版 设置audio rate
	public static class SMsgAVIoctrlSetAudioRateReq {
		int channel; // Camera Index
		byte mode; // Video mode
		byte[] reserved = new byte[3];

		public static byte[] parseContent(int spk_mic,int rate) {
			byte[] result = new byte[8];

			byte[] ty= Packet.intToByteArray_Little(spk_mic);
			byte[] ra= Packet.intToByteArray_Little(rate);
			System.arraycopy(ty, 0, result, 0, 4);
			System.arraycopy(ra, 0, result, 4, 4);
			return result;
		}
	}
	// 新版 设置audio 声道
	public static class SMsgAVIoctrlSetAudioChannelReq {
		int channel; // Camera Index
		byte mode; // Video mode
		byte[] reserved = new byte[3];

		public static byte[] parseContent(int spk_mic,int channel) {
			byte[] result = new byte[8];

			byte[] ty= Packet.intToByteArray_Little(spk_mic);
			byte[] ch= Packet.intToByteArray_Little(channel);
			System.arraycopy(ty, 0, result, 0, 4);
			System.arraycopy(ch, 0, result, 4, 4);
			return result;
		}
	}
	// 新版 设置视频画面反转
	public static class SMsgAVIoctrlSetImageRoateReq {
		int channel; // Camera Index
		byte mode; // Video mode
		byte[] reserved = new byte[3];

		public static byte[] parseContent(int rotate) {
			byte[] result = new byte[8];

			byte[] ro= Packet.intToByteArray_Little(rotate);
			System.arraycopy(ro, 0, result, 0, 4);

			return result;
		}
	}
	// 新版 设置画面分辨率
	public static class SMsgAVIoctrlSetVideoResolutionReq {
		int channel; // Camera Index
		byte mode; // Video mode
		byte[] reserved = new byte[3];

		public static byte[] parseContent(int channel,int resolution) {
			byte[] result = new byte[12];

			byte[] ch= Packet.intToByteArray_Little(channel);
			byte[] re= Packet.intToByteArray_Little(resolution);
			System.arraycopy(ch, 0, result, 0, 4);
			System.arraycopy(re, 0, result, 4, 4);
			return result;
		}
	}
	// 新版 设置
	public static class SMsgAVIoctrlSetVideoFramerateReq {
		int channel; // Camera Index
		byte mode; // Video mode
		byte[] reserved = new byte[3];

		public static byte[] parseContent(int channel,int framerate) {
			byte[] result = new byte[8];

			byte[] ch= Packet.intToByteArray_Little(channel);
			byte[] fr= Packet.intToByteArray_Little(framerate);
			System.arraycopy(ch, 0, result, 0, 4);
			System.arraycopy(fr, 0, result, 4, 4);
			return result;
		}
	}
	// 新版 设置
	public static class SMsgAVIoctrlSetVideoGopReq {
		int channel; // Camera Index
		byte mode; // Video mode
		byte[] reserved = new byte[3];

		public static byte[] parseContent(int channel,int sec) {
			byte[] result = new byte[8];

			byte[] ch= Packet.intToByteArray_Little(channel);
			byte[] se= Packet.intToByteArray_Little(sec);
			System.arraycopy(ch, 0, result, 0, 4);
			System.arraycopy(se, 0, result, 4, 4);
			return result;
		}
	}
	// 新版 设置VideoQualily
	public static class SMsgAVIoctrlSetVideoQualilyReq {
		int channel; // Camera Index
		byte mode; // Video mode
		byte[] reserved = new byte[3];

		public static byte[] parseContent(int channel,byte qualily) {
			byte[] result = new byte[8];

			byte[] ch= Packet.intToByteArray_Little(channel);
			System.arraycopy(ch, 0, result, 0, 4);
			result[4] = qualily;
			return result;
		}
	}
	// 新版 获取设备音量
	public static class SMsgAVIoctrlGetAudioVolumeReq {
		int channel; // Camera Index
		byte[] reserved = new byte[4];

		public static byte[] parseContent(int channel) {

			byte[] result = new byte[8];
			byte[] ch = Packet.intToByteArray_Little(channel);
			System.arraycopy(ch, 0, result, 0, 4);

			return result;
		}
	}

	// 新版 设置亮度
	public static class SMsgAVIoctrlSetLightBrightReq {
		int channel; // Camera Index
		byte mode; // Video mode
		byte[] reserved = new byte[3];

		public static byte[] parseContent(int channel,byte light) {
			byte[] result = new byte[8];

			byte[] ch= Packet.intToByteArray_Little(channel);
			System.arraycopy(ch, 0, result, 0, 4);
			result[4] = light;
			return result;
		}
	}

	// 新版 同步设备和手机系统的时间
	public static class SMsgAVIoctrlSetSyncTimeReq {
		int channel; // Camera Index
		byte mode; // Video mode
		byte[] reserved = new byte[3];

		public static byte[] parseContent(int year,int month,int day,int hour,int minute,int secound) {
			byte[] result = new byte[24];

			byte[] ye= Packet.intToByteArray_Little(year);
			byte[] mon= Packet.intToByteArray_Little(month);
			byte[] da= Packet.intToByteArray_Little(day);
			byte[] ho= Packet.intToByteArray_Little(hour);
			byte[] min= Packet.intToByteArray_Little(minute);
			byte[] se= Packet.intToByteArray_Little(secound);
			System.arraycopy(ye, 0, result, 0, 4);
			System.arraycopy(mon, 0, result, 4, 4);
			System.arraycopy(da, 0, result, 8, 4);
			System.arraycopy(ho, 0, result, 12, 4);
			System.arraycopy(min, 0, result, 16, 4);
			System.arraycopy(se, 0, result, 20, 4);
			return result;
		}
	}
	// 新版 获取设备亮度
	public static class SMsgAVIoctrlGetLightBrightReq {
		int channel; // Camera Index
		byte[] reserved = new byte[4];

		public static byte[] parseContent(int channel) {

			byte[] result = new byte[8];
			byte[] ch = Packet.intToByteArray_Little(channel);
			System.arraycopy(ch, 0, result, 0, 4);

			return result;
		}
	}
	// 新版 设置wifi
	public static class SMsgAVIoctrlNewSetWifiReq {
		byte[] ssid = new byte[32];
		byte[] password = new byte[32];
		byte mode;
		byte enctype;
		byte[] reserved = new byte[10];

		public static byte[] parseContent(byte[] ssid, byte[] password, byte ipv,byte mode, byte enctype) {

			byte[] result = new byte[92];

			System.arraycopy(ssid, 0, result, 0, ssid.length);
			System.arraycopy(password, 0, result, 36, password.length);
			result[72] = ipv;//ip地址
			result[88] = mode;//wifi模式
			result[89] =enctype;//加密

			return result;
		}
		public static byte[] parseContent(byte[] ssid, byte[] password) {
			byte[] result = new byte[76];
			System.arraycopy(ssid, 0, result, 0, ssid.length);
			System.arraycopy(password, 0, result, 32, password.length);
			return result;
		}

	}



	public class SMsgAVIoctrlSetMotionDetectResp {
		byte result;
		byte[] reserved = new byte[3];
	}

	public static class SMsgAVIoctrlGetCurrencyReq {
		int channel; // Camera Index
		byte[] reserved = new byte[4];

		public static byte[] parseContent(int channel) {

			byte[] result = new byte[8];
			byte[] ch = Packet.intToByteArray_Little(channel);
			System.arraycopy(ch, 0, result, 0, 4);

			return result;
		}
	}

	public class SMsgAVIoctrlGetMotionDetectResp {
		int channel; // Camera Index
		int sensitivity; /* 0(disbale) ~ 100(MAX) */
	}

	public static class SMsgAVIoctrlDeviceInfoReq {

		static byte[] reserved = new byte[4];;

		public static byte[] parseContent() {
			return reserved;
		}
	}

	public class SMsgAVIoctrlDeviceInfoResp {
		byte[] model = new byte[16];
		byte[] vendor = new byte[16];
		int version;
		int channel;
		int total; /* MByte */
		int free; /* MByte */
		byte[] reserved = new byte[8];
	}

	public static class SMsgAVIoctrlSetPasswdReq {
		byte[] oldPasswd = new byte[32];
		byte[] newPasswd = new byte[32];

		public static byte[] parseContent(String oldPwd, String newPwd) {

			byte[] oldpwd = oldPwd.getBytes();
			byte[] newpwd = newPwd.getBytes();
			byte[] result = new byte[64];

			System.arraycopy(oldpwd, 0, result, 0, oldpwd.length);
			System.arraycopy(newpwd, 0, result, 32, newpwd.length);

			return result;
		}
	}

	public class SMsgAVIoctrlSetPasswdResp {
		byte result;
		byte[] reserved = new byte[3];
	}

	public static class SMsgAVIoctrlListWifiApReq {

		static byte[] reserved = new byte[4];

		public static byte[] parseContent() {

			return reserved;
		}
	}

	public static class SWifiAp {
		public byte[] ssid = new byte[32];
		public byte mode;
		public byte enctype;
		public byte signal;
		public byte status;

		public static int getTotalSize() {
			return 36;
		}

		public SWifiAp(byte[] data) {

			System.arraycopy(data, 1, ssid, 0, data.length);
			mode = data[32];
			enctype = data[33];
			signal = data[34];
			status = data[35];
		}

		public SWifiAp(byte[] bytsSSID, byte bytMode, byte bytEnctype, byte bytSignal, byte bytStatus) {

			System.arraycopy(bytsSSID, 0, ssid, 0, bytsSSID.length);
			mode = bytMode;
			enctype = bytEnctype;
			signal = bytSignal;
			status = bytStatus;
		}
	}

	public class SMsgAVIoctrlListWifiApResp {
		int number; // MAX: 1024/36= 28
		SWifiAp stWifiAp;
	}

	public static class SMsgAVIoctrlSetWifiReq {
		byte[] ssid = new byte[32];
		byte[] password = new byte[32];
		byte mode;
		byte enctype;
		byte[] reserved = new byte[10];

		public static byte[] parseContent(byte[] ssid, byte[] password, byte mode, byte enctype) {

			byte[] result = new byte[76];

			System.arraycopy(ssid, 0, result, 0, ssid.length);
			System.arraycopy(password, 0, result, 32, password.length);
			result[64] = mode;
			result[65] = enctype;

			return result;
		}
		public static byte[] parseContent(byte[] ssid, byte[] password) {
			byte[] result = new byte[76];
			System.arraycopy(ssid, 0, result, 0, ssid.length);
			System.arraycopy(password, 0, result, 32, password.length);
			return result;
		}

	}

	public class SMsgAVIoctrlSetWifiResp {
		byte result;
		byte[] reserved = new byte[3];
	}

	public static class SMsgAVIoctrlGetWifiReq {

		static byte[] reserved = new byte[4];

		public static byte[] parseContent() {
			return reserved;
		}
	}

	public class SMsgAVIoctrlGetWifiResp {
		byte[] ssid = new byte[32];
		byte[] password = new byte[32];
		byte mode;
		byte enctype;
		byte signal;
		byte status;
	}

	public static class SMsgAVIoctrlSetRecordReq {
		int channel; // Camera Index
		int recordType;
		byte[] reserved = new byte[4];

		public static byte[] parseContent(int channel, int recordType) {

			byte[] result = new byte[12];
			byte[] ch = Packet.intToByteArray_Little(channel);
			byte[] type = Packet.intToByteArray_Little(recordType);

			System.arraycopy(ch, 0, result, 0, 4);
			System.arraycopy(type, 0, result, 4, 4);

			return result;
		}
	}

	public class SMsgAVIoctrlSetRecordResp {
		byte result;
		byte[] reserved = new byte[3];
	}

	public static class SMsgAVIoctrlGetRecordReq {
		int channel; // Camera Index
		byte[] reserved = new byte[4];

		public static byte[] parseContent(int channel) {

			byte[] result = new byte[8];
			byte[] ch = Packet.intToByteArray_Little(channel);
			System.arraycopy(ch, 0, result, 0, 4);

			return result;
		}
	}

	public class SMsgAVIoctrlGetRecordResp {
		int channel; // Camera Index
		int recordType;
	}

	public class SMsgAVIoctrlSetRcdDurationReq {
		int channel; // Camera Index
		int presecond;
		int durasecond;
	}

	public class SMsgAVIoctrlSetRcdDurationResp {
		byte result;
		byte[] reserved = new byte[3];
	}

	public class SMsgAVIoctrlGetRcdDurationReq {
		int channel; // Camera Index
		byte[] reserved = new byte[4];
	}

	public class SMsgAVIoctrlGetRcdDurationResp {
		int channel; // Camera Index
		int presecond;
		int durasecond;
	}

	public static class STimeDay {

		private byte[] mBuf;
		public short year;
		public byte month;
		public byte day;
		public byte wday;
		public byte hour;
		public byte minute;
		public byte second;

		public STimeDay(byte[] data) {

			mBuf = new byte[8];
			System.arraycopy(data, 0, mBuf, 0, 8);

			year = Packet.byteArrayToShort_Little(data, 0);
			month = data[2];
			day = data[3];
			wday = data[4];
			hour = data[5];
			minute = data[6];
			second = data[7];
		}

		public long getTimeInMillis() {

			Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("gmt"));
			cal.set(year, month - 1, day, hour, minute, second);

			return cal.getTimeInMillis();
		}

		public String getLocalTime() {

			Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("gmt"));
			calendar.setTimeInMillis(getTimeInMillis());
			// calendar.add(Calendar.MONTH, -1);

			SimpleDateFormat dateFormat = new SimpleDateFormat();
			dateFormat.setTimeZone(TimeZone.getDefault());

			return dateFormat.format(calendar.getTime());
		}

		public byte[] toByteArray() {
			return mBuf;
		}

		public static byte[] parseContent(int year, int month, int day, int wday, int hour, int minute, int second) {

			byte[] result = new byte[8];

			byte[] y = Packet.shortToByteArray_Little((short) year);
			System.arraycopy(y, 0, result, 0, 2);

			result[2] = (byte) month;
			result[3] = (byte) day;
			result[4] = (byte) wday;
			result[5] = (byte) hour;
			result[6] = (byte) minute;
			result[7] = (byte) second;

			return result;
		}
	}

	public static class SMsgAVIoctrlListEventReq {

		int channel; // Camera Index
		byte[] startutctime = new byte[8];
		byte[] endutctime = new byte[8];
		byte event;
		byte status;
		byte[] reversed = new byte[2];

		public static byte[] parseConent(int channel, long startutctime, long endutctime, byte event, byte status) {

			/**
			 *  改动，将格林尼治时区换成手机系统时区（为了配合公司的设备完成录像搜索功能）
			 */
//			Calendar startCal = Calendar.getInstance(TimeZone.getTimeZone("gmt"));
//			Calendar stopCal = Calendar.getInstance(TimeZone.getTimeZone("gmt"));
			Calendar startCal = Calendar.getInstance(TimeZone.getDefault());
			Calendar stopCal = Calendar.getInstance(TimeZone.getDefault());
			System.out.println(startutctime+"startutctime--------->"+endutctime+"endutctime");

			startCal.setTimeInMillis(startutctime);
			stopCal.setTimeInMillis(endutctime);
			/**
			 System.out.println("search from " + startCal.get(Calendar.YEAR) + "/" + startCal.get(Calendar.MONTH) + "/" + startCal.get(Calendar.DAY_OF_MONTH)
			 + " " + startCal.get(Calendar.HOUR_OF_DAY) + ":" + startCal.get(Calendar.MINUTE) + ":" + startCal.get(Calendar.SECOND));
			 System.out.println("       to   " + stopCal.get(Calendar.YEAR) + "/" + stopCal.get(Calendar.MONTH) + "/" + stopCal.get(Calendar.DAY_OF_MONTH) + " "
			 + stopCal.get(Calendar.HOUR_OF_DAY) + ":" + stopCal.get(Calendar.MINUTE) + ":" + stopCal.get(Calendar.SECOND));
			 */
			byte[] result = new byte[24];

			byte[] ch = Packet.intToByteArray_Little(channel);
			System.arraycopy(ch, 0, result, 0, 4);

			//-32 7 3 14 5 7 1 0
			//int year, int month, int day, int wday, int hour, int minute, int second
			/**
			 *  改动，为了完成录像搜索功能
			 */
//			byte[] start = STimeDay.parseContent(startCal.get(Calendar.YEAR),
//					startCal.get(Calendar.MONTH) + 1,
//					startCal.get(Calendar.DAY_OF_MONTH),
//					startCal.get(Calendar.DAY_OF_WEEK),
//					startCal.get(Calendar.HOUR_OF_DAY),
//					startCal.get(Calendar.MINUTE), 0);
			byte[] start = STimeDay.parseContent(startCal.get(Calendar.YEAR),
					startCal.get(Calendar.MONTH) + 1,
					startCal.get(Calendar.DAY_OF_MONTH),
					startCal.get(Calendar.DAY_OF_WEEK),
					startCal.get(Calendar.HOUR_OF_DAY),
					startCal.get(Calendar.MINUTE),
					startCal.get(Calendar.SECOND));

			System.arraycopy(start, 0, result, 4, 8);


			/**
			 *  改动，为了完成录像搜索功能
			 */
//			byte[] stop = STimeDay.parseContent(stopCal.get(Calendar.YEAR), stopCal.get(Calendar.MONTH) + 1, stopCal.get(Calendar.DAY_OF_MONTH),
//					stopCal.get(Calendar.DAY_OF_WEEK), stopCal.get(Calendar.HOUR_OF_DAY), stopCal.get(Calendar.MINUTE), 0);
			byte[] stop = STimeDay.parseContent(stopCal.get(Calendar.YEAR), stopCal.get(Calendar.MONTH) + 1, stopCal.get(Calendar.DAY_OF_MONTH),
					stopCal.get(Calendar.DAY_OF_WEEK), stopCal.get(Calendar.HOUR_OF_DAY), stopCal.get(Calendar.MINUTE), stopCal.get(Calendar.SECOND));
			System.arraycopy(stop, 0, result, 12, 8);


			result[20] = event;
			result[21] = status;

			return result;
		}
	}

	public static class SAvEvent {
		byte[] utctime = new byte[8];
		byte event;
		byte status;
		byte[] reserved = new byte[2];

		public static int getTotalSize() {
//			return 12;
			return 20; // 设备端的结构体改动了，SAvEvent的长度不一样
		}
	}

	public class SMsgAVIoctrlListEventResp {
		int channel; // Camera Index
		int total;
		byte index;
		byte endflag;
		byte count;
		byte reserved;
		SAvEvent stEvent;
	}

	public static class SMsgAVIoctrlPlayRecord {
		int channel; // Camera Index
		int command; // play record command
		int Param;
		byte[] stTimeDay = new byte[8];
		byte[] reserved = new byte[4];

		public static byte[] parseContent(int channel, int command, int param, long time) {

			byte[] result = new byte[24];

			byte[] ch = Packet.intToByteArray_Little(channel);
			System.arraycopy(ch, 0, result, 0, 4);

			byte[] cmd = Packet.intToByteArray_Little(command);
			System.arraycopy(cmd, 0, result, 4, 4);

			byte[] p = Packet.intToByteArray_Little(param);
			System.arraycopy(p, 0, result, 8, 4);

			Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("gmt"));
			cal.setTimeInMillis(time);
			cal.add(Calendar.DAY_OF_MONTH, -1);
			cal.add(Calendar.DATE, 1);
			byte[] timedata = STimeDay.parseContent(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH),
					cal.get(Calendar.DAY_OF_WEEK), cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), cal.get(Calendar.SECOND));
			System.arraycopy(timedata, 0, result, 12, 8);

			return result;
		}

		public static byte[] parseContent(int channel, int command, int param, byte[] time) {

			byte[] result = new byte[24];

			byte[] ch = Packet.intToByteArray_Little(channel);
			System.arraycopy(ch, 0, result, 0, 4);

			byte[] cmd = Packet.intToByteArray_Little(command);
			System.arraycopy(cmd, 0, result, 4, 4);

			byte[] p = Packet.intToByteArray_Little(param);
			System.arraycopy(p, 0, result, 8, 4);

			System.arraycopy(time, 0, result, 12, 8);

			return result;
		}
	}

	// only for play record start command
	public class SMsgAVIoctrlPlayRecordResp {
		int channel;
		int result;
		byte[] reserved = new byte[4];
	} // only for play record start command

	public class SMsgAVIoctrlEvent {
		STimeDay stTime; // 8 bytes
		int channel; // Camera Index
		int event; // Event Type
		byte[] reserved = new byte[4];
	}

	// 旧版 设置画面反转
	public static class SMsgAVIoctrlSetVideoModeReq {
		int channel; // Camera Index
		byte mode; // Video mode
		byte[] reserved = new byte[3];

		public static byte[] parseContent(int channel, byte mode) {
			byte[] result = new byte[8];

			byte[] ch = Packet.intToByteArray_Little(channel);
			System.arraycopy(ch, 0, result, 0, 4);

			result[4] = mode;

			return result;
		}
	}


	public class SMsgAVIoctrlSetVideoModeResp {
		int channel; // Camera Index
		byte result; // 1 - succeed, 0 - failed
		byte[] reserved = new byte[3];
	}

	public static class SMsgAVIoctrlGetVideoModeReq {
		int channel; // Camera Index
		byte[] reserved = new byte[4];

		public static byte[] parseContent(int channel) {
			byte[] result = new byte[8];

			byte[] ch = Packet.intToByteArray_Little(channel);
			System.arraycopy(ch, 0, result, 0, 4);

			return result;
		}
	}

	public class SMsgAVIoctrlGetVideoModeResp {
		int channel; // Camera Index
		byte mode; // Video Mode
		byte[] reserved = new byte[3];
	}

	// 旧版
	public static class SMsgAVIoctrlSetEnvironmentReq {
		int channel; // Camera Index
		byte mode; // Environment mode
		byte[] reserved = new byte[3];

		public static byte[] parseContent(int channel, byte mode) {

			byte[] result = new byte[8];

			byte[] ch = Packet.intToByteArray_Little(channel);
			System.arraycopy(ch, 0, result, 0, 4);

			result[4] = mode;

			return result;
		}
	}


	public class SMsgAVIoctrlSetEnvironmentResp {

		int channel; // Camera Index
		byte result; // 1 - succeed, 0 - failed
		byte[] reserved = new byte[3];
	}

	public static class SMsgAVIoctrlGetEnvironmentReq {
		int channel; // Camera Index
		byte[] reserved = new byte[4];

		public static byte[] parseContent(int channel) {

			byte[] result = new byte[8];

			byte[] ch = Packet.intToByteArray_Little(channel);
			System.arraycopy(ch, 0, result, 0, 4);

			return result;
		}
	}

	public class SMsgAVIoctrlGetEnvironmentResp {
		int channel; // Camera Index
		byte mode; // Environment Mode
		byte[] reserved = new byte[3];
	}

	public static class SMsgAVIoctrlFormatExtStorageReq {

		int storage; // Storage index (ex. sdcard slot = 0, internal flash = 1,
		// ...)
		byte[] reserved = new byte[4];

		public static byte[] parseContent(int storage) {

			byte[] result = new byte[8];

			byte[] ch = Packet.intToByteArray_Little(storage);
			System.arraycopy(ch, 0, result, 0, 4);

			return result;
		}
	}

	public class SMsgAVIoctrlFormatExtStorageResp {

		int storage; // Storage index
		byte result; // 0: success;
		// -1: format command is not supported.
		// otherwise: failed.

		byte[] reserved = new byte[3];
	}

	public static class SStreamDef {

		public int index; // the stream index of camera
		public int channel; // the channel index used in AVAPIs

		public SStreamDef(byte[] data) {

			index = Packet.byteArrayToShort_Little(data, 0);
			channel = Packet.byteArrayToShort_Little(data, 2);
		}

		public String toString() {
			return ("CH" + String.valueOf(index + 1));
		}
	}

	public static class SMsgAVIoctrlGetSupportStreamReq {

		public static byte[] parseContent() {

			return new byte[4];
		}

		public static int getContentSize() {
			return 4;
		}
	}

	public class SMsgAVIoctrlGetSupportStreamResp {

		public SStreamDef mStreamDef[];
		public long number;
	}

	public static class SMsgAVIoctrlGetAudioOutFormatReq {

		public static byte[] parseContent() {
			return new byte[8];
		}
	}

	public class SMsgAVIoctrlGetAudioOutFormatResp {
		public int channel;
		public int format;
	}

	//IOTYPE_USER_IPCAM_GET_FLOWINFO_REQ              = 0x390
	public static class SMsgAVIoctrlGetFlowInfoReq {
		public int channel;
		public int collect_interval;

	}

	//IOTYPE_USER_IPCAM_GET_FLOWINFO_RESP            = 0x391
	public static class SMsgAVIoctrlGetFlowInfoResp {
		public int channel;
		public int collect_interval;

		public static byte[] parseContent(int channel, int collect_interval) {

			byte[] result = new byte[8];

			byte[] ch = Packet.intToByteArray_Little(channel);
			System.arraycopy(ch, 0, result, 0, 4);

			byte[] col = Packet.intToByteArray_Little(collect_interval);
			System.arraycopy(col, 0, result, 4, 4);

			return result;
		}
	}
	// IOTYPE_USER_IPCAM_CURRENT_FLOWINFO              = 0x392
	public static class SMsgAVIoctrlCurrentFlowInfo {
		public int channel;
		public int total_frame_count;
		public int lost_incomplete_frame_count;
		public int total_expected_frame_size;
		public int total_actual_frame_size;
		public int elapse_time_ms;

		public static byte[] parseContent(int channel, int total_frame_count,int lost_incomplete_frame_count,int total_expected_frame_size,int total_actual_frame_size,int elapse_time_ms) {

			byte[] result = new byte[32];

			byte[] ch = Packet.intToByteArray_Little(channel);
			System.arraycopy(ch, 0, result, 0, 4);

			byte[] total_frame = Packet.intToByteArray_Little(total_frame_count);
			System.arraycopy(total_frame, 0, result, 4, 4);

			byte[] lost_incomplete = Packet.intToByteArray_Little(lost_incomplete_frame_count);
			System.arraycopy(lost_incomplete, 0, result, 8, 4);

			byte[] total_expected = Packet.intToByteArray_Little(total_expected_frame_size);
			System.arraycopy(total_expected, 0, result, 12, 4);

			byte[] total_actual = Packet.intToByteArray_Little(total_actual_frame_size);
			System.arraycopy(total_actual, 0, result, 16, 4);

			byte[] elapse_time = Packet.intToByteArray_Little(elapse_time_ms);
			System.arraycopy(elapse_time, 0, result, 20, 4);

			return result;
		}
	}

	/* IOTYPE_USER_IPCAM_GET_TIMEZONE_REQ               = 0x3A0
	 * IOTYPE_USER_IPCAM_GET_TIMEZONE_RESP              = 0x3A1
	 * IOTYPE_USER_IPCAM_SET_TIMEZONE_REQ               = 0x3B0
	 * IOTYPE_USER_IPCAM_SET_TIMEZONE_RESP              = 0x3B1
	 */
	public static class SMsgAVIoctrlTimeZone{
		public int cbSize;
		public int nIsSupportTimeZone;
		public int nGMTDiff;
		public byte[] szTimeZoneString = new byte[256];

		public static byte[] parseContent() {

			return new byte[268];
		}

		public static byte[] parseContent(int cbSize, int nIsSupportTimeZone, int nGMTDiff, byte[] szTimeZoneString) {

			byte[] result = new byte[12+256];

			byte[] size = Packet.intToByteArray_Little(cbSize);
			System.arraycopy(size, 0, result, 0, 4);

			byte[] isSupportTimeZone = Packet.intToByteArray_Little(nIsSupportTimeZone);
			System.arraycopy(isSupportTimeZone, 0, result, 4, 4);

			byte[] GMTDiff = Packet.intToByteArray_Little(nGMTDiff);
			System.arraycopy(GMTDiff, 0, result, 8, 4);

			System.arraycopy(szTimeZoneString, 0, result, 12, szTimeZoneString.length);

			return result;
		}
	}

	public static class SMsgAVIoctrlReceiveFirstIFrame {
		int channel; // Camera Index
		int recordType;
		byte[] reserved = new byte[4];

		public static byte[] parseContent(int channel, int recordType) {

			byte[] result = new byte[12];
			byte[] ch = Packet.intToByteArray_Little(channel);
			byte[] type = Packet.intToByteArray_Little(recordType);

			System.arraycopy(ch, 0, result, 0, 4);
			System.arraycopy(type, 0, result, 4, 4);

			return result;
		}
	}
	public static class SMsgAVIoctrlSetSaveDropboxREQ{
		/*	short nLinked; // 0:no link/ 1:linked
			byte[] szLinkUDID = new byte[64]; // UDID for App
			//use mac address + device id
			byte[]  szAccessToken = new byte[32];// Oauth token
			byte[] szAccessTokenSecret = new byte[32];// Oauth token secret
			byte[] szAppKey = new byte[32] ; // App Key (reserved)
			byte[] szSecret = new byte[32]; // Secret (reserved)
			byte[]  reserved = new byte[4];*/

		public static byte[] parseContent(short nLinked,byte[] szLinkUDID,byte[] szAccessToken ,byte[] szAccessTokenSecret,
										  byte[] APP_KEY ,byte[] APP_SECRET){

			byte[] result = new byte[2+64+32+32+32+32+4];

			System.arraycopy(Packet.shortToByteArray_Little(nLinked), 0, result, 0, 2);
			System.arraycopy(szLinkUDID, 0, result, 2, szLinkUDID.length);
			System.arraycopy(szAccessToken, 0, result,66,szAccessToken.length);
			System.arraycopy(szAccessTokenSecret, 0, result,98,szAccessTokenSecret.length);
			System.arraycopy(APP_KEY, 0, result,130,APP_KEY.length);
			System.arraycopy(APP_SECRET, 0, result,164,APP_SECRET.length);
			return result;
		}
	}
	public static class SMsgAVIoctrlSetTimeReq{
		public int mod;//1,ntp 2 pc or manul
		public byte []server = new byte[32];

		public  int year;
		public  int month;
		public  int day;
		public  int hour;
		public  int minute;
		public  int second;

		public static byte[]TimeZone;//0~25:(GMT-12)~GMT~(GMT+12)

		public static byte[] parseContent(int mod,byte[] server,int timezone,Date time){
			byte[] result = new byte[4+32+6*4+4];

			byte[] ch = Packet.intToByteArray_Little(mod);
			System.arraycopy(ch, 0, result, 0, 4);

			System.arraycopy(server, 0, result, 4, 32);

			byte[] year = Packet.intToByteArray_Little(time.getYear());
			System.arraycopy(year, 0, result, 4+32+4*0, 4);
			byte[] month = Packet.intToByteArray_Little(time.getMonth());
			System.arraycopy(server, 0, result, 4+32+4*1, 4);
			byte[] day = Packet.intToByteArray_Little(time.getDay());
			System.arraycopy(server, 0, result, 4+32+4*2, 4);
			byte[] Hour = Packet.intToByteArray_Little(time.getHours());
			System.arraycopy(Hour, 0, result, 4+32+4*3, 4);
			byte[] Minutes = Packet.intToByteArray_Little(time.getMinutes());
			System.arraycopy(Minutes, 0, result, 4+32+4*4, 4);
			byte[] Seconds = Packet.intToByteArray_Little(time.getSeconds());
			System.arraycopy(Seconds, 0, result, 4+32+4*5, 4);

			TimeZone = Packet.intToByteArray_Little(timezone);
			System.arraycopy(TimeZone, 0, result, 4+32+4*6, 4);

			return result;
		}
	}
	public static class SMsgAVIoctrlScheduleTimeSet{
		int channel;
		int week;
		int start_m1;
		int start_h1;
		int start_m2;
		int start_h2;
		int stop_m1;
		int stop_h1;
		int stop_m2;
		int stop_h2;

		public static byte[] parseContent(int channel,int week,
										  int start_m1,int start_h1,
										  int start_m2,int start_h2,
										  int stop_m1,int stop_h1,
										  int stop_m2,int stop_h2) {

			byte[] result = new byte[24];

			byte[] ch = Packet.intToByteArray_Little(channel);
			System.arraycopy(ch, 0, result, 0, 4);

			byte[] we = Packet.intToByteArray_Little(week);
			System.arraycopy(we, 0, result, 4, 4);

			byte[] startTime1 = Packet.ScheduleTimeToByteArray_Little(start_m1, start_h1);
			System.arraycopy(startTime1, 0, result, 8, 4);

			byte[] stopTime1 = Packet.ScheduleTimeToByteArray_Little(stop_m1, stop_h1);
			System.arraycopy(stopTime1, 0, result, 12, 4);

			byte[] startTime2 = Packet.ScheduleTimeToByteArray_Little(start_m2, start_h2);
			System.arraycopy(startTime2, 0, result, 16, 4);

			byte[] stopTime2 = Packet.ScheduleTimeToByteArray_Little(stop_m2, stop_h2);
			System.arraycopy(stopTime2, 0, result, 20, 4);

			return result;
		}
	}


	public static class SMsgAVIoctrlGetTimeReq{
		public int mod;//1,ntp 2 pc or manul
		public byte []server = new byte[32];

		public  int year;
		public  int month;
		public  int day;
		public  int hour;
		public  int minute;
		public  int second;

		public static byte[]TimeZone;//0~25:(GMT-12)~GMT~(GMT+12)

		public static byte[] parseContent(){
			byte[] result = new byte[4+32+6*4+4];
			return result;
		}
	}

	public static class SMsgAVIoctrlSetRESETReq{
		public int channel;		// camera index
		public byte []reset_flag = new byte[1];
		public byte []reserved = new byte[3];

		public static byte[] parseContent(int channel, byte[]flag,byte[]reserv){
			byte[] result = new byte[4+1+3];

			byte[] ch = Packet.intToByteArray_Little(channel);
			System.arraycopy(ch, 0, result, 0, 4);

			System.arraycopy(flag, 0, result, 4, 1);
			System.arraycopy(reserv, 0, result, 4+1, 3);

			return result;
		}
	}

	public static class SMsgAVIoctrlSetRESETResp{
		public int channel;// camera index
		public byte []reset_flag = new byte[1];//1: success 0: fail
		public byte []reserved   = new byte[3];
	}


	public static class SMsgAVIoctrCloudDataSetReq{
		public int channel;
		byte cmd ;
		byte len ;
		byte data ;
		public static byte[] parseContent(int channel, byte cmd,byte length,byte data) {
			byte[] result = new byte[72];
			byte[] ch = Packet.intToByteArray_Little(channel);

			System.arraycopy(ch, 0, result, 0, 4);
			result[4] = cmd;
			result[5] = length;
			result[6] = data;
			return result;
		}
	}

	public static class SMsgAVIoctrlCloudDataGetReq{
		public int channel;
		public static byte[] parseContent(int channel, byte cmd) {
			byte[] result = new byte[8];
			byte[] ch = Packet.intToByteArray_Little(channel);

			System.arraycopy(ch, 0, result, 0, 4);
			result[4] = cmd;
			return result;
		}
	}

	public static class SMsgAVIoctrlGetSDRECORDReq{
		public int channel;
		public static byte[] parseContent(int channel,byte reserved){
			byte[] result = new byte[8];
			byte[] ch = Packet.intToByteArray_Little(channel);
			System.arraycopy(ch, 0, result, 0, 4);
			result[4] = reserved;
			return result;
		}
	}



	public static class SMsgAVIoctrlLearningModeReq{

		public byte[] devID = new byte[4];
		public byte devCmd = 0;
		public byte cmdMode = 0;
		//public byte[] reserved = new byte[5];
		public byte verifySum  = -1;
		/**
		 *  进入学习模式
		 *  遥控模块进入学习码模式信息包数据格式
		 *
		 * @param devID 设备ID（门警设备之类） 共预留 4byte 目前暂用 3byte 最高字节为零
		 * @param devCmd 设备命令  这里用（byte 0x01）
		 * @param cmdMode 模式选择  这里用（0x02:探测器学习模式）( 0x01:遥控器学习模式    0x02:探测器学习模式    0x03:控制器学习模式   )
		 *
		 * @return result（byte[]）
		 *
		 */
		public static byte[] parseContent(int devID, byte devCmd,byte cmdMode){
			byte[] result = new byte[12];
			if(devID != 0){
				byte[] id = Packet.intToByteArray_Little(devID);
				System.arraycopy(id, 0, result, 0, id.length);
			}
			result[4] = devCmd;
			result[5] = cmdMode;
			result[11] = Packet.xORVerifySum(result);
			return result;
		}
	}



	public static class SMsgAVIoctrlQuitLearningModeReq{

		public byte[] devID = new byte[4];
		public byte devCmd = 0;

		public byte verifySum  = -1;
		/**
		 *  退出学习模式
		 *  遥控模块退出学习码模式信息包数据格式
		 *
		 * @param devID 设备ID（门警设备之类） 共预留 4byte 目前暂用 3byte 最高字节为零
		 * @param devCmd 设备命令  这里用（byte 0x02）
		 *
		 *
		 * @return result（byte[]）
		 *
		 */
		public static byte[] parseContent(int devID, byte devCmd){
			byte[] result = new byte[12];

			if(devID != 0){
				byte[] id = Packet.intToByteArray_Little(devID);
				System.arraycopy(id, 0, result, 0, id.length);
			}
			result[4] = devCmd;

			result[11] = Packet.xORVerifySum(result);
			return result;
		}
	}


	public static class SMsgAVIoctrlLearningResultResp{

		public byte[] devID = new byte[4];
		public byte devCmd = 0;
		public byte learnResult = 0;
		public int learnedDevID = 0;
		//public byte[] reserved = new byte[2];
		public byte verifySum  = -1;


		/**
		 *
		 * 	学到的结果
		 *  遥控模学习码模式执行结果报告信息包数据格式
		 *
		 * @param devID 设备ID（门警设备之类） 共预留 4byte 目前暂用 3byte 最高字节为零
		 * @param devCmd 设备命令  这里用（byte 0x03）
		 * @param learnedResult （学习结果:0x00 ：学习失败  0xFF：学习成功）
		 * @param learnedDevID （学到的ID:3byte）
		 *
		 *
		 *
		 */
		public static byte[] parseContent(int devID, byte devCmd, byte learnedResult,int learnedDevID){
			byte[] result = new byte[12];

			if(devID != 0){
				byte[] id = Packet.intToByteArray_Little(devID);
				System.arraycopy(id, 0, result, 0, id.length);
			}
			result[4] = devCmd;
			result[5] = learnedResult;
			System.arraycopy(Packet.intToByte(learnedDevID, 3), 0, result, 6, 3);
			result[11] = Packet.xORVerifySum(result);
			return result;
		}
	}
	public static class SMsgAVIoctrlClearModeReq{

		public byte[] devID = new byte[4];
		public byte devCmd = 0;
		public byte verifySum  = -1;


		/**
		 *  清码模式
		 *  遥控模块进清码模式信息包数据格式
		 *
		 *
		 *
		 *
		 * @param devID 设备ID（门警设备之类） 共预留 4byte 目前暂用 3byte 最高字节为零
		 * @param devCmd 设备命令  这里用（byte 0x04）
		 *
		 *
		 * @return result（byte[]）
		 *
		 */
		public static byte[] parseContent(int devID, byte devCmd){
			byte[] result = new byte[12];

			if(devID != 0){
				byte[] id = Packet.intToByteArray_Little(devID);
				System.arraycopy(id, 0, result, 0, id.length);
			}
			result[4] = devCmd;

			result[11] = Packet.xORVerifySum(result);
			return result;
		}


	}

	public static class SMsgAVIoctrlCmdReq{

		public byte[] devID = new byte[4];
		public byte devCmd = 0;
		public byte defenseAreaType = 0;
		public byte armingSwitchType = 0;
		//public byte[] reserved = new byte[2];
		public int timingSet = 0;
		public byte verifySum  = -1;


		/**
		 *  传感器控制指令信息包数据格式
		 *
		 * @param devID 设备ID（门警设备之类） 共预留 4byte 目前暂用 3byte 最高字节为零
		 * @param devCmd 设备命令  这里用（byte 0x05）
		 * @param defenseAreaType   断电数据依然保存，存在 EEPROM 中
		 *        低 4 位 D3D2D1D0   防区类型 (0x01:火警防区; 0x02:煤气泄漏防区; 0x03:紧急防区; 0x04:防盗防区 ;
		 *        0x05:周界防区  0x06:医疗防区   0x07:劫警防区    0x08:出入口防区  )
		 *        高 4 位 D7D6D5D4  传感器类型   (0x01:门磁; 0x02:红外探头; 0x03:烟感; 0x04:气感 ;
		 *        0x05:玻璃破碎  0x06:预留  0x07:预留    0x0F:遥控器  )
		 * @param armingSwitchType  布防开关 断电数据依然保存，存在 EEPROM 中低 4 位 D3D2D1D0
		 *                          0x00:布防开  0x0F:布防关
		 *                          高 4 位 D7D6D5D4
		 *                          D7D6=00:定时开开启
		 *                          D7D6=11:定时开关闭
		 *                          D5D4=00:定时关开启
		 *                         D5D4=11:定时关关闭
		 * @param timeStartHour 定时设置    (BYTE4:开机时(h) BYTE3:开机分(m) BYTE2:关机时(h) BYTE1:关机分(m))
		 * @return result（byte[]）
		 *
		 */
		public static byte[] parseContent(int devID, byte devCmd,byte defenseAreaType,byte armingSwitchType,
										  byte timeStartHour,byte timeStartMinute,byte timeEndHour,byte timeEndMinute){
			byte[] result = new byte[12];

			if(devID != 0){
				byte[] id = Packet.intToByteArray_Little(devID);
				System.arraycopy(id, 0, result, 0, id.length);
			}
			result[4] = devCmd;
			result[5] = defenseAreaType;
			result[6] = armingSwitchType;
			result[7] = timeStartHour;
			result[8] = timeStartMinute;
			result[9] = timeEndHour;
			result[10] = timeEndMinute;

			result[11] = Packet.xORVerifySum(result);
			return result;
		}
	}



	public static class SMsgAVIoctrlSetUpTimeReq{
		public byte[] devID = new byte[4];
		public byte devCmd = 0;
		public byte hour = 0;
		public byte minute = 0;
		public byte second = 0;

		public byte verifySum  = -1;


		/**
		 *  对时指令
		 * 智能家居    对时指令包数据格式
		 *
		 *
		 * @param devID 设备ID（门警设备之类） 共预留 4byte 目前暂用 3byte 最高字节为零
		 * @param devCmd 设备命令  这里用（byte 0x06）
		 * @param hour
		 * @param minute
		 * @param second
		 * @return result（byte[]）
		 *
		 */
		public static byte[] parseContent(int devID, byte devCmd,byte hour,byte minute ,byte second){
			byte[] result = new byte[12];

			if(devID != 0){
				byte[] id = Packet.intToByteArray_Little(devID);
				System.arraycopy(id, 0, result, 0, id.length);
			}
			result[4] = devCmd;
			result[5] = hour;
			result[6] = minute;
			result[7] = second;
		/*byte[] timingSets = Packet.intToByteArray_Little(timingSet);
		System.arraycopy(timingSets, 0, result, 6, timingSets.length);*/
			result[11] = Packet.xORVerifySum(result);
			return result;
		}
	}


	public static class SMsgAVIoctrlSensorAlarmResp{
		public byte[] devID = new byte[4];
		public byte devCmd = 0;
		public byte armingDisarmArea = 0;
		public byte verifySum  = -1;


		/**
		 *  传感器传感器报警信息包数据格式
		 *
		 * @param devID 设备ID（门警设备之类） 共预留 4byte 目前暂用 3byte 最高字节为零
		 * @param devCmd 设备命令  这里用（byte 0x07）
		 * @param armingDisarmArea 布撤防区  A：布防区B: 撤防区C:开报警音D::报警音
		 * @return result（byte[]）
		 *
		 */
		public static byte[] parseContent(int devID, byte devCmd,byte armingDisarmArea){
			byte[] result = new byte[12];

			if(devID != 0){
				byte[] id = Packet.intToByteArray_Little(devID);
				System.arraycopy(id, 0, result, 0, id.length);
			}
			result[4] = devCmd;
			result[5] = armingDisarmArea;


			result[11] = Packet.xORVerifySum(result);
			return result;
		}
	}


	public static class SMsgAVIoctrlSensorStatusResp{
		public byte[] devID = new byte[4];
		public byte devCmd = 0;
		public byte  sensorStatus = 0;
		public byte  defenseAreaType = 0;
		public short armType = 0;
		public byte verifySum  = -1;


		/**
		 *  传感器状态报告信息包数据格式
		 *
		 * @param devID 设备ID（门警设备之类） 共预留 4byte 目前暂用 3byte 最高字节为零
		 * @param devCmd 设备命令  这里用（byte 0x08）
		 * @param sensorStatus 0x01:表示门磁报警；0x02:表示门磁电池电压低；0x03:表示门磁遥控器撤防0x04:表示门磁遥控器布防
		 *        0x05:表示红外探头报警  0x06:表示外探头电池电压低  0x07:表示外探头控器撤防 0x08:表示外探头遥控器布防 0x09:表示烟感报警
		 *        0x0A:表示烟感电池电压低 0X0B:表示烟感遥控器撤防  0x0c:表示烟感遥控器布防 0x0d:表示气感报警  0x0e:表示气感电池电压低
		 *        0x0f:表示气感遥控器撤防  0x10:表示气感遥控器布防  0x11:表示玻璃破碎磁报警 0x12:表示玻璃破碎电池电压低  0x13:表示玻璃破碎遥控器撤防
		 *        0x14:表示玻璃破碎遥控器布防  0x15:表示开锁指令  0x16:表示关锁指令  0x17:表示门锁状态报警  0x18:表示门锁状态电池电压低
		 *        0x19:表示门锁状态遥控器撤防  0x1a:表示门锁状态遥控器布防   0X1B:无线警号撤防  0X1C:无线警号布防  0X1D:无线警号撤防
		 *        0X1F:无线警号低电报警  0xF0:无线门铃报警
		 *
		 * @param defenseAreaType  防区类型       断电数据依然保存，存在 EEPROM中
		 *       低 4 位 D3D2D1D0(0x01:火警防区; 0x02:煤气泄漏防区; 0x03:紧急防区; 0x04:防盗防区 ;
		 *       0x05:周界防区  0x06:医疗防区   0x07:劫警防区    0x08:出入口防区  )
		 *
		 * @return result（byte[]）
		 *
		 */
		public static byte[] parseContent(int devID, byte devCmd,byte sensorStatus ,byte defenseAreaType){
			byte[] result = new byte[12];

			if(devID != 0){
				byte[] id = Packet.intToByteArray_Little(devID);
				System.arraycopy(id, 0, result, 0, id.length);
			}
			result[4] = devCmd;
			result[5] = sensorStatus;
			result[6] = defenseAreaType;
		/*byte[] timingSets = Packet.intToByteArray_Little(timingSet);
		System.arraycopy(timingSets, 0, result, 6, timingSets.length);*/
			result[11] = Packet.xORVerifySum(result);
			return result;
		}
	}
	public static class SMsgAVIoctrlLogInfoReq{
		public byte[] devID = new byte[4];
		public byte devCmd = 0;



		/**
		 *  上位机发送上位机清求下位机上传传感器记录命令
		 *
		 * @param devID 设备ID（门警设备之类） 共预留 4byte 目前暂用 3byte 最高字节为零
		 * @param devCmd 设备命令  这里用（byte 0x09）
		 *
		 * @return result（byte[]）
		 *
		 */
		public static byte[] parseContent(int devID, byte devCmd){
			byte[] result = new byte[12];

			if(devID != 0){
				byte[] id = Packet.intToByteArray_Little(devID);
				System.arraycopy(id, 0, result, 0, id.length);
			}
			result[4] = devCmd;

			/*byte[] timingSets = Packet.intToByteArray_Little(timingSet);
			System.arraycopy(timingSets, 0, result, 6, timingSets.length);*/
			result[11] = Packet.xORVerifySum(result);
			return result;
		}
	}


	public static class SMsgAVIoctrlUploadLogInfoReq{
		public byte[] devID = new byte[4];
		public byte devCmd = 0;
		public byte recordNum = 0;
		public byte logInfoAndDefenseAreaType = 0;
		//public byte sensorStatus = 0;
		//public int  arlmTime = 0;

		/**   下位机会复上传传感器记录命令
		 *
		 *
		 * @param devID 设备ID（门警设备之类） 共预留 4byte 目前暂用 3byte 最高字节为零
		 * @param devCmd 设备命令  这里用（byte 0x0A）
		 *
		 * @return result（byte[]）
		 *
		 */
		public static byte[] parseContent(int devID, byte devCmd,byte recordNum, byte logInfoAndDefenseAreaType){
			byte[] result = new byte[12];

			if(devID != 0){
				byte[] id = Packet.intToByteArray_Little(devID);
				System.arraycopy(id, 0, result, 0, id.length);
			}
			result[4] = devCmd;
			result[5] = recordNum;
			result[6] = logInfoAndDefenseAreaType;

			result[11] = Packet.xORVerifySum(result);
			return result;
		}
	}


	public static class SMsgAVIoctrlAirConditioningSetReq{
		public byte[] devID = new byte[4];
		public byte devCmd = 0;

		public byte modelSwith = 0;
		public long airConditioningCtrCmd = 0;

		/**   空调控制指令数据字段格式
		 *
		 *
		 * @param devID 设备ID（门警设备之类） 共预留 4byte 目前暂用 3byte 最高字节为零
		 * @param devCmd 设备命令  这里用（byte 0x0B）
		 *
		 * @return result（byte[]）
		 *
		 */
		public static byte[] parseContent(int devID, byte devCmd, byte modelSwith,long airConditioningCtrCmd){
			byte[] result = new byte[12];

			if(devID != 0){
				byte[] id = Packet.intToByteArray_Little(devID);
				System.arraycopy(id, 0, result, 0, id.length);
			}
			result[4] = devCmd;
			result[5] = modelSwith;
			System.arraycopy(Packet.longToByte(airConditioningCtrCmd,5), 0, result, 6, 5);

			result[11] = Packet.xORVerifySum(result);
			return result;
		}
	}
	public static class  SMsgAVIoctrlTransparentTransmissionOfData{
		public byte[] devID = new byte[4];
		public byte devCmd = 0;

		public long data =0;

		/**   数据透传指令数据字段格式
		 *
		 *
		 * @param devID 设备ID（门警设备之类） 共预留 4byte 目前暂用 3byte 最高字节为零
		 * @param devCmd 设备命令  这里用（byte 0x0C）
		 *
		 * @return result（byte[]）
		 *
		 */
		public static byte[] parseContent(int devID, byte devCmd,long data){
			byte[] result = new byte[12];

			if(devID != 0){
				byte[] id = Packet.intToByteArray_Little(devID);
				System.arraycopy(id, 0, result, 0, id.length);
			}
			result[4] = devCmd;

			System.arraycopy(Packet.longToByte(data,6), 0, result, 6, 6);
		/*	byte[] timingSets = Packet.intToByteArray_Little(timingSet);
			System.arraycopy(timingSets, 0, result, 6, timingSets.length);*/
			result[11] = Packet.xORVerifySum(result);
			return result;
		}
	}
	public static class  SMsgAVIoctrlSensorStatusInquiryReq{
		public byte[] devID = new byte[4];
		public byte devCmd = 0;

		public long data =0;

		/**   传感器状态查询指令
		 *
		 *
		 * @param devID 设备ID（门警设备之类） 共预留 4byte 目前暂用 3byte 最高字节为零
		 * @param devCmd 设备命令  这里用（byte 0x0C）
		 *
		 * @return result（byte[]）
		 *
		 */
		public static byte[] parseContent(int devID, byte devCmd){
			byte[] result = new byte[12];

			if(devID != 0){
				byte[] id = Packet.intToByteArray_Little(devID);
				System.arraycopy(id, 0, result, 0, id.length);
			}
			result[4] = devCmd;

			result[11] = Packet.xORVerifySum(result);
			return result;
		}
	}
	public static class  SMsgAVIoctrlUARTConfigurationReq{
		public byte[] devID = new byte[4];
		public byte devCmd = 0;

		public byte UART =0;

		/** 波特率参数配置数据字段格式
		 *
		 *
		 * @param devID 设备ID（门警设备之类） 共预留 4byte 目前暂用 3byte 最高字节为零
		 * @param devCmd 设备命令  这里用（byte 0xD0）
		 * @param :00波特率 9600    01:波特率 19200 02:波特率 38400    固定为 8 位数据，1 位停止位，无效验位
		 *
		 *
		 * @return result（byte[]）
		 *
		 */
		public static byte[] parseContent(int devID, byte devCmd,byte UART){
			byte[] result = new byte[12];

			if(devID != 0){
				byte[] id = Packet.intToByteArray_Little(devID);
				System.arraycopy(id, 0, result, 0, id.length);
			}
			result[4] = devCmd;
			result[5] = UART;
			result[11] = Packet.xORVerifySum(result);
			return result;
		}
	}

	public static class  SMsgAVIoctrlCommunicationTestReq{
		public byte[] devID = new byte[4];
		public byte devCmd = 0;



		/** 通讯测试指令数据字段格式
		 *  上位机发送通讯测试指令后，接收遥控模块 UART 返回 12 个 0XAA，则表示通讯正常
		 *
		 * @param devID 设备ID（门警设备之类） 共预留 4byte 目前暂用 3byte 最高字节为零
		 * @param devCmd 设备命令  这里用（byte 0x00）
		 *
		 *
		 *
		 * @return result（byte[]）
		 *
		 */
		public static byte[] parseContent(int devID, byte devCmd){
			byte[] result = new byte[12];

			if(devID != 0){
				byte[] id = Packet.intToByteArray_Little(devID);
				System.arraycopy(id, 0, result, 0, id.length);
			}
			result[4] = devCmd;

			result[11] = Packet.xORVerifySum(result);
			return result;
		}
	}
}
