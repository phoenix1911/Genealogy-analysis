package com.dao;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.google.gson.Gson;
import com.bean.Investment;
import com.mongodb.BasicDBObject;
import com.mongodb.Block;
import com.mongodb.client.MongoCollection;
import com.util.MongoDBUtil;

public class InvestmentDao {
	public static List<String> list = new ArrayList<String>();

	static Block<Document> printBlock = new Block<Document>() {
		public void apply(final Document document) {
			list.add(document.toJson());
		}
	};
	
	/**
	 * 实现从MongoDB 数据库中获取所属企业的对外投资公司信息，并将其信息保存到相应的实例化对象中
	 * @param regist_num
	 * @return
	 */
	public List<Investment> investmentinfo(String regist_num){
		List<Investment> data = new ArrayList<Investment>();
		Gson gson = new Gson();
		try {
			MongoCollection<Document> collection = MongoDBUtil.getConnection("t_investment_info");
			BasicDBObject query = new BasicDBObject("i_c_regist_num", regist_num);
			collection.find(query).forEach(printBlock);
			
			for (int i = 0; i < list.size(); i++) {
				list.set(i, "{" + list.get(i).substring(list.get(i).indexOf("\"i_c_regist_num\""), list.get(i).indexOf("\"outputValue\"")-2)
				+ "}");
				Investment investment = gson.fromJson(list.get(i), Investment.class);
				data.add(investment);
			}

		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
		list.clear();
		return data;
	}
	
	/**
	 * 获取企业对外投资公司名称
	 * @param regist_num
	 * @return
	 */
	public String investmentfirm(String regist_num){
		String result = null;
		try {
			MongoCollection<Document> collection = MongoDBUtil.getConnection("t_investment_info");
			BasicDBObject query = new BasicDBObject("i_c_regist_num", regist_num);

			collection.find(query).forEach(printBlock);
			
			for (int i = 0; i < list.size(); i++) {
				list.set(i, "{" + list.get(i).substring(list.get(i).indexOf("\"i_firm\""), list.get(i).indexOf("\"i_legal_person\"")-2)
				+ "}");
				list.set(i, list.get(i).replace("i_firm", "name"));
			}
			
			if (list.isEmpty()) {
				result = "{\"name\":\"对外投资\"},";
			} else{
				result = "{\"name\":\"对外投资\",\"children\":" + "[";	
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
