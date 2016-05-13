package com.dvt.djxt.activity.login;

import com.dvt.djxt.common.BaseActivity;
import com.dvt.djxt.util.ContextUtils;
import com.example.postalservicedjxt.R;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class IPSettingActivity extends BaseActivity implements OnClickListener {

	private EditText hostEditText, portEditText;
	private Button okBtn, escBtn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		this.setTitle("设置IP地址");
		this.setContentView(R.layout.activity_ipsetting);
		hostEditText = (EditText) this.findViewById(R.id.ipsetting_et_host);
		portEditText = (EditText) this.findViewById(R.id.ipsetting_et_port);

		/**
		 * test data below
		 */
		hostEditText.setText("192.168.60.108");
		portEditText.setText("8888");

		okBtn = (Button) this.findViewById(R.id.ipsetting_btn_OK);
		escBtn = (Button) this.findViewById(R.id.ipsetting_btn_ESC);

		okBtn.setOnClickListener(this);
		escBtn.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.ipsetting_btn_OK:
			String host = hostEditText.getText().toString().trim();
			int port = Integer.parseInt(portEditText.getText().toString().trim());
			checkIP(host, port);
			break;
		case R.id.ipsetting_btn_ESC:
			finish();
			break;
		default:
			break;
		}
	}

	@Override
	public boolean dispatchKeyEvent(KeyEvent event) {
		if (event.getKeyCode() == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN) {
			try {
				String host = hostEditText.getText().toString();
				int port = Integer.parseInt(portEditText.getText().toString().trim());
				if (host.length() > 0) {
					portEditText.requestFocus();
					portEditText.setCursorVisible(true);
					portEditText.setFocusable(true);
					portEditText.setFocusableInTouchMode(true);
					checkIP(host, port);
				} else {
					Toast.makeText(ContextUtils.getContext(), "请输入正确的IP！", Toast.LENGTH_SHORT).show();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return true;

		}

		if (event.getKeyCode() == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_UP) {
			return true;
		}
		return super.dispatchKeyEvent(event);

	}

	private void checkIP(String host, int port) {

		String regex = "^(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|[1-9])\\." + "(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\."
				+ "(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\." + "(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)$";
		if (host.matches(regex)) {
			// 保存至数据库
			finish();
		} else {
			Toast.makeText(ContextUtils.getContext(), "请输入正确的IP！", Toast.LENGTH_SHORT).show();
			hostEditText.requestFocus();
			hostEditText.setCursorVisible(true);
			hostEditText.setText("");

		}

	}

	public static void actionStart(Context context) {
		Intent intent = new Intent(context, IPSettingActivity.class);
		context.startActivity(intent);
	}

}
