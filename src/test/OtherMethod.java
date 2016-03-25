package test;

import android.test.InstrumentationTestCase;
import android.util.Log;

public class OtherMethod extends InstrumentationTestCase {
	public void testEnum() {
		int count = Type.values().length;
		Log.i("LX", "count:"+count);
	}

	public enum Type {
		VIDEO(0), Picture(1), TEXT(3),LIST(100);
		private int type;
		private Type(int tag) {
			type = tag;
		}

	}
}
