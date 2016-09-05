package com.std.framework.comm.dao;

import com.std.framework.comm.entity.SampleTable.SampleTableColumn;

public class SQLBuilder {
	public static String buildSampleTableSQL(){
		return String.format("create table sampletable(%s int,%s varchar2,%s int);", 
				SampleTableColumn._ID,SampleTableColumn.NAME,SampleTableColumn.AGE);
	}
}
