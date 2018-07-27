package com.bean;

import java.util.Date;

public class Court {
	private int c_Id;
	private String c_c_regist_num;
	private String c_notice_people;
	private String c_parties_concerned;
	private String c_announcement_type;
	private Date c_release_date;

	public int getC_Id() {
		return c_Id;
	}

	public void setC_Id(int c_Id) {
		this.c_Id = c_Id;
	}

	public String getC_c_regist_num() {
		return c_c_regist_num;
	}

	public void setC_c_regist_num(String c_c_regist_num) {
		this.c_c_regist_num = c_c_regist_num;
	}

	public String getC_notice_people() {
		return c_notice_people;
	}

	public void setC_notice_people(String c_notice_people) {
		this.c_notice_people = c_notice_people;
	}

	public String getC_parties_concerned() {
		return c_parties_concerned;
	}

	public void setC_parties_concerned(String c_parties_concerned) {
		this.c_parties_concerned = c_parties_concerned;
	}

	public String getC_announcement_type() {
		return c_announcement_type;
	}

	public void setC_announcement_type(String c_announcement_type) {
		this.c_announcement_type = c_announcement_type;
	}

	public Date getC_release_date() {
		return c_release_date;
	}

	public void setC_release_date(Date c_release_date) {
		this.c_release_date = c_release_date;
	}

	public Court(int c_Id, String c_c_regist_num, String c_notice_people, String c_parties_concerned,
			String c_announcement_type, Date c_release_date) {
		super();
		this.c_Id = c_Id;
		this.c_c_regist_num = c_c_regist_num;
		this.c_notice_people = c_notice_people;
		this.c_parties_concerned = c_parties_concerned;
		this.c_announcement_type = c_announcement_type;
		this.c_release_date = c_release_date;
	}

}
