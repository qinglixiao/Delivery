package com.std.framework.service;

import com.std.framework.service.ICallBack;

interface IRemoteService {
	void registerCallBack(ICallBack onCallBack);
	void unRegisterCallBack(ICallBack onCallBack);
	int remoteAddition(int a,int b);
}
