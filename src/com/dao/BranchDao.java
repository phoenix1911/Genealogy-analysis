package com.dao;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.google.gson.Gson;
import com.bean.Branch;
import com.mongodb.BasicDBObject;
import com.mongodb.Block;
import com.mongodb.client.MongoCollection;
import com.util.MongoDBUtil;

public class BranchDao {
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
	public List<Branch> branchinfo(String regist_num){
		List<Branch> data = new ArrayList<Branch>();
		Gson gson = new Gson();
		try {
			MongoCollection<Document> collection = MongoDBUtil.getConnection("t_branch_info");
			BasicDBObject query = new BasicDBObject("b_c_regist_num", regist_num);
			collection.find(query).forEach(printBlock);		
			for (int i = 0; i < list.size(); i++) {
				list.set(i, "{" + list.get(i).substring(list.get(i).indexOf("\"b_c_regist_num\""), list.get(i).indexOf(", \"outputValue\""))
				+ "}");
				Branch branch = gson.fromJson(list.get(i), Branch.class);
				data.add(branch);
			}
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
		list.clear();
		return data;
	}
}
