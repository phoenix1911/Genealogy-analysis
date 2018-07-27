package com.bean;

public class Shareholder {

	private String s_c_regist_num;
	private String s_name;
	private String s_type;
	private String s_money;

	public String getS_c_regist_num() {
		return s_c_regist_num;
	}

	public void setS_c_regist_num(String s_c_regist_num) {
		this.s_c_regist_num = s_c_regist_num;
	}

	public String getS_name() {
		return s_name;
	}

	public void setS_name(String s_name) {
		this.s_name = s_name;
	}

	public String getS_type() {
		return s_type;
	}

	public void setS_type(String s_type) {
		this.s_type = s_type;
	}

	public String getS_money() {
		return s_money;
	}

	public void setS_money(String s_money) {
		this.s_money = s_money;
	}

	public Shareholder() {
		super();
	}

	@Override
	public String toString() {
		return "Shareholder [s_c_regist_num=" + s_c_regist_num + ", s_name=" + s_name + ", s_type=" + s_type
				+ ", s_money=" + s_money + "]";
	}

}
