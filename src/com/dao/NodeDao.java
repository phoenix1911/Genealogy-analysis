package com.dao;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.mongodb.BasicDBObject;
import com.mongodb.Block;
import com.mongodb.client.MongoCollection;
import com.util.MongoDBUtil;

public class NodeDao {
	public static List<String> list = new ArrayList<String>();

	static Block<Document> printBlock = new Block<Document>() {
		public void apply(final Document document) {
			list.add(document.toJson());
		}
	};
	
	/**
	 * 获取企业相关的疑似关系结点信息
	 * @param regist_num
	 * @return
	 */
	public String getNode(String regist_num){
		String result = null;
		try {
			MongoCollection<Document> collection = MongoDBUtil.getConnection("t_node_info");
			BasicDBObject query = new BasicDBObject("n_c_regist_num", regist_num);
			collection.find(query).forEach(printBlock);

			for (int i = 0; i < list.size(); i++) {
				list.set(i, "{" + list.get(i).substring(list.get(i).indexOf("\"n_node_name\"") , list.get(i).indexOf("\"outputValue\"")-2) + "}");
				list.set(i, list.get(i).replace("n_node_name", "name"));
				list.set(i, list.get(i).replace("n_type", "category"));
			}
			
			for (int i = 0; i < list.size(); i++) {
				String value = list.get(i).substring(list.get(i).indexOf("\"category\"")+14 , list.get(i).indexOf("}")-1);
				String str = list.get(i).substring(list.get(i).indexOf("\"category\"")+13 , list.get(i).indexOf("}"));
				String num = String.valueOf(Integer.parseInt(value));
				list.set(i, list.get(i).replace(str, num));
			}

			if (list.isEmpty()) {
				result = "";
			} else{
				result = "[";	
				for (int i = 0; i < list.size(); i++) {
					result = result + list.get(i) + ",";
				}
				result = result.substring(0, result.length() - 1) + "]";						
			}
			System.out.println(result);
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
		list.clear();
		return result;	
	}
	
	/**
	 * 获取企业的相关疑似关系信息
	 * @param regist_num
	 * @return
	 */
	public String getNodeRela(String regist_num){
		String result = null;
		try {
			MongoCollection<Document> collection = MongoDBUtil.getConnection("t_node_rela_info");
			BasicDBObject query = new BasicDBObject("n_c_regist_num", "320000000043078");
			collection.find(query).forEach(printBlock);
			
			for (int i = 0; i < list.size(); i++) {
				list.set(i, "{" + list.get(i).substring(list.get(i).indexOf("\"n_master\"") , list.get(i).indexOf("\"outputValue\"")-2) + "}");
				list.set(i, list.get(i).replace("n_master", "source"));
				list.set(i, list.get(i).replace("n_slave", "target"));
				list.set(i, list.get(i).replace("n_associated", "value"));
			}
			
			for (int i = 0; i < list.size(); i++) {
				String value = list.get(i).substring(list.get(i).indexOf("\"source\"")+12 , list.get(i).indexOf("\"target\"")-3);
				String str = list.get(i).substring(list.get(i).indexOf("\"source\"")+11 , list.get(i).indexOf("\"target\"")-2);
				String num = String.valueOf(Integer.parseInt(value) - 1);
				list.set(i, list.get(i).replace(str, num));
				
				value = list.get(i).substring(list.get(i).indexOf("\"target\"")+12 , list.get(i).indexOf("\"value\"")-3);
				str = list.get(i).substring(list.get(i).indexOf("\"target\"")+11 , list.get(i).indexOf("\"value\"")-2);
				num = String.valueOf(Integer.parseInt(value) - 1);
				list.set(i, list.get(i).replace(str, num));
			}
		
			if (list.isEmpty()) {
				result = "";
			} else{
				result = "[";	
				for (int i = 0; i < list.size(); i++) {
					result = result + list.get(i) + ",";
				}
				result = result.substring(0, result.length() - 1) + "]";					
			}
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
		list.clear();
		return result;
	}
}
