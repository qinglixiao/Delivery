package com.std.framework.service;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

public class DeskWidgetService extends RemoteViewsService {

	@Override
	public RemoteViewsFactory onGetViewFactory(Intent intent) {
		// TODO Auto-generated method stub
		return new PhotoFactory(this,intent);
	}
	
	private class PhotoFactory implements RemoteViewsFactory{
		private static final String P_URL = "content://android.provider.MediaStore.Images.Thumbnails";
		private Context context;
		private Intent intent;
		
		public PhotoFactory(Context context,Intent intent){
			this.context = context; 
			this.intent = intent;
		}

		@Override
		public void onCreate() {
			//Load Photo
			Uri uri = Uri.parse(P_URL);
			ContentResolver resolver = context.getContentResolver();
			resolver.query(uri, null, "", null, null);
			
		}

		@Override
		public void onDataSetChanged() {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onDestroy() {
			// TODO Auto-generated method stub
			
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public RemoteViews getViewAt(int position) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public RemoteViews getLoadingView() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public int getViewTypeCount() {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public boolean hasStableIds() {
			// TODO Auto-generated method stub
			return false;
		}
		
	}

}
