package com.dvt.djxt.common;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.device.ScanManager;
import android.os.Bundle;
import android.view.KeyEvent;

/**
 * @ClassName ScanBaseActivity
 * @Description TODO(扫描头)
 * @author 高健峰
 * @date 2014年5月6日 下午5:48:50
 */

public abstract class ScanBaseActivity extends BaseActivity {
	private final static String SCAN_ACTION = "urovo.rcv.message";
	public static boolean isScan = true;// 出光
	public static boolean cgFlag = true;
	public static ScanManager mScanManager = new ScanManager();
	private static boolean scanPowerState;
	private static boolean lockTrigglerState;
	public BroadcastReceiver mScanReceiver = new BroadcastReceiver() {

		@Override
		public void onReceive(Context context, Intent intent) {

			byte[] barocode = intent.getByteArrayExtra("barocode");
			int barocodelen = intent.getIntExtra("length", 0);
			String barcodeStr = new String(barocode, 0, barocodelen);
			if (isScan)
				onScaned(barcodeStr);

		}

	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		initScan();
		// setContentView(R.layout.main);
	}

	/**
	 * deal the batcode in this method
	 * 
	 * @param barcode
	 */
	protected abstract void onScaned(String barcode);

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		IntentFilter filter = new IntentFilter();
		filter.addAction(SCAN_ACTION);
		registerReceiver(mScanReceiver, filter);
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		// if (mScanManager != null) {
		// mScanManager.stopDecode();
		// }
		// System.out.println("前:" + scanPowerState);
		// if (mScanManager != null && !scanPowerState) {
		// mScanManager.closeScanner();
		// }
		// System.out.println("后:" + scanPowerState);
		unregisterReceiver(mScanReceiver);
	}

	public static void initScan() {
		System.out.println("initScan");
		// mScanManager = new ScanManager();
		scanPowerState = mScanManager.getScannerState();
		if (!scanPowerState) {
			mScanManager.openScanner();
		}
		lockTrigglerState = mScanManager.getTriggerLockState();
		if (lockTrigglerState) {
			mScanManager.unlockTriggler();
		}
		mScanManager.switchOutputMode(0);// 调动程序的动作
		isScan = true;
	}

	public boolean onKeyDown(int keyCode, KeyEvent event) {
		switch (keyCode) {
		case 112:
			return true;
		case 114:
			return true;
		case 5:
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
}
