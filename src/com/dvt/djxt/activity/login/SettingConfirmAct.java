package com.dvt.djxt.activity.login;

import com.dvt.djxt.common.BaseActivity;
import com.example.postalservicedjxt.R;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

public class SettingConfirmAct extends BaseActivity implements OnClickListener {

	private EditText passwordEditText;
	private Button confirmButton, resetButton;
	private Activity activity;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.setContentView(R.layout.activity_common_confirm);

		activity = this;

		passwordEditText = (EditText) this.findViewById(R.id.cnfrm_et_pwd);
		confirmButton = (Button) this.findViewById(R.id.cnfrm_btn_comfirm);
		resetButton = (Button) this.findViewById(R.id.cnfrm_btn_reset);

		confirmButton.setOnClickListener(this);
		resetButton.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.cnfrm_btn_comfirm:
			String password = passwordEditText.getText().toString();
			if (password.equals("123456")) {
				// 跳转到设置列表页面
				SettingSelectAct.actionStart(activity);
			}
			break;
		case R.id.cnfrm_btn_reset:
			break;
		default:
			break;
		}
	}

	public static void actionStart(Context context) {

		Intent intent = new Intent(context, SettingConfirmAct.class);
		context.startActivity(intent);

	}

}
