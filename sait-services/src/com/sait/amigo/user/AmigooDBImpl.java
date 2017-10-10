package com.sait.amigo.user;

import java.util.Arrays;

import org.bson.Document;
import org.omg.PortableInterceptor.AdapterManagerIdHelper;

import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class AmigooDBImpl implements AmigooDb {

	private static final String mongoDbConnectionURI = "mongodb://amigoo-dba:Babais4u&me@cluster0-shard-00-00-p7y9v.mongodb.net:27017,cluster0-shard-00-01-p7y9v.mongodb.net:27017,cluster0-shard-00-02-p7y9v.mongodb.net:27017/test?ssl=true&replicaSet=Cluster0-shard-0&authSource=admin";

	// private String mongoDbName=null;
	public AmigooDBImpl() {
		// TODO Auto-generated constructor stub
		// this.mongoDbName=mongoDnName;
	}

	@Override
	public MongoClient getMongoClient() {
		// TODO Auto-generated method stub
		MongoClientURI clientURI = new MongoClientURI(mongoDbConnectionURI);
		MongoClient mongoClient = new MongoClient(clientURI);

		return mongoClient;
	}

	@Override
	public MongoDatabase getMongoDataBase(String mongoDbName, MongoClient mongoClient) {
		// TODO Auto-generated method stub
		// MongoClient mongoClient=this.getMongoClient();
		MongoDatabase database = mongoClient.getDatabase(mongoDbName);
		return database;
	}

	@Override
	public MongoCollection<Document> getMongoDBCollection(String mongoDbCollection, MongoDatabase database) {
		// TODO Auto-generated method stub

		MongoCollection<Document> collection = database.getCollection(mongoDbCollection);
		return collection;
	}

	/*
	 * @Override private MongoClientURI getMongoClientURI() { // TODO
	 * Auto-generated method stub
	 * 
	 * MongoClientURI clientURI = new MongoClientURI(mongoDbConnectionURI);
	 * 
	 * return clientURI; }
	 */
	/*public static void main(String args[]) {
		try {

			AmigooDBImpl amdimpl = new AmigooDBImpl();

			MongoDatabase database = amdimpl.getMongoDataBase("test3", amdimpl.getMongoClient());
			MongoCollection collection = database.getCollection("test");

			Document doc = new Document("name", "MongoDB").append("type", "database").append("count", 1)
					.append("versions", Arrays.asList("v3.2", "v3.0", "v2.6"))
					.append("info", new Document("x", 203).append("y", 102));
			collection.insertOne(doc);

		} catch (Exception e) {
			e.printStackTrace();

			// TODO: handle exception
		}

	}*/
}
