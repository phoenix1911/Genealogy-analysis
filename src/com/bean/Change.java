package com.bean;

public class Change {
	private String c_c_regist_num;
	private String c_change_date;
	private String c_change_project;
	private String c_change_before;
	private String c_change_after;

	public String getC_c_regist_num() {
		return c_c_regist_num;
	}

	public void setC_c_regist_num(String c_c_regist_num) {
		this.c_c_regist_num = c_c_regist_num;
	}

	public String getC_change_date() {
		return c_change_date;
	}

	public void setC_change_date(String c_change_date) {
		this.c_change_date = c_change_date;
	}

	public String getC_change_project() {
		return c_change_project;
	}

	public void setC_change_project(String c_change_project) {
		this.c_change_project = c_change_project;
	}

	public String getC_change_before() {
		return c_change_before;
	}

	public void setC_change_before(String c_change_before) {
		this.c_change_before = c_change_before;
	}

	public String getC_change_after() {
		return c_change_after;
	}

	public void setC_change_after(String c_change_after) {
		this.c_change_after = c_change_after;
	}

	public Change() {
		super();
	}

	@Override
	public String toString() {
		return "Change [c_c_regist_num=" + c_c_regist_num + ", c_change_date=" + c_change_date + ", c_change_project="
				+ c_change_project + ", c_change_before=" + c_change_before + ", c_change_after=" + c_change_after
				+ "]";
	}

}
