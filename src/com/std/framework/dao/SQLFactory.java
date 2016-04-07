package com.std.framework.dao;

import com.std.framework.entity.SampleTable.SampleTableColumn;

public class SQLFactory {
	public static String buildSampleTableSQL(){
		return String.format("create table sampletable(%s int,%s varchar2,%s int);", 
				SampleTableColumn._ID,SampleTableColumn.NAME,SampleTableColumn.AGE);
	}
}
