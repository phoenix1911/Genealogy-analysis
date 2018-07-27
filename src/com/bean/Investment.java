package com.bean;

public class Investment {

	private String i_c_regist_num;
	private String i_firm;
	private String i_legal_person;
	private String i_regist_capital;
	private String i_capital_proportion;
	private String i_create_date;
	private String i_status;

	public String getI_c_regist_num() {
		return i_c_regist_num;
	}

	public void setI_c_regist_num(String i_c_regist_num) {
		this.i_c_regist_num = i_c_regist_num;
	}

	public String getI_firm() {
		return i_firm;
	}

	public void setI_firm(String i_firm) {
		this.i_firm = i_firm;
	}

	public String getI_legal_person() {
		return i_legal_person;
	}

	public void setI_legal_person(String i_legal_person) {
		this.i_legal_person = i_legal_person;
	}

	public String getI_regist_capital() {
		return i_regist_capital;
	}

	public void setI_regist_capital(String i_regist_capital) {
		this.i_regist_capital = i_regist_capital;
	}

	public String getI_capital_proportion() {
		return i_capital_proportion;
	}

	public void setI_capital_proportion(String i_capital_proportion) {
		this.i_capital_proportion = i_capital_proportion;
	}

	public String getI_create_date() {
		return i_create_date;
	}

	public void setI_create_date(String i_create_date) {
		this.i_create_date = i_create_date;
	}

	public String getI_status() {
		return i_status;
	}

	public void setI_status(String i_status) {
		this.i_status = i_status;
	}

	public Investment() {
		super();
	}

	@Override
	public String toString() {
		return "Investment [i_c_regist_num=" + i_c_regist_num + ", i_firm=" + i_firm + ", i_legal_person="
				+ i_legal_person + ", i_regist_capital=" + i_regist_capital + ", i_capital_proportion="
				+ i_capital_proportion + ", i_create_date=" + i_create_date + ", i_status=" + i_status + "]";
	}

}
