package com.dvt.djxt.helper;


import com.example.postalservicedjxt.R;

import android.app.Service;
import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.SoundPool;
import android.os.Vibrator;

public class SoundHelper {
	private SoundPool soundpool = null;
	private int errorsoundid, scansoundid,dwchangeid;
	public final static int SOUND_TYPE_SUCCESS = 0;
	public final static int SOUND_TYPE_ERR = 1;
	public final static int SOUND_TYPE_DWCHANGE = 2;
	private static SoundHelper mySound = null;
	private Context context;

	public static SoundHelper getMySound(Context context) {
		if (mySound == null) {
			mySound = new SoundHelper(context);
		}
		return mySound;
	}

	public SoundHelper(Context context) {
		this.context = context;
		if (soundpool == null) {
			soundpool = new SoundPool(1, AudioManager.STREAM_NOTIFICATION, 100);
			errorsoundid = soundpool.load(context, R.raw.error, 1);
			scansoundid = soundpool.load(context, R.raw.success, 1);
			dwchangeid=soundpool.load(context, R.raw.dwchange, 1);
		}
	}

	public void playSound(int soundType) {
		System.out.println("进入playSound：" + soundType);
		float streamVolume = 0.8f;
		int soundResId = scansoundid;
		switch (soundType) {
		case SOUND_TYPE_SUCCESS:
			soundpool.play(soundResId, streamVolume, streamVolume, 1, 0, 1f);
			break;
		case SOUND_TYPE_ERR:
			soundpool.play(errorsoundid, streamVolume, streamVolume, 1, 0, 1f);
			break;
			
		case SOUND_TYPE_DWCHANGE:
			soundpool.play(dwchangeid, streamVolume, streamVolume, 1, 0, 1f);
			break;
		default:
			break;
		}
	}


	public void playMusic(String name) {
		MediaPlayer mediaPlayer = MediaPlayer.create(
				context,
				context.getResources().getIdentifier(name, "raw",
						context.getPackageName()));
		try {
			mediaPlayer.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
		mediaPlayer.setOnCompletionListener(new OnCompletionListener() {
			public void onCompletion(MediaPlayer mp) {
				mp.release();
			}
		});
	}

	public void Vibrate(long milliseconds) {
		try {
			Vibrator vib = (Vibrator) context
					.getSystemService(Service.VIBRATOR_SERVICE);
			vib.vibrate(milliseconds);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void scanSound() {
		if (soundpool == null) {
		}
		if (scansoundid == 0) {
		}
		soundpool.play(scansoundid, 1, 1, 0, 0, 1);
	}

	public void scansoundTrueOrFalse(boolean isTrue) {
		if (isTrue)
			SoundHelper.getMySound(context).playSound(SOUND_TYPE_SUCCESS);
		else {
			SoundHelper.getMySound(context).playSound(SOUND_TYPE_ERR);
			SoundHelper.getMySound(context).Vibrate(500);
		}
	}

	public static void setMaxVoice(Context context) {
		AudioManager audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
		int max = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
		audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, max, AudioManager.FX_FOCUS_NAVIGATION_UP);
	}
}
