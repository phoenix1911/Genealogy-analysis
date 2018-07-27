package com.dao;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.google.gson.Gson;
import com.bean.Corp;
import com.bean.EchartsModel;
import com.bean.Shareholder;
import com.mongodb.BasicDBObject;
import com.mongodb.Block;
import com.mongodb.client.MongoCollection;
import com.util.MongoDBUtil;
import com.util.URLtoUTF8;

public class Test1 {
	
	public static List<String> list = new ArrayList<String>();

	static Block<Document> printBlock = new Block<Document>() {
		public void apply(final Document document) {
			list.add(document.toJson());
		}
	};
	
	public static void main(String args[]) {
		
		ShareholderDao sDao = new ShareholderDao();
		
		//String corpName = "江苏星网软件有限公司";
		String regist_num = "320000000043078";
		String result = null;
		try {
			MongoCollection<Document> collection = MongoDBUtil.getConnection("t_node_info");
			BasicDBObject query = new BasicDBObject("n_c_regist_num", "320000000043078");
			collection.find(query).forEach(printBlock);

			for (int i = 0; i < list.size(); i++) {
				list.set(i, "{" + list.get(i).substring(list.get(i).indexOf("\"n_node_name\"") , list.get(i).indexOf("\"outputValue\"")-2) + "}");
				list.set(i, list.get(i).replace("n_node_name", "name"));
				list.set(i, list.get(i).replace("n_type", "category"));
			}
			
			for (int i = 0; i < list.size(); i++) {
				String value = list.get(i).substring(list.get(i).indexOf("\"category\"")+12 , list.get(i).indexOf("}")-1);
				String str = list.get(i).substring(list.get(i).indexOf("\"category\"")+11 , list.get(i).indexOf("}"));
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
//		System.out.println(result);
		
		
	}
	
	
	
	
//	public static void main(String args[]) {
//		String result = null;
//		try {
//			List<String> data = Test1.get();
//			
//			
//			
//			for (int i = 0; i < data.size(); i++) {
//				int index = 0;
//		        if( ( index = data.get(i).indexOf("股东变更") ) != -1 )
//		        {
//		        	System.out.println(data.get(i));
//		        }
//			}
//			
////			for (int i = 0; i < list.size(); i++) {
////				list.set(i, "{" + list.get(i).substring(list.get(i).indexOf("\"p_staff_name\""), list.get(i).indexOf("\"p_position\"")-2)
////				+ "}");
////				list.set(i, list.get(i).replace("p_staff_name", "name"));
////			}
////			
////			if (list.isEmpty()) {
////				result = "{\"name\":\"高管\"},";
////			} else{
////				result = "{\"name\":\"高管\",\"children\":" + "[";	
////				int i = 0;
////				while (i < list.size()) {
////					result = result + list.get(i) + ",";
////					i++;
////				}
////				result = result.substring(0, result.length() - 1) + "]},";						
////			}
////			System.out.println(result);
//		} catch (Exception e) {
//			System.err.println(e.getClass().getName() + ": " + e.getMessage());
//		}
//	}
//	
//	public static List<String> get(){
//		//String result = null;
//		try {
//			MongoCollection<Document> collection = MongoDBUtil.getConnection("t_change_info");
//			BasicDBObject query = new BasicDBObject("c_c_regist_num", "320000000043078");
//
//			collection.find(query).forEach(printBlock);
//			
////			for (int i = 0; i < list.size(); i++) {
////				System.out.println(list.get(i));
////				
////			}
//		} catch (Exception e) {
//			System.err.println(e.getClass().getName() + ": " + e.getMessage());
//		}
//		return list;
//	}
}
