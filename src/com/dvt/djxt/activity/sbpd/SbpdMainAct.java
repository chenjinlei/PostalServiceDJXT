package com.dvt.djxt.activity.sbpd;

import com.dvt.djxt.common.BaseActivity;
import com.example.postalservicedjxt.R;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class SbpdMainAct extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.setTitle("选择配属局");
		this.setContentView(R.layout.activity_sbpd_main);
		
	}
	
	public static void actionStart(Context context) {
		Intent intent = new Intent(context, SbpdMainAct.class);
		context.startActivity(intent);
		
	} 
	
}
