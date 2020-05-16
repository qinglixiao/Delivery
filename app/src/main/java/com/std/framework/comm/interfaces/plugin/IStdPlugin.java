package com.std.framework.comm.interfaces.plugin;

import android.content.Context;
import android.content.Intent;

import androidx.fragment.app.Fragment;

//插件接口
public interface IStdPlugin {
	Fragment loadPlugin(Context context, Intent intent);
	void unLoadPlugin();
	int getPluginVersion();
	String getDescribe();
	String getGUID();
}
