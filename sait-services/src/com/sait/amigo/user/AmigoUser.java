package com.sait.amigo.user;
import com.mongodb.*;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import java.util.Arrays;

import org.bson.Document;


public class AmigoUser {

	public AmigoUser() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		
		
		// TODO Auto-generated method stub
		MongoClientURI uri = new MongoClientURI("mongodb://admin:Babais4u&me@cluster0-shard-00-00-aviqx.mongodb.net:27017,cluster0-shard-00-01-aviqx.mongodb.net:27017,cluster0-shard-00-02-aviqx.mongodb.net:27017/test?ssl=true&replicaSet=Cluster0-shard-0&authSource=admin");

				MongoClient mongoClient = new MongoClient(uri);
				 MongoDatabase database = mongoClient.getDatabase("mydb");
				 MongoCollection<Document> collection = database.getCollection("test");
				 
				 Document doc = new Document("name", "MongoDB")
			                .append("type", "database")
			                .append("count", 1)
			                .append("versions", Arrays.asList("v3.2", "v3.0", "v2.6"))
			                .append("info", new Document("x", 203).append("y", 102));
				 collection.insertOne(doc);

	}
	
	

}
