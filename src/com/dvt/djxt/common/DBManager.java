package com.dvt.djxt.common;

import java.io.File;

import com.dvt.djxt.helper.DBOpenHelper;
import com.dvt.djxt.model.Tsbdjdjb;
import com.dvt.djxt.model.Tsbpddjb;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;

public class DBManager {

	/**
	 * 数据库名
	 */
	public static final String DB_NAME = getDBDirectory() + "PostalDeviceCheck";

	/**
	 * 数据库版本
	 */
	public static final int VERSION = 1;
	private static DBManager dbManager;
	private SQLiteDatabase db;

	/**
	 * 将构造方法私有化
	 */
	private DBManager(Context context) {
		DBOpenHelper dbHelper = new DBOpenHelper(context, DB_NAME, null, VERSION);
		db = dbHelper.getWritableDatabase();
	}

	/**
	 * 获取DBManager的实例
	 */
	public synchronized static DBManager getInstance(Context context) {
		if (dbManager == null)
			dbManager = new DBManager(context);
		return dbManager;

	}

	/**
	 * 获取DB所在目录
	 * 
	 * @return 文件路径
	 */
	private static File getDBDirectory() {
		File dir = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/dvtdjdb/");
		if (!dir.exists())
			dir.mkdirs();
		return dir;

	}
	
	/**
	 * 将Tsbpddjb实例存储到数据库。
	 */
	public void saveTsbpddjb(Tsbpddjb pdjg) {
		if (pdjg != null) {
			ContentValues values = new ContentValues();
			values.put("jhbh", pdjg.getJhbh());
			values.put("sbtm", pdjg.getSbtm());
			values.put("pdbz", pdjg.getPdbz());
			values.put("jzsj", pdjg.getJzsj());
			values.put("smbz", pdjg.getSmbz());
			values.put("smsj", pdjg.getSmsj());
			values.put("smr", pdjg.getSmr());
			values.put("scbz", pdjg.getScbz());
			values.put("scsj", pdjg.getScsj());
			db.insert("Tsbpddjb", null, values);
		}
	}

	/**
	 * 将Tsbdjdjb实例存储到数据库。
	 */
	public void saveTsbdjdjb(Tsbdjdjb djjg) {
		if (djjg != null) {
			ContentValues values = new ContentValues();
			values.put("jhbh", djjg.getJhbh());
			values.put("sbtm", djjg.getSbtm());
			values.put("pdbz", djjg.getGjdtm());
			values.put("gjdxh", djjg.getGjdxh());
			values.put("GJBJMC", djjg.getGjbjmc());
			values.put("jzsj", djjg.getJzsj());
			values.put("smbz", djjg.getSmbz());
			values.put("smsj", djjg.getScsj());
			values.put("smr", djjg.getSmr());
			values.put("scbz", djjg.getScbz());
			values.put("scsj", djjg.getScsj());
			db.insert("Tsbdjdjb", null, values);
		}
	}

}
