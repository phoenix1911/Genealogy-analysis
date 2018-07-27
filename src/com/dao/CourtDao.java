package com.dao;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.mongodb.BasicDBObject;
import com.mongodb.Block;
import com.mongodb.client.MongoCollection;
import com.util.MongoDBUtil;

public class CourtDao {
	public static List<String> list = new ArrayList<String>();

	static Block<Document> printBlock = new Block<Document>() {
		public void apply(final Document document) {
			list.add(document.toJson());
		}
	};
	
	/**
	 * 获取企业相关的法院公告
	 * @param regist_num
	 * @return
	 */
	public String courtName(String regist_num){
		String result = null;
		try {
			MongoCollection<Document> collection = MongoDBUtil.getConnection("t_court_info");
			BasicDBObject query = new BasicDBObject("c_c_regist_num", regist_num);

			collection.find(query).forEach(printBlock);
			
			for (int i = 0; i < list.size(); i++) {
				list.set(i, "{" + list.get(i).substring(list.get(i).indexOf("\"c_parties_concerned\"") , list.get(i).indexOf("\"outputValue\"")-2) + "}");
				list.set(i, list.get(i).replace("c_parties_concerned", "name"));
			}
			
			for (int i = 0; i < list.size(); i++) {
		        if (list.get(i).indexOf("-") != -1) {
					list.remove(i);
				}	
			}
		
			if (list.isEmpty()) {
				result = "{\"name\":\"法院公告\"},";
			} else{
				result = "{\"name\":\"法院公告\",\"children\":" + "[";	
				int i = 0;
				while (i < list.size()) {
					result = result + list.get(i) + ",";
					i++;
				}
				result = result.substring(0, result.length() - 1) + "]},";						
			}
			System.out.println(result);
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
		list.clear();
		return result;
	}
}
