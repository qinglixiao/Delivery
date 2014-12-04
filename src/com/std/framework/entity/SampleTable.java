package com.std.framework.entity;

public class SampleTable {
	private String name;
	private String age;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}
	
	public interface SampleTableColumn extends BaseColumn{
		public static final String NAME = "name";
		public static final String AGE = "age";
	}

}
