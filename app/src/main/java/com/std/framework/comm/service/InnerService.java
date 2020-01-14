package com.std.framework.comm.service;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import com.std.framework.comm.service.aidl.ICallBack;
import com.std.framework.comm.service.aidl.IRemoteService;

public class InnerService extends Service {
//	private RemoteCallbackList<NetCallBack> callbackList = new RemoteCallbackList<NetCallBack>();
	private ICallBack callBack;

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return service;
	}

	private IRemoteService.Stub service = new IRemoteService.Stub() {

		@Override
		public void unRegisterCallBack(ICallBack onCallBack) throws RemoteException {
			// TODO Auto-generated method stub
			callBack = onCallBack;
		}

		@Override
		public void registerCallBack(ICallBack onCallBack) throws RemoteException {
			// TODO Auto-generated method stub
			callBack = onCallBack;
		}

		@Override
		public int remoteAddition(int a, int b) throws RemoteException {
			// TODO Auto-generated method stub
			Bundle bundle = new Bundle();
			bundle.putString("result", (a+b)+"");
			callBack.onCallBack(bundle);
			return 0;
		}
	};

	public void onDestroy() {
		Log.d("LX", "onDestroy()");
	};

}
