package com.dvt.djxt.util;

import com.dvt.djxt.common.ScanBaseActivity;
import com.dvt.djxt.helper.SoundHelper;

import android.app.Service;
import android.content.Context;
import android.os.Vibrator;

public class ScanBaseUtils {

	public static void unlockTriggler() {
		ScanBaseActivity.mScanManager.unlockTriggler();
		try {
			Thread.sleep(50);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		ScanBaseActivity.mScanManager.unlockTriggler();
	}

	public static void lockTriggler() {
		ScanBaseActivity.mScanManager.lockTriggler();
	}

	private static void Vibrate(Context context) {
		Vibrator vib = (Vibrator) context.getSystemService(Service.VIBRATOR_SERVICE);
		vib.vibrate(500);
	}
	
	public static void getRing(Context context) {
		SoundHelper.getMySound(context).playSound(SoundHelper.SOUND_TYPE_SUCCESS);
		Vibrate(context);
	}

	public static void getMessageBoxRing(Context context) {
		SoundHelper.getMySound(context).playSound(SoundHelper.SOUND_TYPE_ERR);
	}

	public static void getDwchangeRing(Context context) {
		SoundHelper.getMySound(context).playSound(SoundHelper.SOUND_TYPE_DWCHANGE);
	}
}
