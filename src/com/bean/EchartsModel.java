package com.bean;

public class EchartsModel {
	private String value;
	private String type;
	private String name;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public EchartsModel() {
		super();
	}

	@Override
	public String toString() {
		return "EchartsModel [value=" + value + ", type=" + type + ", name=" + name + "]";
	}

}
