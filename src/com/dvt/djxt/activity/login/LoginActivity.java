package com.dvt.djxt.activity.login;

import com.dvt.djxt.common.BaseActivity;
import com.dvt.djxt.common.ScanBaseActivity;
import com.dvt.djxt.helper.CrashHandler;
import com.dvt.djxt.helper.SoundHelper;
import com.dvt.djxt.util.PubFunc;
import com.example.postalservicedjxt.R;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.device.DeviceManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnKeyListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends BaseActivity implements OnKeyListener {

	private EditText nameEditText, passwordEditText;
	private Button loginBtn, clearBtn, settingBtn;
	private Activity activity;

	private static Dialog dialog;
	private static MyHandler handler;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_login);

		nameEditText = (EditText) this.findViewById(R.id.lgn_et_name);
		passwordEditText = (EditText) this.findViewById(R.id.lgn_et_password);

		loginBtn = (Button) this.findViewById(R.id.lgn_btn_login);
		clearBtn = (Button) this.findViewById(R.id.lgn_btn_clear);
		settingBtn = (Button) this.findViewById(R.id.lgn_btn_setting);

		activity = this;
		handler = new MyHandler();

		DeviceManager deviceManager = new DeviceManager();
		deviceManager.enableHomeKey(false);

		SoundHelper.setMaxVoice(activity);
		SoundHelper.getMySound(activity);

		CrashHandler crashHandler = CrashHandler.getInstance();
		crashHandler.init(getApplicationContext());

		ScanBaseActivity.initScan();

		loginBtn.setOnKeyListener(this);
		clearBtn.setOnKeyListener(this);
		settingBtn.setOnKeyListener(this);

	}

	private void LoginProcess() {
		String name = nameEditText.getText().toString();
		String password = passwordEditText.getText().toString();
		if (name.length() == 0 || password.length() == 0) {
			Toast.makeText(activity, "用户名或密码不能为空！", Toast.LENGTH_SHORT).show();
			return;
		} else {
			// 调用WebService服务校验用户名密码；有个线程，在线程处理逻辑中跳转 TODO
			// Toast.makeText(activity, "要成功登录了哦", Toast.LENGTH_SHORT).show();
			dialog = PubFunc.createLoadingDialog(activity, "正在登录");
			dialog.show();
			new MyThread().start();

		}

	}

	@SuppressLint("HandlerLeak")
	class MyHandler extends Handler {
		@Override
		public void handleMessage(Message msg) {
			if (msg.what == 1) {
				dialog.dismiss();
				MenuActivity.actionStart(activity);
			}
		}
	}

	class MyThread extends Thread implements Runnable {

		@Override
		public void run() {
			Message msg = handler.obtainMessage();
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			msg.what = 1;
			handler.sendMessage(msg);

		}

	}

	@Override
	public boolean onKey(View v, int keyCode, KeyEvent event) {

		switch (v.getId()) {
		case R.id.lgn_et_name:
			if (event.getAction() == KeyEvent.ACTION_UP) {
				passwordEditText.requestFocus();
				passwordEditText.setCursorVisible(true);
				passwordEditText.setFocusable(true);
				passwordEditText.setFocusableInTouchMode(true);
				return true;
			}
			break;
		case R.id.lgn_et_password:
			if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN) {
				LoginProcess();
			}
			break;
		default:
			break;
		}
		return false;
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == 131 && event.getAction() == KeyEvent.ACTION_DOWN) {
			LoginProcess();
		}
		if (keyCode == 132 && event.getAction() == KeyEvent.ACTION_DOWN) {
			nameEditText.setText("");
			passwordEditText.setText("");
			nameEditText.requestFocus();
			nameEditText.setCursorVisible(true);
		}
		if (keyCode == 133 && event.getAction() == KeyEvent.ACTION_DOWN) {
			SettingConfirmAct.actionStart(activity);
		}

		return super.onKeyDown(keyCode, event);

	}

	/**
	 * 点击事件的处理
	 * 
	 * @param view
	 */
	public void OnClick(View view) {
		switch (view.getId()) {
		case R.id.lgn_btn_login:// 登录
			LoginProcess();
			break;
		case R.id.lgn_btn_clear:// 清除
			this.nameEditText.setText("");
			this.passwordEditText.setText("");
			this.nameEditText.requestFocus();
			this.nameEditText.setCursorVisible(true);
			break;
		case R.id.lgn_btn_setting:// 设置
			// passwordReset();
			// Intent id = new Intent(LoginActivity.this,
			// SettingSelActivity.class);
			// startActivity(id);
			SettingConfirmAct.actionStart(activity);
			break;
		}
	}
}
