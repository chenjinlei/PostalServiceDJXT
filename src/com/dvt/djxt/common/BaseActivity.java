package com.dvt.djxt.common;

import com.dvt.djxt.util.ActivityCollectorUtils;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;

public class BaseActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		ActivityCollectorUtils.addActivity(this);
	}

	@Override
	protected void onDestroy() {
		
		super.onDestroy();
		ActivityCollectorUtils.removeActivity(this);
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
