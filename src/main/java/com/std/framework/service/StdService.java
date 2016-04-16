package com.std.framework.service;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

public class StdService extends Service {
//	private RemoteCallbackList<ICallBack> callbackList = new RemoteCallbackList<ICallBack>();
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
