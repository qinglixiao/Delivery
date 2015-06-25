package test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import test.TestMethod.NearByBean.NearbyData;
import test.TestMethod.NearByBean.NearbyData.NearbyInnerData;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.test.AndroidTestCase;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.library.file.SDCardHelper;
import com.library.util.LibUtil;
import com.library.util.SecurityUtil.MD5;
import com.library.util.SecurityUtil.SHA1;
import com.std.framework.interfaces.plugin.IStdPlugin;
import com.thoughtworks.xstream.InitializationException;

import dalvik.system.DexClassLoader;

public class TestMethod extends AndroidTestCase {
	private static final String TAG = "LX";

	@SuppressLint("NewApi")
	public void testDex() {
		String apkPath = LibUtil.getSdCardRootDirectory() + File.separator + "MyPlugin.apk";
		DexClassLoader classLoader = new DexClassLoader(apkPath, getContext().getApplicationInfo().dataDir, null, this.getClass()
				.getClassLoader());
		try {
			ActivityManager activityManager;
			Class pluginClass = classLoader.loadClass("Plugin");
			IStdPlugin plugin = (IStdPlugin) pluginClass.newInstance();
			//			Log.d("LX", "package:"+plugin.getPackage());
			//			Log.d("LX", "version:"+plugin.getVersion());
			//			Log.d("LX", "title:"+plugin.getTitle());
			Fragment intent = plugin.loadPlugin(getContext(), new Intent());
			Log.d("LX", "codePath:" + getContext().getPackageCodePath());
			Log.d("LX", "packageName:" + getContext().getPackageName());

			//			Activity activity;
			//			activity.getComponentName()
		}
		catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void formatStr() {
		String f_str = String.format("hello_%s%s", "wold", "你好");
		Log.d("LX", f_str);
	}

	public void dbCreate() throws IOException {
		InputStream stream = getContext().getResources().getAssets().open("db/sdb.db");
		File file = new File(LibUtil.getAppHomeDirectory(getContext()), "sdb");
		FileOutputStream fileOutputStream = new FileOutputStream(file, true);
		byte[] buffer = new byte[1024];
		while (stream.read(buffer) != -1) {
			fileOutputStream.write(buffer);
			fileOutputStream.flush();
		}
		fileOutputStream.close();

	}

	public void testMD5() {
		String ss = "信息来源";
		Log.d("LX", SHA1.encrypt(ss));
		ss = "MD5加密";
		Log.d("LX", SHA1.encrypt(ss));
		Log.d("LX", MD5.encrypt(ss));
	}

	public void testDb() {
		//		DbHelper helper = new DbHelper(DbHelper.createFromSdCard(LibUtil.getAppHomeDirectory(getContext()), "testdb"));
		//		helper.exec("insert into people(name,age) values('王刚',12);");
		//		String ss = SampleTableColumn.NAME;
	}

	public void testquery() {
		//		long old = System.currentTimeMillis();
		//		DbHelper helper = new DbHelper(DbHelper.createFromSdCard(LibUtil.getAppHomeDirectory(getContext()), "db.db"));
		//		old = System.currentTimeMillis();
		//		Cursor cursor = helper.rawQuery("select * from people;", null);
		//		int columncount = cursor.getColumnCount();
		//
		//		//		while(cursor.moveToNext()){
		//		//			Log.d("LX", cursor.getString()+"");
		//		//		}
		//		Log.d("LX", cursor.getCount() + "");
		//		Log.d("LX", System.currentTimeMillis() - old + "");
		//		String ss = SampleTableColumn.NAME;
	}

	public void testSdCard() {
		Log.d("LX", LibUtil.getCacheDirectory(getContext()));
		Log.d("LX", LibUtil.getDownLoadDirectory());
		SDCardHelper helper = new SDCardHelper(getContext());
		helper.openOrCreateFile(LibUtil.getAppHomeDirectory(getContext()) + "/lx/lx", "me");
		helper.writeString("你好啊");
		helper.close();
		helper.delete(LibUtil.getAppHomeDirectory(getContext()));

	}

	public void movebit() {
		int MODE_SHIFT = 30;
		int MODE_MASK = 3 << MODE_SHIFT;
		int left = MODE_SHIFT << 3;
		int right = 3 >> 2;
		Log.d("LX", MODE_MASK + "");
		Log.d("LX", left + "");
		Log.d("LX", right + "");
	}

	public void testMemory() {
		long size = Runtime.getRuntime().maxMemory() / 1024 / 1024;
		Log.d(TAG, size + "");
	}

	public void testPhoneNumber() {
		String phone = LibUtil.getPhoneNumber(getContext());
		try {
			Runtime.getRuntime().exec("input text fuytfrdrsxgf");
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void throwException() {
		throw new IllegalArgumentException("");
	}

	public void parseJson() {
		try {
			InputStream inputStream = getContext().getResources().getAssets().open("dynamic_sample.txt");
			InputStreamReader reader = new InputStreamReader(inputStream, "UTF-8");
			BufferedReader bufferedReader = new BufferedReader(reader);
			String json = "";
			StringBuffer buffer = new StringBuffer();
			while ((json = bufferedReader.readLine()) != null) {
				buffer.append(json);
			}
			json = buffer.toString();
			Log.d("LX", json);
			ArrayList<MessageDataInfo> list = new Gson().fromJson(json, new TypeToken<List<MessageDataInfo>>() {
			}.getType());
			Log.d("LX", list.size() + "");
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void parseGson() {
		ArrayList<MessageDataInfo> list = new ArrayList<TestMethod.MessageDataInfo>();
		MessageDataInfo info1 = new MessageDataInfo();
		info1.setTitle("title1");
		info1.setContent("内容");
		info1.setSendTime("2013/3/3");
		list.add(info1);
		info1 = new MessageDataInfo();
		info1.setTitle("title1");
		info1.setContent("内容");
		info1.setSendTime("2013/3/3");
		list.add(info1);
		info1 = new MessageDataInfo();
		info1.setTitle("title1");
		info1.setContent("内容");
		info1.setSendTime("2013/3/3");
		list.add(info1);
		String gson = new Gson().toJson(list);

		Log.d("LX", gson);
	}

	public void buildNearbyGson() {
		NearByBean bean = new NearByBean();
		bean.msg = "a";
		bean.result = "a";
		bean.operateTime = "adf";

		NearbyData data = bean.new NearbyData();
		data.total = 50;
		data.currentPageNumber = 1;
		data.eachPageItems = 40;
		data.list = new ArrayList<NearbyInnerData>();

		for (int i = 0; i < 6; i++) {
			NearbyInnerData innerData = data.new NearbyInnerData();
			innerData.title = "fda";
			innerData.name = "fad";
			innerData.activityBeginDate = "fad";
			innerData.activityEndDate = "end";
			innerData.address = "fad";
			innerData.createdate = "fda";
			innerData.exchangeMode = "fa";
			innerData.icon = "d";
			innerData.id = "t";
			innerData.introduction = "fad";
			innerData.JoinActivityCount = "rt";
			innerData.objectId = "p[";
			innerData.objectType = 1;
			innerData.position = "fa";
			innerData.promotionStatement = "few";
			innerData.score = "fd";
			innerData.type = "fd";
			innerData.userId = "fda";
			data.list.add(innerData);
		}

		bean.data = data;
		String gson = new Gson().toJson(bean);

		Log.d("LX", gson);
	}

	/** 周边 */
	public class NearByBean implements Serializable {
		private static final long serialVersionUID = 1L;

		public String result;

		public String operateTime;

		public String msg;

		/** 数据集 */
		public NearbyData data;

		public class NearbyData {
			/** 分页总数 */
			public int total;

			/** 当前被拉取的是第几页 */
			public int currentPageNumber;

			/** 每页显示的数目 */
			public int eachPageItems;

			public List<NearbyInnerData> list;

			public class NearbyInnerData {
				/** 图标，名片、活动 */
				public String icon;

				/** 名称， 活动 */
				public String title;

				/** 名称， 名片*/
				public String name;

				/** 推广语， 名片、活动 */
				public String promotionStatement;

				/** 简介，名片 */
				public String introduction;

				/** 类型，名片、活动 */
				public String type;

				/** 职位，名片 */
				public String position;

				/** 类型，不填是全部，1附近的人 2企业 3-员工名片 4活动 5群组 */
				public int objectType;

				public String createdate;

				/** 距离 ,名片 活动 */
				public String score;

				/** 主键id,名片 企业 */
				public String id;
				/**活动群组*/
				public String objectId;
				public String userId;
				public String exchangeMode;

				/** 地址，活动 */
				public String address;

				/** 活动开始时间 */
				public String activityBeginDate;

				/** 活动结束时间 */
				public String activityEndDate;

				/** 活动报名数 */
				public String JoinActivityCount;
			}
		}

		/** TRUE表示返回值正常 */
		public boolean isSuccess() {
			return "0".equals(result);
		}
	}

	public class MessageDataInfo {

		private String id;// 服务器端Id

		private String toUserId;

		private String toCardId;// 收信人cardId

		private int toCardIdType;// 接收方名片类型 1个人 2企业 3员工

		private String fromUserId;// 发信人userId

		private String fromCardId;// 发信人cardId

		private int fromCardIdType;// 发送方名片类型 1个人 2企业 3员工

		private String content;// 内容

		private String title;

		private String contentPara;// 客户端数据结构

		private String targetUrl;// 跳转地址

		private int noticeType;// 来源通知类型:1:系统广播、2:个人消息、3:活动消息、4:群组消息、5:VIP消息、6:企业消息、7:应用

		private String noticeTypeName;

		private int source;// 通知 8；动态16 ； 圈动态32 活动成员动态64 活动动态128 群组成员动态256
				   // 群组动态512

		private String sendTime;

		private int isRead;// 0已读 1未读

		private String vipAvatar;

		private int vipNum;

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getToCardId() {
			return toCardId;
		}

		public void setToCardId(String toCardId) {
			this.toCardId = toCardId;
		}

		public String getFromUserId() {
			return fromUserId;
		}

		public void setFromUserId(String fromUserId) {
			this.fromUserId = fromUserId;
		}

		public String getFromCardId() {
			return fromCardId;
		}

		public void setFromCardId(String fromCardId) {
			this.fromCardId = fromCardId;
		}

		public String getContent() {
			return content;
		}

		public void setContent(String content) {
			this.content = content;
		}

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public String getContentPara() {
			return contentPara;
		}

		public void setContentPara(String contentPara) {
			this.contentPara = contentPara;
		}

		public String getTargetUrl() {
			return targetUrl;
		}

		public void setTargetUrl(String targetUrl) {
			this.targetUrl = targetUrl;
		}

		public int getNoticeType() {
			return noticeType;
		}

		public void setNoticeType(int noticeType) {
			this.noticeType = noticeType;
		}

		public int getSource() {
			return source;
		}

		public void setSource(int source) {
			this.source = source;
		}

		public String getSendTime() {
			return sendTime;
		}

		public void setSendTime(String sendTime) {
			this.sendTime = sendTime;
		}

		public int getIsRead() {
			return isRead;
		}

		public String getToUserId() {
			return toUserId;
		}

		public void setToUserId(String toUserId) {
			this.toUserId = toUserId;
		}

		public int getToCardIdType() {
			return toCardIdType;
		}

		public void setToCardIdType(int toCardIdType) {
			this.toCardIdType = toCardIdType;
		}

		public int getFromCardIdType() {
			return fromCardIdType;
		}

		public void setFromCardIdType(int fromCardIdType) {
			this.fromCardIdType = fromCardIdType;
		}

		public String getNoticeTypeName() {
			return noticeTypeName;
		}

		public void setNoticeTypeName(String noticeTypeName) {
			this.noticeTypeName = noticeTypeName;
		}

		public void setIsRead(int isRead) {
			this.isRead = isRead;
		}

		public String getVipAvatar() {
			return vipAvatar;
		}

		public void setVipAvatar(String vipAvatar) {
			this.vipAvatar = vipAvatar;
		}

		public int getVipNum() {
			return vipNum;
		}

		public void setVipNum(int vipNum) {
			this.vipNum = vipNum;
		}

	}
	
	
}
