package com.bean;

public class Personnel {
	private String p_c_regist_num;
	private String p_staff_name;
	private String p_position;

	public String getP_c_regist_num() {
		return p_c_regist_num;
	}

	public void setP_c_regist_num(String p_c_regist_num) {
		this.p_c_regist_num = p_c_regist_num;
	}

	public String getP_staff_name() {
		return p_staff_name;
	}

	public void setP_staff_name(String p_staff_name) {
		this.p_staff_name = p_staff_name;
	}

	public String getP_position() {
		return p_position;
	}

	public void setP_position(String p_position) {
		this.p_position = p_position;
	}

	public Personnel() {
		super();
	}

	@Override
	public String toString() {
		return "Personnel [p_c_regist_num=" + p_c_regist_num + ", p_staff_name=" + p_staff_name + ", p_position="
				+ p_position + "]";
	}

}
