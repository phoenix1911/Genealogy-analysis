package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.google.gson.Gson;
import com.bean.Corp;
import com.mongodb.BasicDBObject;
import com.mongodb.Block;
import com.mongodb.client.MongoCollection;
import com.util.MongoDBUtil;

import com.util.URLtoUTF8;

import com.util.DBConnUtil;

public class CorpDao {
	public static List<String> list = new ArrayList<String>();

	static Block<Document> printBlock = new Block<Document>() {
		public void apply(final Document document) {
			list.add(document.toJson());
		}
	};
	
	private Connection conn;

	public CorpDao() throws Exception {
		conn = DBConnUtil.getConnection();
	}
	
	/**
	 * 获取企业注册号
	 * @param corpName
	 * @return
	 */
	public String getCorpRegistNum(String corpName) throws SQLException{
		
		String result = null;
		
		String sql = "select c_regist_num from t_corp where c_name = '" + corpName + "'";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
			result = rs.getString("c_regist_num");
		}
		return result;
	}

	/**
	 * 实现从MongoDB 数据库中获取所查询企业的基本信息，并将其信息保存到相应的实例化对象中
	 * @param regist_num
	 * @return
	 */
	public List<Corp> corpinfo(String regist_num) {
		List<Corp> data = new ArrayList<Corp>();
		Gson gson = new Gson();
		
		String url = "http://map.baidu.com/?newmap=1&ie=utf-8&s=s%26wd%3D";
		String url_suffix = null;

		MongoCollection<Document> collection = MongoDBUtil.getConnection("t_corp_info");
		BasicDBObject query = new BasicDBObject("c_regist_num", regist_num);

		collection.find(query).forEach(printBlock);

		for (int i = 0; i < list.size(); i++) {
			list.set(i, "{" + list.get(i).substring(list.get(i).indexOf("\"c_name\""),
					list.get(i).indexOf("\"outputValue\"") - 2) + "}");
			
			Corp corp = gson.fromJson(list.get(i), Corp.class);
			url_suffix = URLtoUTF8.toUtf8String(corp.getC_name());
			corp.setC_map(url + url_suffix);
			data.add(corp);
		}
		list.clear();
		return data;
	}
	
	/**
	 * 获取指定的企业名称
	 * @param regist_num
	 * @return
	 */
	public String getCorpName(String regist_num){
		List<Corp> data = new ArrayList<Corp>();
		Gson gson = new Gson();

		MongoCollection<Document> collection = MongoDBUtil.getConnection("t_corp_info");
		BasicDBObject query = new BasicDBObject("c_regist_num", regist_num);

		collection.find(query).forEach(printBlock);

		for (int i = 0; i < list.size(); i++) {
			list.set(i, "{" + list.get(i).substring(list.get(i).indexOf("\"c_name\""),
					list.get(i).indexOf("\"outputValue\"") - 2) + "}");
			Corp corp = gson.fromJson(list.get(i), Corp.class);
			data.add(corp);
		}
		list.clear();
		return data.get(0).getC_name();
	}
	
	/**
	 * 获取全部的企业名称
	 * @param regist_num
	 * @return
	 */
	public List<String> getAllCorpName(){
		List<String> data = new ArrayList<String>();
		Gson gson = new Gson();

		MongoCollection<Document> collection = MongoDBUtil.getConnection("t_corp_info");

		collection.find().forEach(printBlock);

		for (int i = 0; i < list.size(); i++) {
			list.set(i, "{" + list.get(i).substring(list.get(i).indexOf("\"c_name\""),
					list.get(i).indexOf("\"outputValue\"") - 2) + "}");
			Corp corp = gson.fromJson(list.get(i), Corp.class);
			data.add(corp.getC_name());
		}
		list.clear();
		return data;
	}
}
