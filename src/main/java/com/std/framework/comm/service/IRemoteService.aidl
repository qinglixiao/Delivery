package com.std.framework.comm.service;

import com.std.framework.comm.service.ICallBack;

interface IRemoteService {
	void registerCallBack(ICallBack onCallBack);
	void unRegisterCallBack(ICallBack onCallBack);
	int remoteAddition(int a,int b);
}
