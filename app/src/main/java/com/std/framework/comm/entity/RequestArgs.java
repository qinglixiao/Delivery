package com.std.framework.comm.entity;

import android.os.Parcel;
import android.os.Parcelable;

public class RequestArgs implements Parcelable {
	public String id;
	public String title;
	public String manufacturer;

	public RequestArgs(Parcel parcel) {
		id = parcel.readString();
		title = parcel.readString();
		manufacturer = parcel.readString();
	}

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		// TODO Auto-generated method stub
		dest.writeString(id);
		dest.writeString(title);
		dest.writeString(manufacturer);
	}

	public static final Creator<RequestArgs> CREATOR = new Creator<RequestArgs>() {
		@Override
		public RequestArgs createFromParcel(Parcel source) {
			return new RequestArgs(source);
		}

		@Override
		public RequestArgs[] newArray(int size) {
			return new RequestArgs[size];
		}
	};

}
