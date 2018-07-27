package com.util;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class MongoDBUtil {
	public static MongoCollection<Document> getConnection(String db_collection){
		MongoClient mongoClient = new MongoClient("localhost", 27017);
		MongoDatabase database = mongoClient.getDatabase("db_genealogy");

		MongoCollection<Document> collection = database.getCollection(db_collection);
		
		return collection;
    }
}
