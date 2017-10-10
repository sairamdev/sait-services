package com.sait.amigo.user;


//import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
//import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public interface AmigooDb {
	//public MongoClientURI getMongoClientURI();
	public MongoClient getMongoClient();
	public MongoDatabase getMongoDataBase(String mongoDbName,MongoClient mongoClient);
	public MongoCollection getMongoDBCollection(String mongoDbCollection,MongoDatabase database);
	

}
