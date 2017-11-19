package com.std.framework.comm.service.aidl;

import com.std.framework.comm.service.aidl.ICallBack;

interface IRemoteService {
	void registerCallBack(ICallBack onCallBack);
	void unRegisterCallBack(ICallBack onCallBack);
	int remoteAddition(int a,int b);
}
