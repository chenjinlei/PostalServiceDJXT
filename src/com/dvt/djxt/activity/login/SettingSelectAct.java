package com.dvt.djxt.activity.login;

import com.dvt.djxt.common.BaseActivity;
import com.dvt.djxt.util.LogUtils;
import com.example.postalservicedjxt.R;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class SettingSelectAct extends BaseActivity implements OnClickListener {

	private TextView ipSettingTextView;
	private Activity activity;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.activity_settingsel);
		this.setTitle("设置");
		activity = this;
		ipSettingTextView = (TextView) this.findViewById(R.id.settingsel_tv_ip);
		ipSettingTextView.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {

		LogUtils.d("login", "onclick");
		switch (v.getId()) {
		case R.id.settingsel_tv_ip:
			// 跳转到设置IP的页面
			IPSettingActivity.actionStart(activity);
			break;
		default:
			break;
		}
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {

		if (keyCode == KeyEvent.KEYCODE_1) {
			IPSettingActivity.actionStart(activity);
			finish();
		}
		return super.onKeyDown(keyCode, event);
	}

	public static void actionStart(Context context) {
		Intent intent = new Intent(context, SettingSelectAct.class);
		context.startActivity(intent);

	}

}
