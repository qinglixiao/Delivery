package test;

import java.io.IOException;

import android.test.AndroidTestCase;
import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;

public class GreenDaoMethod extends AndroidTestCase {
	private static final String SCHEMA = "com.systoon.toon.common.dao.entity";
	private static final String DEST_PROJECT_PATH = "../Toon/src-gen";

	public void testTableDynamicCreate() throws IOException, Exception {
		Schema schema = new Schema(1, SCHEMA);
		addLeaveMsgTable(schema);
//		new DaoGenerator().generateAll(schema, DEST_PROJECT_PATH);
		
	}
	
	public void testEmpty(){
		
	}

	private  void addLeaveMsgTable(Schema schema) {
		Entity entity = schema.addEntity("LeaveMsg");
		entity.setTableName("leave_msg");

		entity.addIdProperty();
		entity.addStringProperty("from_card_id");
		entity.addStringProperty("to_card_id");
		entity.addStringProperty("group_id");
		entity.addStringProperty("from_card_type");
		entity.addStringProperty("to_card_type");
		entity.addStringProperty("leave_type");
		entity.addStringProperty("leave_count");
		entity.addStringProperty("leave_msg");
		entity.addStringProperty("leave_time");
	}

}
