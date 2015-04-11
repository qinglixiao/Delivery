package com.std.framework.service;

import com.std.framework.receiver.CallInReceiver;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

public class StdService extends Service {
//	private RemoteCallbackList<ICallBack> callbackList = new RemoteCallbackList<ICallBack>();
	private ICallBack callBack;
	
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
//		broadcastRegister();
	}
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// TODO Auto-generated method stub
		notifyRemoteServiceDeath(service);
		return super.onStartCommand(intent, flags, startId);
		
	}
	
	private void notifyRemoteServiceDeath(IBinder iBinder){
		try {
			iBinder.linkToDeath(new MyLinkToDeathCallback(), 0);
		}
		catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

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
	
	private void broadcastRegister(){
		/**注册手机来电广播*/
		registerReceiver(new CallInReceiver(), new IntentFilter("android.intent.action.PHONE_STATE"));
		
	}
	
	public void onDestroy() {
		Log.d("LX", "onDestroy()");
	};
	
	class MyLinkToDeathCallback implements IBinder.DeathRecipient{

		@Override
		public void binderDied() {
			// TODO Auto-generated method stub
			Log.d("LX", "service_death");
		}
		
	}

}
