/**
 * 
 */
package com.sait.amigo.user;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.bson.Document;
import com.fasterxml.jackson.databind.JsonNode;
import com.mongodb.MongoClient;
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
	public String getMyCarInfo(JsonNode inputCarData) {

		AmigooDBImpl amigoDbImpl = new AmigooDBImpl();
		MongoClient mongoClient = amigoDbImpl.getMongoClient();
		MongoDatabase database = mongoClient.getDatabase("amigoo");
		MongoCollection<Document> myCarDetailColl = database.getCollection("mycar-details");

		Document myDoc = myCarDetailColl.find(Filters.eq("Registration-Number", "AP10R592")).first();
		return myDoc.toJson();

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
	public String getMyCarBrkDwnInfo(JsonNode inputCarData) {
		AmigooDBImpl amigoDbImpl = new AmigooDBImpl();
		MongoClient mongoClient = amigoDbImpl.getMongoClient();
		MongoDatabase database = mongoClient.getDatabase("amigoo");
		MongoCollection<Document> myCarBrkDownColl = database.getCollection("mycar-breakdown");
		
		Document myDoc = myCarBrkDownColl.find(Filters.eq("name","sairamdev")).first();
		return myDoc.toJson();
	}
}
