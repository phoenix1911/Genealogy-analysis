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

public class ShareholderDao {
	public static List<String> list = new ArrayList<String>();

	static Block<Document> printBlock = new Block<Document>() {
		public void apply(final Document document) {
			list.add(document.toJson());
		}
	};
	
	/**
	 * ʵ�ִ�MongoDB ���ݿ��л�ȡ�ɶ���Ϣ����������Ϣ���浽��Ӧ��ʵ����������
	 * @param regist_num ��ҵע���
	 * @return
	 */
	public List<Shareholder> shareholderinfo(String regist_num){
		List<Shareholder> data = new ArrayList<Shareholder>();
		Gson gson = new Gson();
		try {
			MongoCollection<Document> collection = MongoDBUtil.getConnection("t_shareholder_info");
			BasicDBObject query = new BasicDBObject("s_c_regist_num", regist_num);
			collection.find(query).forEach(printBlock);
			
			for (int i = 0; i < list.size(); i++) {
				System.out.println(list.get(i));
			}
			
			for (int i = 0; i < list.size(); i++) {
				list.set(i, "{" + list.get(i).substring(list.get(i).indexOf("\"s_c_regist_num\""), list.get(i).indexOf(", \"outputValue\""))
				+ "}");
				Shareholder shareholder = gson.fromJson(list.get(i), Shareholder.class);
				data.add(shareholder);
			}

		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
		list.clear();
		return data;
	}
	
	/**
	 * ��ȡ��ҵ�ɶ�����, ����������ϳ�Json��ʽ�ַ���
	 * @return
	 */
	public String getShareholderTypeJson(){
		String result = "[";	
		try {
			MongoCollection<Document> collection = MongoDBUtil.getConnection("getShareholderType");
			collection.find().forEach(printBlock);

			for (int i = 0; i < list.size(); i++) {
				list.set(i, "{" + list.get(i).substring(list.get(i).indexOf("\"s_t_TypeName\""), list.get(i).indexOf(", \"outputValue\""))
				+ "}");
				list.set(i, list.get(i).replace("s_t_TypeName", "name"));
				result = result + list.get(i) + ",";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		list.clear();
		return result;
	}
	
	/**
	 * ��ȡ��ҵ����ɶ���Ϣ, ����������ϳ�Json��ʽ�ַ���
	 * @param regist_num ��ҵע���
	 * @return
	 */
	public String getShareholderNameJson(String regist_num){
		String result = "";	
		try {
			MongoCollection<Document> collection = MongoDBUtil.getConnection("t_shareholder_info");
			BasicDBObject query = new BasicDBObject("s_c_regist_num", regist_num);
			collection.find(query).forEach(printBlock);

			for (int i = 0; i < list.size(); i++) {
				list.set(i, "{" + list.get(i).substring(list.get(i).indexOf("\"s_name\""), list.get(i).indexOf("\"s_type\"")-2)
				+ "}");
				list.set(i, list.get(i).replace("s_name", "name"));
				if (i == list.size() - 1) {
					result = result + list.get(i) + "]";
				}else {
					result = result + list.get(i) + ",";
				}
			}	
		} catch (Exception e) {
			e.printStackTrace();
		}
		list.clear();
		return result;
	}
	
	/**
	 * ��ȡ��ҵ�ɶ��������ֹɷݶ�, ����������ϳ�Json��ʽ�ַ���
	 * @param regist_num
	 * @return
	 */
	public String getShareholderTypeValueJson(String regist_num){
		List<EchartsModel> data = new ArrayList<EchartsModel>();
		Gson gson = new Gson();
		String result = "";
		String result1 = "";
		String result2 = "";
		String result3 = "";
		int maxvalue = 0;
		String maxtype = "";
		int naturalvalue = 0;
		int enterprisevalue = 0;
		try {
			MongoCollection<Document> collection = MongoDBUtil.getConnection("getShareholderTypeMaxValue");
			BasicDBObject query = new BasicDBObject("s_c_regist_num", regist_num);
			collection.find(query).forEach(printBlock);

			for (int i = 0; i < list.size(); i++) {
				list.set(i, "{" + list.get(i).substring(list.get(i).indexOf("\"s_money\""),
						list.get(i).indexOf("\"s_name\"") - 2) + "}");
				list.set(i, list.get(i).replace("s_money", "value"));
				list.set(i, list.get(i).replace("s_type", "type"));
				EchartsModel echartsModel = gson.fromJson(list.get(i), EchartsModel.class);
				data.add(echartsModel);
			}
			
			if (!data.isEmpty()) {
				for (int i = 0; i < list.size(); i++) {
					if (maxvalue < Integer.parseInt(data.get(i).getValue())) {
						maxvalue = Integer.parseInt(data.get(i).getValue());
						maxtype = data.get(i).getType();
					}
				}
				result1 = result1 + "{\"value\" : \"" + maxvalue + "\", \"name\" : \"��ɶ�\"}";

				for (int i = 0; i < list.size(); i++) {
					if (data.get(i).getType().equals("��Ȼ�˹ɶ�")) {
						naturalvalue = naturalvalue + Integer.parseInt(data.get(i).getValue());
					} else {
						enterprisevalue = enterprisevalue + Integer.parseInt(data.get(i).getValue());
					}
				}

				if (maxtype.equals("��Ȼ�˹ɶ�")) {
					naturalvalue = naturalvalue - maxvalue;
				} else {
					enterprisevalue = enterprisevalue - maxvalue;
				}

				result2 = result2 + "{\"value\" : \"" + naturalvalue + "\", \"name\" : \"��Ȼ�˹ɶ�\"}";
				result3 = result3 + "{\"value\" : \"" + enterprisevalue + "\", \"name\" : \"��ҵ�ɶ�\"}";

				if (maxvalue == 0 && naturalvalue == 0 && enterprisevalue == 0) {
					result = "";
				} else if (maxvalue != 0 && naturalvalue == 0 && enterprisevalue == 0) {
					result = result1;
				} else if (maxvalue == 0 && naturalvalue != 0 && enterprisevalue == 0) {
					result = result2;
				} else if (maxvalue == 0 && naturalvalue == 0 && enterprisevalue != 0) {
					result = result3;
				} else if (maxvalue != 0 && naturalvalue != 0 && enterprisevalue == 0) {
					result = result1 + ", " + result2;
				} else if (maxvalue != 0 && naturalvalue == 0 && enterprisevalue != 0) {
					result = result1 + ", " + result3;
				} else if (maxvalue == 0 && naturalvalue != 0 && enterprisevalue != 0) {
					result = result2 + ", " + result3;
				} else {
					result = result1 + ", " + result2 + ", " + result3;
				}
				result = "[" + result + "]";
			} else {
				result = "";
			}
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
		list.clear();
		return result;
	}
	
	/**
	 * ��ȡ��ҵ����ɶ����ֹɷݶ�, ����������ϳ�Json��ʽ�ַ���
	 * @param regist_num
	 * @return
	 */
	public String getShareholderIndividualValueJson(String regist_num){
		List<EchartsModel> data = new ArrayList<EchartsModel>();
		Gson gson = new Gson();
		String result = "";
		String result1 = "";
		String result2 = "";
		int maxvalue = 0;
		String maxname = "";
		try {
			MongoCollection<Document> collection = MongoDBUtil.getConnection("getShareholderTypeMaxValue");
			BasicDBObject query = new BasicDBObject("s_c_regist_num", regist_num);
			collection.find(query).forEach(printBlock);
			for (int i = 0; i < list.size(); i++) {
				list.set(i, "{" + list.get(i).substring(list.get(i).indexOf("\"s_money\""),
						list.get(i).indexOf("\"outputValue\"") - 2) + "}");
				list.set(i, list.get(i).replace("s_money", "value"));
				list.set(i, list.get(i).replace("s_type", "type"));
				list.set(i, list.get(i).replace("s_name", "name"));
				EchartsModel echartsModel = gson.fromJson(list.get(i), EchartsModel.class);
				data.add(echartsModel);
			}
			
			if (!data.isEmpty()) {
				for (int i = 0; i < list.size(); i++) {
					if (maxvalue < Integer.parseInt(data.get(i).getValue())) {
						maxvalue = Integer.parseInt(data.get(i).getValue());
						maxname = data.get(i).getName();
					}
				}
				result1 = result1 + "{\"value\" : \"" + maxvalue + "\", \"name\" : \"" + maxname + "\"}";

				for (int i = 0; i < list.size(); i++) {
					if (data.get(i).getType().equals("��Ȼ�˹ɶ�")) {
						if (Integer.parseInt(data.get(i).getValue()) != maxvalue) {
							String str = "{\"value\" : \"" + Integer.parseInt(data.get(i).getValue())
									+ "\", \"name\" : \"" + data.get(i).getName() + "\"}";
							if (i == list.size() - 1) {
								result2 = result2 + str;
							} else {
								result2 = result2 + str + ",";
							}
						}
					} else {
						if (Integer.parseInt(data.get(i).getValue()) != maxvalue) {
							String str = "{\"value\" : \"" + Integer.parseInt(data.get(i).getValue())
									+ "\", \"name\" : \"" + data.get(i).getName() + "\"}";
							if (i == list.size() - 1) {
								result2 = result2 + str;
							} else {
								result2 = result2 + str + ",";
							}
						}
					}
				}
				result = "[" + result1 + ", " + result2 + "]";
			} else {
				result = "";
			}
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
		list.clear();
		return result;
	}
	
	/**
	 * ��ȡ��ҵ���ɶ�
	 * @param regist_num
	 * @return
	 * @throws Exception 
	 */
	public String getMaxShareholder(String regist_num) throws Exception{
		CorpDao corpName = new CorpDao();
		List<EchartsModel> data = new ArrayList<EchartsModel>();
		Gson gson = new Gson();
		String result = "";
		String result1 = "";
		String result2 = "";
		int maxvalue = 0;
		String maxname = "";
		
		int x1 = 300, x2 = 350, y = 300;
		
		try {	
			MongoCollection<Document> collection = MongoDBUtil.getConnection("getShareholderTypeMaxValue");
			BasicDBObject query = new BasicDBObject("s_c_regist_num", regist_num);
			collection.find(query).forEach(printBlock);
			for (int i = 0; i < list.size(); i++) {
				list.set(i, "{" + list.get(i).substring(list.get(i).indexOf("\"s_money\""),
						list.get(i).indexOf("\"outputValue\"") - 2) + "}");
				list.set(i, list.get(i).replace("s_money", "value"));
				list.set(i, list.get(i).replace("s_type", "type"));
				list.set(i, list.get(i).replace("s_name", "name"));
				EchartsModel echartsModel = gson.fromJson(list.get(i), EchartsModel.class);
				data.add(echartsModel);
			}

			if (!data.isEmpty()) {
				for (int i = 0; i < list.size(); i++) {
					if (maxvalue < Integer.parseInt(data.get(i).getValue())) {
						maxvalue = Integer.parseInt(data.get(i).getValue());
						maxname = data.get(i).getName();
					}
				}
				result1 = "{\"name\" : \"" + maxname + "\", \"x\" : \"" + x1 + "\", \"y\" : \"" + y + "\"}";
				List<Corp> corps = corpName.corpinfo(regist_num);
				result2 = "{\"name\" : \"" + corps.get(0).getC_name() + "\", \"x\" : \"" + x2 + "\", \"y\" : \"" + y + "\"}";
				
				result = "[" + result1 + "," + result2 + "]";			
			} else {
				result = "";
			}	
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
		list.clear();
		return result;
	}
	
	/**
	 * ��ȡ��ҵ�ɶ�����(tree-Json��ʽ)
	 * @param regist_num
	 * @return
	 */
	public String shareholderName(String regist_num){
		String result = "";	
		try {
			MongoCollection<Document> collection = MongoDBUtil.getConnection("t_shareholder_info");
			BasicDBObject query = new BasicDBObject("s_c_regist_num", regist_num);
			collection.find(query).forEach(printBlock);
			
			for (int i = 0; i < list.size(); i++) {
				list.set(i, "{" + list.get(i).substring(list.get(i).indexOf("\"s_name\""), list.get(i).indexOf("\"s_type\"")-2)
				+ "}");
				list.set(i, list.get(i).replace("s_name", "name"));
				if (i == list.size() - 1) {
					result = result + list.get(i);
				}else {
					result = result + list.get(i) + ",";
				}
			}
			result = "{\"name\":\"�ɶ�\",\"children\":[" + result + "]},";
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
		list.clear();
		return result;
	}
}
