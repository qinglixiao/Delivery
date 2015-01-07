package com.std.framework.db;

import com.library.util.PathUtil;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class DbHelper {
	private SQLiteDatabase db;
	
	public DbHelper(Context context){
		this(context,1);
	}
	
	public DbHelper(Context context,int version){
		db = new SQLiteHelper(context,"stdb",null,version).getWritableDatabase();
	}
	
	public DbHelper(SQLiteDatabase db){
		this.db = db;
	}

	/**
	 * 
	 * 描          述 ：从SD卡创建数据库
	 * 创建日期  : 2014-7-29
	 * 作           者 ： lx
	 * 修改日期  : 
	 * 修   改   者 ：
	 * @version   : 1.0
	 * @param dir 目录
	 * @param fileName 文件名
	 * @return
	 *
	 */
	public static SQLiteDatabase createFromSdCard(String dir, String fileName) {
		return SQLiteDatabase.openOrCreateDatabase(PathUtil.merge(dir, fileName), null);
	}

	public void exec(String sql) {
		db.execSQL(sql);
	}
	/**
	 * 
	 * 描          述 ：执行sql语句
	 * 创建日期  : 2014-7-30
	 * 作           者 ： lx
	 * 修改日期  : 
	 * 修   改   者 ：
	 * @version   : 1.0
	 * @param sql 含有占位符的sql语句（例如：INSERT INTO person VALUES (NULL, ?, ?)）
	 * @param args 占位符对应的值
	 *
	 */
	public void exec(String sql,String[] args){
		db.execSQL(sql, args);
	}
	
	/**
	 * 
	 * 描          述 ：执行查询操作
	 * 创建日期  : 2014-7-30
	 * 作           者 ： lx
	 * 修改日期  : 
	 * 修   改   者 ：
	 * @version   : 1.0
	 * @param sql 含有占位符的sql语句（例如：SELECT * FROM person WHERE age >= ?）
	 * @param selectionArgs 占位符对应的值
	 * @return
	 *
	 */
	public Cursor rawQuery(String sql,String[] selectionArgs){
		return db.rawQuery(sql, selectionArgs);
	}
	
	public void beginTransaction(){
		db.beginTransaction();
	}
	
	public void setTransactionSuccessful(){
		db.setTransactionSuccessful();
	}
	
	public void endTransaction(){
		db.endTransaction();
	}
	
	public void close(){
		db.close();
	}

	class SQLiteHelper extends SQLiteOpenHelper {
		public SQLiteHelper(Context context) {
			super(context, "stdb", null, 1);
		}

		public SQLiteHelper(Context context, String name, CursorFactory factory, int version) {
			super(context, name, factory, version);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			String sql = SQLFactory.buildSampleTableSQL();
			db.execSQL(sql);
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			Log.d("LX", "onUpgrade");
		}
	}

}
