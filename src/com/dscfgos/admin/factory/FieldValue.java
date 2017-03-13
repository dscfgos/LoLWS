package com.dscfgos.admin.factory;

public class FieldValue 
{
	private String name 	= null;
	private Object value 	= null;
	private int		sqlType = -1;
	
	public Object getValue() {
		return value;
	}
	public void setValue(Object value) {
		this.value = value;
	}
	public int getSqlType() {
		return sqlType;
	}
	public void setSqlType(int sqlType) {
		this.sqlType = sqlType;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public FieldValue(String name, Object value, int sqlType) {
		super();
		this.name = name;
		this.value = value;
		this.sqlType = sqlType;
	}
	
	
}
