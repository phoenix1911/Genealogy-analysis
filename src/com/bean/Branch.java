package com.bean;

public class Branch {

	private String b_c_regist_num;
	private String b_firm_name;

	public String getB_c_regist_num() {
		return b_c_regist_num;
	}

	public void setB_c_regist_num(String b_c_regist_num) {
		this.b_c_regist_num = b_c_regist_num;
	}

	public String getB_firm_name() {
		return b_firm_name;
	}

	public void setB_firm_name(String b_firm_name) {
		this.b_firm_name = b_firm_name;
	}

	public Branch() {
		super();
	}

	@Override
	public String toString() {
		return "Branch [b_c_regist_num=" + b_c_regist_num + ", b_firm_name=" + b_firm_name + "]";
	}

}
