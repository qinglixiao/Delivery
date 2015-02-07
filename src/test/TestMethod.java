package test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.content.Intent;
import android.database.Cursor;
import android.support.v4.app.Fragment;
import android.test.AndroidTestCase;
import android.util.Log;

import com.library.file.SDCardHelper;
import com.library.util.LibUtil;
import com.library.util.SecurityUtil.MD5;
import com.library.util.SecurityUtil.SHA1;
import com.std.framework.db.DbHelper;
import com.std.framework.entity.SampleTable.SampleTableColumn;
import com.std.framework.interfaces.plugin.IStdPlugin;

import dalvik.system.DexClassLoader;

public class TestMethod extends AndroidTestCase {
	private static final String TAG = "LX";

	@SuppressLint("NewApi")
	public void testDex() {
		String apkPath = LibUtil.getSdRootDirectory() + File.separator + "MyPlugin.apk";
		DexClassLoader classLoader = new DexClassLoader(apkPath, getContext().getApplicationInfo().dataDir, null, this.getClass()
				.getClassLoader());
		try {
			ActivityManager activityManager;
			Class pluginClass = classLoader.loadClass("Plugin");
			IStdPlugin plugin = (IStdPlugin) pluginClass.newInstance();
			//			Log.d("LX", "package:"+plugin.getPackage());
			//			Log.d("LX", "version:"+plugin.getVersion());
			//			Log.d("LX", "title:"+plugin.getTitle());
			Fragment intent = plugin.loadPlugin(getContext(), new Intent());
			Log.d("LX", "codePath:" + getContext().getPackageCodePath());
			Log.d("LX", "packageName:" + getContext().getPackageName());

			//			Activity activity;
			//			activity.getComponentName()
		}
		catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void formatStr() {
		String f_str = String.format("hello_%s%s", "wold", "你好");
		Log.d("LX", f_str);
	}

	public void dbCreate() throws IOException {
		InputStream stream = getContext().getResources().getAssets().open("db/sdb.db");
		File file = new File(LibUtil.getAppInstallDirectory(getContext()), "sdb");
		FileOutputStream fileOutputStream = new FileOutputStream(file, true);
		byte[] buffer = new byte[1024];
		while (stream.read(buffer) != -1) {
			fileOutputStream.write(buffer);
			fileOutputStream.flush();
		}
		fileOutputStream.close();

	}

	public void testMD5() {
		String ss = "信息来源";
		Log.d("LX", SHA1.encrypt(ss));
		ss = "MD5加密";
		Log.d("LX", SHA1.encrypt(ss));
		Log.d("LX", MD5.encrypt(ss));
	}

	public void testDb() {
		DbHelper helper = new DbHelper(DbHelper.createFromSdCard(LibUtil.getAppInstallDirectory(getContext()), "testdb"));
		helper.exec("insert into people(name,age) values('王刚',12);");
		String ss = SampleTableColumn.NAME;
	}

	public void testquery() {
		long old = System.currentTimeMillis();
		DbHelper helper = new DbHelper(DbHelper.createFromSdCard(LibUtil.getAppInstallDirectory(getContext()), "db.db"));
		old = System.currentTimeMillis();
		Cursor cursor = helper.rawQuery("select * from people;", null);
		int columncount = cursor.getColumnCount();

		//		while(cursor.moveToNext()){
		//			Log.d("LX", cursor.getString()+"");
		//		}
		Log.d("LX", cursor.getCount() + "");
		Log.d("LX", System.currentTimeMillis() - old + "");
		String ss = SampleTableColumn.NAME;
	}

	public void testSdCard() {
		Log.d("LX", LibUtil.getCacheDirectory(getContext()));
		Log.d("LX", LibUtil.getDownLoadDirectory());
		SDCardHelper helper = new SDCardHelper(getContext());
		helper.openOrCreateFile(LibUtil.getAppInstallDirectory(getContext()) + "/lx/lx", "me");
		helper.writeString("你好啊");
		helper.close();
		helper.delete(LibUtil.getAppInstallDirectory(getContext()));

	}

	public void movebit() {
		int MODE_SHIFT = 30;
		int MODE_MASK = 3 << MODE_SHIFT;
		int left = MODE_SHIFT << 3;
		int right = 3 >> 2;
		Log.d("LX", MODE_MASK + "");
		Log.d("LX", left + "");
		Log.d("LX", right + "");
	}

	public void testMemory(){
		long size = Runtime.getRuntime().maxMemory()/1024/1024;
		Log.d(TAG, size+"");
	}
	
	public void testPhoneNumber(){
		String phone = LibUtil.getPhoneNumber(getContext());
		try {
			Runtime.getRuntime().exec("input text fuytfrdrsxgf");
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
