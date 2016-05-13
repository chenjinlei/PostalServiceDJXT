package com.dvt.djxt.activity.login;

import com.dvt.djxt.activity.sbpd.SbpdMainAct;
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
import android.widget.Toast;

public class MenuActivity extends BaseActivity implements OnClickListener {

	private Button sbpdBtn, sbdjBtn;
	private Activity activity;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.setContentView(R.layout.activity_menu);
		
		activity = this;

		sbpdBtn = (Button) this.findViewById(R.id.menu_btn_sbpd);
		sbdjBtn = (Button) this.findViewById(R.id.menu_btn_sbdj);

		sbpdBtn.setOnClickListener(this);
		sbdjBtn.setOnClickListener(this);

	}

	public static void actionStart(Context context) {
		Intent intent = new Intent(context, MenuActivity.class);
		context.startActivity(intent);
	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.menu_btn_sbpd:
			Toast.makeText(this, "要跳转到设备盘点页面了哦！", Toast.LENGTH_SHORT).show();
			SbpdMainAct.actionStart(activity);
			break;
		case R.id.menu_btn_sbdj:
			Toast.makeText(this, "要跳转到设备点检页面了哦！", Toast.LENGTH_SHORT).show();
			break;
		default:
			break;
		}

	}

}
