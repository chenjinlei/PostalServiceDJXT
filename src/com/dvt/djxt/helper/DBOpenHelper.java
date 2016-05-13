package com.dvt.djxt.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.provider.BaseColumns;
import android.database.sqlite.SQLiteOpenHelper;

public class DBOpenHelper extends SQLiteOpenHelper {

	/**
	 * TSBPDDJB表模型
	 */
	public static abstract class TSBPDDJB implements BaseColumns {
		public static final String TABLE_TSBPDDJB = "Tsbpddjb";

		public static final String JHBH = "jhbh";
		public static final String SBTM = "sbtm";
		public static final String PDBZ = "pdbz";
		public static final String JZSJ = "jzsj";
		public static final String SMBZ = "smbz";
		public static final String SMSJ = "smsj";
		public static final String SMR = "smr";
		public static final String SCBZ = "scbz";
		public static final String SCSJ = "scsj";
	}

	/**
	 * TSBPDDJB表建表语句
	 */
	public static final String CREATE_TSBPDDJB = "CREATE TABLE " + TSBPDDJB.TABLE_TSBPDDJB + "(" + TSBPDDJB.JHBH
			+ " VARCHAR(21)," + TSBPDDJB.SBTM + " VARCHAR(15)," + TSBPDDJB.PDBZ + " VARCHAR(1)," + TSBPDDJB.JZSJ
			+ " DATETIME," + TSBPDDJB.SMBZ + " VARCHAR(1)," + TSBPDDJB.SMSJ + " DATETIME," + TSBPDDJB.SMR
			+ " VARCHAR(20)," + TSBPDDJB.SCBZ + " VARCHAR(1)," + TSBPDDJB.SCSJ + " DATETIME" + ")";

	/**
	 * TSBDJDJB表模型
	 */
	public static abstract class TSBDJDJB implements BaseColumns {
		public static final String TABLE_TSBDJDJB = "Tsbdjdjb";

		public static final String JHBH = "jhbh";
		public static final String SBTM = "sbtm";
		public static final String GJDTM = "gjdtm";
		public static final String GJDXH = "gjdxh";
		public static final String GJBJMC = "gjbjmc";
		public static final String JZSJ = "jzsj";
		public static final String SMBZ = "smbz";
		public static final String SMSJ = "smsj";
		public static final String SMR = "smr";
		public static final String DJBZ = "djbz";
		public static final String SCBZ = "scbz";
		public static final String SCSJ = "scsj";
	}

	/**
	 * TSBDJDJB表建表语句
	 */
	public static final String CREATE_TSBDJDJB = "CREATE TABLE" + TSBDJDJB.TABLE_TSBDJDJB + "(" + TSBDJDJB.JHBH
			+ " VARCHAR(21), " + TSBDJDJB.SBTM + " VARCHAR(15), " + TSBDJDJB.GJDTM + " VARCHAR(17)" + TSBDJDJB.GJDXH
			+ " VARCHAR(2)" + TSBDJDJB.GJBJMC + " VARCHAR(40)" + TSBDJDJB.JZSJ + " DATETIME" + TSBDJDJB.SMBZ
			+ " VARCHAR(1)" + TSBDJDJB.SMSJ + " DATETIME" + TSBDJDJB.SMR + " VARCHAR(20)" + TSBDJDJB.DJBZ
			+ " VARCHAR(1)" + TSBDJDJB.SCBZ + " VARCHAR(1)" + TSBDJDJB.SCSJ + " DATETIME)";

	public DBOpenHelper(Context context, String name, CursorFactory factory, int version) {
		super(context, name, factory, version);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(CREATE_TSBPDDJB);
		db.execSQL(CREATE_TSBDJDJB);

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}

}
