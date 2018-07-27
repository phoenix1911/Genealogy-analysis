package com.dao;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.mongodb.BasicDBObject;
import com.mongodb.Block;
import com.mongodb.client.MongoCollection;
import com.util.MongoDBUtil;

public class RefereeDao {
	public static List<String> list = new ArrayList<String>();

	static Block<Document> printBlock = new Block<Document>() {
		public void apply(final Document document) {
			list.add(document.toJson());
		}
	};
	
	/**
	 * 获取企业相关的裁判文书
	 * @param regist_num
	 * @return
	 */
	public String refereeName(String regist_num){
		String result = null;
		try {
			MongoCollection<Document> collection = MongoDBUtil.getConnection("t_referee_info");
			BasicDBObject query = new BasicDBObject("r_c_regist_num", regist_num);

			collection.find(query).forEach(printBlock);
			
			for (int i = 0; i < list.size(); i++) {
				list.set(i, "{" + list.get(i).substring(list.get(i).indexOf("\"r_firm_name\"") , list.get(i).indexOf("\"outputValue\"")-2) + "}");
				list.set(i, list.get(i).replace("r_firm_name", "name"));
			}

			if (list.isEmpty()) {
				result = "{\"name\":\"裁判文书\"},";
			} else{
				result = "{\"name\":\"裁判文书\",\"children\":" + "[";	
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
