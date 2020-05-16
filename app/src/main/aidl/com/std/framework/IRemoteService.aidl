package com.std.framework;
import com.std.framework.ICallBack;

interface IRemoteService {
	void registerCallBack(ICallBack onCallBack);
	void unRegisterCallBack(ICallBack onCallBack);
	int remoteAddition(int a,int b);
}
