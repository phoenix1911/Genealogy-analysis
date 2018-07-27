package com.dao;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.google.gson.Gson;
import com.bean.Personnel;
import com.mongodb.BasicDBObject;
import com.mongodb.Block;
import com.mongodb.client.MongoCollection;
import com.util.MongoDBUtil;

public class PersonnelDao {
	public static List<String> list = new ArrayList<String>();

	static Block<Document> printBlock = new Block<Document>() {
		public void apply(final Document document) {
			list.add(document.toJson());
		}
	};
	
	/**
	 * 实现从MongoDB 数据库中获取企业主要人员信息，并将其信息保存到相应的实例化对象中
	 * @param regist_num
	 * @return
	 */
	public List<Personnel> personnelinfo(String regist_num){
		List<Personnel> data = new ArrayList<Personnel>();
		Gson gson = new Gson();
		try {
			MongoCollection<Document> collection = MongoDBUtil.getConnection("t_personnel_info");
			BasicDBObject query = new BasicDBObject("p_c_regist_num", regist_num);
			collection.find(query).forEach(printBlock);
			
			for (int i = 0; i < list.size(); i++) {
				list.set(i, "{" + list.get(i).substring(list.get(i).indexOf("\"p_c_regist_num\""), list.get(i).indexOf(", \"outputValue\""))
				+ "}");
				Personnel personnel = gson.fromJson(list.get(i), Personnel.class);
				data.add(personnel);
			}
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
		list.clear();
		return data;
	}
	
	/**
	 * 获取企业高管名称
	 * @param regist_num
	 * @return
	 */
	public String personnelName(String regist_num){
		String result = null;
		try {
			MongoCollection<Document> collection = MongoDBUtil.getConnection("t_personnel_info");
			BasicDBObject query = new BasicDBObject("p_c_regist_num", regist_num);

			collection.find(query).forEach(printBlock);
			
			for (int i = 0; i < list.size(); i++) {
				list.set(i, "{" + list.get(i).substring(list.get(i).indexOf("\"p_staff_name\""), list.get(i).indexOf("\"p_position\"")-2)
				+ "}");
				list.set(i, list.get(i).replace("p_staff_name", "name"));
			}
			
			if (list.isEmpty()) {
				result = "{\"name\":\"高管\"},";
			} else{
				result = "{\"name\":\"高管\",\"children\":" + "[";	
				int i = 0;
				while (i < list.size()) {
					result = result + list.get(i) + ",";
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
