package com.dao;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.google.gson.Gson;
import com.bean.Change;
import com.mongodb.BasicDBObject;
import com.mongodb.Block;
import com.mongodb.client.MongoCollection;
import com.util.MongoDBUtil;

public class ChangeDao {
	public static List<String> list = new ArrayList<String>();

	static Block<Document> printBlock = new Block<Document>() {
		public void apply(final Document document) {
			list.add(document.toJson());
		}
	};
	
	/**
	 * 实现从MongoDB 数据库中获取所属企业的分支机构的基本信息，并将其信息保存到相应的实例化对象中
	 * @param regist_num
	 * @return
	 */
	public List<Change> changeinfo(String regist_num){
		List<Change> data = new ArrayList<Change>();
		Gson gson = new Gson();
		try {
			MongoCollection<Document> collection = MongoDBUtil.getConnection("t_change_info");
			BasicDBObject query = new BasicDBObject("c_c_regist_num", regist_num);
			collection.find(query).forEach(printBlock);		
			for (int i = 0; i < list.size(); i++) {
				list.set(i, "{" + list.get(i).substring(list.get(i).indexOf("\"c_c_regist_num\""), list.get(i).indexOf(", \"outputValue\""))
				+ "}");
				Change change = gson.fromJson(list.get(i), Change.class);
				data.add(change);
			}
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
		list.clear();
		return data;
	}
	
	/**
	 * 获取企业历史股东信息
	 * @return
	 */
	public String getHistoryShareholder(String regist_num){
		List<String> data = new ArrayList<String>();
		List<String> strarr = new ArrayList<String>();
		List<String> historyname = new ArrayList<String>();
		String result = null;
		
		try {
			MongoCollection<Document> collection = MongoDBUtil.getConnection("t_change_info");
			BasicDBObject query = new BasicDBObject("c_c_regist_num", regist_num);

			collection.find(query).forEach(printBlock);
			
			for (int i = 0; i < list.size(); i++) {
		        if(list.get(i).indexOf("股东变更") != -1 )
		        {
		        	data.add(list.get(i));
		        }	
			}
			
			for (int i = 0; i < data.size(); i++) {
				data.set(i, data.get(i).substring(data.get(i).indexOf("\"c_change_before\"")+20 , data.get(i).indexOf("\"c_change_after\"")-2));
			}
			
			for (int i = 0; i < data.size(); i++) {
				for (int j = 0; j < data.get(i).split("、").length; j++) {
					strarr.add(data.get(i).split("、")[j]);			
				}
			}
			
			for (int i = 0; i < strarr.size(); i++) {
		        if(strarr.get(i).indexOf("【退出】") != -1 )
		        {
		        	historyname.add(strarr.get(i).replace("【退出】", ""));
		        }
			}
		
			if (historyname.isEmpty()) {
				result = "{\"name\":\"历史股东\"},";
			} else{
				result = "{\"name\":\"历史股东\",\"children\":" + "[";	
				int i = 0;
				while (i < historyname.size()) {
					result = result + "{\"name\" : \"" + historyname.get(i) + "},";
					i++;
				}
				result = result.substring(0, result.length() - 1) + "]},";						
			}	
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
		list.clear();
		return result;
	}
	
	/**
	 * 获取企业历史法定代表人信息
	 * @param regist_num
	 * @return
	 */
	public String getHistoryLegal(String regist_num){
		List<String> data = new ArrayList<String>();
		List<String> strarr = new ArrayList<String>();
		List<String> historyname = new ArrayList<String>();
		String result = null;
		try {
			MongoCollection<Document> collection = MongoDBUtil.getConnection("t_change_info");
			BasicDBObject query = new BasicDBObject("c_c_regist_num", regist_num);

			collection.find(query).forEach(printBlock);
			
			for (int i = 0; i < list.size(); i++) {
		        if(list.get(i).indexOf("法定代表人变更 ") != -1 )
		        {
		        	data.add(list.get(i));
		        }	
			}
			
			for (int i = 0; i < data.size(); i++) {
				data.set(i, data.get(i).substring(data.get(i).indexOf("\"c_change_before\"")+21 , data.get(i).indexOf("\"c_change_after\"")-3));
			}
			
			for (int i = 0; i < data.size(); i++) {
				for (int j = 0; j < data.get(i).split("、").length; j++) {
					strarr.add(data.get(i).split("、")[j]);			
				}
			}
			
			for (int i = 0; i < strarr.size(); i++) {
		        if(strarr.get(i).indexOf("【退出】") != -1 )
		        {
		        	historyname.add(strarr.get(i).replace("【退出】", ""));
		        }
			}
		
			if (historyname.isEmpty()) {
				result = "{\"name\":\"历史法人\"},";
			} else{
				result = "{\"name\":\"历史法人\",\"children\":" + "[";	
				int i = 0;
				while (i < historyname.size()) {
					result = result + "\"" + historyname.get(i) + "\"" + ",";
					i++;
				}
				result = result.substring(0, result.length() - 1) + "]},";						
			}
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
		list.clear();
		return result;
	}
}
