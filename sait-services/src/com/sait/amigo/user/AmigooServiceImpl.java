/**
 * 
 */
package com.sait.amigo.user;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.bson.Document;
import com.fasterxml.jackson.databind.JsonNode;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

/**
 * @author home
 *
 */
@Path("/amigoo-services")
public class AmigooServiceImpl {

	/**
	 * 
	 */
	public AmigooServiceImpl() {
		// TODO Auto-generated constructor stub
	}

	@POST
	@Path("setMyCarInfo")
	@Consumes(MediaType.APPLICATION_JSON)
	// @Produces(MediaType.APPLICATION_JSON)
	public void setMyCarInfo(JsonNode inputCarData) {
		/*
		 * MyCar myCar = new MyCar(); return myCar;
		 */
		// return Object;
		AmigooDBImpl amigoDbImpl = new AmigooDBImpl();
		MongoClient mongoClient = amigoDbImpl.getMongoClient();
		MongoDatabase database = mongoClient.getDatabase("amigoo");
		MongoCollection<Document> myCarDetailColl = database.getCollection("mycar-details");
		Document myCarDoc = Document.parse(inputCarData.toString());
		myCarDetailColl.insertOne(myCarDoc);

	}

	@POST
	@Path("getMyCarInfo")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Document> getMyCarInfo(JsonNode inputCarData) {
		String userId = inputCarData.get("UserID").asText();
		AmigooDBImpl amigoDbImpl = new AmigooDBImpl();
		MongoClient mongoClient = amigoDbImpl.getMongoClient();
		MongoDatabase database = mongoClient.getDatabase("amigoo");
		MongoCollection<Document> myCarDetailColl = database.getCollection("mycar-details");

		// Document myDoc = myCarDetailColl.find(Filters.eq("UserID",
		// "251615")).first();
		FindIterable<Document> results = myCarDetailColl.find(Filters.eq("UserID", userId));
		ArrayList<Document> myCarList = new ArrayList<Document>();
		for (Document doc : results) {
			System.out.println(doc);
			myCarList.add(doc);
		}

		return myCarList;

	}

	@POST
	@Path("setMyCarBrkDwnInfo")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public void setMyCarBrkDwnInfo(JsonNode inputCarBrkDown) {
		AmigooDBImpl amigoDbImpl = new AmigooDBImpl();
		MongoClient mongoClient = amigoDbImpl.getMongoClient();
		MongoDatabase database = mongoClient.getDatabase("amigoo");
		MongoCollection<Document> myCarBrkDownColl = database.getCollection("mycar-breakdown");
		Document myCarBrkDownDoc = Document.parse(inputCarBrkDown.toString());
		myCarBrkDownColl.insertOne(myCarBrkDownDoc);
	}

	@POST
	@Path("getMyCarBrkDwnInfo")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public  ArrayList<Document> getMyCarBrkDwnInfo(JsonNode inputCarData) {
		String userId = inputCarData.get("UserID").asText();
		AmigooDBImpl amigoDbImpl = new AmigooDBImpl();
		MongoClient mongoClient = amigoDbImpl.getMongoClient();
		MongoDatabase database = mongoClient.getDatabase("amigoo");
		MongoCollection<Document> myCarDetailColl = database.getCollection("mycar-breakdown");

		// Document myDoc = myCarDetailColl.find(Filters.eq("UserID",
		// "251615")).first();
		FindIterable<Document> results = myCarDetailColl.find(Filters.eq("UserID", userId));
		ArrayList<Document> myCarBreakDownList = new ArrayList<Document>();
		for (Document doc : results) {
			System.out.println(doc);
			myCarBreakDownList.add(doc);
		}

		return myCarBreakDownList;
	}
}
