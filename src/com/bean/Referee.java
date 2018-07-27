package com.bean;

public class Referee {
	private String r_c_regist_num;
	private String r_firm_name;
	private String r_reason;

	public String getR_c_regist_num() {
		return r_c_regist_num;
	}

	public void setR_c_regist_num(String r_c_regist_num) {
		this.r_c_regist_num = r_c_regist_num;
	}

	public String getR_firm_name() {
		return r_firm_name;
	}

	public void setR_firm_name(String r_firm_name) {
		this.r_firm_name = r_firm_name;
	}

	public String getR_reason() {
		return r_reason;
	}

	public void setR_reason(String r_reason) {
		this.r_reason = r_reason;
	}

	public Referee(String r_c_regist_num, String r_firm_name, String r_reason) {
		super();
		this.r_c_regist_num = r_c_regist_num;
		this.r_firm_name = r_firm_name;
		this.r_reason = r_reason;
	}

}
