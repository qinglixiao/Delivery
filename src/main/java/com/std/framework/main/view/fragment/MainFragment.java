package com.std.framework.main.view.fragment;

import com.std.framework.R;
import com.std.framework.comm.BaiduLocationProvider;
import com.std.framework.comm.BaiduLocationProvider.LocationListener;
import com.std.framework.fragment.BaseFragment;
import com.std.framework.service.ICallBack;
import com.std.framework.service.IRemoteService;
import com.std.framework.service.StdService;

import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainFragment extends BaseFragment implements OnClickListener {
	public View view;
	private Intent intent;
	private Button btn_open;
	private Button btn_close;
	private Button btn_request;
	private TextView tv_result;
	private IRemoteService remoteService;
	private EditText edt_a;
	private EditText edt_b;
	private Button btn_location;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		view = inflater.inflate(R.layout.fragment_main, null);
		btn_open = (Button) view.findViewById(R.id.btn_open);
		btn_close = (Button) view.findViewById(R.id.btn_close);
		tv_result = (TextView) view.findViewById(R.id.tv_result);
		btn_request = (Button) view.findViewById(R.id.btn_request);
		edt_a = (EditText) view.findViewById(R.id.edt_a);
		edt_b = (EditText) view.findViewById(R.id.edt_b);
		btn_location = (Button) view.findViewById(R.id.btn_location);
		btn_open.setOnClickListener(this);
		btn_close.setOnClickListener(this);
		btn_request.setOnClickListener(this);
		btn_location.setOnClickListener(this);
		return view;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
			case R.id.btn_open:
				Intent intent = new Intent(getActivity(), StdService.class);
				getActivity().bindService(intent, connection, Context.BIND_AUTO_CREATE);
				break;
			case R.id.btn_close:
				intent = new Intent(getActivity(), StdService.class);
				getActivity().unbindService(connection);
				break;
			case R.id.btn_request:
				if (remoteService != null) {
					try {
						remoteService.remoteAddition(Integer.valueOf(edt_a.getText().toString()),
								Integer.valueOf(edt_b.getText().toString()));
					}
					catch (NumberFormatException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					catch (RemoteException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				break;
			case R.id.btn_location:
				BaiduLocationProvider locationProvider = new BaiduLocationProvider(getActivity());
				locationProvider.setListener(locationListener);
				locationProvider.start();
				break;
		}
	}

	private LocationListener locationListener = new LocationListener() {

		@Override
		public void onReceiveLocation(String address) {
			// TODO Auto-generated method stub
			tv_result.setText(address);
		}
	};

	private ServiceConnection connection = new ServiceConnection() {

		@Override
		public void onServiceDisconnected(ComponentName name) {
			// TODO Auto-generated method stub
			remoteService = null;
		}

		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			// TODO Auto-generated method stub
			Toast.makeText(getActivity(), "连接成功", 1).show();
			remoteService = IRemoteService.Stub.asInterface(service);
			try {
				remoteService.registerCallBack(callback);
			}
			catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	};

	private Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			tv_result.setText(msg.getData().getString("result"));
		};
	};

	private ICallBack.Stub callback = new ICallBack.Stub() {

		@Override
		public void onCallBack(Bundle bundle) throws RemoteException {
			// TODO Auto-generated method stub
			Log.d("LX", Looper.myLooper() == Looper.getMainLooper() ? "==" : "!=");
			Message message = Message.obtain(handler, 7);
			message.setData(bundle);
			message.sendToTarget();
		}
	};

}
