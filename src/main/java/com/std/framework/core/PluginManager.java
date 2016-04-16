package com.std.framework.core;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;

import com.std.framework.interfaces.plugin.IStdPlugin;

import dalvik.system.DexClassLoader;

public class PluginManager {
	private ArrayList<IStdPlugin> plugins = new ArrayList<IStdPlugin>();
	private Context context;

	public PluginManager(Context context) {
		this.context = context;
	}

	@SuppressLint("NewApi")
	public void load() {
		PackageManager packageManager = context.getPackageManager();
		Intent intent = new Intent("com.cn.std.plugin", null);
		List<ResolveInfo> activitys = packageManager.queryIntentActivities(intent, 0);
		for (ResolveInfo info : activitys) {
			ActivityInfo activityInfo = info.activityInfo;
			String packageName = activityInfo.packageName;
			String dexPath = activityInfo.applicationInfo.sourceDir;
			String dexOutputDir = context.getApplicationInfo().dataDir;
			String libPath = activityInfo.applicationInfo.nativeLibraryDir;
			
			try {
				DexClassLoader classLoader = new DexClassLoader(dexPath, dexOutputDir, libPath, getClass().getClassLoader());
//				Class<?> clazz = classLoader.loadClass(packageName + ".PluginClass");
				Class<?> clazz = classLoader.loadClass(packageName + ".MainActivity");
				IStdPlugin plugin = (IStdPlugin) clazz.newInstance();
				plugins.add(plugin);
			}
			catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			catch(Exception e){
				e.printStackTrace();
			}

		}
	}
	
	public ArrayList<IStdPlugin> getPlugins(){
		return plugins;
	}
}
